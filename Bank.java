public class Bank {

    private Customer[] bank = new Customer[100];
    private int numCustomers = 0;

    public void becomeCustomer(Customer name) throws InvalidTransactionException {
        if (parseArr(name, numCustomers)) {
            throw new InvalidTransactionException("You are already a customer of the bank");
        }
        bank[numCustomers] = new Customer(name.getFirstName(), name.getLastName(), numCustomers);
        numCustomers++;
    }

    public void makeAccount(Customer name, int type, Currency money) throws InvalidTransactionException {
        Account newAccount;
        if (!parseArr(name, numCustomers))
            throw new InvalidTransactionException("You need to become a customer first!");
        if (type == 0) {
            newAccount = new Checking(money);
        }
        else if (type == 1) {
            newAccount = new Savings(money, 1.02);
        }
        else if (type == 2) {
            newAccount = new CD(money,1.02);
        } else throw new InvalidTransactionException("Please try selecting an account again");

        try {
            name.addAccount(newAccount);
            System.out.println("success in making acc");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }


    public void printBalance(Customer customer, Account account) throws InvalidTransactionException {
        if (!parseArr(customer, numCustomers)) 
            throw new InvalidTransactionException("You are not a customer of our bank");
        try {
            customer.balance(account);
        } catch (Exception e) { e.getMessage(); }
    }


    public void deposit(Customer name, Account account, Currency money) {
        try {
            name.deposit(money, account); 
        } catch (Exception e) { e.getMessage(); }
    }

    public void withdraw(Customer name, Account type, Currency money) {
        try {    
            name.withdraw(money, type);
        } catch (Exception e) { e.getMessage(); } 
    }


    // HELPER METHODS
    public boolean parseArr(Customer name, int numCustomers) {
        boolean b = true;
        if (numCustomers == 0) {
            b = false;
        }
        else if (!bank[numCustomers-1].equals(name))
            parseArr(name, numCustomers-1);
        return b;
    }


}