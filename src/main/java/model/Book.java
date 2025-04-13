package model;

public class Book {
    private final String title;
    private final String authors;
    private final String year;

    public Book(String title, String authors, String year) {
        this.title = title;
        this.authors = authors;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getYear() {
        return year;
    }
}