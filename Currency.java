public class Currency implements Comparable<Currency>{
    private int cents;

    public Currency() {}

    public Currency(int cents) {
        if (cents >= 0) {
            this.cents = cents;
        } else throw new IllegalArgumentException("Currency must be greater than 0")
    }

    public int getValue() {
        return cents;
    }

    public Currency add (Currency rhs) {
        return new Currency(cents + rhs.cents);
    }

    public Currency subtract (Currency rhs) {
        return new Currency(cents - rhs.cents);
    }

    public String toString() {
        return "$" + cents/100;
    }

    public int compareTo(Currency other) {
        if (other.cents > cents)
            return -1;
        else if (temp.cents == cents)
            return 0;
        else return 1;
    }
}