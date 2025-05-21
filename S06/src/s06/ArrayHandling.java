/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s06;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * S06 - Delete duplicate elements in an array
 *
 * @author QuyenTTTCE200993
 */
public class ArrayHandling {

    private int[] array;

    /**
     * The constructor Initialize an array of size n and check whether the input
     * size is valid or not.
     *
     * @param n Number of elements
     * @throws Exception If n is negative integer, the Exception is thrown
     */
    public ArrayHandling(int n) throws Exception {
        if (n <= 0) {
            throw new Exception("Number of elment must be a positive integer");
        } else {
            array = new int[n];
        }
    }

    /**
     * Enter the elements of the array from the user.
     *
     * @param m The array size parameter is passed to the input method.
     */
    public void input(int m) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object for user input

        for (int i = 0; i < m; i++) { // Loop through each index from 0 to m-1
            int n = 0; // Temporary variable to store input number
            boolean pass = true; // Flag to check if input is valid
            do {
                try {
                    pass = true; // Assume valid input initially
                    System.out.print("Enter element[" + i + "]: "); // Prompt user to enter element at index i
                    n = Integer.parseInt(sc.nextLine()); // Parse input string to integer
                    array[i] = n; // Assign the valid input to the array
                } catch (Exception e) { // Catch exception if input is invalid
                    System.out.println("Number of element must be an integer"); // Show error message
                    pass = false; // Set flag to false to retry input
                }
            } while (!pass); // Repeat input until it's valid
        }
    }

    /**
     * Method to remove duplicate elements from the array. - Uses a
     * `LinkedHashSet` to maintain insertion order while removing duplicates. -
     * Converts the set back to an array after filtering out duplicates.
     */
    public void removeDuplicates() {
        Set<Integer> set = new LinkedHashSet<>(); // Create a LinkedHashSet to store unique elements while keeping insertion order
        for (int num : array) { // Iterate through each element in the array
            set.add(num); // Add the number to the set (duplicates will be automatically ignored)
        }
        array = set.stream().mapToInt(Integer::intValue).toArray(); // Convert the set back to an int array
    }

    /**
     * @return A string representation of the array with elements separated by
     * spaces.
     */
    @Override
    public String toString() {
        // StringBuilder allows appending, modifying, and deleting characters without creating new string objects each time,
        // which improves performance when handling multiple string operations.
        StringBuilder sb = new StringBuilder();

        for (int num : array) {
            sb.append(num);
            sb.append("         ");
        }
        return sb.toString().trim();
    }
}
