package testing;

import java.util.ArrayList;
import java.util.List;

public class Book extends Product implements Comparable<Book> {
    private String name;
    private String author;
    private String isbn;
    private int publicationYear;
    private String description;
    private double rating;
    private boolean isBestSeller;
    private int stock;
    private List<String> comments;
    private List<Double> ratings; // List to store individual ratings

    public Book(String name, double price, int stock, String author, String isbn, int publicationYear, String description, double rating, boolean isBestSeller) {
        super(name, price);
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.description = description;
        this.rating = rating;
        this.isBestSeller = isBestSeller;
        this.stock = stock;
        this.comments = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }

    public String getDescription() {
        return description;
    }
    
    public double getRating() {
        return rating;
    }
    
    public boolean isIsBestSeller() {
        return isBestSeller;
    }
    
    public int getStock() {
        return stock;
    }
    
    public List<String> getComments() {
        return comments;
    }
    
    public List<Double> getRatings() {
        return ratings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setIsBestSeller(boolean isBestSeller) {
        this.isBestSeller = isBestSeller;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }
    
    @Override
    public String viewDetails() {
        return getName() + " by " + author + " (ISBN: " + isbn + "), RM" + getPrice() + ", Rating: " + rating + " stars\nDescription: " + description;
    }

    public void addRating(String customerName, double newRating, String comment) {
        // Add the rating and comment
        ratings.add(newRating);
        comments.add(comment);
        // Calculate new average rating
        double totalRating = ratings.stream().mapToDouble(Double::doubleValue).sum();
        this.rating = totalRating / ratings.size();
        System.out.println("Rating added: " + newRating + " stars. Comment: " + comment);
    }

    public void viewComments() {
        System.out.println("\nComments for " + getName() + ":");
        if (comments.isEmpty()) {
            System.out.println("No comments yet.");
        } else {
            for (String comment : comments) {
                System.out.println("- " + comment);
            }
        }
    }

    public void reduceStock() {
        if (stock > 0) {
            stock--;
        } else {
            System.out.println("Out of stock!");
        }
    }

    // Implement the Comparable interface's compareTo method for sorting by name
    @Override
    public int compareTo(Book other) {
        return this.getName().compareTo(other.getName());
    }

    public String toString(int index) {
        return String.format("%d. %s by %s (ISBN: %s), Price: RM%.2f, Rating: %.1f stars, Published: %d", index, getName(), author, isbn, getPrice(), rating, publicationYear);
    }
}
