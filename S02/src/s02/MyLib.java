/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s02;

import java.util.Scanner;

/**
 * S02 - String Array Manipulations.
 *
 * @author QuyenTTTCE200993
 */
public class MyLib {

    static Scanner sc = new Scanner(System.in);

    /**
     * Enter a positive integer from the keyboard.
     *
     * @param msg Message to request input.
     * @param erroMsg Error message if input is incorrect.
     * @return A valid positive integer.
     */
    public static int getPositiveInteger(String msg, String erroMsg) {
        int n = 0; // Variable to store the valid positive integer
        boolean isOk = true; // Flag to check if the input is valid

        do {
            try {
                isOk = true; // Assume the input is valid

                System.out.print(msg); // Display the input prompt message

                n = Integer.parseInt(sc.nextLine()); // Read input and convert it to an integer

                if (n <= 0) { // Check if the number is not positive
                    System.out.println(erroMsg); // Show error message
                    isOk = false; // Mark input as invalid to repeat the loop
                }
            } catch (Exception e) { // Catch input errors (e.g., letters instead of numbers)
                System.out.println(erroMsg); // Show error message
                isOk = false; // Mark input as invalid to repeat the loop
            }
        } while (!isOk); // Repeat the loop until a valid input is received

        return n; // Return the valid positive integer
    }

    /**
     * Enter name from keyboard, only letters (A–Z, a–z) are accepted.
     *
     * @param erroMsg Error message if entered incorrectly.
     * @return Valid name entered by user.
     */
    public static String getName(String erroMsg) {
        String name = ""; // Variable to store the valid name
        boolean isOk = true; // Flag to check if the input is valid

        do {
            try {
                isOk = true; // Assume the input is valid

                name = sc.nextLine().trim(); // Read input and remove leading/trailing spaces

                // Check if the input matches the name format (only letters and spaces between words)
                if (!name.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                    System.out.println(erroMsg); // Show error message
                    isOk = false; // Mark input as invalid to repeat the loop
                }
            } catch (Exception e) { // Catch unexpected input exceptions
                System.out.println(erroMsg); // Show error message
                isOk = false; // Mark input as invalid to repeat the loop
            }
        } while (!isOk); // Repeat the loop until a valid name is entered

        return name; // Return the validated name
    }
}
