package org.example.project1.kevin;

public class Library {

    private Book[] books;
    private int bookCount;
    private final int capacity;


    public Library(int size) {
        this.capacity = size;
        this.books = new Book[size];
        this.bookCount = 0;
    }


    public boolean addBook(Book newBook) {
        if (bookCount >= capacity) {
            System.out.println("Library is full. Book"+newBook.getTitle()+"can not be added");
            return false;
            }
            books[bookCount] = newBook;
            bookCount++;
            System.out.println("Book added:"+newBook.getTitle());
            return true;

    }


    public int getBookCount() {
        return bookCount;
    }


    public void listBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the library.");
        } else {
            for (int i = 0; i < bookCount; i++) {
                System.out.println(books[i]);
            }
        }
    }
    public boolean removeBook(String isbn) {
        int index = -1;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                index = i;
                for (int j = index; j < bookCount - 1 ; j++) {
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null;
                bookCount--;
                System.out.println("Book with ISBN " + isbn + " removed.");
                return true;


            }

        }
        System.out.println("Book with ISBN " + isbn + " not found.");
        return false;
    }

    public Book[] findBookByTitle(String title) {
        int count = 0;
        Book[] matches = new Book[bookCount];
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches[count++] = books[i];
            } else {System.out.println("No books found with this title containing ");}
        }

        Book[] result = new Book [count];
             for (int i = 0; i < count; i++) {
            result[i] = matches[i];
             }
        return result;


    }
}
