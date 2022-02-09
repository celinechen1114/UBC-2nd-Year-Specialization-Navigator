package model;

// Represents a course having a subject code and a course number
public class Course {

    private String code;         // subject code of the course
    private int number;          // course number

    /*
    REQUIRES:


     */

    public Course(String subjectCode, int courseNumber, int courseCredit) {
        this.code = subjectCode;
        this.number = courseNumber;
    }

    // getters
    public String getCode() {
        return code;
    }

    public int getNumber() {
        return number;
    }

}
