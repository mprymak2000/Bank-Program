public class CD extends Account{

    private double rate;
    static final String type = "CD";
    static int termMonths = 24; //sample certificate of deposit term
    static int months = 15;  //sample time since opening the account

    public CD (Currency initialAmount, double rate) {
        super(initialAmount);
        this.rate = rate;
    }

    public void withdraw(Currency money)  {
        if (months > termMonths)
            balance = balance.subtract(money);
    }

    public void deposit(Currency money) {
        balance = balance.add(money);
    }

    Currency getBalance() {
        double tempBalance = balance.getValue()*rate;
        int newBalance = (int) tempBalance;
        balance = new Currency(newBalance);
        return balance;
    }
}