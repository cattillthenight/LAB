/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package v01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author QuyenTTTCE200993
 */
public class Input {

    Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for an integer input with validation.
     *
     * @param msg The prompt message for the user.
     * @param errorMsg The error message displayed on invalid input.
     * @return A valid integer value within the allowed range.
     */
    public int Add(String msg, String errorMsg) {
        int n = 0;//Used to save input values.
        boolean pass = true; // Reset the valid state before each input attempt.
        do {

            try {
                pass = true;
                System.out.print(msg);// Display the input request message.
                n = Integer.parseInt(sc.nextLine());// Read the user input value and convert it to an integer.
                if (n > 2) // If the entered number is greater than 6, it will be considered invalid.
                {
                    System.out.println(errorMsg);
                    pass = false;
                }
            } catch (Exception e) // If the input is not an integer, an error will be caught and asked to re-enter.
            {
                System.out.println(errorMsg);
                pass = false;
            }

        } while (!pass);// Repeat until user input is valid.
        return n;// Returns a valid value.
    }

    /**
     * Takes user input and validates it based on the specified mode.
     *
     * @param msg The message to prompt the user.
     * @param errMsg The error message to display in case of invalid input.
     * @param mode The mode to determine validation type (0 for account number,
     * 1 for PIN).
     * @return A valid account number (14 digits) or PIN (6 digits).
     */
    public String inputAccount(String msg, String errMsg, boolean mode) {
        System.out.print(msg);
        while (true) {
            String n = sc.nextLine();
            if (n.matches("\\d{14}") && mode == true) {
                return n;
            } else if (n.matches("\\d{6}") && mode == false) {
                return n;
            } else {
                System.out.print(errMsg);
            }
        }
    }

    /**
     * Prompts the user to enter a valid monetary amount.
     *
     * @param msg The message to prompt the user.
     * @param errMsg The error message to display in case of invalid input.
     * @return A valid positive double value representing money.
     */
    public double inputMoney(String msg, String errMsg) {
        double n = 0;
        while (true) {
            try {
                System.out.print(msg);
                n = Double.parseDouble(sc.nextLine());
                if (n > 0) {
                    return n;
                } else {
                    System.out.print("Please enter money > 0: ");
                }
            } catch (NumberFormatException e) {
                System.out.println(errMsg);
            }
        }
    }

    /**
     * Prompts the user to enter a valid currency code (USD, VND, YEN).
     *
     * @return A valid currency code (case-insensitive, but stored in
     * uppercase).
     */
    public String inputCurrency() {
        Set<String> formatCurrency = new HashSet<>(Arrays.asList("USD", "VND", "YEN"));
        System.out.print("--Please enter currency: ");
        String currency;
        while (true) {
            currency = sc.nextLine().trim();
            for (String curren : formatCurrency) {
                if (curren.equalsIgnoreCase(currency)) {
                    return curren;
                }
            }
            System.out.print("Erorr! Please currency again (USD-VND-YEN): ");
        }
    }

    /**
     * Prompts the user to enter a valid name.
     *
     * @return A properly formatted name.
     */
    public String inputAccountName() {
        String name;
        System.out.print("--Please enter name: ");
        while (true) {
            name = sc.nextLine().trim();
            if (name.matches("[a-zA-Z ]+") && !name.matches(".*[ ]{2,}.*") && countString(name) >= 2) {
                name = capitalizeName(name);
                return name;
            } else if (countString(name) < 2) {
                System.out.print("Please! Import full name: ");
            } else {
                System.out.print("Erorr! Please name again (Not Null): ");
            }
        }
    }

    /**
     * Count the number of words in the input chain, using spaces as a separate
     * sign.
     *
     * @param input string needs to count the number of words.
     * @return the number from the string, returns 0 if the string is empty or
     * null.
     */
    public int countString(String input) {
        return input.split("\\s+").length;
    }

    /**
     * Converts the first letter of each word in a name to uppercase.
     *
     * @param name The input name string.
     * @return The formatted name with each word capitalized.
     */
    public String capitalizeName(String name) {
        String[] words = name.trim().toLowerCase().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return result.toString().trim();
    }
}
