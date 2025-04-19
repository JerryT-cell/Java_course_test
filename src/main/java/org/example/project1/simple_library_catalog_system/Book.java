package org.example.project1.simple_library_catalog_system;

public class Book {
    // Private attributes for encapsulation
    private String title;
    private String author;
    private int yearPublished;
    private String isbn;

    // A static field to track the total number of Book instances created
    private static int totalBooksCreated = 0;


    // Getter and Setter for title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for author
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter and Setter for yearPublished
    public int getYearPublished() {
        return yearPublished;
    }
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    // Getter and Setter for isbn
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter for the static counter
    public static int getTotalBooksCreated() {
        return totalBooksCreated;
    }

    // toString method for easy printing of Book details
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublished=" + yearPublished +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

