public class Bank {

    public Customer[] bank;
    private int numCustomers = 0;

    public Bank(int maxCustomers) {
        bank = new Customer[maxCustomers];
    }

    public Customer becomeCustomer(Customer customer) throws InvalidTransactionException {
        isNotCustomer(customer);
        bank[numCustomers] = new Customer(customer.getFirstName(), customer.getLastName(), numCustomers);
        numCustomers++;
        return bank[numCustomers-1];
    }

    int getNumCustomers() {
        return numCustomers;
    }

    Account makeAccount(Customer customer, int type, Currency money) throws InvalidTransactionException {
        isCustomer(customer);
        Account newAccount;
        if (type == 1) {
            newAccount = new Checking(money);
        } else if (type == 2) {
            newAccount = new Savings(money, 1.02);
        } else if (type == 3) {
            newAccount = new CD(money,1.10);
        } else throw new InvalidTransactionException("Please try selecting an account again\n");
        
        customer.addAccount(newAccount);
        System.out.println("Your account was created successfully with an initial balance of: " + money + "\n");
        return newAccount;
    }


    void printBalance(Customer customer, Account account) throws InvalidTransactionException {
    //    if (!validateCustomer(customer))
    //        throw new InvalidTransactionException("Please become a customer first.");
        isCustomer(customer);
        System.out.println("Your balance is: " + customer.balance(account));
    }


    void deposit(Customer customer, Account account, Currency money) throws InvalidTransactionException {
    //    if (!validateCustomer(customer))
    //        throw new InvalidTransactionException("Please become a customer first.");
        isCustomer(customer);
        customer.deposit(money, account); 
        System.out.print("Your transaction was successful.");
        printBalance(customer, account);
    }

    void withdraw(Customer customer, Account account, Currency money) throws InvalidTransactionException {
     //   if (isNotCustomer(customer))
     //       throw new InvalidTransactionException("Please become a customer first."); 
        isCustomer(customer);
        customer.withdraw(money, account);
        System.out.print("Your transaction was successful.");
        printBalance(customer, account);
    }


    // HELPER METHODS
    Customer findCustomer(Customer customer, int numCustomers) {
        if (numCustomers == 0) {
            return null;
        }
        if (bank[numCustomers-1].equals(customer)) {
            return bank[numCustomers-1];
        } else return findCustomer(customer, numCustomers-1);
    }

    boolean isCustomer (Customer customer) throws InvalidTransactionException {
        boolean isCustomer = false;
        if (findCustomer(customer, numCustomers) != null) {
            isCustomer = true;
        } else throw new InvalidTransactionException("\nPlease become a customer first.");
        return isCustomer;
    }

    boolean isNotCustomer (Customer customer) throws InvalidTransactionException {
        boolean isNotCustomer = false;
        if (findCustomer(customer, numCustomers) == null) {
            isNotCustomer = true;
        } else throw new InvalidTransactionException("\nYou are already a customer of MP bank.");
        return isNotCustomer;
    }
    
}