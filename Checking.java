package com.MichalPrymak;

public class Checking extends Account {

    static final String type = "Checking";

    public Checking (Currency initialAmount)  {
        super(initialAmount);
    }

    public void withdraw(Currency money) {
        balance = balance.subtract(money);
    }

    public void deposit(Currency money) {
        balance = balance.add(money);
    }

    Currency getBalance() {
        return balance;
    }
}
