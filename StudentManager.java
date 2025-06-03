/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * L01 - Create a Java console program to manage students.
 *
 * @author QuyenTTTCE200993
 */
public class StudentManager {

    private List<Student> students = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    CheckEntry ce = new CheckEntry();

    /**
     * Creates a new student by prompting user input. Validates ID and student
     * name to ensure uniqueness. Stops if there are 10 students or if the user
     * opts to cancel.
     */
    public void createStudent() {
        String name;
        while (true) {
            if (!checkList()) {
                return;
            } else {
                String id = ce.inputId();
                Student s = checkId(id);
                if (s != null) {
                    System.out.println("ID already exists. The owner of this ID is: " + s.getNameStudent());
                    name = s.getNameStudent();
                } else {
                    name = ce.inputNameStudent();
                }
                while (true) {
                    String semester = ce.inputSemester();
                    if (!checkSemester(semester, id)) {
                        String course = ce.inputNameCourse();
                        if (browserStudent(id, semester, course)) {
                            System.out.println("This ID has registered this " + course + " in the semester:" + semester);
                            System.out.println("Please enter again!");
                        } else {
                            students.add(new Student(id, name, semester, course));
                            break;
                        }
                    } else {
                        System.out.println("Signed up all the course of this period, please select another period.");
                    }
                }
                System.out.println("Create successful students!");
                if (students.size() >= 10) {
                    return;
                }
            }
        }
    }

    /**
     * This method searches for students by name or ID and sorts the result by
     * name. After searching, the matching students will be displayed in a
     * sorted list.
     */
    public void searchAndSort() {
        List<Student> sortTable = new ArrayList<>();
        while (true) {
            String search = ce.inputSearchByName().toLowerCase().trim();
            for (Student s : students) {
                if (s.getId().toLowerCase().contains(search) || s.getNameCourse().toLowerCase().contains(search)) {
                    sortTable.add(s);
                }
            }
            if (sortTable.isEmpty()) {
                System.out.println("No name found! Please search again.");
                continue;
            }
            Collections.sort(sortTable, Comparator.comparing(Student::getNameStudent));
            int index = 1;
            System.out.printf("------------------- List after search and sort ------------------\n"
                    + "|%-5s|%-10s|%-25s|%-10s|%-9s|\n", "No.", "ID", "Name", "Semester", "Course");
            System.out.println("-----------------------------------------------------------------");
            for (Student s : sortTable) {
                System.out.printf("|%5d|%-10s|%-25s|%-10s|%-9s|\n", index++, s.getId(), s.getNameStudent(), s.getSemester(), s.getNameCourse());
            }
            System.out.println("+-----+----------+-------------------------+----------+---------+");
            return;
        }
    }

