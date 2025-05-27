public class Bank {

    private Customer[] bank = new Customer[100];
    static int numCustomers = 0;

    public void becomeCustomer(Customer name) {
        if (!parseArr(name, numCustomers)) {
            bank[numCustomers] = new Customer(name.getFirstName(), name.getLastName());
            numCustomers++;
        } else throw new IllegalArgumentException("You are already a customer of our bank");
    }

    public void makeAccount(Customer name, String type, Currency money) throws Exception {
        if (!parseArr(name, numCustomers))
            throw new NullPointerException("You are not a customer of our bank");
        else if (name.accountArr[Integer.parseInt(type)] != null)
            throw new IllegalArgumentException("You already have that account");
        if (type == "0") {
            Account account = new Checking(money);
            bank[numCustomers].addAccount(account);
        }
        else if (type == "1") {
            Account account = new Savings(money, 1.02);
            name.addAccount(account);
        }
        else if (type == "2") {
            Account account = new CD(money,1.02);
            name.addAccount(account);
        }
    }


    public void printBalance(Customer name, String type) {
        if (!parseArr(name, numCustomers))
            throw new NullPointerException("You are not a customer of our bank");
        if (name.accountArr[Integer.parseInt(type)] == null)
            throw new NullPointerException("You do not have that account with our bank");
        name.accountArr[Integer.parseInt(type)].getBalance();
    }


    public void deposit(Customer name, String type, Currency money) {
            name.deposit(money, type); //all exceptions thrown in Customer class. Caught in main
    }

    public void withdraw(Customer name, String type, Currency money) {
            name.withdraw(money, type); //all exceptions thrown in Customer class. Caught in main
    }

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