package PracticeUsingMokito;

public class BankService {
    private BankAccountRepo dao;

    public BankService(BankAccountRepo dao) {
        this.dao = dao;
    }

    public double deposit(int accountId, double amount) {
        if (amount <= 0)  throw new IllegalArgumentException("Deposit amount must be greater than zero");
        BankAccount account = dao.findById(accountId);
        if (account == null) throw new RuntimeException("Account not found");
        account.setBalance(account.getBalance() + amount);
        dao.updateAccount(account);
        return account.getBalance();
    }

    public double withdraw(int accountId, double amount) {
        if (amount <= 0)  throw new IllegalArgumentException("Deposit amount must be greater than zero");
        BankAccount account = dao.findById(accountId);
        if (account == null) throw new RuntimeException("Account not found");
        if (account.getBalance() < amount) throw new RuntimeException("Insufficient funds");
        if (amount > 5000) {
            throw new IllegalArgumentException("Withdrawal limit exceeded (max 5000 per transaction)");
        }
        account.setBalance(account.getBalance() - amount);
        dao.updateAccount(account);
        return account.getBalance();
    }
}
