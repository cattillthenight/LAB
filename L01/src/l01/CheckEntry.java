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
            if (id.matches("[a-zA-Z]{2}\\d{6}") && !id.isEmpty()) {
                return id.toUpperCase();
            } else {
                System.out.print("--------Erorr! Please Student's ID again(Format must start with 2 words and 6 numbers): ");
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
            int count = countString(nameStudent);
            if (nameStudent.matches("[a-zA-Z ]+") && !nameStudent.matches(".*[ ]{2,}.*") && count >= 2) {
                nameStudent = capitalizeName(nameStudent);
                return nameStudent;
            } else if (count < 2) {
                System.out.print("Please! Import full name: ");
            } else {
                System.out.print("--------Erorr! Please name again (Not Null): ");
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
        if (currentMonth <= 4) {
            validPrefix = "SP";
        } else if (currentMonth <= 8) {
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
                            if (period.matches("(?i)SP|SU")) {
                                return semester.toUpperCase();
                            }
                            break;
                        case "FA":
                            if (period.matches("(?i)SP|SU|FA")) {
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
        Set<String> formatCourses = new HashSet<>(Arrays.asList(".Net", "Java", "C/C++"));
        System.out.print("--Please enter name Course: ");
        String nameCourse;
        while (true) {
            nameCourse = sc.nextLine().trim();
            for (String course : formatCourses) {
                if (course.equalsIgnoreCase(nameCourse)) {
                    return course;
                }
            }
            System.out.print("--------Erorr! Please Name Course again (.Net-Java-C/C++): ");
        }
    }

    /**
     * Prompts the user to enter a valid ID student.
     *
     * @return A properly formatted search.
     */
    public String inputSearch() {
        String searchStudent;
        System.out.print("---Search Name or Student's ID: ");
        while (true) {
            searchStudent = sc.nextLine().trim();
            if (searchStudent.matches("[a-zA-Z ]+|[a-zA-Z]{2}\\d{6}") && !searchStudent.matches(".*[ ]{2,}.*")) {
                return searchStudent;
            } else {
                System.out.print("--------Erorr! Please name or Student's ID again (Not Null or wrong Student's ID): ");
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
