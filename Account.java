public abstract class Account {
    protected Currency balance;

    public Account(Currency initialDeposit) {
        this.balance = new Currency(initialDeposit.getValue());
    }

    public abstract boolean withdraw(Currency money);

    public abstract deposit(Currency moneyIn);

    abstract Currency getBalance();
}