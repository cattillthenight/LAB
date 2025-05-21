/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        String input; // Declare a variable to store user input

        do { // Loop until the user inputs a non-empty string
            System.out.print("Input String: ");
            input = sc.nextLine().trim(); // Read a line of input and remove leading/trailing spaces

            if (input.isEmpty()) { // Check if the input is empty
                System.out.println("Please enter any character string into the string");
            }
        } while (input.isEmpty()); // Repeat if input is still empty

        System.out.println("-----Result Analysis-----"); // Print result header

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
        List<Integer> allNumbers = new ArrayList<>();

        while (matcher.find()) {
            allNumbers.add(Integer.parseInt(matcher.group()));
        }

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
        String allChars = input.chars()
                .filter(c -> !Character.isDigit(c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        String upperChars = allChars.chars()
                .filter(Character::isUpperCase)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        String lowerChars = allChars.chars()
                .filter(Character::isLowerCase)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        String specialChars = allChars.chars()
                .filter(c -> !Character.isLetterOrDigit(c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        System.out.println("Uppercase Characters: " + upperChars); // Display uppercase characters
        System.out.println("Lowercase Characters: " + lowerChars); // Display lowercase characters
        System.out.println("Special Characters: " + specialChars); // Display special characters
        System.out.println("All Characters: \n" + allChars); // Display the entire original string
    }
}
