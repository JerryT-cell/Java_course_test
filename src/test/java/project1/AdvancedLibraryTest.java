package project1;

import org.example.project1.pc.Book;
import org.example.project1.pc.Library;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A comprehensive test suite for the Library Catalog System, using JUnit 5.
 */
public class AdvancedLibraryTest {

    // Stream to capture output printed to System.out.
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;

    // Set up the standard output capture before each test.
    @BeforeEach
    public void setUpStreams() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    // Restore the original System.out after each test.
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        outContent.reset();
    }



    /**
     * Test that Book creation increases the static counter.
     */
    @Test
    public void testBookCreationAndStaticCounter() {
        int initialCount = Book.getTotalBooksCreated();
        // Create several books.
        Book b1 = new Book("Test Book A", "Author A", 2021, "ISBN-A");
        Book b2 = new Book("Test Book B", "Author B", 2022, "ISBN-B");
        Book b3 = new Book("Test Book C", "Author C", 2023, "ISBN-C");

        // Validate that the static counter reflects all created books.
        assertEquals(initialCount + 3, Book.getTotalBooksCreated(),
               "Static counter should update after new Book creations.");
    }

    /**
     * Test adding books within capacity constraints.
     */
    @Test
    public void testAddBooksWithinCapacity() {
        Library library = new Library(3);
        Book b1 = new Book("Book One", "Author1", 2001, "ID1");
        Book b2 = new Book("Book Two", "Author2", 2002, "ID2");
        Book b3 = new Book("Book Three", "Author3", 2003, "ID3");

        assertTrue(library.addBook(b1), "Book One should be added.");
        assertTrue(library.addBook(b2), "Book Two should be added.");
        assertTrue(library.addBook(b3), "Book Three should be added.");

        // Library capacity is 3; this addition should fail.
        Book b4 = new Book("Book Four", "Author4", 2004, "ID4");
        assertFalse(library.addBook(b4), "Library is full, so Book Four should not be added.");

        // Optionally, capture printed message for a full library.
        library.listBooks();
        String output = outContent.toString();
        assertTrue(output.contains("Book One"));
        assertTrue(output.contains("Book Two"));
        assertTrue(output.contains("Book Three"));
    }

    /**
     * Test the removal of a book and the shifting of remaining books.
     */
    @Test
    public void testRemoveBookAndShift() {
        Library library = new Library(5);
        Book b1 = new Book("Alpha", "Author1", 2000, "A1");
        Book b2 = new Book("Bravo", "Author2", 2001, "B2");
        Book b3 = new Book("Charlie", "Author3", 2002, "C3");
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // Remove the book with ISBN "B2"
        assertTrue(library.removeBook("B2"), "Book Bravo should be removed.");

        // Capture output from listing books.
        library.listBooks();
        String output = outContent.toString();
        // The output should contain "Alpha" and "Charlie" but not "Bravo".
        assertTrue(output.contains("Alpha"));
        assertTrue(output.contains("Charlie"));
        assertFalse(output.contains("Bravo"));
    }

    /**
     * Test listBooks output when the library is empty.
     */
    @Test
    public void testListBooksEmpty() {
        Library library = new Library(3);
        library.listBooks();
        String output = outContent.toString();
        assertTrue(output.contains("No books in the library."),
                "Empty library should display an appropriate message.");
    }

    /**
     * Test the search functionality to match m ultiple books.
     */
    @Test
    public void testFindBookByTitleMultipleMatches() {
        Library library = new Library(5);
        Book b1 = new Book("Java Programming Basics", "Author1", 2010, "J1");
        Book b2 = new Book("Advanced Java Techniques", "Author2", 2011, "J2");
        Book b3 = new Book("Python Programming Intro", "Author3", 2012, "P3");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // Search for "Java" should match two books.
        library.findBookByTitle("Java");
        String output = outContent.toString();
        assertTrue(output.contains("Java Programming Basics"), "Should find first Java book.");
        assertTrue(output.contains("Advanced Java Techniques"), "Should find second Java book.");
        assertFalse(output.contains("Python Programming Intro"), "Python book should not be listed.");
    }

    /**
     * Test removal of a non-existent book.
     */
    @Test
    public void testRemoveNonExistentBook() {
        Library library = new Library(3);
        Book b1 = new Book("Unique Book", "Author", 2005, "UNI-001");
        library.addBook(b1);
        assertFalse(library.removeBook("NON-EXIST"),
                "Removal should fail if the book does not exist.");

        String output = outContent.toString();
        assertTrue((output.contains("not found")),
                "Output should indicate that the book was not found.");
    }

    /**
     * Test getter and setter methods for the Book class.
     */
    @Test
    public void testBookGetterAndSetter() {
        Book book = new Book("Old Title", "Old Author", 1990, "OLDISBN");
        // Verify getters return the correct initial values.
        assertEquals("Old Title", book.getTitle());
        assertEquals("Old Author", book.getAuthor());
        assertEquals(1990, book.getYearPublished());
        assertEquals("OLDISBN", book.getIsbn());

        // Update values using setters.
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setYearPublished(2000);
        book.setIsbn("NEWISBN");

        // Verify that getters return the updated values.
        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals(2000, book.getYearPublished());
        assertEquals("NEWISBN", book.getIsbn());
    }

    /**
     * Test the search functionality when no matches are found.
     */
    @Test
    public void testFindBookNoMatches() {
        Library library = new Library(3);
        Book b1 = new Book("C Programming Essentials", "Author1", 1995, "C1");
        library.addBook(b1);

        // Search for a title that does not exist.
        library.findBookByTitle("Ruby");
        String output = outContent.toString();
        assertTrue(output.contains("No books found with title containing"),
                "Should indicate that no matching books were found.");
    }

    /**
     * A stress test involving multiple add and remove operations.
     */
    @Test
    public void testStressAddAndRemoveOperations() {
        // Create a library with a capacity to handle many books.
        Library library = new Library(10);
        // Add 10 books.
        for (int i = 1; i <= 10; i++) {
            boolean added = library.addBook(new Book("Book " + i, "Author " + i, 2000 + i, "ISBN" + i));
            assertTrue(added, "Book " + i + " should be added.");
        }
        // Attempting to add an 11th book should fail.
        assertFalse(library.addBook(new Book("Book 11", "Author11", 2011, "ISBN11")),
                "Adding a book beyond capacity should fail.");

        // Remove a few books and add new ones.
        assertTrue(library.removeBook("ISBN5"), "Should remove Book 5.");
        assertTrue(library.removeBook("ISBN3"), "Should remove Book 3.");
        // Now adding new books should succeed.
        assertTrue(library.addBook(new Book("New Book A", "New Author", 2022, "NEW-A")));
        assertTrue(library.addBook(new Book("New Book B", "New Author", 2023, "NEW-B")));

        // Verify that the listBooks output reflects the correct current set.
        library.listBooks();
        String output = outContent.toString();
        // Books 3 and 5 should be gone.
        assertFalse(output.contains("ISBN3"));
        assertFalse(output.contains("ISBN5"));
        // New books should be present.
        assertTrue(output.contains("NEW-A"));
        assertTrue(output.contains("NEW-B"));
    }
}

