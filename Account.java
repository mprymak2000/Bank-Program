public abstract class Account {
    protected Currency balance;

    public Account(Currency initialDeposit) {
        if (initialDeposit == null) throw new IllegalArgumentException("Initial deposit cannot be null");
        if (initialDeposit.equals(initialDeposit)) throw new IllegalArgumentException("Initial deposit must be positive");
        this.balance = initialDeposit;
    }

    abstract void withdraw(Currency money);

    abstract void deposit(Currency moneyIn);

    abstract Currency getBalance();
}