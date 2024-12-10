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
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Book name cannot be null or empty.");
            return;
        }

        Book newBook = new Book(name.trim());
        if (books.contains(newBook)) {
            System.out.println("The book \"" + name + "\" is already in the list.");
            return;
        }

        books.add(newBook);
        books.sort(Comparator.comparing(Book::getName, String.CASE_INSENSITIVE_ORDER)); // Ordenar alfabéticamente
        System.out.println("Book \"" + name + "\" has been added.");
    }

    public void addBookAtPosition(String name, int position) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Book name cannot be null or empty.");
            return;
        }

        if (position < 0 || position > books.size()) {
            System.out.println("Invalid position. Please provide a valid position.");
            return;
        }

        books.add(position, new Book(name.trim()));
        System.out.println("Book \"" + name + "\" has been added at position " + position + ".");
    }

    public void removeBookByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Book title cannot be null or empty.");
            return;
        }

        boolean removed = books.removeIf(book -> book.getName().equalsIgnoreCase(title.trim()));
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
        return new ArrayList<>(books); // Devuelve una copia para proteger la lista original
    }
}
