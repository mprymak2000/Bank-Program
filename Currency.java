public class Currency implements Comparable{
    private int cents;

    public Currency() {}

    public Currency(int cents) {
        this.cents = cents;
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

    public int compareTo(Object o) {
        Currency temp = (Currency) o;
        if (temp.cents > cents)
            return -1;
        else if (temp.cents == cents)
            return 0;
        else return 1;
    }
}