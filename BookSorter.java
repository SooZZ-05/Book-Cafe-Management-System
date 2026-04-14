package testing;

import java.util.Collections;
import java.util.List;

public class BookSorter {

    // Sort books by Name (Alphabetically)
    public void sortBooksByName(List<Book> books) {
        Collections.sort(books, (book1, book2) -> book1.getName().compareTo(book2.getName()));
        System.out.println("\nBooks sorted by Name:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString(i + 1));
        }
    }

    // Sort books by ISBN
    public void sortBooksByIsbn(List<Book> books) {
        Collections.sort(books, (book1, book2) -> book1.getIsbn().compareTo(book2.getIsbn()));
        System.out.println("\nBooks sorted by ISBN:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString(i + 1));
        }
    }

    // Sort books by Author
    public void sortBooksByAuthor(List<Book> books) {
        Collections.sort(books, (book1, book2) -> book1.getAuthor().compareTo(book2.getAuthor()));
        System.out.println("\nBooks sorted by Author:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString(i + 1));
        }
    }

    // Sort books by Rating (Descending)
    public void sortBooksByRating(List<Book> books) {
        Collections.sort(books, (book1, book2) -> Double.compare(book2.getRating(), book1.getRating()));
        System.out.println("\nBooks sorted by Rating (Descending):");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString(i + 1));
        }
    }

    // Sort books by Publication Year (Descending)
    public void sortBooksByPublicationYear(List<Book> books) {
        Collections.sort(books, (book1, book2) -> Integer.compare(book2.getPublicationYear(), book1.getPublicationYear()));
        System.out.println("\nBooks sorted by Publication Year (Descending):");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString(i + 1));
        }
    }
    
    public String toString(List<Book> books) {
        Collections.sort(books, (book1, book2) -> book1.getName().compareTo(book2.getName()));
        String output = "\nBooks sorted by Name:";
        for (int i = 0; i < books.size(); i++) {
            output += ((i + 1) + ". " + books.get(i).getName());
        }
        return output;
    }
}