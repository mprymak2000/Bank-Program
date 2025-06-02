

public class Customer {

    private String first;
    private String last;
    private int numAccounts;
    private int customerNumber;
    private static final int checkingIndex = 1;
    private static final int savingsIndex = 2;
    private static final int CDIndex = 3;
    protected Account[] accountArr = new Account[3];

    public Customer (String first, String last) {
        this.first = first;
        this.last = last;
    }
    
    public Customer (String first, String last, int customerNumber) {
        this.first = first;
        this.last = last;
        this.customerNumber = customerNumber;
    }

    public void addAccount(Account account) throws InvalidTransactionException {
        if (numAccounts==3) {
            throw new InvalidTransactionException("You have already opened each account");
        }
        if (account == null) {
            throw new IllegalArgumentException("Error. Something went wrong.");
        }
        if (account instanceof Checking) {
            addIfEmpty(account, checkingIndex, "Checking");
            numAccounts++;
        } else if (account instanceof Savings) {
            addIfEmpty(account, savingsIndex, "Savings");
            numAccounts++;
        }
        else if (account instanceof CD) {
            addIfEmpty(account, CDIndex, "CD");
            numAccounts++;
        } else throw new InvalidTransactionException("Something went wrong. Unknown account type.");
    
    }

    String getFirstName() {
        return first;
    }

    String getLastName() {
        return last;
    }

    int getCustomerNumber() {
        return customerNumber;
    }

    int getNumAccounts () {
        return numAccounts;
    }

    Account getAccount(int index) throws InvalidTransactionException {
        String accountType;
        if (index == 1)
            accountType = "Checking";
        else if (index == 2)
            accountType = "Saving"; 
        else if (index == 3) 
            accountType = "CD";
        else throw new IllegalArgumentException("Bad input");

        if (accountArr[index-1] == null)
            throw new InvalidTransactionException("You do not have a " + accountType + " account");
        return accountArr[index-1];
    }
    
   // public Account[] getAccounts () {
   //     return accountArr;
    //}

    void deposit(Currency money, Account account) throws InvalidTransactionException{
        if (account == null) {
            throw new IllegalArgumentException("You do not have this type of account." );
        } else if (account instanceof CD) {
            throw new InvalidTransactionException("You cannot deposit more into a CD Account");
        } 
                  
        if (account instanceof Checking || account instanceof Savings) {
            account.deposit(money);
        } else throw new IllegalArgumentException("Unknown type of account");
    }
        
    void withdraw(Currency money, Account account) throws InvalidTransactionException {
         if (account == null) {
            throw new IllegalArgumentException("You do not have this type of account.");
        } else if (account instanceof CD && !((CD)account).isMature()) {
            throw new InvalidTransactionException("You cannot withdraw from a CD Account until the maturity date");
        }         
        if (account instanceof Checking || account instanceof Savings || account instanceof CD) {
            account.withdraw(money);
        } else throw new IllegalArgumentException("Error: Unknown type of account");
    }

    Currency balance(Account account) {
        if (account == null) 
            throw new IllegalArgumentException("You do not have this type of account." );
        if (account instanceof Checking || account instanceof Savings || account instanceof CD) 
            return account.getBalance();
        else throw new IllegalArgumentException("Unknown type of account");
    }

    public boolean equals(Object obj) {
        if(obj instanceof Customer == false)
            return false;
        Customer temp = (Customer) obj;
        return this.first.equals(temp.first) && this.last.equals(temp.last);
    }

    private void addIfEmpty(Account account, int index, String type) throws InvalidTransactionException{
        if (accountArr[index-1] != null) 
            throw new InvalidTransactionException("You already have a " + type + " account");
        else accountArr[index-1] = account;
    }

}


        
        
/*        if (type == "0") {
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
*/
