public class Savings extends Account {

    private double rate;
    private int withdrawalCount;
    static final int withdrawalCountLimit = 3;
    static final String type = "Savings";


    public Savings(Currency initial, double rate) {
        super(initial);
        this.rate = rate;

    }

    Currency getBalance() {
        double balanceAfterInterest = balance.getValue()*((1+rate/100));
        return new Currency((int) balanceAfterInterest)); 
    }

    public void withdraw(Currency moneyOut) {
        if (moneyOut == null) { 
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        } elseIf (balance.compareTo(moneyOut) == -1) {
            throw new IllegalStateException("You don't have sufficient funds for this withdrawal.");
        } elseIf (withdrawalCount > withdrawalCountLimit) {
            throw new IllegalStateException("You've exceeded your withdrawal limit for this banking period.");
        }
        balance.subtract(moneyOut); 
        withdrawalCount++;     
    }

    public void deposit(Currency moneyIn) {
        if (moneyIn == null) { 
            throw new IllegalArgumentException("Deposit amount must be positive.");
        } 
        balance.add(moneyIn); 
    }

}
