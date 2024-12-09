package org.ezedev.junitapp.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();

        try (Scanner consola = new Scanner(System.in)) {
            boolean exit = false;

            while (!exit) {
                showMenu();
                try {
                    exit = executeOption(consola, bookManager);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
                System.out.println();
            }
        }
    }

    private static void showMenu() {
        System.out.println("""
        =============================
        ** Book Manager **
        =============================
        1. Show all books
        2. Add a new book
        3. Add a book at a specific position
        4. Get a book by position
        5. Remove a book by title
        6. Exit
        =============================
        """);
        System.out.print("Choose an option: ");
    }

    private static boolean executeOption(Scanner consola, BookManager bookManager) {
        int option = Integer.parseInt(consola.nextLine());

        switch (option) {
            case 1 -> bookManager.showBooks();
            case 2 -> {
                System.out.print("Enter the name of the new book: ");
                String name = consola.nextLine();
                bookManager.addBook(name);
            }
            case 3 -> {
                System.out.print("Enter the name of the book: ");
                String name = consola.nextLine();
                System.out.print("Enter the position: ");
                int position = Integer.parseInt(consola.nextLine());
                bookManager.addBookAtPosition(name, position);
            }
            case 4 -> {
                System.out.print("Enter the position of the book: ");
                int position = Integer.parseInt(consola.nextLine());
                Book book = bookManager.getBookAtPosition(position);
                if (book != null) {
                    System.out.println("Book at position " + position + ": " + book.getName());
                }
            }
            case 5 -> {
                System.out.print("Enter the title of the book to remove: ");
                String title = consola.nextLine();
                bookManager.removeBookByTitle(title);
            }
            case 6 -> {
                System.out.println("Goodbye!");
                return true;
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
        return false;
    }
}
