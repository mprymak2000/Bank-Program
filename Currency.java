public class Currency implements Comparable<Currency>{
    
    private class InvalidCurrencyException extends IllegalArgumentException {
    
    private String userMessage;

    public InvalidCurrencyException(String devMessage, String userMessage) {
        super(devMessage);
        this.userMessage = userMessage;
    }
    public InvalidCurrencyException(String devMessage) {
        super(devMessage);
    }
    public protected String getUserMessage() {
        return userMessage;
    }
}
    
    private int cents;

    public Currency() {}

    //constructor that makes sure money amount is positive
    public Currency(int cents) {
        if (cents <= 0) 
            throw new InvalidCurrencyException("Currency must be greater than 0", "Please input an amount greater than 0.");
        this.cents = cents;
    }

    //reteurns money amount in cents
    public int getValue() {
        return cents;
    }

    //immutable members. returns sum as new currency.
    public Currency add (Currency rhs) {
        if (rhs == null) throw new InvalidCurrencyException("Currency added cannot be null");
        return new Currency(cents + rhs.cents);
    }

    //immutable members. returns difference as new currency.
    public Currency subtract (Currency rhs) {
        if (rhs == null) throw new InvalidCurrencyException("Currency added cannot be null");
        if (cents - rhs.cents < 0) throw new InvalidCurrencyException("Resulting currency cannot be less than 0");
        return new Currency(cents - rhs.cents);
    }

    //friendly formatting for returning money amount to user
    public String toString() {
        return "$" + cents/100;
    }

    //overriding the compareTo() method for the interface
    @Override
    public int compareTo(Currency other) {
        if (other.cents > cents)
            return -1;
        else if (other.cents == cents)
            return 0;
        else return 1;
    }
}