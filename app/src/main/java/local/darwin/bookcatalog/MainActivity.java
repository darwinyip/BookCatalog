package local.darwin.bookcatalog;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String BOOK_URL = "https://www.googleapis.com/books/v1/volumes?q=%s&maxResults=15";
    public static List<Book> books = new ArrayList<>();
    private BookAdapter adapter;
    private LoaderManager loaderManager;
    private RecyclerView recyclerView;
    private TextView empty;
    private ProgressBar progressBar;
    private String query = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Creating activity...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleIntent(getIntent());

        recyclerView = findViewById(R.id.list);
        empty = findViewById(R.id.empty);
        progressBar = findViewById(R.id.progress);

        adapter = new BookAdapter(books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Log.d(LOG_TAG, "Getting Loader Manager");
        loaderManager = getLoaderManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(LOG_TAG, "Creating options menu...");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d(LOG_TAG, "Handling intent...");
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);

            Log.d(LOG_TAG, "Init Loader");
            loaderManager.destroyLoader(1);
            loaderManager.initLoader(1, null, this);

            recyclerView.setVisibility(View.GONE);
            empty.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        Log.d(LOG_TAG, "Creating loader...");
        return new BookLoader(this, String.format(BOOK_URL, query));
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        Log.d(LOG_TAG, "Finishing loader...");
        if (books != null) {
            adapter.addList(books);
            adapter.notifyDataSetChanged();

            recyclerView.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }
}
