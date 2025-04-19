package project1.examinationTests;

import org.example.project1.nathan.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Book class functionality
 */
public class BookTest {

    private Book testBook;
    private final String TITLE = "The Great Gatsby";
    private final String AUTHOR = "F. Scott Fitzgerald";
    private final int YEAR = 1925;
    private final String ISBN = "9780743273565";

    public BookTest() {
        // Constructor
    }

    @BeforeEach
    void setUp() {
        testBook = new Book(TITLE, AUTHOR, YEAR, ISBN);
    }

    @Test
    @DisplayName("Test Book constructor and getters")
    void testConstructorAndGetters() {
        assertEquals(TITLE, testBook.getTitle(), "Title should match the constructor parameter");
        assertEquals(AUTHOR, testBook.getAuthor(), "Author should match the constructor parameter");
        assertEquals(YEAR, testBook.getYearPublished(), "Year should match the constructor parameter");
        assertEquals(ISBN, testBook.getIsbn(), "ISBN should match the constructor parameter");
    }

    @Test
    @DisplayName("Test Book setters")
    void testSetters() {
        // New values for testing setters
        String newTitle = "New Title";
        String newAuthor = "New Author";
        int newYear = 2022;
        String newISBN = "9781234567897";

        // Set new values
        testBook.setTitle(newTitle);
        testBook.setAuthor(newAuthor);
        testBook.setYearPublished(newYear);
        testBook.setIsbn(newISBN);

        // Check that values were updated
        assertEquals(newTitle, testBook.getTitle(), "Title should be updated");
        assertEquals(newAuthor, testBook.getAuthor(), "Author should be updated");
        assertEquals(newYear, testBook.getYearPublished(), "Year should be updated");
        assertEquals(newISBN, testBook.getIsbn(), "ISBN should be updated");
    }

    @Test
    @DisplayName("Test static counter increments with new Book instances")
    void testStaticCounter() {
        // Record count before creating new books
        int countBefore = Book.getTotalBooksCreated();

        // Create three new books
        Book book1 = new Book("Book 1", "Author 1", 2001, "ISBN1");
        Book book2 = new Book("Book 2", "Author 2", 2002, "ISBN2");
        Book book3 = new Book("Book 3", "Author 3", 2003, "ISBN3");

        // Check that counter increased by exactly 3
        assertEquals(countBefore + 3, Book.getTotalBooksCreated(),
                "Total books counter should increment by 3");


    }
}
