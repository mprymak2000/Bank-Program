public class Customer {

    private String first;
    private String last;
    private int numAccounts;
    protected Account[] accountArr = new Account[3];

    public Customer (String first, String last) {
        this.first = first;
        this.last = last;
    }

    public void addAccount(Account account) throws InvalidTransactionException {
        if (numAccounts==3) {
            throw new InvalidTransactionException("You have already opened each account");
        }
        if (account == null) {
            throw new InvalidArgumentException("Account cannot be null" );
        }
        if (account instanceof Checking) {
           addIfEmpty(0, account, "Checking");
        } else if (account instanceof Savings) {
           addIfEmpty(1, account, "Savings");
        }
        else if {account instanceof CD) {
            addIfEmpty(0, account, "CD");
        } else throw InvalidTransactionException("Unknown type of account");
        numAccounts++;
    }

    public String getFirstName() {
        return first;
    }

    public String getLastName() {
        return last;
    }

    public void deposit(Currency money, Account account) throws InvalidTransactionException{
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null" );
        } elseif {account instanceof CD) {
            throw new InvalidTransactionException("You cannot deposit more into a CD Account");
        } 
                  
        if (account instanceof Checking || account instanceof Savings) {
            account.deposit(money);
        } else throw IllegalArgumentException("Unknown type of account");
    }
        
     public void withdraw(Currency money, Account account) throws InvalidTransactionException {
         if (account == null) {
            throw new IllegalArgumentException("Account cannot be null" );
        } elseif {account instanceof CD) {
            throw new InvalidTransactionException("You cannot deposit more into a CD Account");
        } 
                  
        if (account instanceof Checking || account instanceof Savings) {
            account.withdraw(money);
        } else throw IllegalArgumentException("Unknown type of account");
    }

    public void balance(Currency money, Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null" );
         }
        if (account instanceof Checking || account instanceof Savings || account instanceof CD) {
            account.withdraw(money);
        } else throw IllegalArgumentException("Unknown type of account");
    }


    public boolean equals(Object obj) {
        if(obj instanceof Customer == false)
            return false;
        Customer temp = (Customer) obj;
        return this.first == temp.first && this.last == temp.last;
    }

    private void addIfEmpty(Account account, int index, String type) {
        if (accountArr[index] != null) {
            throw InvalidTransactionException("You already have a " + type + " account");
        } else accountArr[index] = account;
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
