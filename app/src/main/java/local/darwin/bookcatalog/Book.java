package local.darwin.bookcatalog;

import android.graphics.Bitmap;

import java.util.List;

class Book {
    private String title, subtitle, publisher, description, isbn10, isbn13, thumbnail_url;
    private List<String> authors, categories;
    private Bitmap thumbnail;

    Book(String title, String subtitle, String publisher, String description, String isbn10, String isbn13, String thumbnail_url, List<String> authors, List<String> categories, Bitmap thumbnail) {
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.description = description;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.thumbnail_url = thumbnail_url;
        this.authors = authors;
        this.categories = categories;
        this.thumbnail = thumbnail;
    }

    String getTitle() {
        return title;
    }

    String getSubtitle() {
        return subtitle;
    }

    String getPublisher() {
        return publisher;
    }

    String getDescription() {
        return description;
    }

    String getIsbn10() {
        return isbn10;
    }

    String getIsbn13() {
        return isbn13;
    }

    String getThumbnail_url() {
        return thumbnail_url;
    }

    List<String> getAuthors() {
        return authors;
    }

    List<String> getCategories() {
        return categories;
    }

    Bitmap getThumbnail() {
        return thumbnail;
    }

    static class BookBuilder {
        private String title;
        private String subtitle;
        private String publisher;
        private String description;
        private String isbn10;
        private String isbn13;
        private String thumbnail_url;
        private List<String> authors;
        private List<String> categories;
        private Bitmap thumbnail;

        BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        BookBuilder setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        BookBuilder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        BookBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        BookBuilder setIsbn10(String isbn10) {
            this.isbn10 = isbn10;
            return this;
        }

        BookBuilder setIsbn13(String isbn13) {
            this.isbn13 = isbn13;
            return this;
        }

        BookBuilder setThumbnail_url(String thumbnail_url) {
            this.thumbnail_url = thumbnail_url;
            return this;
        }

        BookBuilder setAuthors(List<String> authors) {
            this.authors = authors;
            return this;
        }

        BookBuilder setCategories(List<String> categories) {
            this.categories = categories;
            return this;
        }

        BookBuilder setThumbnail(Bitmap thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }


        Book createBook() {
            return new Book(title, subtitle, publisher, description, isbn10, isbn13, thumbnail_url, authors, categories, thumbnail);
        }
    }
}
