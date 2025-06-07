# Bank-Program
This is a simple banking system simulation designed to model real-world banking operations using core principles of Object-Oriented Programming (OOP). The project demonstrates the use of classes, inheritance, encapsulation, abstraction, polymorphism, and composition. It also features thorough exception handling throughout, ensuring robust behavior in invalid operations.

Features:
- Become a customer to create and manage bank accounts
- Deposit and withdraw funds
- Check balance of each account
- A simple CLI that runs on user input.

How it's implemented:
 - Abstract Account class
 - Checking, Savings and CD classes that extend Account class with concrete behavior (i.e. CD can't deposit/withdraw, Savings has withdrawal limit) (is-a relationship).
 - Customer class storing up to 3 accounts and capable of depositing, withdrawing, checking balance (has-a relationship).
 - Bank class storing customers, allowing user to become customer and manage their accounts (has-a relationship).
 - Currency class handles  all monetary operations and formatting.
 - Main class provides a command-line interface for user interaction.

Things to improve:
  - Refine exception handling by introducing more specific, custom exceptions to better represent different error cases.
  - Create custom and dynamic data structure similar to Java's map implementations, supporting key-value storage with efficient lookup, insertion and deleting. 
