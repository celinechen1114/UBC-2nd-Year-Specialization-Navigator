package model;

// Represents a course having a name
public class Course {

    private String name;         // name of the course

    /*
    REQUIRES:


     */
    public Course(String courseName) {
        this.name = courseName;
    }

    // getters
    public String getName() {
        return name;
    }

}
