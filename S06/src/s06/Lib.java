/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s06;

import java.util.Scanner;

/**
 * S06 - Delete duplicate elements in an array
 *
 * @author QuyenTTTCE200993
 */
public class Lib {

    /**
     * Read some positive integers from the user via the keyboard. Handling
     * errors if the user inputs an invalid value.
     */
    static Scanner sc = new Scanner(System.in);

    /**
     * Enter an integer from the keyboard and check if it is valid or not.
     *
     * @param msg Notification.
     * @param errorMsg An error message occurred.
     * @return a valid value.
     */
    public static int getPositiveInteger(String msg, String errorMsg) {
        int n = 0; // Variable to store the user input
        boolean pass = true; // Flag to check if the input is valid
        do {
            try {
                pass = true; // Assume input is valid initially
                System.out.print(msg); // Show prompt message
                n = Integer.parseInt(sc.nextLine()); // Read and parse user input to integer
                if (n <= 0) { // Check if number is not positive
                    System.out.println(errorMsg); // Show error message
                    pass = false; // Set flag to false to retry
                }
            } catch (Exception e) { // Catch exception if input is not a valid integer
                System.out.println(errorMsg); // Show error message
                pass = false; // Set flag to false to retry
            }
        } while (!pass); // Repeat until valid input is received
        return n; // Return the valid positive integer
    }
}
