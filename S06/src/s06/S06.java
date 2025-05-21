/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package s06;

/**
 * S06 - Delete duplicate elements in an array
 *
 * @author QuyenTTTCE200993
 */
public class S06 {

    /**
     * The main method serves as the entry point of the program. - Prompts the
     * user to enter the size of the array. - Creates an instance of
     * `ArrayHandling` with the given size. - Allows the user to input array
     * elements. - Displays the original array before and after removing
     * duplicate elements. - Uses exception handling to catch and display
     * errors.
     *
     * @param args the command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        try {
            int n = Lib.getPositiveInteger("Please enter size of array: ", "Number of elements must be a positive integer"); // Prompt for positive array size
            ArrayHandling ah = new ArrayHandling(n); // Create ArrayHandling object with size n
            ah.input(n); // Input array elements
            System.out.println("The original array:\n" + ah.toString()); // Print original array
            ah.removeDuplicates(); // Remove duplicate elements from array
            System.out.println("The array after removing duplicate elements:\n" + ah.toString()); // Print updated array
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); // Print exception message if error occurs
        }
    }
}
