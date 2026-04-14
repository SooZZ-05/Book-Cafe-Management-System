package testing;

public class Member extends Customer {
    private boolean isMember;

    public Member(String name, String email, String password) {
        super(name, email, password);
        this.isMember = true;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }
    
    public double applyDiscount(double amount) {
        return amount * 0.95; // 5% discount
    }
    
    @Override
    public String toString() {
        if (isMember) {
            return "This is a member";
        } else {
            return "This is not a member";
        }
    }
}