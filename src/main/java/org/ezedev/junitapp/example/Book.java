package org.ezedev.junitapp.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    private final int id;
    private String name;
    private static int numberBook = 0;

    public Book(String name) {
        this.id = ++Book.numberBook;
        setName(name); // Para validar
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Book name cannot be null or empty.");
        }
        this.name = name.trim();
    }

    public static List<Book> createBooksList() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("book1"));
        books.add(new Book("book2"));

        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name.toLowerCase(), book.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}
