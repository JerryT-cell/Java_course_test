package org.example.project1.pc;

public class Book {

    private String title;
    private String author;
    private int yearPublished;
    private String isbn;
    private static int created;

    public Book(String title, String author, int yearPublished, String isbn){
        this.title = title ;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        created++;
    }

    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public int getYearPublished(){
        return yearPublished;
    }
    public String getIsbn(){
        return isbn;
    }

    public void setAuthor(String newAuthor) {
        author = newAuthor;
    }

    public void setIsbn(String newIsbn) {
        isbn = newIsbn;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setYearPublished(int newYearPublished) {
        yearPublished = newYearPublished;
    }

    public static int getTotalBooksCreated(){
        return created;
    }
}

