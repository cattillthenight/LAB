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
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        List<Integer> allNumbers = matcher.results()
            .map(MatchResult::group)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        
        List<Integer> oddNumbers = allNumbers.stream()
            .filter(n -> n % 2 != 0)
            .collect(Collectors.toList());

        List<Integer> evenNumbers = allNumbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        
        List<Integer> squareNumbers = allNumbers.stream()
            .filter(n -> Math.sqrt(n) == (int) Math.sqrt(n))
            .collect(Collectors.toList());


        System.out.println("Square Numbers: " + squareNumbers);
        System.out.println("Odd Numbers: " + oddNumbers);
        System.out.println("Even Numbers: " + evenNumbers);
        System.out.println("All Numbers: " + allNumbers);
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
