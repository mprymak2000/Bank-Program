public class CD extends Account{

    private double rate;
    static final String type = "CD";
    static int monthsToMaturity = 24; //sample certificate of deposit term
    static int monthsPassed = 15;  //sample time since opening the account

    
    public CD (Currency initialAmount, double rate) {
        super(initialAmount);
        this.rate = rate;
    }

    //returns the account balance after applying earned interest (currency object).
    Currency getBalance() {
        double balanceAfterInterest = balance.getValue()*((1+rate/100));
        return new Currency((int) balanceAfterInterest); 
    }

    //method for subtracting currency with guard clauses for invalid account operations
    //guard clauses: money is null or bad business logic since you can't withdraw from CD until maturity or withdraw more than account balance
    void withdraw(Currency moneyOut) {
        if (moneyOut == null) throw new IllegalArgumentException("Withdrawal ammount cannot be null");
        if (!isMature()) throw new IllegalStateException("CD account not at maturity for withdrawal"); 
        if (balance.compareTo(moneyOut) == -1) throw new IllegalArgumentException("Withdrawal ammount cannot exceed current balance.");

        balance = balance.subtract(moneyOut);  
    }

    //unsupported op: Can't deposit into a CD account after initial deposit
    void deposit(Currency moneyIn) {
        throw new UnsupportedOperationException("Cannot deposit more to CD account.");
    }

    boolean isMature() {
        if (monthsPassed < monthsToMaturity)
            return false;
        else return true;
    }

}