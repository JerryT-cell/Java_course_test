package org.example.project1.nathan;

public class Library {

    private Book[] books;
    public int count;
    private static int member = 0;

    public Library(int collectionSize) {
        books = new Book[collectionSize];
        count = 0;
        member ++;
    }

    public String getNumberOfLibraries(){
        return "Number of Libraries created " + member;
    }

    public int getBookCount(){
        return count;
    }

    public boolean addBook(Book newBook) {
        if (count < books.length) {
            books[count] = newBook;
            count++;
            return true;
        }
        else {
            System.out.println("The Collection is full, please remove some books or leave!");
            return false;
        }

    }
    public boolean removeBook(String isbn) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            String bookIsbn = books[i].getIsbn();
            if (bookIsbn.equals(isbn)) {
                index = i;
                count--;
                break;
            }
        }
        if (index == -1) {
            System.out.print("The book with the provided ISBN " + isbn + " is not founded in the collection");
            return false;

        }
        else {
            for (int i = index; i < count; i++) {
                books[i] = books[i + 1];
            }
            books[count] = null;

            return true;




        }


    }

    public String listBooks(){
        String booklist = "";
        for (int i = 0; i < count; i++){
            String list = books[i].getTitle() + ", " +  books[i].getAuthor() + ", " + books[i].getYearPublished() + ", " + books[i].getIsbn() + "\n" ;
            System.out.println(list);
            booklist += list;
        }
        if (booklist.isEmpty()){
            System.out.println("No books in the library.") ;
        }
        return booklist;

    }
    public Book[] findBookByTitle(String title){
        int compteur = 0;
        for (int i = 0; i < count; i++){
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())){
                System.out.println("Found matching book: " + books[i].getTitle());
                compteur++;
            }

        }
        Book[] booksTitles = new Book[compteur];
        int compteur2 = 0;
        for (int i = 0; i < count; i++){
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())){
                booksTitles[compteur2] = books[i];
                compteur2++;


            }
        }
        if (compteur == 0){

            System.out.print("No books found with title containing: " + title );
        }
        else{
            System.out.println("Found " + compteur + " book(s) with title containing: " + title);
        }



        return booksTitles;

    }

    public String summary(){
        String summaryBooks = "";
        if (count == 0){
            summaryBooks += "The collection is empty.";
        }
        else {
            for (int i = 0; i < count; i++) {
                summaryBooks += books[i].getTitle() + ", " + books[i].getYearPublished() + "\n";
            }


        }
        return summaryBooks;
    }


}