    /**
     * Updates or deletes a student based on their ID. Prompts the user for
     * actions to update the student details or delete the student from the
     * list.
     */
//    public void updateAndDelete() {
//        printTable();
//        System.out.print("----------Search ID----------\n");
//        String id = ce.inputId();
//        String indexId = searchStudent(id);
//        if (indexId != null) {
//            int[] numbersId = Arrays.stream(indexId.trim().split("\\s+"))
//                    .mapToInt(Integer::parseInt)
//                    .toArray();
//            while (true) {
//                int index = ce.inputIndex() - 1;
//                if (index < students.size() && Arrays.stream(numbersId).anyMatch(n -> n == index)) {
//                    Student s = students.get(index);
//                    String choice = ce.choice("Update (U) or Delete (D)? ", "Please enter the correct format!", true);
//                    if (choice.equalsIgnoreCase("U")) {
//                        while (true) {
//                            String semester = ce.inputSemester();
//                            if (!checkSemester(semester, id)) {
//                                String course = ce.inputNameCourse();
//                                if (browserStudent(id, semester, course)) {
//                                    System.out.println("This ID has registered this " + course + " in the semester:" + semester);
//                                    System.out.println("Please enter again!");
//                                } else {
//                                    s.setSemester(semester);
//                                    s.setNameCourse(course);
//                                    System.out.println("Update students successfully!");
//                                    break;
//                                }
//                            } else {
//                                System.out.println("Signed up all the course of this semester, please select another semester.");
//                            }
//                        }
//                    } else if (choice.equalsIgnoreCase("D")) {
//                        students.remove(s);
//                        System.out.println("Delete success: " + s);
//                    }
//                    printTable();
//                    String option = ce.choice("Continue (N) or Exit (E)? ", "Please enter the correct format!", false);
//                    if (option.equalsIgnoreCase("N")) {
//                        indexId = searchStudent(id);
//                        if (indexId != null) {
//                            numbersId = Arrays.stream(indexId.trim().split("\\s+"))
//                                    .mapToInt(Integer::parseInt)
//                                    .toArray();
//                        } else {
//                            System.out.println("No input ID found. ");
//                            return;
//                        }
//                    } else if (option.equalsIgnoreCase("E")) {
//                        System.out.println("Exited!");
//                        return;
//                    }
//                } else {
//                    System.out.println("Please enter the correct index !");
//                }
//            }
//        } else {
//            System.out.println("No input ID found. ");
//        }
//    }
    public void updateAndDelete() {
        List<Student> matched = new ArrayList<>();

        while (true) {
            String search = ce.inputSearchById().toLowerCase().trim();
            for (Student s : students) {
                if (s.getId().toLowerCase().contains(search)) {
                    matched.add(s);
                }
            }
            if (matched.isEmpty()) {
                System.out.println("No student found with this ID! Please search again.");
                continue;
            }

            // In danh sách kết quả tìm thấy
            System.out.printf("------------- Found Students Matching ID \"%s\" -------------\n", search);
            System.out.printf("|%-5s|%-10s|%-25s|%-10s|%-9s|\n", "No.", "ID", "Name", "Semester", "Course");
            System.out.println("-----------------------------------------------------------------");
            int index = 1;
            for (Student s : matched) {
                System.out.printf("|%5d|%-10s|%-25s|%-10s|%-9s|\n", index++, s.getId(), s.getNameStudent(), s.getSemester(), s.getNameCourse());
            }
            System.out.println("-----------------------------------------------------------------");

            // Chọn số thứ tự
            int choiceIndex = ce.inputIndex();
            if (choiceIndex < 1 || choiceIndex > matched.size()) {
                System.out.println("Invalid index. Try again.");
                continue;
            }

            Student selected = matched.get(choiceIndex - 1);
            String action = ce.choice("Update (U) or Delete (D)? ", "Please enter correct format!", true);
            if (action.equalsIgnoreCase("U")) {
                while (true) {
                    String semester = ce.inputSemester();
                    if (!checkSemester(semester, selected.getId())) {
                        String course = ce.inputNameCourse();
                        if (browserStudent(selected.getId(), semester, course)) {
                            System.out.println("This ID has already registered " + course + " in semester: " + semester);
                        } else {
                            selected.setSemester(semester);
                            selected.setNameCourse(course);
                            System.out.println("Updated successfully.");
                            break;
                        }
                    } else {
                        System.out.println("Already registered all courses for this semester. Try another.");
                    }
                }
            } else if (action.equalsIgnoreCase("D")) {
                students.remove(selected);
                System.out.println("Deleted: " + selected);
            }

            printTable();  // Show updated list

            String cont = ce.choice("Continue update/delete (Y) or Exit (N)? ", "Only Y/N allowed", false);
            if (cont.equalsIgnoreCase("N")) {
                return;
            }

            matched.clear(); // Xóa kết quả cũ để tìm lại kết quả mới
        }
    }

    /**
     * Searches for a student by their ID in the list of students. If found,
     * prints the position of the student in the list and returns their
     * index(es).
     *
     * @param id The ID of the student to search for.
     * @return A string containing the index(es) of the student in the list,
     * separated by spaces if multiple matches exist. Returns {@code null} if no
     * student with the given ID is found.
     */
    public String searchStudent(String id) {
        StringBuilder reIndex = new StringBuilder();
        System.out.printf("------------------- List after search and sort ------------------\n"
                + "|%-5s|%-10s|%-25s|%-10s|%-9s|\n", "No.", "ID", "Name", "Semester", "Course");
        System.out.println("-----------------------------------------------------------------");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                System.out.printf("|%5d|%-10s|%-25s|%-10s|%-9s|\n", (i + 1), students.get(i).getId(), students.get(i).getNameStudent(), students.get(i).getSemester(), students.get(i).getNameCourse());
                reIndex.append(i).append(" ");
            }
        }
        System.out.println("+-----+----------+-------------------------+----------+---------+");
        if (reIndex.length() == 0) {
            return null;
        }
        return reIndex.toString().trim();
    }

    /**
     * Adds a predefined list of 10 students to the system if the count is equal
     * to 1. This method prevents duplicate records from being added.
     *
     * @param count The trigger value to determine if students should be added.
     * - If count == 1, the method populates the list with 10 students.
     * Otherwise, it displays a message indicating that the records already
     * exist.
     */
