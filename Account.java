public abstract class Account {
    protected Currency balance;

    public Account(Currency initialDeposit) {
        this.balance = new Currency(initialDeposit.getValue());
    }

    public abstract boolean withdraw(Currency money);

    public void deposit(Currency moneyIn) {
        if (moneyIn.getValue() > 0) {
            balance.add(moneyIn);
        }
    }

    abstract Currency getBalance();
}