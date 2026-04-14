package testing;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private Customer customer;
    private Cart cart;
    private PaymentMethod paymentMethod;
    private List<Product> orderList;
    private LocalDateTime orderDate;

    public Order(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
        this.orderList = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Cart getCart() {
        return cart;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public List<Product> getOrderList() {
        return orderList;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setOrderList(List<Product> orderList) {
        this.orderList = orderList;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    
    public void printOrderSummary() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\nOrder Date: " + orderDate.format(formatter));
        System.out.println("Order Summary for: " + customer.getName());
        if (orderList.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nItems:");
            System.out.println("--------------------------------------------------------------------");
            System.out.printf("%-48s %-10s %-10s\n", "Product Name", "Price", "Quantity");
            System.out.println("--------------------------------------------------------------------");
            for (Product product : orderList) {
                System.out.printf("%-48s RM%-8.2f %9s\n", product.getName(), product.getPrice(), "1"); 
            }
            System.out.println("--------------------------------------------------------------------");
        }
    }

    public double getTotalAmount() {
        return cart.calculateTotal();
    }

    public boolean processPayment(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        double amount = getTotalAmount();
        if (paymentMethod.processPayment(amount)) {
            System.out.println("Payment Successful! Total Paid: RM" + amount);
            return true;
        }
        return false;
    }
    
    public void updateOrderList() {
        orderList.addAll(cart.getProducts());
    }
    
    @Override
    public String toString() {
        return "To string";
    }
}