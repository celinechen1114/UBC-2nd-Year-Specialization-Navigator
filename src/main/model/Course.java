package model;

// Represents a course having a subject code and a course number
public class Course {

    private static String code;         // subject code of the course
    private static int number;          // course number

    /*
    REQUIRES:


     */

    public Course(String subjectCode, int courseNumber) {
        this.code = subjectCode;
        this.number = courseNumber;
    }



    // getters
    public static String getCode(Course course) {
        return code;
    }

    public static int getNumber(Course course) {
        return number;
    }

}
