package org.example.project1.zoe;

public class Book {
    private String title;
    private String author;
    private int yearPublished;
    private String isbn;

    public Book(String title, String author, int yearPublished, String isbn) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return this.author;
    }




    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {
        return this.title;
    }

    public int getYearPublished() {
        return yearPublished;
    }
    public String getIsbn() {
        return isbn;

    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;


    }


}
