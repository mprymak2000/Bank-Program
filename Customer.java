

public class Customer {

    private String first;
    private String last;
    private int numAccounts;
    private int customerNumber;
    Account[] accountArr = new Account[3];

    private static final int checkingIndex = 1;
    private static final int savingsIndex = 2;
    private static final int CDIndex = 3;


    public Customer (String first, String last) {
        this.first = first;
        this.last = last;
    }
    
    //overloaded constructor that keeps a customer number. My program doesn't use it. 
    public Customer (String first, String last, int customerNumber) {
        this.first = first;
        this.last = last;
        this.customerNumber = customerNumber;
    }

    //method that adds account to a customer instance. Guard clauses ensure good business logic.
    //Guard Clauses: prevent null accounts from being added and making a new account if each type of account already exists. 
    public void addAccount(Account account) throws InvalidTransactionException {
        if (numAccounts==3) throw new InvalidTransactionException("You have already opened each account");
        if (account == null) throw new IllegalArgumentException("Error. Something went wrong.");

        if (account instanceof Checking) 
            addIfEmpty(account, checkingIndex, "Checking");
        else if (account instanceof Savings)
            addIfEmpty(account, savingsIndex, "Savings");
        else if (account instanceof CD)
            addIfEmpty(account, CDIndex, "CD");
        else throw new InvalidTransactionException("Something went wrong. Unknown account type.");
        numAccounts++;
    }

    //invokes .deposit() from an Account child class depending on object instance at runtime.
    //Guard clause for a null account. All other invalid issues 
    void deposit(Currency money, Account account) throws InvalidTransactionException{
        if (account == null) throw new IllegalArgumentException("You do not have this type of account." );
        account.deposit(money);
    }
        
    void withdraw(Currency money, Account account) throws InvalidTransactionException {
        if (account == null) throw new IllegalArgumentException("You do not have this type of account.");
        account.withdraw(money);
    }

    Currency balance(Account account) {
        if (account == null) throw new IllegalArgumentException("You do not have this type of account." );
        return account.getBalance();
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
        if (accountArr[index-1] == null) throw new InvalidTransactionException("This account doesn't exist.");
        return accountArr[index-1];
    }
    

    public boolean equals(Object obj) {
        if(obj instanceof Customer == false)
            return false;
        Customer temp = (Customer) obj;
        return this.first.equals(temp.first) && this.last.equals(temp.last);
    }

    private void addIfEmpty(Account account, int index, String type) throws InvalidTransactionException{
        if (accountArr[index-1] != null) throw new InvalidTransactionException("You already have a " + type + " account");
        accountArr[index-1] = account;
    }

}
