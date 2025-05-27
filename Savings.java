public class Savings extends Account {

    private double rate;
    static final String type = "Savings";

    public Savings(Currency initial, double rate) {
        super(initial);
        this.rate = rate;

    }

    Currency getBalance() {
        double tempBalance = balance.getValue()*rate;
        int newBalance = (int) tempBalance;
        balance = new Currency(newBalance);
        return balance;
    }


    public void withdraw(Currency money) {
        balance = balance.subtract(money);
    }

    public void deposit(Currency money) {
        balance = balance.add(money);
    }

}
