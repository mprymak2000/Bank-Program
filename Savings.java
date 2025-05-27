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
        double interest = balance.getValue()*(rate/100);
        balance.add(new Currency((int) interest)); 
        return balance;
    }

    public void withdraw(Currency moneyOut) {
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

    public void withdraw(Currency money) {
        balance = balance.subtract(money);
    }

    public void deposit(Currency money) {
        balance = balance.add(money);
    }

}
