package org.ezedev.junitapp.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookManagerTest {

    @Test
    void testBookListIsNotNullAfterCreation() {
        BookManager bookManager = new BookManager();
        assertNotNull(bookManager.getBooks());
    }

    @Test
    void testBookListSizeAfterAddingBooks() {
        BookManager bookManager = new BookManager();
        bookManager.addBook("Book 1");
        bookManager.addBook("Book 2");
        bookManager.addBook("Book 3");

        assertEquals(3, bookManager.getBooks().size(),
                "The size of the book list should be 3 after adding three books.");
    }

    @Test
    void testBookAtSpecificPosition() {
        BookManager bookManager = new BookManager();
        bookManager.addBook("Book 1");
        bookManager.addBook("Book 2");
        bookManager.addBook("Book 3");

        Book bookAtPosition2 = bookManager.getBookAtPosition(1); // Índice 1 equivale a la posición 2.

        assertNotNull(bookAtPosition2, "The book at position 2 should not be null.");
        assertEquals("Book 2", bookAtPosition2.getName(),
                "The book at position 2 should have the name 'Book 2'.");
    }

    @Test
    void testGetBookAtSpecificPosition() {
        BookManager bookManager = new BookManager();
        bookManager.addBook("Book 1");
        bookManager.addBook("Book 2");
        bookManager.addBook("Book 3");

        Book bookAtPosition1 = bookManager.getBookAtPosition(1);
        Book bookAtPosition2 = bookManager.getBookAtPosition(2);
        Book bookInvalid = bookManager.getBookAtPosition(5);

        assertNotNull(bookAtPosition1, "The book at position 1 should not be null.");
        assertEquals("Book 2", bookAtPosition1.getName(), "The book at position 1 should be 'Book 2'.");

        assertNotNull(bookAtPosition2, "The book at position 2 should not be null.");
        assertEquals("Book 3", bookAtPosition2.getName(), "The book at position 2 should be 'Book 3'.");

        assertNull(bookInvalid, "The book at an invalid position should be null.");
    }

    @Test
    void testAddBookUpdatesList() {
        BookManager bookManager = new BookManager();
        int initialSize = bookManager.getBooks().size();

        bookManager.addBook("New Book");

        assertEquals(initialSize + 1, bookManager.getBooks().size(),
                "The size of the list should increase by 1 after adding a book.");
        assertTrue(bookManager.getBooks().stream()
                        .anyMatch(book -> book.getName().equalsIgnoreCase("New Book")),
                "The list should contain the newly added book.");
    }

    @Test
    void testRemoveBookDecreasesListSize() {
        BookManager bookManager = new BookManager();
        bookManager.addBook("Book 1");
        bookManager.addBook("Book 2");
        bookManager.addBook("Book 3");
        int initialSize = bookManager.getBooks().size();

        bookManager.removeBookByTitle("Book 2");

        assertEquals(initialSize - 1, bookManager.getBooks().size(),
                "The size of the list should decrease by 1 after removing a book.");
        assertFalse(bookManager.getBooks().stream()
                        .anyMatch(book -> book.getName().equalsIgnoreCase("Book 2")),
                "The book 'Book 2' should no longer be in the list.");
    }

    @Test
    void testListRemainsAlphabeticallySorted() {
        BookManager bookManager = new BookManager();
        bookManager.addBook("C Book");
        bookManager.addBook("A Book");
        bookManager.addBook("B Book");


        bookManager.addBook("D Book");
        bookManager.removeBookByTitle("B Book");

        List<Book> sortedBooks = new ArrayList<>(bookManager.getBooks());
        sortedBooks.sort(Comparator.comparing(Book::getName, String.CASE_INSENSITIVE_ORDER));

        assertEquals(sortedBooks, bookManager.getBooks(),
                "The list should remain alphabetically sorted after adding or removing books.");
    }
}