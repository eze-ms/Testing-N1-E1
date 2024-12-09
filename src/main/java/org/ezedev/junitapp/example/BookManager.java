package org.ezedev.junitapp.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookManager {
    private final List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>();
    }

    public void addBook(String name) {
        Book newBook = new Book(name);

        // Evitar duplicados
        if (books.contains(newBook)) {
            System.out.println("The book \"" + name + "\" is already in the list.");
            return;
        }

        // Agregar el nuevo libro y ordenar la lista
        books.add(newBook);
        books.sort(Comparator.comparing(Book::getName, String.CASE_INSENSITIVE_ORDER)); // Mantener orden alfabético
        System.out.println("Book \"" + name + "\" has been added.");
    }

    public void addBookAtPosition(String name, int position) {
        if (position < 0 || position > books.size()) {
            System.out.println("Invalid position. Please provide a valid position.");
            return;
        }
        books.add(position, new Book(name));
        System.out.println("Book \"" + name + "\" has been added at position " + position + ".");
    }

    public void removeBookByTitle(String title) {
        boolean removed = books.removeIf(book -> book.getName().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Book \"" + title + "\" has been removed.");
        } else {
            System.out.println("No book found with the title \"" + title + "\".");
        }
    }

    public Book getBookAtPosition(int position) {
        if (position < 0 || position >= books.size()) {
            System.out.println("Invalid position.");
            return null;
        }
        return books.get(position);
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("The book list is empty.");
        } else {
            System.out.println("Books in the collection:");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i).getName());
            }
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
