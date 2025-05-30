public class CD extends Account{

    private double rate;
    static final String type = "CD";
    static int monthsToMaturity = 24; //sample certificate of deposit term
    static int monthsPassed = 15;  //sample time since opening the account

    public CD (Currency initialAmount, double rate) {
        super(initialAmount);
        this.rate = rate;
    }

    Currency getBalance() {
        double balanceAfterInterest = balance.getValue()*((1+rate/100));
        return new Currency((int) balanceAfterInterest); 
    }

    public void withdraw(Currency moneyOut) {
        if (moneyOut == null) { 
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        } else if (monthsToMaturity > monthsPassed) {
            throw new IllegalStateException("Your deposit has not matured yet."); 
        }
        balance.subtract(moneyOut);  
    }

    public void deposit(Currency moneyIn) {
        throw new IllegalStateException("You cannot make additional deposits to this account");
    }

}