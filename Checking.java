public class Checking extends Account {

    static final String type = "Checking";

    public Checking (Currency initialAmount)  {
        super(initialAmount);
    }

    public voild withdraw(Currency moneyOut) {
        if (moneyOut == null) { 
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        } elseIf (balance.compareTo(moneyOut) == -1) {
            throw new IllegalStateException("You do not have sufficient funds for this withdrawal.");
        } 
        balance.subtract(moneyOut); 
    }

    public void deposit(Currency moneyIn) {
        if (moneyIn == null) { 
            throw new IllegalArgumentException("Deposit amount must be positive.");
        } 
        balance.add(moneyIn); 
    }

    Currency getBalance() {
        return balance;
    }
}
