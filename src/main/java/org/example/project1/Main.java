package org.example.project1;

import org.example.project1.simple_library_catalog_system.Book;
import org.example.project1.simple_library_catalog_system.Library;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {


        Library library = new Library(3);

        Library library1 = new Library(3);

        if(library1.equals(library)) {
            System.out.println("library and library1 are the same");
        } else {
            System.out.println("library and library1 are different");
        }


        Book b1 = new Book();



    }


    static int getUser_Int_Input(Scanner scanner) {
        return scanner.nextInt();
    }

    static double getUser_double_Input(Scanner scanner) {
        return scanner.nextDouble();
    }









    public static void exampleIfForWhile() {
        int a = 5;
        int b = 10;

        if (a < b) {
            //System.out.println("a is less than b");
        } else {
            System.out.println("a is greater than or equal to b");
        }

        for (int j = 0; j < 5; j++) {
            System.out.println("Parent loop j = " + j);
            for (int i = 0; i < 5; i++) {
                System.out.println(" child i = " + i);
                for (int k = 0; k < 5; k++) {
                    System.out.println("grandchild k = " + k);
                }
                if (i == 2) {
                    System.out.println("Breaking out of the child loop when i = " + i);
                    break; // Breaks out of the child loop
                }
            }
        }







        int j = 0;
        while (j < 5) {
            System.out.println("j = " + j);
            j++;
        }
    }
}