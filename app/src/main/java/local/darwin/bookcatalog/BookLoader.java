package local.darwin.bookcatalog;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private static final String LOG_TAG = BookLoader.class.getSimpleName();
    private String url;

    BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "Starting load...");
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        Log.d(LOG_TAG, "Loading in background...");
        if (this.url == null) {
            return null;
        }
        return Utils.fetchBookData(this.url);
    }
}
