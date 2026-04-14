package testing;

public class TNGPayment implements PaymentMethod {
    private String phoneNumber;
    private String password;

    public TNGPayment(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!isValidPhoneNumber(phoneNumber)) {
            Main.clearScr();
            System.out.println("Payment failed. Invalid phone number format.");
            return false;
        }
        Main.clearScr();
        System.out.println("Processing payment of RM" + amount + " via Touch 'n Go eWallet...");
        if (password.length() == 6) { 
            System.out.println("Payment verified with phone number: " + phoneNumber);
            return true;
        }
        System.out.println("Payment failed. Invalid password.");
        return false;
    }
    
    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.startsWith("01")) {
            return false;
        }
        if (phoneNumber.length() == 11 && phoneNumber.charAt(2) == '1') {
            return true;
        } else if (phoneNumber.length() == 10 && "023456789".contains(String.valueOf(phoneNumber.charAt(2)))) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return phoneNumber;
    }
}