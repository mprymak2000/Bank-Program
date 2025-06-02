public class Checking extends Account {

    static final String type = "Checking";

    public Checking (Currency initialAmount)  {
        super(initialAmount);
    }

    public void withdraw(Currency moneyOut) {
        if (moneyOut == null) { 
            throw new IllegalArgumentException("An unexpected error occured.");
        } else if (balance.compareTo(moneyOut) == -1) {
            throw new IllegalArgumentException("You do not have sufficient funds for this withdrawal.");
        } 
        balance = balance.subtract(moneyOut); 
    }

    public void deposit(Currency moneyIn) {
        if (moneyIn == null) { 
            throw new IllegalArgumentException("Deposit amount must be positive.");
        } 
        balance = balance.add(moneyIn); 
    }

    Currency getBalance() {
        return balance;
    }
}
