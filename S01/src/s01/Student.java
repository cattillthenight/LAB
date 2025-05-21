package s01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * S01 - Manage student
 *
 * Represents a student with code, name, date of birth, and learning point.
 * Implements Comparable interface to allow sorting by student name.
 *
 * @author QuyenTTTCE200993
 */
public class Student implements Comparable<Student> {

    private String code;          // Student code: 2 letters + 6 digits
    private String name;          // Student full name, each word starts uppercase
    private LocalDate dob;        // Date of birth (between 18 and 26 years old)
    private double learningPoint; // Learning point (GPA) between 0.0 and 4.0

    // Define a shared formatter for the whole class to avoid duplication
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    /**
     * Constructor to initialize a student object.
     *
     * @param code Student code, forced format (2 uppercase letters + 6 digits)
     * @param name Student name, each word must start with uppercase letter
     * @param dob Date of birth
     * @param learningPoint Learning point between 0.0 and 4.0
     */
    public Student(String code, String name, LocalDate dob, double learningPoint) {
        this.code = code;
        this.name = name;
        this.dob = dob;
        this.learningPoint = learningPoint;
    }

    /**
     * Returns student code
     *
     * @return code of the student
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets student code
     *
     * @param code student code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns student name
     *
     * @return name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets student name
     *
     * @param name student name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns date of birth of the student
     *
     * @return date of birth of the student
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Sets date of birth of the student
     *
     * @param dob date of birth
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Returns learning point of the student
     *
     * @return learning point (GPA) of the student
     */
    public double getLearningPoint() {
        return learningPoint;
    }

    /**
     * Sets learning point of the student
     *
     * @param learningPoint learning point (GPA)
     */
    public void setLearningPoint(double learningPoint) {
        this.learningPoint = learningPoint;
    }

    /**
     * Convert student data to CSV string with format:
     * code,name,dd-MMM-yyyy,learningPoint
     *
     * @return CSV string representation of the student
     */
    public String toFileString() {
        return code + "," + name + "," + dob.format(FORMATTER) + "," + learningPoint;
    }

    /**
     * Create a Student object from a CSV-formatted string with format:
     * code,name,dd-MMM-yyyy,learningPoint
     *
     * @param line CSV line read from file
     * @return Student object parsed from string
     */
    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        LocalDate dob = LocalDate.parse(parts[2], FORMATTER);
        return new Student(parts[0], parts[1], dob, Double.parseDouble(parts[3]));
    }

    /**
     * Override toString to display student info in a formatted way.
     *
     * @return formatted string representing student information
     */
    @Override
    public String toString() {
        return "---------------------------\n"
                + "Student code: " + code + "\n"
                + "Student name: " + name + "\n"
                + "Date of birth: " + dob.format(FORMATTER) + "\n"
                + "Learning point: " + learningPoint;
    }

    /**
     * Compare two students by name in ascending order, ignoring case.
     *
     * @param other another student to compare to
     * @return negative if this < other, 0 if equal, positive if this > other
     */
    @Override
    public int compareTo(Student other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}
