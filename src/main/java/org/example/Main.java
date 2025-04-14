package org.example;

import org.example.simple_library_catalog_system.Book;
import org.example.simple_library_catalog_system.Library;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       // System.out.printf("Hello and welcome!");

       /* for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }*/

        Library library = new Library(3);
        Book b1 = new Book("Book One", "Author1", 2001, "ID1");
        Book b2 = new Book("Book Two", "Author2", 2002, "ID2");
        Book b3 = new Book("Book Three", "Author3", 2003, "ID3");


        // Library capacity is 3; this addition should fail.
        Book b4 = new Book("Book Four", "Author4", 2004, "ID4");

        // Add books to the library
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        // Attempt to add a fourth book, which should fail
        boolean added = library.addBook(b4);


        // Optionally, capture printed message for a full library.
        library.listBooks();



    }
}