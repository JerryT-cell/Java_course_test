package org.example.project1.zoe;

public class Library {
    private Book[] bookList;
    private int counter = 0;

    public Library(int initialSize) {
        bookList = new Book[initialSize];
    }

    public Boolean addBook(Book newBook) {
        if (counter < bookList.length) {
            bookList[counter] = newBook;
            counter++;
            System.out.println("The book " + newBook.getTitle() + " from " + newBook.getAuthor() +
                    " published in " + newBook.getYearPublished() + " has been added to the library.");
            return true;
        } else {
            System.out.println("Sorry, the Library is full. Unfortunately, the book " + newBook.getTitle() + " could not added.");
            return false;
        }
    }

    public boolean removeBook(String isbn) {
        boolean found = false;
        int shifting = 0;

        for (int i = 0; i < counter; i++) {
            Book book = bookList[i];
            if (book != null && book.getIsbn().equals(isbn)) {
                found = true;
            } else {
                bookList[shifting] = book;
                shifting++;
            }
        }

        if (found) {

            for (int i = shifting; i < counter; i++) {
                bookList[i] = null;
            }
            counter = shifting;
            System.out.println("The book " + isbn + " was removed from the library.");
            return true;
        } else {
            System.out.println("Sorry, the book " + isbn + " was not found in the library.");
            return false;
        }
    }




    public void listBooks() {
        System.out.println("Here is the list of books you can find in our library:");
        for (int i = 0; i < bookList.length; i++) {
            if (bookList[i] != null) {
                System.out.println(bookList[i]);
            }


        }
    }


    public Book[] findBookByTitle(String title) {
        Book[] foundedBook = new Book[counter];
        int founded = 0;

        for (int i = 0; i < counter; i++) {
            Book book = bookList[i];
            if (book != null && book.getTitle().contains(title)) {
                foundedBook[founded] = book;
                founded++;
            }
        }

        if (founded > 0) {
            System.out.println("Good news! We found " + founded + " books containing '" + title + "' in the title.");
        } else {
            System.out.println("Sorry, no books found with '" + title + "' in the title.");
        }

        Book[] result = new Book[founded];
        return result;
    }


    public int getBookCount(){
        return counter;
    }

}









