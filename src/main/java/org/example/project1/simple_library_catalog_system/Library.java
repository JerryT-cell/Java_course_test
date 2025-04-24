package org.example.project1.simple_library_catalog_system;

import java.util.Arrays;

public class Library{
    // Array to hold Book objects and an integer to track the current count of books
    private Book[] books;
    private int count;  // current number of books in the library


    // Constructor to initialize the array with a given capacity
    public Library(int capacity) {
        books = new Book[capacity];
        count = 0;
    }

    /**
     * addBook
     * Attempts to add a new Book to the library. If the array is full, it prints an error message.
     * @param newBook the Book to add.
     * @return true if the book was added successfully, false otherwise.
     */
    public boolean addBook(Book newBook) {
        if (count >= books.length) {
            System.out.println("Error: Library is full. Cannot add new book.");
            return false;
        }
        books[count] = newBook;
        count++;
        return true;
    }

    /**
     * removeBook
     * Removes the first occurrence of a Book by matching its ISBN.
     * If the book is found, the method shifts subsequent books left and decrements the count.
     * @param isbn the ISBN of the book to remove.
     * @return true if removal was successful, false otherwise.
     */
    public boolean removeBook(String isbn) {
        for (int i = 0; i < count; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                // Shift books left to fill the gap
                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[count - 1] = null;
                count--;
                return true;
            }
        }
        System.out.println("Error: No book found with ISBN: " + isbn);
        System.out.println("not found");
        return false;
    }

    /**
     * listBooks
     * Prints out all the books in the library.
     */
    public void listBooks() {
        if (count == 0) {
            System.out.println("No books in the library.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(books[i]);
            }
        }
    }

    /**
     * findBookByTitle
     * Searches for all books whose titles contain the given substring (case-insensitive).
     * @param title the title or partial title to search for.
     * @return an array of Book objects that match the search criteria.
     */
    public Book[] findBookByTitle(String title) {
        // First, determine the number of matching books
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Found matching book: " + books[i]);
                matchCount++;
            }
        }
        // Create an array to hold the matching books
        Book[] foundBooks = new Book[matchCount];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks[index] = books[i];
                index++;
            }
        }
        if (matchCount == 0) {
            System.out.println("No books found with title containing: " + title);
        } else {
            System.out.println("Found " + matchCount + " book(s) with title containing: " + title);
        }
        return foundBooks;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Library library = (Library) obj;
        return count == library.count;
    }

    // Getter to retrieve the current number of books in the library
    public int getBookCount() {
        return count;
    }
}

