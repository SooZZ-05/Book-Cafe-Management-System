package testing;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolderName, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!isValidVisaCard(cardNumber)) {
            System.out.println("Payment failed. Invalid Visa card number.");
            return false;
        }

        if (!isValidCvv(cvv)) {
            System.out.println("Payment failed. Invalid CVV.");
            return false;
        }

        if (!isValidExpirationDate(expirationDate)) {
            System.out.println("Payment failed. Card is expired or expiration date is invalid.");
            return false;
        }
        Main.clearScr();
        System.out.println("Processing payment of RM" + amount + " via Visa Credit Card...");
        return true;
    }
    
    private boolean isValidVisaCard(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return false;
        }

        if (!cardNumber.startsWith("4")) {
            return false;
        }

        int length = cardNumber.length();
        if (length != 13 && length != 16 && length != 19) {
            return false;
        }

        return isValidLuhn(cardNumber);
    }
    
    private boolean isValidLuhn(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
    
    private boolean isValidCvv(String cvv) {
        if (cvv == null || !cvv.matches("\\d{3}")) {
            return false;
        }
        return true;
    }

    private boolean isValidExpirationDate(String expirationDate) {
        if (expirationDate == null || !expirationDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            return false;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expiration = YearMonth.parse(expirationDate, formatter);
            YearMonth current = YearMonth.now();

            return !expiration.isBefore(current);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return cardNumber;
    }
}