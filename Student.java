/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l01;

/**
 * L01 - Create a Java console program to manage students.
 *
 * @author QuyenTTTCE200993
 */
public class Student {

    private String id;
    private String nameStudent;
    private String semester;
    private String nameCourse;

    /**
     * Constructor initiates Student with basic attributes
     *
     * @param id Student ID
     * @param nameStudent Student's full name
     * @param semester Current semester
     * @param nameCourse Enrolled course name (.Net, Java, C/C++)
     */
    public Student(String id, String nameStudent, String semester, String nameCourse) {
        this.id = id;
        this.nameStudent = nameStudent;
        this.semester = semester;
        this.nameCourse = nameCourse;
    }

    /**
     * Retrieves the ID of the student.
     *
     * @return the student ID as a String
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id id the new student ID as a String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the student.
     *
     * @return the student's name as a String
     */
    public String getNameStudent() {
        return nameStudent;
    }

    /**
     * Sets the name of the student.
     *
     * @param nameStudent the new name of the student as a String
     */
    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    /**
     * Retrieves the semester of the student.
     *
     * @return the current semester as an integer
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester of the student.
     *
     * @param semester the new semester as an integer
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Retrieves the name of the course.
     *
     * @return the course name as a String
     */
    public String getNameCourse() {
        return nameCourse;
    }

    /**
     * Sets the name of the course.
     *
     * @param nameCourse the new course name as a String
     */
    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    /**
     * Returns a string representation of the student, including their ID, name,
     * semester, and course name.
     *
     * @return a formatted string containing the student's ID, name, semester,
     * and course name
     */
    @Override
    public String toString() {
        return id + " - " + nameStudent + " - Semester " + semester + " - " + nameCourse;
    }

}
