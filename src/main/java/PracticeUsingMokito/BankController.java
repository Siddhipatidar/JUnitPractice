package PracticeUsingMokito;

public class BankController {
    private BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    public String depositMoney(int accountId, double amount) {
        double balance = service.deposit(accountId, amount);
        return "Deposit successful! New Balance: " + balance;
    }

    public String withdrawMoney(int accountId, double amount) {
        double balance = service.withdraw(accountId, amount);
        return "Withdrawal successful! New Balance: " + balance;
    }
}
