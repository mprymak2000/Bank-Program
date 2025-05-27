package com.MichalPrymak;

public class Checking extends Account {

    static final String type = "Checking";

    public Checking (Currency initialAmount)  {
        super(initialAmount);
    }

    public boolean withdraw(Currency moneyOut) {
        if (balance.compareTo(moneyOut) != -1) {
            balance = balance.subtract(moneyOut);
            return true;
        } else { return false; }
    }

    public void deposit(Currency moneyIn) {
        balance.add(moneyIn);
    }

    Currency getBalance() {
        return balance;
    }
}
