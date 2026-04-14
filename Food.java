package testing;

public class Food extends Product {
    private String description;

    public Food(String name, double price, String description) {
        super(name, price);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String viewDetails() {
        String price = String.format("%.2f",getPrice());
        return getName() + " - RM" + price + " (" + description + ")";
    }
    
    @Override
    public String toString() {
        String price = String.format("%.2f",getPrice());
        return getName() + " - RM" + price + " (" + description + ")";
    }
}