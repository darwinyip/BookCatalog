package local.darwin.bookcatalog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class BookHolder extends RecyclerView.ViewHolder {

    private ImageView thumbnail;
    private TextView title;
    private TextView authors;

    BookHolder(View itemView) {
        super(itemView);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        title = itemView.findViewById(R.id.title);
        authors = itemView.findViewById(R.id.authors);
    }

    void setBook(Book book) {
        title.setText(book.getTitle());
        authors.setText(Utils.joinList(book.getAuthors(), ", "));
    }
}
