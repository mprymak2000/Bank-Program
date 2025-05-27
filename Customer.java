import java.io.InvalidObjectException;
import java.security.InvalidParameterException;

public class Customer {

    private String first;
    private String last;
    private int numAccounts;
    protected Account[] accountArr = new Account[3];

    static InvalidParameterException ipe = new InvalidParameterException("You do not have enough money to withdraw");
    static NullPointerException npe = new NullPointerException("");

    public Customer (String first, String last) {
        this.first = first;
        this.last = last;
    }

    public void addAccount(Account account) throws Exception{
        if (numAccounts==3)
            throw new Exception("You have already opened each account");
        else if(account instanceof Checking) {
            if (accountArr[0] != null)
                throw new InvalidObjectException("You already have a Checking account");
            else accountArr[0] = account;;
;        }
        else if(account instanceof Savings) {
            if (accountArr[1] == null)
                accountArr[1] = account;
            else throw new InvalidObjectException("You already have a Savings account");
        }
        else {
            if (accountArr[2] == null)
                accountArr[2] = account;
            else throw new InvalidObjectException("You already have a CD account");
        }
        numAccounts++;
    }

    public String getFirstName() {
        return first;
    }

    public String getLastName() {
        return last;
    }

    public void deposit(Currency money, String type) {
        if (type == "0") {
            if (accountArr[0] == null)
                throw new NullPointerException("You do not possess a Checking account");
            else accountArr[0].deposit(money);
        }
        else if (type == "1") {
            if(accountArr[1] == null)
                throw new NullPointerException("You do not possess a Savings account");
            else accountArr[1].deposit(money);
        }
        else if (type == "2") {
            if (accountArr[2] == null)
                throw new NullPointerException("You do not possess a CD account");
            else accountArr[2].deposit(money);
        }
        else throw new IllegalArgumentException("Please enter a valid account you would like to deposit to.");
    }

    public void withdraw(Currency money, String type) {
        if (type == "0") {
            if (accountArr[0] == null)
                throw new NullPointerException("You do not possess a Checking account");
            else if (accountArr[0].getBalance().compareTo(money) != 1)
                throw new IllegalArgumentException("The amount you're trying to withdraw exceeds your Checking balance");
            else accountArr[0].withdraw(money);
        } else if (type == "1") {
            if (accountArr[1] == null)
                throw new NullPointerException("You do not possess a Savings account");
            else if (accountArr[1].getBalance().compareTo(money) != 1)
                throw new IllegalArgumentException("The amount you're trying to withdraw exceeds your Savings balance");
            else accountArr[1].withdraw(money);

        } else if (type == "2") {
            if (accountArr[2] == null)
                throw new NullPointerException("You do not possess a CD account");
            else if (accountArr[2].getBalance().compareTo(money) != 1)
                throw new IllegalArgumentException("The amount you're trying to withdraw exceeds your CD account balance");
            else accountArr[2].withdraw(money);
        } else throw new IllegalArgumentException("Please enter a valid account you would like to withdraw from");
    }


    public boolean equals(Object obj) {
        if(obj instanceof Customer == false)
            return false;
        Customer temp = (Customer) obj;
        return this.first == temp.first && this.last == temp.last;

    }
}