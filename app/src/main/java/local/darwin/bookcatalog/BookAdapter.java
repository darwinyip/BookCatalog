package local.darwin.bookcatalog;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    private static final String LOG_TAG = BookAdapter.class.getSimpleName();
    private List<Book> books;

    BookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "Creating view holder...");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new BookHolder(v);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        Log.d(LOG_TAG, "Binding view holder...");
        holder.setBook(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setList(List<Book> books) {
        this.books = books;
    }

    void clear() {
        this.books.clear();
    }

    void addList(List<Book> books) {
        this.books.addAll(books);
    }
}
