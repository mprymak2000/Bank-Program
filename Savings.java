public class Savings extends Account {

    private double rate;
    private int withdrawalCount = 0;
    private int withdrawalCountLimit = 3; //default 3 withdrawals max billing cycle
    static final String type = "Savings";


    public Savings(Currency initial, double rate) {
        super(initial);
        if (rate < 0) throw new IllegalArgumentException("Interest Rate cannot be negative.");
        this.rate = rate;

    }

    //overloaded constructor to change default number of withdrawals allowed per billing cycle
    public Savings(Currency initial, double rate, int withdrawalCountLimit) {
        this(initial, rate);
        if (withdrawalCountLimit < 0) throw new IllegalArgumentException("Withdrawal limit cannot be negative.");
        this.withdrawalCountLimit = withdrawalCountLimit;
        
    }

    //returns the account balance after applying earned interest (currency object).
    Currency getBalance() {
        double balanceAfterInterest = balance.getValue()*((1+rate/100));
        return new Currency((int) balanceAfterInterest); 
    }

    //method for subtracting currency with guard clauses for invalid account operations
    //guard clauses: money is null or bad business logic since you can't withdraw more than account balance or withdraw more than 3 times
    public void withdraw(Currency moneyOut) {
        if (moneyOut == null) throw new IllegalArgumentException("Withdrawal ammount cannot be null.");
        if (balance.compareTo(moneyOut) == -1) throw new IllegalArgumentException("Withdrawal ammount exceeds current account balance.");
        if (withdrawalCount >= withdrawalCountLimit) throw new IllegalArgumentException("Failed: Withdrawal limit for this billing cycle has been exceeded.");
        
        balance = balance.subtract(moneyOut); 
        withdrawalCount++;     
    }

    public void deposit(Currency moneyIn) {
        if (moneyIn == null) { 
            throw new IllegalArgumentException("Deposit cannot be null.");
        } 
        balance = balance.add(moneyIn); 
    }

}
