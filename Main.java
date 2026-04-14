package testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Art of 64-bit Assembly", 80.0, 10, "Randall Hyde", "9781234567890", 2022, "Advanced Assembly techniques", 4.2, true));
        books.add(new Book("Junior Level Books Introduction to Computer", 60.0, 10, "Amit Garg", "9781234567891", 2021, "Introduction to Computer Science", 4.0, false));
        books.add(new Book("Publish News Letter", 50.0, 10, "Amit Garg", "9781234567892", 2019, "How to publish news and newsletters", 4.3, false));
        books.add(new Book("Client Server Computing", 90.0, 10, "Sebastian Tan", "9781234567893", 2020, "Client-Server architecture and applications", 4.8, true));
        books.add(new Book("Mobile Computing", 75.0, 10, "Vinay Kumar Singhal", "9781234567894", 2021, "Exploring mobile computing technologies", 4.6, true));
        books.add(new Book("Data Structure Using C++", 65.0, 10, "Steve Lee", "9781234567895", 2021, "Data structures with C++ examples", 4.2, false));
        books.add(new Book("Computer Network", 85.0, 10, "David Gan", "9781234567896", 2020, "Fundamentals of computer networking", 4.7, true));
        books.add(new Book("Fundamental of Operating System", 70.0, 10, "Soo ZZ", "9781234567897", 2021, "Basic concepts of operating systems", 4.4, false));
        
        List<Food> foodItems = new ArrayList<>();
        foodItems.add(new Food("Coffee", 10.00, "Hot black coffee"));
        foodItems.add(new Food("Cake", 15.00, "Chocolate cake slice"));
        foodItems.add(new Food("Sandwich", 12.50, "Cheese and ham sandwich"));
        foodItems.add(new Food("Burger", 20.00, "Beef burger with fries"));
        foodItems.add(new Food("Pasta", 18.00, "Creamy Alfredo pasta"));
        
        Scanner scanner = new Scanner(System.in);
        boolean exitProg = false;
        while(exitProg == false) {
            boolean continueInput = true;
            int loginOption = 0;
            do {
                try{
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    System.out.println("3. Forgot password");
                    System.out.println("4. Exit");
                    System.out.print("Choose an option: ");
                    loginOption = scanner.nextInt();
                    continueInput= false;
                }
                catch (Exception ex) {
                    clearScr();
                    System.out.println("Invalid option. Please try again.");
                    scanner.nextLine();
                }
            } while(continueInput);
            switch (loginOption) {
                case 1: 
                    Customer customer = new Customer();
                    customer = customer.login();
                    if (customer == null) {
                    } else {
                        menu(books, foodItems, customer);
                    }
                    break;
                case 2:
                    Customer customer1 = new Customer();
                    customer1.register();
                    break;
                case 3:
                    Customer customer2 = new Customer();
                    customer2 = customer2.forgotPassword();
                    if (customer2 != null) {
                        customer2.changePassword();
                    }
                    break;
                case 4:
                    exitProg = true;
                    break;
                default:
                    clearScr();
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    public static void menu(List bookss, List foodItemss, Customer customer) {
        Scanner scanner = new Scanner(System.in);
        
        // Create BookSorter object to sort and display books
        BookSorter sorter = new BookSorter();
        
        List<Book> books = bookss;
        List<Food> foodItems = foodItemss;
        Cart cart = new Cart();
        List<Book> purchasedBooks = new ArrayList<>();
        boolean exit = false;

        while (!exit) {
            boolean continueInput = true;
            int choice = 0;
            do {
                try {
                    System.out.println("\n--- MENU ---");
                    System.out.println("1. Search and Sort Books");
                    System.out.println("2. View Food Items");
                    System.out.println("3. Add to Cart");
                    System.out.println("4. Manage Cart (View, Remove, Proceed to Payment)");
                    System.out.println("5. View Purchase History");
                    System.out.println("6. Log out");
                    System.out.print("Choose an option: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    continueInput = false;
                }
                catch(Exception ex) {
                    clearScr();
                    System.out.println("Invalid option. Please try again.");
                    scanner.nextLine();
                }
            } while(continueInput);
            
            switch (choice) {
                case 1:
                    boolean continueSearchSort = true;
                    int searchSortChoice = 0;
                    do {
                        try {
                            System.out.println("\n--- Search and Sort Books ---");
                            System.out.println("1. Search Books");
                            System.out.println("2. Sort Books");
                            System.out.print("Choose an option: ");
                            searchSortChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            continueSearchSort = false;
                        }
                        catch(Exception ex) {
                            clearScr();
                            System.out.println("Invalid option!");
                            scanner.nextLine();
                        }
                    } while(continueSearchSort);

                    if (searchSortChoice == 1) {
                        // Search Books
                        System.out.print("\nEnter book title to search: ");
                        String searchTitle = scanner.nextLine();
                        List<Book> matchingBooks = new ArrayList<>();
                        for (Book book : books) {
                            if (book.getName().toLowerCase().contains(searchTitle.toLowerCase())) {
                                matchingBooks.add(book);
                            }
                        }

                        if (matchingBooks.isEmpty()) {
                            System.out.println("No books found with the title: " + searchTitle);
                        } else {
                            System.out.println("\nBooks matching your search:");
                            for (int i = 0; i < matchingBooks.size(); i++) {
                                System.out.println((i + 1) + ". " + matchingBooks.get(i).getName());
                            }

                            // Select a book to view details
                            boolean continueSearch = true;
                            int bookChoice = 0;
                            do {
                                try {
                                    System.out.print("\nEnter the number of the book to view details: ");
                                    bookChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    continueSearch = false;
                                } catch (Exception ex) {
                                    System.out.println("Invalid option!");
                                    scanner.nextLine();
                                }
                            } while (continueSearch);

                            if (bookChoice > 0 && bookChoice <= matchingBooks.size()) {
                                Book selectedBook = matchingBooks.get(bookChoice - 1);
                                clearScr();
                                System.out.println("\nDetails of the selected book:");
                                System.out.println(selectedBook.viewDetails());
                                selectedBook.viewComments();
                            } else {
                                clearScr();
                                System.out.println("Invalid choice.");
                            }
                        }
                    } else if (searchSortChoice == 2) {
                        // Sort Books
                        boolean continueSort = true;
                        int sortChoice = 0;
                        do {
                            try {
                                System.out.println("Choose sorting criteria:");
                                System.out.println("1. Sort by Name");
                                System.out.println("2. Sort by ISBN");
                                System.out.println("3. Sort by Author");
                                System.out.println("4. Sort by Publication Year");
                                System.out.println("5. Sort by Rating");
                                System.out.print("Enter your choice: ");
                                sortChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                continueSort = false;
                            } catch (Exception ex) {
                                clearScr();
                                System.out.println("Invalid option!");
                                scanner.nextLine();
                            }
                        } while (continueSort);

                        switch (sortChoice) {
                            case 1:
                                clearScr();
                                System.out.println("Sorting books by Name...");
                                sorter.sortBooksByName(books);
                                break;
                            case 2:
                                clearScr();
                                System.out.println("Sorting books by ISBN...");
                                sorter.sortBooksByIsbn(books);
                                break;
                            case 3:
                                clearScr();
                                System.out.println("Sorting books by Author...");
                                sorter.sortBooksByAuthor(books);
                                break;
                            case 4:
                                clearScr();
                                System.out.println("Sorting books by Publication Year...");
                                sorter.sortBooksByPublicationYear(books);
                                break;
                            case 5:
                                clearScr();
                                System.out.println("Sorting books by Rating...");
                                sorter.sortBooksByRating(books);
                                break;
                            default:
                                clearScr();
                                System.out.println("Invalid choice.");
                                continueSort = false;
                        }

                        // View sorted book details
                        if (!books.isEmpty() && sortChoice >= 1 && sortChoice <= 5) {
                            boolean continueView = true;
                            int bookChoice = 0;
                            do {
                                try {
                                    System.out.print("\nEnter the number of the book to view details: ");
                                    bookChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    continueView = false;
                                } catch (Exception ex) {
                                    System.out.println("Invalid option!");
                                    scanner.nextLine();
                                }
                            } while (continueView);

                            if (bookChoice > 0 && bookChoice <= books.size()) {
                                Book selectedBook = books.get(bookChoice - 1);
                                clearScr();
                                System.out.println("\nDetails of the selected book:");
                                System.out.println(selectedBook.viewDetails());
                            } else {
                                clearScr();
                                System.out.println("Invalid choice.");
                            }
                        }
                    } else {
                        clearScr();
                        System.out.println("Invalid option.");
                    }
                    break;

                case 2:
                    // View food items
                    clearScr();
                    System.out.println("\nAvailable Food Items:");
                    for (Food food : foodItems) {
                        System.out.println("- " + food.viewDetails());
                    }
                    break;

                case 3:
                    // Add items to cart
                    boolean continueAdd = true;
                    int addChoice = 0;
                    do {
                        try {
                            System.out.println("\n1. Add Book");
                            System.out.println("2. Add Food");
                            System.out.println("3. Back to Menu");
                            System.out.print("Choose an option: ");
                            addChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            continueAdd = false;
                        }
                        catch (Exception ex) {
                            clearScr();
                            System.out.println("Invalid choice.");
                            scanner.nextLine();
                        }
                    } while(continueAdd);
                    

                    if (addChoice == 1) {
                        boolean continueAddBook = true;
                        int bookNumber = 0;
                        do {
                            try {
                                System.out.println("\nAvailable Books:");
                                for (int i = 0; i < books.size(); i++) {
                                    System.out.println((i + 1) + ". " + books.get(i).getName());
                                }
                                System.out.print("\nEnter the number of the book to add to the cart: ");
                                bookNumber = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                continueAddBook = false;
                            }
                            catch(Exception ex) {
                                clearScr();
                                System.out.println("Invalid option!");
                                scanner.nextLine();
                            }
                        } while(continueAddBook);
                        

                        if (bookNumber > 0 && bookNumber <= books.size()) {
                            cart.addProduct(books.get(bookNumber - 1));
                            clearScr();
                            System.out.println("Book added to cart.");
                        } else {
                            clearScr();
                            System.out.println("Invalid choice.");
                        }
                    } else if (addChoice == 2) {
                        boolean continueAddFood = true;
                        int foodNumber = 0;
                        do {
                            try {
                                System.out.println("\nAvailable Food:");
                                for (int i = 0; i < foodItems.size(); i++) {
                                    System.out.printf("%d. %s - RM%.2f%n", (i + 1), foodItems.get(i).getName(), foodItems.get(i).getPrice());
                                }
                                System.out.print("\nEnter the number of the food item to add to the cart: ");
                                foodNumber = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                continueAddFood = false;
                            }
                            catch(Exception ex) {
                                clearScr();
                                System.out.println("Invalid option!");
                                scanner.nextLine();
                            }
                        } while(continueAddFood);
                        

                        if (foodNumber > 0 && foodNumber <= foodItems.size()) {
                            cart.addProduct(foodItems.get(foodNumber - 1));
                            clearScr();
                            System.out.println("Food item added to cart.");
                        } else {
                            clearScr();
                            System.out.println("Invalid choice.");
                        }
                    } else if (addChoice == 3) {
                        clearScr();
                    } else {
                        clearScr();
                        System.out.println("Invalid option.");
                    }
                    break;
                case 4:
                    // View cart
                    System.out.println("\n--- Manage Cart ---");
                    cart.viewCart();
                    
                    if (!cart.isEmpty()) {
                        boolean continueManageCart = true;
                        int cartChoice = 0;
                        do {
                            try {
                                System.out.println("\n1. Remove Item");
                                System.out.println("2. Proceed to Payment");
                                System.out.println("3. Back to Menu");
                                System.out.print("Choose an option: ");
                                cartChoice = scanner.nextInt();
                                scanner.nextLine();
                                continueManageCart = false;
                            }
                            catch(Exception ex) {
                                System.out.println("Invalid option!");
                                scanner.nextLine();
                            }
                        } while (continueManageCart);
                        
                        if (cartChoice == 1){
                            
                            boolean continueRemove = true;
                            int itemIndex = -1;
                            do {
                                try {
                                    System.out.print("\nEnter the number of the item to remove: ");
                                    itemIndex = scanner.nextInt() - 1;
                                    scanner.nextLine();
                                    continueRemove = false;
                                }
                                catch(Exception ex) {
                                    System.out.println("Invalid option!");
                                    scanner.nextLine();
                                }
                            } while(continueRemove);
                            cart.removeProduct(itemIndex);
                    
                        } else if (cartChoice == 2){
                            boolean chkmember = false;
                            double total = cart.calculateTotal();
                            if (!customer.isMember()) {
                                System.out.println("Would you like to join as a member for RM10? (yes/no)");
                                String membershipChoice = scanner.nextLine();
                                if (membershipChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("RM10 membership fee added.");
                                    total += 10.00;
                                    chkmember = true;
                                }
                            }
                        
                            if (customer.isMember()) {
                                total = customer.applyDiscount(total);
                                System.out.println("5% discount applied. New total: RM" + total);
                            } else {
                                System.out.println("Total: RM" + total);
                            }

                            boolean continuePayment = true;
                            int paymentChoice = 0;
                            do {
                                try {
                                    System.out.println("Choose a payment method:");
                                    System.out.println("1. Credit Card");
                                    System.out.println("2. Touch 'n Go eWallet");
                                    System.out.print("Enter your choice: ");
                                    paymentChoice = scanner.nextInt();
                                    scanner.nextLine(); 
                                    continuePayment = false;
                                }
                                catch(Exception ex) {
                                    clearScr();
                                    System.out.println("Invalid option!");
                                    scanner.nextLine();
                                }
                            } while(continuePayment);
                        
                            PaymentMethod paymentMethod = null;

                            if (paymentChoice == 1) {
                                System.out.print("Enter Credit Card Number: ");
                                String cardNumber = scanner.nextLine();
                                System.out.print("Enter Cardholder Name: ");
                                String cardHolderName = scanner.nextLine();
                                System.out.print("Enter Expiration Date (MM/YY): ");
                                String expirationDate = scanner.nextLine();
                                System.out.print("Enter CVV: ");
                                String cvv = scanner.nextLine();

                                paymentMethod = new CreditCardPayment(cardNumber, cardHolderName, expirationDate, cvv);
                            } else if (paymentChoice == 2) {
                                System.out.print("Enter Phone Number (without '-'): ");
                                String phoneNumber = scanner.nextLine();
                                System.out.print("Enter 6-Digit TNG Password: ");
                                String password = scanner.nextLine();

                                paymentMethod = new TNGPayment(phoneNumber, password);
                            } else {
                                System.out.println("Invalid payment method selected.");
                                break;
                            }

                            if (paymentMethod.processPayment(total)) {
                                System.out.println("Payment successful!");
                                Order order = new Order(customer, cart);
                                order.updateOrderList();
                                customer.addToOrderHistory(order);
                                for (Product product : cart.getProducts()) {
                                    if (product instanceof Book) {
                                        Book purchasedBook = (Book) product;
                                        purchasedBooks.add(purchasedBook);

                                        // Prompt for rating and comment
                                        System.out.print("\nEnter your rating for the book \"" + purchasedBook.getName() + "\" (1 to 5): ");
                                        double rating = scanner.nextDouble();
                                        scanner.nextLine(); // Consume newline
                                        System.out.print("Enter your comment for the book: ");
                                        String comment = scanner.nextLine();
                                        purchasedBook.addRating(customer.getName(), rating, comment);
                                    }
                                }

                                cart.clear();
                                System.out.println("Thank you for your purchase!");
                                if(chkmember) {
                                    customer.setMember();        
                                }
                            } else {
                                System.out.println("Payment failed. Please try again.");

                            }
                        } else if (cartChoice == 3) {
                            clearScr();
                        } else {
                            clearScr();
                            System.out.println("Invalid Option");
                        }
                        
                    } else {
                        System.out.println("\nYour cart is empty. Please add items to proceed.");
                    }    
                    break;

                case 5:
                    clearScr();
                    customer.viewOrderHistory();
                    break;

                case 6:
                    exit = true;
                    clearScr();
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    clearScr();
                    System.out.println("Invalid option.");
            }
        }
    }
    
    public static void clearScr() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
