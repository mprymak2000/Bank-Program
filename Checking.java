public class Checking extends Account {

    static final String type = "Checking";

    public Checking (Currency initialAmount)  {
        super(initialAmount);
    }
        
    Currency getBalance() {
        return balance;
    }

    //method for subtracting currency from account with guard clauses for invalid account operations
    //guard clauses: money is null or bad business logic prevent op since you can't withdraw more than account balance 
    void withdraw(Currency moneyOut) {
        if (moneyOut == null) throw new IllegalArgumentException("Withdrawal ammount cannot be null.");
        if (balance.compareTo(moneyOut) == -1) throw new IllegalArgumentException("Withdrawal ammount cannot exceed current balance");
        
        balance = balance.subtract(moneyOut); 
    }

    //method for adding currency to account with a guard clause for a null input.
    void deposit(Currency moneyIn) {
        if (moneyIn == null) throw new IllegalArgumentException("Deposit cannot be null");
        balance = balance.add(moneyIn); 
    }


}
