package s01;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * S01 - Manage student
 *
 * @author QuyenTTTCE200993
 */
public class StudentManager {

    private static final String FILE_NAME = "Student.txt"; // File to store student data
    private List<Student> students; // List to hold student objects
    private Scanner scanner = new Scanner(System.in); // Scanner for user input (separate from main)

    /**
     * Constructor: loads the student list from file when the object is created.
     * If loading fails, an empty list is initialized.
     */
    public StudentManager() {
        try {
            this.students = loadStudentsFromFile(); // Load student list from file
        } catch (IOException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
            this.students = new ArrayList<>(); // Initialize empty list on error
        }
    }

    /**
     * Prompts user to enter a new student's information, validates the input,
     * adds the student to the list, sorts the list, and saves it to the file.
     */
    public void enterStudent() {
        System.out.println("Enter new student:");

        // Input and validate student code
        String code;
        while (true) { // Start an infinite loop to repeatedly prompt for input
            System.out.print("Student code: "); // Ask the user to enter the student code
            code = scanner.nextLine().trim(); // Read input and remove leading/trailing whitespace

            if (code.isEmpty()) { // Check if the input is empty
                System.out.println("Student code cannot be empty."); // Inform the user the code cannot be empty
                continue; // Go back to the beginning of the loop
            }

            if (!code.matches("^[A-Z]{2}\\d{6}$")) { // Check if code does not match the pattern: 2 uppercase letters + 6 digits
                System.out.println("Invalid format. Must be 2 letters followed by 6 digits."); // Show format error message
                continue; // Ask again
            }

            break; // Exit the loop if the input is valid
        }

        // Input and validate student name
        String name;

        while (true) { // Start an infinite loop to validate the name input
            System.out.print("Student name: "); // Prompt the user to enter the student's name
            name = scanner.nextLine().trim(); // Read the input and remove leading/trailing spaces

            if (name.isEmpty()) { // Check if the input is empty
                System.out.println("Name cannot be empty."); // Notify that the name cannot be empty
                continue; // Repeat the loop
            }

            if (!name.matches("^([A-Z][a-z]+\\s*)+$")) { // Check if name format is invalid (Each word must start with uppercase followed by lowercase)
                System.out.println("Each word in the name must start with an uppercase letter followed by lowercase letters."); // Show format error
                continue; // Repeat the loop
            }

            break; // Exit the loop if the name is valid
        }

        // Input and validate date of birth (must be between 18 and 26 years old)
        LocalDate dob; // Declare a variable to store the date of birth
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); // Define the expected date format (e.g., 01-Jan-2000)

        int currentYear = LocalDate.now().getYear(); // Get the current year
        int minYear = currentYear - 26; // Minimum allowed year of birth (age 26)
        int maxYear = currentYear - 18; // Maximum allowed year of birth (age 18)

        while (true) { // Start an infinite loop for input validation
            System.out.print("Date of birth: "); // Prompt the user to enter the date of birth
            String dobInput = scanner.nextLine().trim(); // Read and trim user input

            try {
                dob = LocalDate.parse(dobInput, formatter); // Try to parse the input using the specified date format
                int age = Period.between(dob, LocalDate.now()).getYears(); // Calculate the age from the date of birth

                if (age < 18 || age > 26) { // Check if age is outside the valid range
                    System.out.printf("Student age must be between 18 and 26 years (i.e., born between %d and %d).\n", minYear, maxYear); // Show range warning
                } else {
                    break; // Exit the loop if age is valid
                }

            } catch (DateTimeParseException e) { // Catch parsing errors if the input doesn't match the format
                System.out.println("Invalid date format. Use dd-MMM-yyyy (e.g., 01-Jan-2000)."); // Show error message
            }
        }

        double point; // Input and validate learning point (GPA)

        while (true) { // Start an infinite loop to repeatedly ask for valid input
            System.out.print("Learning point: "); // Prompt the user to enter GPA
            String pointInput = scanner.nextLine().trim(); // Read and trim user input

            if (pointInput.isEmpty()) { // Check if the input is empty
                System.out.println("Learning point cannot be empty."); // Inform user that input is required
                continue; // Continue to the next iteration
            }

            try {
                point = Double.parseDouble(pointInput); // Try to convert the input to a double

                if (point < 0.0 || point > 4.0) { // Check if GPA is out of the allowed range
                    System.out.println("Learning point must be between 0.0 and 4.0."); // Show range error message
                    continue; // Ask again
                }

                break; // Exit the loop if the input is valid

            } catch (NumberFormatException e) { // Catch error if input is not a valid number
                System.out.println("Learning point must be a numeric value."); // Show format error message
            }
        }

        // Add student to list and sort it
        students.add(new Student(code, name, dob, point));
        Collections.sort(students); // Sort by Comparable implementation (e.g., code)

        // Save updated student list to file
        try {
            saveStudentsToFile(students);
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }

    /**
     * Searches for students by name (case-insensitive) and displays matching
     * results.
     */
    public void lookupStudent() {
        System.out.print("Please enter student name: "); // Prompt user to enter the name to search
        String nameToFind = scanner.nextLine().toLowerCase(); // Read input and convert it to lowercase for case-insensitive comparison
        boolean found = false; // A flag to track if any matching student is found

        for (Student s : students) { // Iterate through the list of students
            if (s.getName().toLowerCase().contains(nameToFind)) { // Check if the student's name contains the search keyword (case-insensitive)
                System.out.println(s.toString()); // Print student information using toString() method
                found = true; // Set flag to true if a match is found
            }
        }

        if (!found) { // If no matching student was found
            System.out.println("Student not found."); // Display not found message
        }
    }

    /**
     * Displays the full list of students in the console.
     */
    public void displayStudentList() {
        System.out.println("Student list:");
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    /**
     * Loads student list from a file and returns it as a list of Student
     * objects.
     *
     * @return List of students read from the file
     * @throws IOException If file reading fails
     */
    private List<Student> loadStudentsFromFile() throws IOException {
        List<Student> loadedStudents = new ArrayList<>(); // Create a new list to hold loaded students

        File file = new File(FILE_NAME); // Create a File object with the specified file name
        if (!file.exists()) {
            return loadedStudents; // Return empty list if the file does not exist
        }

        BufferedReader reader = new BufferedReader(new FileReader(file)); // Create a BufferedReader to read the file line by line
        String line;

        while ((line = reader.readLine()) != null) { // Read lines until the end of the file
            if (!line.equals("#")) { // Ignore lines that contain only the '#' marker
                loadedStudents.add(Student.fromFileString(line)); // Convert the line to a Student object and add to the list
            }
        }

        reader.close(); // Close the file reader to free system resources
        return loadedStudents; // Return the list of loaded students
    }

    /**
     * Saves the current student list to the file. Each student entry is
     * followed by "#" as a separator.
     *
     * @param students List of students to save
     * @throws IOException If file writing fails
     */
    private void saveStudentsToFile(List<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Student s : students) {
            writer.write(s.toFileString()); // Write student as string
            writer.newLine();
            writer.write("#"); // Write separator
            writer.newLine();
        }
        writer.close();
    }
}
