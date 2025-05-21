/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * S05 - Analyze the user input string.
 *
 * @author QuyenTTTCE200993
 */
public class AnalysisString {

    private final Scanner sc = new Scanner(System.in);

    /**
     * The program's control logic is put into in the run function.
     *
     */
    public void run() {

        System.out.println("===== Analysis String Program ====="); // Print program header

        String input;  // Declare a variable to store user input

        do { // Loop until the user inputs a non-empty string
            System.out.print("Input String: ");
            input = sc.nextLine().trim(); // Read a line of input and remove leading/trailing spaces

            if (input.isEmpty()) { // Check if the input is empty
                System.out.println("Please enter any character string into the string");
            }
        } while (input.isEmpty()); // Repeat if input is still empty

        System.out.println("-----Result Analysis-----");   // Print result header

        getNumber(input); // Call method to analyze numbers in the string

        getCharacter(input); // Call method to analyze characters in the string
    }

    /**
     * The algorithm used is intended to consider all the characters in the
     * input string and select only the numbers to be included in the list of
     * square numbers, odd numbers, even numbers and all the numbers that appear
     * in the string.
     *
     * @param input All characters that exist on the operating system
     */
    public void getNumber(String input) {
        HashMap<String, List<Integer>> result = new HashMap<>();
        List<Integer> squareNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> allNumbers = new ArrayList<>();

        String current = ""; // Temporary variable used to store consecutive number series
        String sentinal = input + " "; // Create a flag variable to ensure the last number is handled correctly

        for (int i = 0; i < sentinal.length(); i++) { // Iterate through each character in the string
            char ch = sentinal.charAt(i); // Get each character at position i
            if (Character.isDigit(ch)) { // Check if the current character is a digit
                current += ch; // Add digits to temporary string current
            } else { // If a non-numeric character is encountered then start a new string
                if (!current.isEmpty()) { // If the current string is not empty
                    int number = Integer.parseInt(current); // Convert string to int type
                    allNumbers.add(number); // Add all numbers to the list
                    if (number % 2 == 0) { // Check if the number is even
                        evenNumbers.add(number); // Add even numbers to the list
                    } else { // If it is odd numbers
                        oddNumbers.add(number); // Add to odd number list
                    }
                    if (Math.sqrt(number) == (int) Math.sqrt(number)) { // Check if the number is a perfect square
                        squareNumbers.add(number); // Add to square number list
                    }
                    current = ""; // Reset the current string to prepare for the next number string
                }
            }
        }

        result.put("Square Numbers", squareNumbers);
        result.put("Odd Numbers", oddNumbers);
        result.put("Even Numbers", evenNumbers);
        result.put("All Numbers", allNumbers);

        System.out.println("Square Numbers: " + result.get("Square Numbers"));
        System.out.println("Odd Numbers: " + result.get("Odd Numbers"));
        System.out.println("Even Numbers: " + result.get("Even Numbers"));
        System.out.println("All Numbers: " + result.get("All Numbers"));
    }

    /**
     * The algorithm used is intended to consider all the characters in the
     * input string and select only all the characters and letters except
     * numbers to include in the list of uppercase, lowercase, all special
     * characters appearing in the string.
     *
     * @param input All characters that exist on the operating system
     */
    public void getCharacter(String input) {
        HashMap<String, StringBuilder> result = new HashMap<>();
        StringBuilder upperChars = new StringBuilder();
        StringBuilder lowerChars = new StringBuilder();
        StringBuilder specialChars = new StringBuilder();
        StringBuilder allChars = new StringBuilder();

        for (char ch : input.toCharArray()) { // Loop through each character in the input string
            allChars.append(ch); // Append the current character to collect every character
            if (Character.isUpperCase(ch)) { // Check if the character is an uppercase letter
                upperChars.append(ch); // Add to upperChars List
            } else if (Character.isLowerCase(ch)) { // Check if the character is a lowercase letter
                lowerChars.append(ch); // Add to lowerChars List
            } else if (!Character.isLetterOrDigit(ch)) { // Check if the character is a special character
                specialChars.append(ch); // Add to specialChars List
            }
        }

        result.put("Uppercase Characters", upperChars); // Store the string of all uppercase characters into the result map
        result.put("Lowercase Characters", lowerChars); // Store the string of all lowercase characters into the result map
        result.put("Special Characters", specialChars); // Store the string of all special characters into the result map
        result.put("All Characters", allChars); // Store the full original string into the result map

        System.out.println("Uppercase Characters: " + result.get("Uppercase Characters")); // Display uppercase characters
        System.out.println("Lowercase Characters: " + result.get("Lowercase Characters")); // Display lowercase characters
        System.out.println("Special Characters: " + result.get("Special Characters")); // Display special characters
        System.out.println("All Characters: \n" + result.get("All Characters")); // Display the entire original string
    }
}
