package testing;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }
    
    public List<Product> getProducts() {
        return products; // Add this method to return the list of products
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int index) {
        if (index >= 0 && index < products.size()) {
            Product removedProduct = products.remove(index);
            Main.clearScr();
            System.out.println("Removed " + removedProduct.getName() + " from the cart.");
        } else {
            System.out.println("Invalid index. Unable to remove item.");
        }
    }

    public void viewCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nCart:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).viewDetails());
            }
        }
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Your cart is empty.";
        } else {
            String output = "\nCart:";
            for (int i = 0; i < products.size(); i++) {
                output += ((i + 1) + ". " + products.get(i).viewDetails());
            }
            return output;
        }
    }
}