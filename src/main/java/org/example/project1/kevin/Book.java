package org.example.project1.kevin;

public class Book {

    private String title;
    private String author;
    private int yearPublished;
    private String isbn;
    public static int totalBooksCreated = 0;
    
    public Book(String title, String author, int yearPublished, String isbn) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        totalBooksCreated++;
    }

     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYearPublished() {
        return yearPublished;
    }
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public static int getTotalBooksCreated() {
        return totalBooksCreated;
    }
    public static void setTotalBooksCreated(int totalBooksCreated) {
        Book.totalBooksCreated = totalBooksCreated;
    }
}