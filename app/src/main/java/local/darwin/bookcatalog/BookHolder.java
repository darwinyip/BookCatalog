package local.darwin.bookcatalog;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class BookHolder extends RecyclerView.ViewHolder {

    private ImageView thumbnail;
    private TextView title;
    private TextView authors;
    private int position;

    BookHolder(View itemView) {
        super(itemView);
        thumbnail = itemView.findViewById(R.id.thumbnail_summary);
        title = itemView.findViewById(R.id.title_summary);
        authors = itemView.findViewById(R.id.authors_summary);
        itemView.setOnClickListener(view -> {
            Intent intent = new Intent(itemView.getContext(), BookDetailActivity.class);
            intent.putExtra("position", this.position);
            itemView.getContext().startActivity(intent);
        });
    }

    void setBook(Book book, int position) {
        this.position = position;
        title.setText(book.getTitle());
        authors.setText(Utils.joinList(book.getAuthors(), ", "));
    }
}
