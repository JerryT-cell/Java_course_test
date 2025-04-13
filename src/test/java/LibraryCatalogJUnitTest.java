import org.example.simple_library_catalog_system.Book;
import org.example.simple_library_catalog_system.Library;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class LibraryCatalogJUnitTest {

    // Resetting static state is a bit challenging.
    // In a real testing environment we might use reflection or a test runner that resets static state,
    // but for now, we'll assume tests execute in an order that won't cause interference,
    // or you can run each test in a separate JVM instance.

    // ----------- Tests for the Book class -----------

    @Test
    public void testBookCreationAndGetters() {
        Book book = new Book("Clean Architecture", "Robert C. Martin", 2017, "978-0134494166");
        assertEquals("Clean Architecture", book.getTitle(), "Title should match");
        assertEquals("Robert C. Martin", book.getAuthor(), "Author should match");
        assertEquals(2017, book.getYearPublished(), "Year published should match");
        assertEquals("978-0134494166", book.getIsbn(), "ISBN should match");
    }

    @Test
    public void testBookSetters() {
        Book book = new Book("Old Title", "Old Author", 2000, "OLD-ISBN");
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setYearPublished(2020);
        book.setIsbn("NEW-ISBN");

        assertEquals("New Title", book.getTitle(), "Title should be updated");
        assertEquals("New Author", book.getAuthor(), "Author should be updated");
        assertEquals(2020, book.getYearPublished(), "Year published should be updated");
        assertEquals("NEW-ISBN", book.getIsbn(), "ISBN should be updated");
    }

    @Test
    public void testStaticCounterInBook() {
        // Record current count from previous tests (may be non-zero if run in one suite)
        int initialCount = Book.getTotalBooksCreated();
        Book b1 = new Book("Test Book 1", "Author 1", 1999, "ISBN-1");
        Book b2 = new Book("Test Book 2", "Author 2", 2000, "ISBN-2");
        // The counter should increase by 2
        assertEquals(initialCount + 2, Book.getTotalBooksCreated(), "Static counter should increase when new books are created");
    }

    // ----------- Tests for the Library class -----------

    private Library library;
    private Book book1, book2, book3, book4;

    @BeforeEach
    public void setup() {
        // Create a library with a capacity for 3 books for testing
        library = new Library(3);
        book1 = new Book("Effective Java", "Joshua Bloch", 2008, "978-0321356680");
        book2 = new Book("Clean Code", "Robert C. Martin", 2008, "978-0132350884");
        book3 = new Book("Java Concurrency in Practice", "Brian Goetz", 2006, "978-0321349606");
        book4 = new Book("Design Patterns", "Erich Gamma et al.", 1994, "978-0201633610");
    }

    @Test
    public void testAddBook() {
        // Add three books; each should return true
        assertTrue(library.addBook(book1), "Should be able to add book1");
        assertEquals(1, library.getBookCount(), "Book count should be 1 after adding book1");

        assertTrue(library.addBook(book2), "Should be able to add book2");
        assertEquals(2, library.getBookCount(), "Book count should be 2 after adding book2");

        assertTrue(library.addBook(book3), "Should be able to add book3");
        assertEquals(3, library.getBookCount(), "Book count should be 3 after adding book3");

        // Adding a fourth book should fail because library capacity is 3
        assertFalse(library.addBook(book4), "Should not be able to add book4 when library is full");
        assertEquals(3, library.getBookCount(), "Book count should remain 3 after failing to add book4");
    }

    @Test
    public void testRemoveBook() {
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Remove book2 by its ISBN
        assertTrue(library.removeBook("978-0132350884"), "Book2 should be successfully removed");
        assertEquals(2, library.getBookCount(), "Book count should be 2 after removal");

        // Try removing a book that doesn't exist
        assertFalse(library.removeBook("non-existent-isbn"), "Removing a non-existent book should return false");
        assertEquals(2, library.getBookCount(), "Book count should remain unchanged after a failed removal");

        // Remove book1 and book3
        assertTrue(library.removeBook("978-0321356680"), "Book1 should be successfully removed");
        assertTrue(library.removeBook("978-0321349606"), "Book3 should be successfully removed");
        assertEquals(0, library.getBookCount(), "Library should be empty after removing all books");
    }

    @Test
    public void testFindBookByTitle() {
        library.addBook(book1); // "Effective Java"
        library.addBook(book2); // "Clean Code"
        library.addBook(book3); // "Java Concurrency in Practice"

        // Search using substring "Java" which should match book1 and book3
        Book[] foundBooks = library.findBookByTitle("Java");
        assertEquals(2, foundBooks.length, "Should find 2 books containing 'Java' in the title");

        // Search using substring "Clean" which should match only book2
        Book[] foundClean = library.findBookByTitle("Clean");
        assertEquals(1, foundClean.length, "Should find 1 book containing 'Clean' in the title");

        // Search with a title that doesn't exist
        Book[] foundNone = library.findBookByTitle("Python");
        assertEquals(0, foundNone.length, "Should return 0 books for a title substring not present in any book");
    }

    @Test
    public void testListingBooks() {
        // Although listBooks() prints to stdout, we can indirectly test by the book count.
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        assertEquals(3, library.getBookCount(), "There should be 3 books in the library");
        // In a more advanced test, you could capture System.out and validate the printed output.
    }
}