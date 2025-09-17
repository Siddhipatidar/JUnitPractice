package PracticeUsingMokito;

public interface BankAccountRepo {
    BankAccount findById(int id);
    void updateAccount(BankAccount account);
}
