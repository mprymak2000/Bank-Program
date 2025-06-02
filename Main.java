import java.util.Scanner;

public class Main {

    public static final int maxCustomers = 30;
    private static Bank bank = new Bank(maxCustomers);
    
    
    private static final int welcomeMenuOptions = 3;
    private static final int customerMenuOptions = 5;
    private static final int accountOptions = 4;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Welcome to MP Bank!");
                System.out.println("1. Customer Menu");
                System.out.println("2. Become a Customer");
                System.out.println("3. Exit");
                int select = input(scanner, welcomeMenuOptions);
                Customer customer;
                switch(select) {
                    case 1:
                        customer = bank.findCustomer(customerNameInput(scanner), bank.getNumCustomers());
                        try {
                            bank.isCustomer(customer);
                        } catch (InvalidTransactionException ite) {
                            System.err.println(ite.getMessage() + "\n");
                            break;
                        } 
                        System.out.println("\ncustomer logging in: " + customer.getFirstName() + " " + customer.getLastName() + "\n");
                        customerMenu(scanner, customer);
                        break;
                    case 2:
                        try { 
                            bank.becomeCustomer(customerNameInput(scanner)); 
                            System.out.println("Success! You are now a customer!\n");
                        } catch (InvalidTransactionException ite) {
                            System.err.println(ite.getMessage() + "\n");
                        } break;
                    case 3:
                        System.out.println("Goodbye!");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("MP Bank is currently down. Please try again later");
        }
    }

    // Customer Menu
    private static void customerMenu(Scanner scanner, Customer customer) {
        boolean active = true;
        while (active) {
            System.out.println("Please choose from the following options:");
            System.out.println("1. Open a new account");
            System.out.println("2. Check balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Back\n");
            
            int select = input(scanner, customerMenuOptions);
            switch (select) {
                case 1:
                    makeNewAccountMenu(scanner, customer);
                    break;
                case 2:
                    checkBalanceMenu(scanner, customer);
                     break;
                case 3:
                    depositMenu(scanner, customer);
                     break;
                case 4:
                    withdrawMenu(scanner, customer);
                     break;
                case 5:
                    active = false;
            }
        }
    }

    public static void makeNewAccountMenu(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Which account would you like to make?");
            System.out.println("1. Checking");
            System.out.println("2. Savings");
            System.out.println("3. Certificate of Deposit (CD)");
            System.out.println("4. Back\n");

            int select = input(scanner, accountOptions);
            if (select == 4)
                break;
            try {
                bank.makeAccount(customer, select, moneyInput(scanner));
                break;
            } catch (InvalidTransactionException ite) {
                System.err.println("\n" + ite.getMessage() + "\n");
            } 
        }
    }

    public static void checkBalanceMenu(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Which account would you like to see the balance of?");
            System.out.println("1. Checking");
            System.out.println("2. Savings");
            System.out.println("3. Certificate of Deposit (CD)");
            System.out.println("4. Back\n");

            int select = input(scanner, accountOptions);
            if (select == 4)
                break;

            try {
                Account account = customer.getAccount(select);
                bank.printBalance(customer, account);
                break;
            } catch (InvalidTransactionException ite) {
                System.err.println("\n" + ite.getMessage() + "\n");
            } catch (IllegalArgumentException iae) {
                System.err.println("\n" + iae.getMessage() + "\n");
            } 
        }
    }

    public static void depositMenu(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Which account would you like to make a deposit to?");
            System.out.println("1. Checking");
            System.out.println("2. Savings");
            System.out.println("3. Certificate of Deposit (CD)");
            System.out.println("4. Back\n");

            int select = input(scanner, accountOptions);
            if (select == 4)
                break;

            try {
                Account account = customer.getAccount(select);
                bank.deposit(customer, account, moneyInput(scanner));
                bank.printBalance(customer, account);
                break;
            } catch (InvalidTransactionException ite) {
                System.err.println("\n" + ite.getMessage() + "\n");
            } catch (IllegalArgumentException iae) {
                System.err.println("\n" + iae.getMessage() + "\n");
            } 
        }
    }

    public static void withdrawMenu(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Which account would you like to withdraw from?");
            System.out.println("1. Checking");
            System.out.println("2. Savings");
            System.out.println("3. Certificate of Deposit (CD)");
            System.out.println("4. Back\n");

            int select = input(scanner, accountOptions);
            if (select == 4)
                break;
                
            try {
                Account account = customer.getAccount(select);
                bank.withdraw(customer, account, moneyInput(scanner));
                bank.printBalance(customer, account);
                break;
            } catch (InvalidTransactionException ite) {
                System.err.println("\n" + ite.getMessage() + "\n");
            } catch (IllegalArgumentException iae) {
                System.err.println("\n" + iae.getMessage() + "\n");
            } 
        }
    }

    //overloaded method from above with an upper limit
    public static int input(Scanner scanner, int optionsAmount) {
        if (optionsAmount < 1) {
            throw new IllegalArgumentException("optionsAmount must be greater than or equal to 1.");
        }
        int select;
        while (!scanner.hasNextInt() || (select = scanner.nextInt()) < 1 || select > optionsAmount) {
            System.err.println("Please enter a valid option"); 
            scanner.next(); 
        } return select;
    }

    //validates user input for their name while joining the bank or 'logging in'
    public static Customer customerNameInput(Scanner sc) {
        String first = "";
        String last = "";
        System.out.println("Please enter your first name");
        while (first.equals("")) {
            first = sc.next();
            if (!first.matches("^[a-zA-Z]*$")) {
                System.out.println("Please enter a valid first name");
                first = "";
            }
        }
        System.out.println("Please enter your last name");
        while (last.equals("")) {
            last = sc.next();
            if (!last.matches("^[a-zA-Z]*$")) {
                System.out.println("Please enter a valid last name");
                last = "";
            }
        } return new Customer(first, last);
    }

    //validates user input for amount of money they would like to deposit.
    public static Currency moneyInput(Scanner sc) {
        System.out.println("Please enter the sum for the transaction.");
        int input = -1;
        while (input < 0) {
           if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (input <= 0) {
                    System.err.println("The amount you'd like to deposit must be positive");
                    input = -1; 
                } 
            } else {
                System.err.println("Please enter a valid amount to deposit");
                sc.next();
            } 
        } return new Currency(input*100);
    }


