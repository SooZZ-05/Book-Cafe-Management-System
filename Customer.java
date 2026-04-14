package testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private static List<Customer> customers = new ArrayList<>();
    private String name;
    private String email;
    private String password;
    private Cart cart;
    private boolean isMember = false;
    private List<Order> orderHistory;

    public Customer() {
    }
    
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    public Cart getCart() {
        return cart;
    }
    
    public boolean isMember() {
        return isMember;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public static Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.email.equals(email)) {
                return customer;  // Return the matching customer object
            }
        }
        return null;  // If no customer is found, return null
    }

    public static void setCustomers(List<Customer> customers) {
        Customer.customers = customers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setMember() {
        this.isMember = true;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void register() {
        String name;
        String email;
        String password;
        String confirmPassword;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = scanner.nextLine();
        Customer testCustomer = null;
        do {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            testCustomer = getCustomerByEmail(email);
            if (testCustomer != null) {
                System.out.println("Email have been registered before!");
            }
            if (email.matches("^[a-z0-9]+@(gmail|yahoo)\\.com$") == false) {
                System.out.println("Invalid email! Email should not contain special characters or capital letters, and should end with @gmail.com or @yahoo.com.");
            }
        } while (email.matches("^[a-z0-9]+@(gmail|yahoo)\\.com$") == false || testCustomer != null);
        System.out.print("Enter password: ");
        password = scanner.nextLine();
        do {
            System.out.print("Confirm password: ");
            confirmPassword = scanner.nextLine();
            if (password.equals(confirmPassword) == false) {
                System.out.println("Password does not match!");
            }
        } while (password.equals(confirmPassword) == false);
        this.name = name;
        this.email = email;
        this.password = password;
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();
        customers.add(this);
        Main.clearScr();
        System.out.println("Account has been registered successfully!");
    }
    
    public static Customer login() {
        Scanner scanner = new Scanner(System.in);
        String email;
        Customer customer;
        String password;
        int attempts = 0;
        System.out.print("Email: ");
        email = scanner.nextLine();
        customer = getCustomerByEmail(email);
        if (customer == null) {
            Main.clearScr();
            System.out.println("Invalid email!");
            return null;
        }
        do {
            System.out.print("Password: ");
            password = scanner.nextLine();
            attempts++;
            if (customer.getPassword().equals(password) == false) {
                System.out.println("Password is incorrect!");
            }
        } while (customer.getPassword().equals(password) == false && attempts < 3) ;
        if (customer.getPassword().equals(password) == true) {
            Main.clearScr();
            System.out.println("Welcome " + customer.getName());
            return customer;
        } else {
            Main.clearScr();
            System.out.println("Maximum attempts reached!");
            return null;
        }
    }
    
    public Customer forgotPassword() {
        Scanner scanner = new Scanner(System.in);
        String email;
        Customer customer;
        System.out.print("Enter your Email: ");
        email = scanner.nextLine();
        customer = getCustomerByEmail(email);
        if (customer == null) {
            Main.clearScr();
            System.out.println("Email does not exists");
        }
        return customer;
    }
    
    public void changePassword() {
        String password;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter new password: ");
            password = scanner.nextLine();
            if (password.equals(this.password)) {
                System.out.println("New password cannot be old password!");
            }
        } while(password.equals(this.password));
        System.out.println("Password has been changed");
        this.password = password;
    }
    
    public void checkProfile() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
    
    public void addToOrderHistory(Order order) {
        orderHistory.add(order);
    }
    
    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No previous orders.");
        } else {
            System.out.println("\n--- Order History ---");
            for (Order order : orderHistory) {
                order.printOrderSummary();
            }
        }
    }
    
    public double applyDiscount(double amount) {
        return amount * 0.95; // 5% discount
    }
    
    @Override
    public String toString() {
        return "Name: " + name + "Email: " + email;
    }
}