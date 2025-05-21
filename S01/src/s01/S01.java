package s01;

import java.util.Scanner;

/**
 * S01 - Manage student
 *
 * @author QuyenTTTCE200993
 */
public class S01 {

    /**
     * Main class to run the student management program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("1. Enter student list");  //print Enter student list
            System.out.println("2. Look up student"); //print Look up student
            System.out.println("3. Display student list"); //print Display student list
            System.out.println("4. Exit"); //print Exit
            System.out.print("Please choose menu (1 – 4): "); //print Please choose menu (1 – 4):
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manager.enterStudent();
                    break;
                case "2":
                    manager.lookupStudent();
                    break;
                case "3":
                    manager.displayStudentList();
                    break;
                case "4":
                    System.out.println("Exiting program");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }
        }
    }
}
