public class Savings extends Account {

    private double rate;
    private int withdrawalCount=0;
    static final int withdrawalCountLimit = 3;
    static final String type = "Savings";


    public Savings(Currency initial, double rate) {
        super(initial);
        this.rate = rate;

    }

    Currency getBalance() {
        double balanceAfterInterest = balance.getValue()*((1+rate/100));
        return new Currency((int) balanceAfterInterest); 
    }

    public void withdraw(Currency moneyOut) {
        if (moneyOut == null) { 
            throw new IllegalArgumentException("An unexpected error occured.");
        } else if (balance.compareTo(moneyOut) == -1) {
            throw new IllegalArgumentException("You don't have sufficient funds for this withdrawal.");
        } else if (withdrawalCount >= withdrawalCountLimit) {
            throw new IllegalArgumentException("You've exceeded your withdrawal limit for this banking period.");
        }
        balance = balance.subtract(moneyOut); 
        withdrawalCount++;     
    }

    public void deposit(Currency moneyIn) {
        if (moneyIn == null) { 
            throw new IllegalArgumentException("Deposit amount must be positive.");
        } 
        balance = balance.add(moneyIn); 
    }

}