/* 
    public static void main(String[] args) {
        while (end == false) {
            printMenu();
            int input = input(6);
            Customer customer;
            Account account;
            int type;

            try {
                switch (input) {
                    case 1:
                        bank.becomeCustomer(nameInput());
                        System.out.println("Success! Welcome to our bank\n");
                        break;
                    case 2:
                        customer = nameInput();
                        customer = bank.parseArr(customer, bank.getNumCustomers());
                        System.out.println("Please select which account you would like to make");
                        printAccountTypes();
                        type = accountTypeInput();
                        System.out.println("Please provide an initial deposit");
                        Currency money = moneyValueInput();
                        account = bank.makeAccount(customer, type, money);
                        System.out.print("Your account was successfully created! ");
                        bank.printBalance(customer, account);
                        break;
                    case 3:
                        customer = nameInput();
                        customer = bank.parseArr(customer, bank.getNumCustomers());
                        System.out.println("Please select which account balance you would like to see");
                        printAccountTypes();
                        type = accountTypeInput();
                        account = accountFromType(customer, type);
                        bank.printBalance(customer, account);
                        break;
                    case 4:
                        customer = nameInput();
                        customer = bank.parseArr(customer, bank.getNumCustomers());
                        System.out.println("Please select which account you would like to deposit to");
                        printAccountTypes();
                        type = accountTypeInput();
                        account = accountFromType(customer, type);
                        System.out.println("Please enter how much money you would like to deposit");
                        bank.deposit(customer, account, moneyValueInput());
                        System.out.print("Your transaction was successful. Your new balance is ");
                        bank.printBalance(customer, account);
                        break;
                    case 5:
                        customer = nameInput();
                        customer = bank.parseArr(customer, bank.getNumCustomers());
                        System.out.println("Please select which account you would like to withdraw from");
                        printAccountTypes();
                        type = accountTypeInput();
                        account = accountFromType(customer, type);
                        System.out.println("Please enter how much money you would like to withdraw");
                        bank.withdraw(customer, account, moneyValueInput());
                        System.out.println("Your transaction was successful. Your new balance is ");
                        bank.printBalance(customer, account);
                        break;
                    case 6:
                        System.out.println("Goodbye");
                        end = true;


                }
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
            } catch (InvalidTransactionException ite) {
                System.err.println(ite.getMessage());
            } catch (Exception e) {
                System.out.println("hi im the problem");
                System.out.println(e);
                System.err.println(e.getMessage());
            }
        }
    }



    public static void printMenu() {
        System.out.println("Welcome to bank MONEY.STEAL. Please select which you would like to do: \n" +
                "1. Become a customer \n" +
                "2. Make an account \n" +
                "3. Check your account balance \n" +
                "4. Deposit \n" +
                "5. Withdraw \n" +
                "6. Exit");
    }

    public static void printAccountTypes() {
        System.out.println("0. Checking");
        System.out.println("1. Savings");
        System.out.println("2. CD");
    }

    public static int input(int optionsAmount) {
        int select = 0;
        while (select < 1 || select > optionsAmount) {
            try {
                select = Integer.parseInt(sc.next());
            } catch (NumberFormatException NFE) {
                System.err.println("Please enter a valid option");
            }
        }
        return select;
    }


    public static int accountTypeInput() {
        int input = -1;
        while (input < 0) {
           if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (input < 0 || input > 2) {
                    System.err.println("Please select a valid option");
                    input = -1; 
                } 
            } else {
                System.out.println("Please enter a number");
                sc.next();
            } 
        } 

            
         String type = "";
        while (type.equals("")) {
            type = sc.next();
            if (!type.matches("[0-2]+")) {
                System.err.println("Please enter a valid option");
                type = "";
            }
        }
 
        return input;
    } 
    

*/   /*   public static Currency moneyValueInput() {
        int input = -1;
        while (input < 0) {
           if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (input <= 0) {
                    System.err.println("The amount you'd like to deposit must be positive");
                    input = -1; 
                } 
            } else {
                System.err.println("Please enter a valid amount to deposit");
                sc.next();
            } 
        } return new Currency(input*100);
    }

    public static Account accountFromType(Customer customer, int type) {
        return customer.accountArr[type];
    }
        */
}