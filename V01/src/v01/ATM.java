/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package v01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author QuyenTTTCE200993
 */
public class ATM {

    Account account = null;// Stores the currently logged-in account
    private HashMap<String, Account> accounts = new HashMap<>(); // Stores all registered accounts using accountNumber as the key
    Input i = new Input();// Handles user input (assumed to be another class in the project)

    /**
     * Registers a new account and saves the details to a file.
     *
     * @param accountNumber Unique identifier for the account.
     * @param pin Security PIN for authentication.
     * @param accountName Name of the account holder.
     * @param balance Initial balance of the account.
     * @param currency Currency type of the account.
     */
    public void registerAccount(String accountNumber, String pin, String accountName, double balance, String currency) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true))) {
            writer.write(accountNumber + " | " + pin + " | " + accountName + " | " + balance + " | " + currency + "\n");
        } catch (IOException e) {
            System.out.println("Error saving account.");
        }
    }

    /**
     * Loads account details from the "accounts.txt" file into the accounts
     * HashMap. The last account read from the file is set as the currently
     * active account.
     */
    private void loadAccounts() {
        Account accounted = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" \\| ");
                accounted = accounts.put(data[0], new Account(data[0], data[1], data[2], Double.parseDouble(data[3]), data[4]));
            }
        } catch (IOException e) {
            System.out.println("Error loading accounts.");
        }
        account = accounted;
    }

    /**
     * Processes a withdrawal request from the given user account.
     *
     * @param user The account from which money will be withdrawn.
     */
    public void withdraw(Account user) {
        double amount = i.inputMoney("Please enter the amount you want: ", "Please enter the correct format: ");
        double balance = user.getBalance();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            user.setBalance(balance);
            user.saveTransaction("Withdraw", amount);
            user.updateAccountFile();
            System.out.println("Withdrawal successful. New balance: " + balance + " " + user.getCurrency());
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    /**
     * Transfers money from the given user account to another account.
     *
     * @param user The account initiating the transfer.
     */
    public void transfer(Account user) {
        if (checkNumberOfUser()) {
            String recipientNumber = i.inputAccount("Please enter your account number: ", "Please enter the correct format(14 digits): ", true);
            if (checkAccount(recipientNumber) != null) {
                Account recipient = accounts.get(recipientNumber);
                double amount = i.inputMoney("Please enter the amount you want: ", "Please enter the correct format: ");
                if (amount > 0 && amount <= user.getBalance()) {
                    user.setBalance(user.getBalance() - amount);
                    recipient.setBalance(recipient.getBalance() + amount);
                    user.saveTransaction("Transfer to " + recipient.getAccountNumber(), amount);
                    recipient.saveTransaction("Received from " + user.getAccountNumber(), amount);
                    user.updateAccountFile();
                    System.out.println("Transfer successful. New balance: " + user.getBalance() + " " + user.getCurrency());
                } else {
                    System.out.println("Insufficient funds or invalid amount.");
                }
            } else {
                System.out.println("The account does not exist!");
            }
        } else {
            System.out.println("The number of users is not enough to make this transaction!");
        }
    }

    /**
     * Checks if an account exists in the system.
     *
     * @param acc The account number to check.
     * @return The account number if found, otherwise returns null.
     */
    public String checkAccount(String acc) {
        for (String st : accounts.keySet()) {
            if (st.equals(acc)) {
                return st;
            }
        }
        return null;
    }

    /**
     * Checks if there are more than one user in the system.
     *
     * @return true if there are more than one user, false otherwise.
     */
    public boolean checkNumberOfUser() {
        if (accounts.size() > 1) {
            return true;
        }
        return false;
    }

    /**
     * Verifies if the provided password matches the PIN of the given account.
     *
     * @param checkAccount The account number to check.
     * @param passWord The PIN to verify.
     * @return true if the PIN is correct, false if the account does not exist
     * or the PIN is incorrect.
     */
    public boolean checkAccountPin(String checkAccount, String passWord) {
        return accounts.get(checkAccount).getPin().equals(passWord);
    }

    /**
     * Checks if the given account has a positive balance.
     *
     * @param user The account to check.
     * @return true if the balance is greater than 0, false otherwise.
     */
    public boolean balance(Account user) {
        if (user.getBalance() == 0) {
            return false;
        }
        return true;
    }

    /**
     * interface user
     */
    public void ui() {
        loadAccounts();
        while (true) {
            System.out.println("\n-----WELCOME TO ATM-----");
            System.out.println("1. Login");
            System.out.println("2. Register account");
            System.out.println("0. Exit");
            int option = i.Add("--Please choose: ", "Pleaase enter correct format!");
            switch (option) {
                case 1:
                    String accNumber = i.inputAccount("Please enter your account number: ", "Please enter the correct format(14 digits): ", true);
                    if (checkAccount(accNumber) != null) {
                        String pin = i.inputAccount("Please enter your account pin: ", "Please enter the correct format(6 digits): ", false);
                        if (checkAccountPin(accNumber, pin)) {
                            Account user = accounts.get(accNumber);
                            Loopoff:
                            while (true) {
                                System.out.println("\n-----WELCOME TO ATM-----");
                                System.out.println("1. WithDraw money");
                                System.out.println("2. Transfer money");
                                System.out.println("0. Exit");
                                int choice = i.Add("--Please choose: ", "Pleaase enter correct format!");
                                switch (choice) {
                                    case 1:
                                        if (balance(user)) {
                                            withdraw(user);
                                            break;
                                        } else {
                                            System.out.println("The account has no money.");
                                            break;
                                        }
                                    case 2:
                                        if (balance(user)) {
                                            transfer(user);
                                            break;
                                        } else {
                                            System.out.println("The account has no money.");
                                            break;
                                        }
                                    case 0:
                                        System.out.println("Exiting...");
                                        break Loopoff;
                                    default:
                                        System.out.println("Invalid choice! Try again.");
                                }
                            }
                        } else {
                            System.out.println("Enter the wrong password!");
                        }
                    } else {
                        System.out.println("The account does not exist!");
                    }
                    break;
                case 2:
                    while (true) {
                        String accountNumber = i.inputAccount("Please enter your account number: ", "Please enter the correct format(14 digits): ", true);
                        if (checkAccount(accountNumber) == null) {
                            while (true) {
                                String pin = i.inputAccount("Please enter your password: ", "Please enter the correct format(6 digits): ", false);
                                String pinv = i.inputAccount("Enter the verification password: ", "Please enter the correct format(6 digits): ", false);
                                if (pinv.equalsIgnoreCase(pin)) {
                                    String accountName = i.inputAccountName();
                                    double balance = i.inputMoney("Please enter the amount you want to load: ", "Please enter the correct format!");
                                    String currency = i.inputCurrency();
                                    registerAccount(accountNumber, pin, accountName, balance, currency);
                                    loadAccounts();
                                    System.out.println("Create a successful account!");
                                    break;
                                } else {
                                    System.out.println("The password is wrong.");
                                }
                            }
                            break;
                        } else {
                            System.out.println("This account number has been used!");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    break;
            }
        }
    }
}
