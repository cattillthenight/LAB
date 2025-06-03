/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l01;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * L01 - Create a Java console program to manage students.
 *
 * @author QuyenTTTCE200993
 */
public class CheckEntry {

    Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for a choice and ensures valid input. This method
     * enforces specific choices based on the provided Boolean flag.
     *
     * @param msg The prompt message for the user.
     * @param errorMsg The error message displayed when invalid input is
     * entered.
     * @param check A Boolean flag that determines valid choices: - If `true`,
     * allows "U" (update) or "D" (delete). - If `false`, allows "N" (new) or
     * "E" (exit).
     * @return A valid choice string: "U", "D", "N", or "E".
     */
    public String choice(String msg, String errorMsg, boolean check) {
        String n = "";
        boolean pass = true;
        do {

            try {
                pass = true;
                System.out.print(msg);
                n = sc.nextLine();
                if (n.equalsIgnoreCase("U") && check == true) {
                    n = "U";
                } else if (n.equalsIgnoreCase("D") && check == true) {
                    n = "D";
                } else if (n.equalsIgnoreCase("N") && check == false) {
                    n = "N";
                } else if (n.equalsIgnoreCase("E") && check == false) {
                    n = "E";
                } else {
                    System.out.println(errorMsg);
                    pass = false;
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
                pass = false;
            }

        } while (!pass);
        return n;
    }

    /**
     * Prompts the user for an integer input with validation.
     *
     * @param msg The prompt message for the user.
     * @param errorMsg The error message displayed on invalid input.
     * @return A valid integer value within the allowed range.
     */
    public int add(String msg, String errorMsg) {
        int n = 0;
        boolean pass = true;
        do {

            try {
                pass = true;
                System.out.print(msg);
                n = Integer.parseInt(sc.nextLine());
                if (n > 6 || n < 1) {
                    System.out.println(errorMsg);
                    pass = false;
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
                pass = false;
            }

        } while (!pass);
        return n;
    }

    /**
     * Prompts the user to enter a valid student ID.
     *
     * @return A valid ID in the format (HM|TM|IB|AI|SExxxxxx).
     */
    public String inputId() {
        String id;
        System.out.print("--Please enter student's ID: ");
        while (true) {
            id = sc.nextLine().trim();
            if (id.matches("^[A-Z]{2}\\d{6}$") && !id.isEmpty()) {
                return id;
            } else {
                System.out.print("--------Error! ID must follow format 2 letters + 6 digits. Please enter again: ");
            }
        }
    }

    /**
     * Prompts the user to enter a valid student name.
     *
     * @return A properly formatted name.
     */
    public String inputNameStudent() {
        String nameStudent;
        System.out.print("--Please enter name: ");
        while (true) {
            nameStudent = sc.nextLine().trim();
            if (nameStudent.matches("^[A-Za-z]+( [A-Za-z]+)+( [A-Za-z]+)*$")) {
                return capitalizeName(nameStudent);
            } else {
                System.out.print("--------Error! Name must contain at least 2 words, only letters, and no extra spaces. Please enter again: ");
            }
        }
    }

    /**
     * Prompts the user to enter a valid semester code. Format: SP/SU/FA + last
     * two digits of the year (e.g., SP24, FA25).
     *
     * @return A valid semester string.
     */
    public String inputSemester() {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear() % 100;

        String validPrefix;
        if (currentMonth <= 5) {
            validPrefix = "SP";
        } else if (currentMonth <= 9) {
            validPrefix = "SU";
        } else {
            validPrefix = "FA";
        }
        System.out.print("--Please enter semester: ");
        String semester;
        while (true) {
            semester = sc.nextLine().trim();
            if (semester.matches("(?i)(SP|SU|FA)\\d{2}")) {
                int year = Integer.parseInt(semester.substring(2));
                if (year < currentYear) {
                    return semester.toUpperCase();
                } else if (year == currentYear) {
                    String period = semester.substring(0, 2);
                    switch (validPrefix) {
                        case "SP":
                            if (period.equalsIgnoreCase("SP")) {
                                return semester.toUpperCase();
                            }
                            break;
                        case "SU":
                            if (period.equalsIgnoreCase("SU")) {
                                return semester.toUpperCase();
                            }
                            break;
                        case "FA":
                            if (period.equalsIgnoreCase("FA")) {
                                return semester.toUpperCase();
                            }
                            break;
                    }
                    System.out.print("This Semester:" + validPrefix + currentYear + " (Do not exceed the current period).\nPlease enter again: ");
                } else {
                    System.out.print("This Semester: " + validPrefix + currentYear + " (Do not exceed the current period).\nPlease enter again: ");
                }
            } else {
                System.out.print("--Erorr! Please semester again (SP/SU/FA + xx): ");
            }
        }
    }

    /**
     * Prompts the user to enter a valid course name from the predefined set.
     * Accepted values: ".NET", "Java", "C/C++" (case-insensitive).
     *
     * @return A properly formatted course name.
     */
    public String inputNameCourse() {
        System.out.print("--Please enter course name (.Net, Java, C/C++): ");
        String nameCourse;
        while (true) {
            nameCourse = sc.nextLine().trim();
            if (nameCourse.equalsIgnoreCase(".Net")) {
                return ".Net";
            } else if (nameCourse.equalsIgnoreCase("Java")) {
                return "Java";
            } else if (nameCourse.equalsIgnoreCase("C/C++")) {
                return "C/C++";
            } else {
                System.out.print("--------Error! Please enter again (.Net - Java - C/C++): ");
            }
        }
    }

    /**
     * Prompts the user to enter a valid ID student.
     *
     * @return A properly formatted search.
     */
    public String inputSearchByName() {
        System.out.print("---Search Student by Name: ");
        while (true) {
            String input = sc.nextLine().trim();
            if (input.matches("^[A-Za-z]+( [A-Za-z]+)+( [A-Za-z]+)*$")) {
                return input;
            } else {
                System.out.print("--------Error! Name must contain at least 2 words, only letters, and no extra spaces. Please enter again: ");
            }
        }
    }

    /**
     * Prompts the user to enter a valid ID student.
     *
     * @return A properly formatted search.
     */
    public String inputSearchById() {
        System.out.print("--Please enter student's ID: ");
        while (true) {
            String id = sc.nextLine().trim();
            if (id.matches("^[A-Z]{2}\\d{6}$")) {
                return id;
            } else {
                System.out.print("--------Error! ID must follow format 2 letters + 6 digits. Please enter again: ");
            }
        }
    }

    /**
     * Method to get a valid index from the user. Ensures the input is a
     * positive integer. Loops until a valid input is provided.
     *
     * @return A positive integer input by the user.
     */
    public int inputIndex() {
        System.out.print("---Please enter index you want to delete or update(No.): ");
        while (true) {
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (n > 0) {
                    return n;
                } else {
                    System.out.print("--------Erorr! Please index again (Not Null or greater 0): ");
                }
            } catch (NumberFormatException e) {
                System.out.print("please enter the correct format: ");
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
