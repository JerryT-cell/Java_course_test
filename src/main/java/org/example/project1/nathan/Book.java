package org.example.project1.nathan;

public class Book {

    private String title;
    private String author;
    private int yearPublished;
    private String isbn;
    private static int totalBooksCreated = 0;

    public Book(String title, String author, int yearPublished, String isbn) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        totalBooksCreated++;

    }

    public static int getTotalBooksCreated(){
        return totalBooksCreated;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public void setYearPublished(int yearPublished){
        this.yearPublished = yearPublished;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }



}
