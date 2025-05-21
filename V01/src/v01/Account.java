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
import java.util.ArrayList;
import java.util.List;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author QuyenTTTCE200993
 */
public class Account {

    private String accountNumber; // Security PIN for authentication
    private String pin;// Name of the account holder
    private String accountName; // Current balance in the account
    private double balance;// Currency type for the account balance
    private String currency;

    /**
     * Constructor to initialize an Account object.
     *
     * @param accountNumber Unique identifier for the account.
     * @param pin Security PIN for authentication.
     * @param accountName Name of the account holder.
     * @param balance Initial balance of the account.
     * @param currency Currency type of the account.
     */
    public Account(String accountNumber, String pin, String accountName, double balance, String currency) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.accountName = accountName;
        this.balance = balance;
        this.currency = currency;
    }

    /**
     * Gets the account number.
     *
     * @return The account number.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number.
     *
     * @param accountNumber The new account number.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the PIN of the account.
     *
     * @return The account PIN.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the PIN for the account.
     *
     * @param pin The new PIN.
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Gets the account holder's name.
     *
     * @return The account holder's name.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the account holder's name.
     *
     * @param accountName The new account holder's name.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Gets the current balance of the account.
     *
     * @return The account balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance for the account.
     *
     * @param balance The new balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the currency type of the account.
     *
     * @return The currency type.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency type for the account.
     *
     * @param currency The new currency type.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Saves a transaction record to the "transactions.txt" file.
     *
     * @param type The type of transaction (e.g., "Withdraw", "Deposit").
     * @param amount The amount involved in the transaction.
     */
    public void saveTransaction(String type, double amount) {
        // Attempt to write the transaction details to "transactions.txt"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true))) {
            writer.write(accountNumber + " | " + type + " | " + amount + " " + currency + "\n");
        } catch (IOException e) {
            // Handle file writing errors
            System.out.println("Error saving transaction.");
        }
    }

    /**
     * Updates the account details in the "accounts.txt" file. This method reads
     * the existing file, modifies the current account's data, and then writes
     * the updated content back to the file.
     */
    public void updateAccountFile() {
        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(" \\| ");
                    if (data[0].equals(this.accountNumber)) {
                        lines.add(accountNumber + " | " + pin + " | " + accountName + " | " + balance + " | " + currency);
                    } else {
                        lines.add(line);
                    }
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", false))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating account file.");
        }
    }
}
