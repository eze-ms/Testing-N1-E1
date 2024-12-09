package org.ezedev.junitapp.example;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testNoDuplicateBookTitles() {
        BookManager bookManager = new BookManager();
        bookManager.addBook("Book 1");
        bookManager.addBook("Book 2");
        bookManager.addBook("Book 3");
        bookManager.addBook("Book 2");

        Set<Book> uniqueBooks = new HashSet<>(bookManager.getBooks());

        assertEquals(3, uniqueBooks.size(),
                "The list of unique books should contain no duplicates.");
    }
}