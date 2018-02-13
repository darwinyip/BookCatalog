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
//        thumbnail.setImageBitmap(Utils.loadImage(book.getThumbnail_url()));
        TextView title = findViewById(R.id.title_detail);
        title.setText(book.getTitle());
        TextView subtitle = findViewById(R.id.subtitle_detail);
        subtitle.setText(book.getSubtitle());
        TextView authors = findViewById(R.id.authors_detail);
        authors.setText(Utils.joinList(book.getAuthors(), ", "));
        TextView categories = findViewById(R.id.categories_detail);
        categories.setText(Utils.joinList(book.getCategories(), ", "));
        TextView publisher = findViewById(R.id.publisher_detail);
        publisher.setText(book.getPublisher());
        TextView isbn10 = findViewById(R.id.isbn10_detail);
        isbn10.setText(book.getIsbn10());
        TextView isbn13 = findViewById(R.id.isbn13_detail);
        isbn13.setText(book.getIsbn13());
        TextView description = findViewById(R.id.description_detail);
        description.setText(book.getDescription());
    }
}
