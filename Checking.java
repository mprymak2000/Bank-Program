package com.MichalPrymak;

public class Checking extends Account {

    static final String type = "Checking";

    public Checking (Currency initialAmount)  {
        super(initialAmount);
    }

    public boolean withdraw(Currency moneyOut) {
        if (moneyOut.getValue() <= balance) {
            balance = balance.subtract(moneyOut);
            return true;
        } else { return false; }
    }

    public boolean deposit(Currency moneyIn) {
        if (moneyIn.getValue() > 0) {
            balance = balance.add(money);
            return true;
        } else { return false; }
    }

    Currency getBalance() {
        return balance;
    }
}
