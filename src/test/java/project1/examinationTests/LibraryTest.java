package project1.examinationTests;

import org.example.project1.nathan.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;
    private final int CAPACITY = 5;
    private Book book1, book2, book3;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public LibraryTest() {
        // Constructor
    }

    @BeforeEach
    void setUp() {
        // Initialize library with capacity
        library = new Library(CAPACITY);

        // Create test books
        book1 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084");
        book2 = new Book("1984", "George Orwell", 1949, "9780451524935");
        book3 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "9780547928227");

        // Redirect System.out to capture printed output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        // Restore original System.out
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    @DisplayName("Test adding books and getting count")
    void testAddBookAndCount() {
        assertTrue(library.addBook(book1), "Should return true when adding a book");
        assertEquals(1, library.getBookCount(), "Book count should be 1 after adding 1 book");

        assertTrue(library.addBook(book2), "Should return true when adding a book");
        assertEquals(2, library.getBookCount(), "Book count should be 2 after adding 2 books");
    }

    @Test
    @DisplayName("Test adding books when library is full")
    void testAddBookWhenFull() {
        // Fill the library to capacity
        for (int i = 0; i < CAPACITY; i++) {
            library.addBook(new Book("Book " + i, "Author " + i, 2000 + i, "ISBN" + i));
        }

        // Try to add one more book
        assertFalse(library.addBook(new Book("Extra Book", "Extra Author", 2023, "ExtraISBN")),
                "Should return false when library is full");

        // Check error message
        assertTrue(outContent.toString().contains("full"),
                "Should print error message when library is full");

        // Verify count remains at capacity
        assertEquals(CAPACITY, library.getBookCount(), "Book count should remain at capacity");
    }

    @Test
    @DisplayName("Test removing a book by ISBN")
    void testRemoveBook() {
        // Add books
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Initial count
        int initialCount = library.getBookCount();

        // Remove a book
        assertTrue(library.removeBook(book2.getIsbn()), "Should return true when removing an existing book");

        // Check count decreased
        assertEquals(initialCount - 1, library.getBookCount(), "Book count should decrease after removal");

        // Find books by title to verify book2 is gone
        Book[] found = library.findBookByTitle("1984");
        assertEquals(0, found.length, "No books should be found with removed title");
    }

    @Test
    @DisplayName("Test removing a non-existent book")
    void testRemoveNonExistentBook() {
        // Add some books
        library.addBook(book1);
        library.addBook(book2);

        // Try to remove a book that doesn't exist
        assertFalse(library.removeBook("nonexistent-isbn"), "Should return false when removing non-existent book");

        // Check error message
        assertTrue((outContent.toString().contains("No book found")||outContent.toString().contains("not found")) && outContent.toString().contains("nonexistent-isbn"),
                "Should print error message when book not found");
        assertTrue(outContent.toString().contains("not found"),
                "Should print 'not found' message");
    }

    @Test
    @DisplayName("Test listing books with empty library")
    void testListBooksEmpty() {
        // List books from empty library
        library.listBooks();

        // Check output message
        assertTrue(outContent.toString().contains("No books in the library"),
                "Should print message indicating empty library");
    }

    @Test
    @DisplayName("Test listing books with non-empty library")
    void testListBooks() {
        // Add books
        library.addBook(book1);
        library.addBook(book2);

        // List books
        library.listBooks();

        // Check output contains book details
        assertTrue(outContent.toString().contains(book1.getTitle()),
                "Output should contain first book title");
        assertTrue(outContent.toString().contains(book2.getTitle()),
                "Output should contain second book title");
    }

    @Test
    @DisplayName("Test finding books by title (exact match)")
    void testFindBookByExactTitle() {
        // Add books
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Find book with exact title
        Book[] foundBooks = library.findBookByTitle("1984");

        // Check results
        assertEquals(1, foundBooks.length, "Should find exactly one book");
        assertEquals(book2.getTitle(), foundBooks[0].getTitle(), "Found book should match the expected one");

        // Check output
        assertTrue(outContent.toString().contains("Found 1 book(s) with title containing: 1984"),
                "Should print message with count of books found");
    }

    @Test
    @DisplayName("Test finding books by title (partial match)")
    void testFindBookByPartialTitle() {
        // Add books with similar titles
        Book bookWithThe1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "ISBN-GG");
        Book bookWithThe2 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, "ISBN-LOTR");
        Book bookWithoutThe = new Book("Pride and Prejudice", "Jane Austen", 1813, "ISBN-PP");

        library.addBook(bookWithThe1);
        library.addBook(bookWithThe2);
        library.addBook(bookWithoutThe);

        // Search for books with "The" in the title
        Book[] foundBooks = library.findBookByTitle("The");

        // Check results
        assertEquals(2, foundBooks.length, "Should find exactly two books");

        // Check output
        assertTrue(outContent.toString().contains("Found 2 book(s) with title containing: The"),
                "Should print message with count of books found");
    }

    @Test
    @DisplayName("Test finding books by title (case insensitivity)")
    void testFindBookByCaseInsensitiveTitle() {
        // Add book
        library.addBook(book1);  // "To Kill a Mockingbird"

        // Search with different case
        Book[] foundBooks = library.findBookByTitle("mockingbird");

        // Check results
        assertEquals(1, foundBooks.length, "Should find exactly one book despite case difference");
        assertEquals(book1.getTitle(), foundBooks[0].getTitle(), "Found book should match the expected one");
    }

    @Test
    @DisplayName("Test finding books when no match exists")
    void testFindBookNoMatch() {
        // Add books
        library.addBook(book1);
        library.addBook(book2);

        // Search for non-existent title
        Book[] foundBooks = library.findBookByTitle("Nonexistent Title");

        // Check results
        assertEquals(0, foundBooks.length, "Should return empty array when no books match");

        // Check output
        assertTrue(outContent.toString().contains("No books") && outContent.toString().contains("Nonexistent Title"),
                "Should print message indicating no books found");
    }

    @Test
    @DisplayName("Test comprehensive library operations")
    void testComprehensiveOperations() {
        // 1. Start with empty library
        assertEquals(0, library.getBookCount(), "New library should be empty");

        // 2. Add books
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        assertEquals(3, library.getBookCount(), "Library should contain 3 books");

        // 3. Remove a book
        library.removeBook(book2.getIsbn());
        assertEquals(2, library.getBookCount(), "Library should contain 2 books after removal");

        // 4. Find remaining books
        Book[] found = library.findBookByTitle("To Kill");
        assertEquals(1, found.length, "Should find one book with 'To Kill' in title");

        // 5. Try to remove already removed book
        assertFalse(library.removeBook(book2.getIsbn()), "Should return false when removing already removed book");

        // 6. Add the book back
        library.addBook(book2);
        assertEquals(3, library.getBookCount(), "Library should contain 3 books after re-adding");
    }
}
