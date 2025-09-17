package BDDMokitoPractice;

public final class FinalMethodPractice {

    // final method -> cannot be overridden
    public final String processPayment(int amount) {
        if (amount > 0) {
            return "Payment of " + amount + " processed";
        }
        return "Invalid amount";
    }

}
