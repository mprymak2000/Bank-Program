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
        double tempBalance = balance.getValue()*(1+(rate/100));
        int balanceAfterInterest = (int) tempBalance;
        balance = new Currency(balanceAfterInterest);
        return balance;
    }


    public void withdraw(Currency money) {
        balance = balance.subtract(money);
    }

    public void deposit(Currency money) {
        balance = balance.add(money);
    }

}
