package local.darwin.bookcatalog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static local.darwin.bookcatalog.MainActivity.books;

public class BookDetailActivity extends Activity {
    private static final String LOG_TAG = BookDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Creating activity...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position");
        Book book = books.get(position);

//        ImageView thumbnail = findViewById(R.id.thumbnail_detail);
        TextView title = findViewById(R.id.title_detail);
        TextView subtitle = findViewById(R.id.subtitle_detail);
        TextView authors = findViewById(R.id.authors_detail);
        TextView categories = findViewById(R.id.categories_detail);
        TextView publisher = findViewById(R.id.publisher_detail);
        TextView isbn10 = findViewById(R.id.isbn10_detail);
        TextView isbn13 = findViewById(R.id.isbn13_detail);
        TextView description = findViewById(R.id.description_detail);

//        thumbnail.setImageBitmap(Utils.loadImage(book.getThumbnail_url()));
//        title.setText(book.getTitle());
//        subtitle.setText(book.getSubtitle());
//        authors.setText(Utils.joinList(book.getAuthors(), ", "));
//        categories.setText(Utils.joinList(book.getCategories(), ", "));
//        publisher.setText(book.getPublisher());
//        isbn10.setText(book.getIsbn10());
//        isbn13.setText(book.getIsbn13());
//        description.setText(book.getDescription());

        Utils.setView(title, book.getTitle());
        Utils.setView(subtitle, book.getSubtitle());
        Utils.setView(authors, Utils.joinList(book.getAuthors(), ", "));
        Utils.setView(categories, Utils.joinList(book.getCategories(), ", "));
        Utils.setView(publisher, book.getPublisher());
        Utils.setView(isbn10, book.getIsbn10());
        Utils.setView(isbn13, book.getIsbn13());
        Utils.setView(description, book.getDescription());

    }
}
