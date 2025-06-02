import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static boolean end = false;
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        while (end == false) {

            printMenu();
            int input = input(6);
            Customer customer;
            int type;

            try {
                switch (input) {
                    case 1:
                        bank.becomeCustomer(nameInput());
                        System.out.println("Success! Welcome to our bank\n");
                        break;
                    case 2:
                        customer = nameInput();
                        for (Customer c : bank.getListCustomers()) {
                            if (c.equals(customer))
                                customer = c; 
                         }
                        System.out.println("Please select which account you would like to make");
                        printAccountTypes();
                        type = accountTypeInput();
                        System.out.println("Please provide an initial deposit");
                        Currency money = moneyValueInput();
                        bank.makeAccount(customer, type, money);
                        System.out.println("Your account was successfully created with an initial deposit of " + customer.accountArr[type].getBalance());
                        break;
                    case 3:
                        customer = nameInput();
                        System.out.println("Please select which account balance you would like to see");
                        printAccountTypes();
                        type = accountTypeInput();
                        bank.printBalance(customer, accountFromInput(customer, type));
                        break;
                    case 4:
                        customer = nameInput();
                        System.out.println("Please select which account you would like to deposit to");
                        printAccountTypes();
                        type = accountTypeInput();
                        System.out.println("Please enter how much money you would like to deposit");
                        bank.deposit(customer, accountFromInput(customer, type), moneyValueInput());
                        System.out.println("Your transaction was successful");
                    case 5:
                        customer = nameInput();
                        System.out.println("Please select which account you would like to withdraw from");
                        printAccountTypes();
                        type = accountTypeInput();
                        System.out.println("Please enter how much money you would like to withdraw");
                        bank.withdraw(customer, accountFromInput(customer, type), moneyValueInput());
                        System.out.println("Your transaction was successful");
                    case 6:
                        System.out.println("Goodbye");
                        end = true;


                }
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
            } catch (InvalidTransactionException ite) {
                System.err.println(ite.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void printMenu() {
        System.out.println("Welcome to bank MONEY.STEAL. Please select which you would like to do: \n" +
                "1. Become a customer \n" +
                "2. Make an account \n" +
                "3. Check you account balance \n" +
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

    public static Customer nameInput() {
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
        }
        return new Customer(first, last);
    }

    public static int accountTypeInput() {
        String type = "";
        while (type.equals("")) {
            type = sc.next();
            if (!type.matches("[0-2]+")) {
                System.err.println("Please enter a valid option");
                type = "";
            }
        }
        return Integer.parseInt(type);
    }

    public static Currency moneyValueInput() {
        int value = 0;
        while (value == 0) {
            try {
                value = sc.nextInt();
            } catch (NumberFormatException nfe) {
                System.err.println("Please enter a valid amount");
            }
        }
        return new Currency(value*100);
    }

    public static Account accountFromInput(Customer customer, int type) {
        return customer.accountArr[type];
    }
}