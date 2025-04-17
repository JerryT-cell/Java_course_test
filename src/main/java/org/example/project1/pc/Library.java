package org.example.project1.pc;

public class Library {
    private int counter ;
    private Book[] books ;

    public Library(int num){
        books = new Book[num];
        counter=0;
    }

    public boolean addBook(Book newBook){
        if (counter >= books.length){
            System.out.println("The library is full!");
            return false;
        }
        else{
            books[counter] = newBook ;
            System.out.println("The book has been added.");
            counter++;
            return true;

        }
    }

    public boolean removeBook(String isbn){
        for (int i = 0; i <= (counter-1); i++) {
            if (isbn.equals(books[i].getIsbn())) {
                for (int j = i; j < (counter-1); j++) {
                    books[j] = books[j + 1];
                }
                books[counter - 1] = null;
                counter--;
                return true ;
            }
        }
        return false;
    }

    public String listBooks(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < counter; i++ ){
            String variable = books[i].getTitle() + ", " + books[i].getAuthor() + ", " + books[i].getYearPublished() ;
            result.append(variable).append("\n");
        }
        return result.toString();
    }

    public Book[] findBookByTitle(String title){
        int h = 0;

        for(int i = 0; i <= (books.length-1); i++){
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())){
                h++;
            }
        }
        System.out.println(h);
        Book[] books2 = new Book[h];

        for (int j = 0; j <= (h-1); j++) {
            if (title.equals(books[j].getTitle())) {
                books2[j] = books[j];
            }
        }
        return books2;
    }

    public int getBookCount(){
        return counter;
    }

}
