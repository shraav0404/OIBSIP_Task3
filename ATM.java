import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private Map<Integer, Account> accounts;
    private Scanner scanner;

    public ATM() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void createAccount(int accountNumber, String accountHolderName, double initialBalance) {
        Account account = new Account(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);
    }

    public void checkBalance(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            System.out.println("Account Holder Name: " + account.getAccountHolderName());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Available Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdrawCash(int accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                account.addTransaction(new Transaction("Withdrawal", amount, "Withdrawal of " + amount));
                System.out.println("Withdrawal successful. Remaining balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void depositCash(int accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            account.setBalance(account.getBalance() + amount);
            account.addTransaction(new Transaction("Deposit", amount, "Deposit of " + amount));
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transferCash(int fromAccountNumber, int toAccountNumber, double amount) {
        if (accounts.containsKey(fromAccountNumber) && accounts.containsKey(toAccountNumber)) {
            Account fromAccount = accounts.get(fromAccountNumber);
            Account toAccount = accounts.get(toAccountNumber);
            if (fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                fromAccount.addTransaction(new Transaction("Transfer", amount, "Transfer of " + amount + " to account " + toAccountNumber));
                toAccount.addTransaction(new Transaction("Transfer", amount, "Transfer of " + amount + " from account " + fromAccountNumber));
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void displayTransactions(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            System.out.println("Transaction History:");
            for (Transaction transaction : account.getTransactions()) {
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Description: " + transaction.getDescription());
                System.out.println();
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Deposit Cash");
            System.out.println("5. Transfer Cash");
            System.out.println("6. Display Transactions");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    createAccount(accountNumber, accountHolderName, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    checkBalance(accountNumber);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    withdrawCash(accountNumber, amount);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    amount = scanner.nextDouble();
                    depositCash(accountNumber, amount);
                    break;
                case 5:
                    System.out.print("Enter from account number: ");
                    int fromAccountNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter to account number: ");
                    int toAccountNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    amount = scanner.nextDouble();
                    transferCash(fromAccountNumber, toAccountNumber, amount);
                    break;
                case 6:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    displayTransactions(accountNumber);
                    break;
                case 7:
                    System.out.println("Exiting ATM...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}