//    public void recordStudent(int count) {
//        students.add(new Student("SE190111", "Nguyen Van A", "FA24", "Java"));
//        students.add(new Student("AI190112", "Tran Thi B", "FA24", ".Net"));
//        students.add(new Student("HM190120", "Nguyen Hong K", "SP25", "Java"));
//        students.add(new Student("TM190114", "Pham Hong D", "SP24", "Java"));
//        students.add(new Student("HM190120", "Nguyen Hong K", "FA24", "Java"));
//        students.add(new Student("SE190111", "Nguyen Van A", "FA24", ".Net"));
//        students.add(new Student("AI190117", "Vo Van G", "SU24", "Java"));
//        students.add(new Student("AI190112", "Tran Thi B", "SU24", ".Net"));
//        students.add(new Student("SE190111", "Nguyen Van A", "FA24", "C/C++"));
//        students.add(new Student("HM190120", "Nguyen Hong K", "FA23", "Java"));
//        System.out.println("-----10 students were created!-----");
//        printTable();
//    }
    /**
     * Prints the table of students in a formatted way.
     */
    public void printTable() {
        int index = 1;
        int maxNameLength = 4; // Minimum width for the "Name" column

        // Find the longest name length
        for (Student s : students) {
            if (s.getNameStudent().length() > maxNameLength) {
                maxNameLength = s.getNameStudent().length();
            }
        }

        // Print table header
        System.out.printf("-------------------- List Students --------------------\n");
        System.out.printf("|%-5s|%-10s|%-" + maxNameLength + "s|%-10s|%-9s|\n",
                "No.", "ID", "Name", "Semester", "Course");

        // Print a separator line
        int totalWidth = 5 + 10 + maxNameLength + 10 + 9 + 6; // total width including column separators
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Print each student's info
        for (Student s : students) {
            System.out.printf("|%5d|%-10s|%-" + maxNameLength + "s|%-10s|%-9s|\n",
                    index++, s.getId(), s.getNameStudent(), s.getSemester(), s.getNameCourse());
        }

        // Print closing separator line
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Generates a report of students, showing the total count of each
     * student-course combination.
     */
    public void report() {

        Map<String, Integer> courseCount = new HashMap<>();
        for (Student s : students) {
            String key = s.getId() + " - " + s.getNameStudent() + " - " + s.getNameCourse();
            courseCount.put(key, courseCount.getOrDefault(key, 0) + 1);
        }
        System.out.println("\n+-----+----------+---------------------------+--------+----------------+");
        System.out.println("| No. |    ID    |        Student Name       | Course | Total Courses |");
        System.out.println("+-----+----------+---------------------------+--------+----------------+");
        int index = 1;
        for (Map.Entry<String, Integer> entry : courseCount.entrySet()) {
            System.out.printf("| %3d |%-10s| %-25s | %-6s |          %5d|%n", index++, entry.getKey().split(" - ")[0], entry.getKey().split(" - ")[1], entry.getKey().split(" - ")[2], entry.getValue());
        }
        System.out.println("+-----+----------+---------------------------+--------+----------------+");
    }

    /**
     * Checks if a student with the given ID, semester, and course already
     * exists in the list.
     *
     * @param id The student's ID.
     * @param semester The semester as an integer.
     * @param course The course name.
     * @return true if a student with the same ID, semester, and course exists;
     * otherwise, false.
     */
    public boolean browserStudent(String id, String semester, String course) {
        for (Student s : students) {
            if (s.getId().equals(id) && s.getSemester().equals(semester) && s.getNameCourse().equals(course)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if all required courses (Java, .NET, C/C++) are present in the
     * given semester.
     *
     * @param semester The semester to check.
     * @param id The student ID registers that semester.
     * @return true if all three courses exist in the semester, false otherwise.
     */
    public boolean checkSemester(String semester, String id) {
        boolean hasJava = false, hasDotNet = false, hasCCpp = false;
        for (Student student : students) {
            if (student.getSemester().equalsIgnoreCase(semester) && student.getId().equalsIgnoreCase(id)) {
                switch (student.getNameCourse()) {
                    case "Java":
                        hasJava = true;
                        break;
                    case ".Net":
                        hasDotNet = true;
                        break;
                    case "C/C++":
                        hasCCpp = true;
                        break;
                }
            }
        }
        return hasJava && hasDotNet && hasCCpp;
    }

    /**
     * Check if the student ID exists on the list. If any, return that ID. If
     * not, return the error message.
     *
     * @param id Student ID needs to check.
     * @return student if found, or Null if not existed.
     */
    public Student checkId(String id) {
        for (Student st : students) {
            if (st.getId().equals(id)) {
                return st;
            }
        }
        return null;
    }

    /**
     * Check whether the student list is empty or not.
     *
     * @return True if the list of empty students, on the contrary, returns
     * False.
     */
    public boolean checkIsEmpty() {
        return students.isEmpty();
    }

    /**
     * Checks if the list of students has reached the limit (10) and prompts the
     * user to proceed or cancel.
     *
     * @return True if continuing, false if canceled.
     */
    public boolean checkList() {
        if (students.size() >= 10) {
            System.out.print("Do you want to order now (Y/N)?");
            while (true) {
                String choice = sc.nextLine().toLowerCase().trim();
                switch (choice) {
                    case "y": // Continue with the program
                        return true;
                    case "n": // Exit method
                        System.out.println("Cancellation of students!");
                        return false;
                    default:
                        System.out.println("Please enter the correct format!");
                }
            }
        }
        return true;
    }

    public static void printAllStudents(List<Student> students) {
        System.out.println("===== ALL STUDENT INFORMATION =====");
        if (students.isEmpty()) {
            System.out.println("No student data available.");
        } else {
            // In ra bảng trên console
            System.out.printf("%-10s%-20s%-15s%-30s\n", "ID", "Name", "Semester", "Courses");
            for (Student s : students) {
                System.out.printf("%-10s%-20s%-15s%-30s\n",
                        s.getId(), s.getNameStudent(), s.getSemester(), s.getNameCourse());
            }

            // Ghi vào file infor.txt
            try (PrintWriter writer = new PrintWriter(new FileWriter("infor.txt"))) {
                for (Student s : students) {
                    writer.printf("%s,%s,%s,%s\n",
                            s.getId(), s.getNameStudent(), s.getSemester(), s.getNameCourse());
                }
            } catch (Exception e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student s : students) {
                writer.printf("%s,%s,%s,%s\n",
                        s.getId(),
                        s.getNameStudent(),
                        s.getSemester(),
                        s.getNameCourse());
            }
            System.out.println("Student list saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        students.clear();  // Xóa danh sách cũ nếu có
        try (Scanner fileScanner = new Scanner(new java.io.File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",", -1); // Giữ cả ô rỗng nếu có
                if (parts.length == 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String semester = parts[2].trim();
                    String course = parts[3].trim();
                    students.add(new Student(id, name, semester, course));
                }
            }
            System.out.println("Student list loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void readFromFile(String fileName) {
        students.clear(); // Xóa danh sách cũ trước khi đọc mới
        try (Scanner fileScanner = new Scanner(new java.io.File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    students.add(new Student(parts[0], parts[1], parts[2], parts[3]));
                }
            }
            System.out.println("Read data from file successfully!");
            printTable(); // In danh sách sau khi đọc xong
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Displays a menu to the user for various student management operations.
     * Options include creating, searching, updating, deleting, and generating
     * reports.
     */
    public void menu() {
        int count = 0;
        while (true) {
            System.out.println("-----WELCOME TO STUDENT MANAGEMENT-----");
            System.out.println("1. Create");
            System.out.println("2. Find and Sort");
            System.out.println("3. Update/Delete");
            System.out.println("4. Report");
            System.out.println("5. Print all students and save to file");
            System.out.println("6. Exit");
            int choice = ce.add("          Please choose: ", "Pleaase enter correct format!");
            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    if (checkIsEmpty()) {
                        System.out.println("List of students are empty!");
                        break;
                    }
                    searchAndSort();
                    break;
                case 3:
                    if (checkIsEmpty()) {
                        System.out.println("List of students are empty!");
                        break;
                    }
                    updateAndDelete();
                    break;
                case 4:
                    if (checkIsEmpty()) {
                        System.out.println("Empty table! Please enroll.");
                        break;
                    }
                    report();
                    break;
                case 6: {
                    System.out.println("Exiting...");
                    return;
                }
                case 5: {
                    if (checkIsEmpty()) {
                        System.out.println("List of students are empty!");
                        break;
                    }
                    printAllStudents(students);
                    break;

                }
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
