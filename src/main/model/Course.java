package model;

// Represents a course having a name
public class Course {

    private static String name;         // name of the course

    /*
    REQUIRES:


     */
    public Course(String courseName) {
        this.name = courseName;
    }

    // getters
    public static String getName(Course course) {
        return name;
    }

}
