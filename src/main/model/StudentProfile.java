package model;

// Represents a Degree Navigator for first year UBC Science students having a student id, student first name,
// student last name, and a list of first year courses the student have taken or is taking right now

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.List;

public class StudentProfile {
    private String firstName;              // first name of the user (of this degree navigator)
    private String lastName;               // last name of the user
    private int id;                        // student id
    private ArrayList<Course> courseList;  // list of 1st year courses the student have taken or is taking right now
    private object major



    /*
     * REQUIRES: firstName and lastName has a non-zero length; id must be a length of 8 numbers
     * EFFECTS: name on profile is set to firstName; account id is an 8 digits
     *          positive integer not assigned to any other student profile;
     *          the courList is set to be empty until Courses are added into it.
     */

    public StudentProfile(String userFirstName, String userLastName, int studentId) {
        this.firstName = userFirstName;
        lastName = userLastName;
        id = studentId;
        this.courseList = new ArrayList<>();
    }

    /*
    REQUIRES:
    MODIFIES: this
    EFFECTS: add course given to the ArrayList
    */
    public void addCourse(Course singleCourse) {
        this.courseList.add(singleCourse);
    }


    /*
    REQUIRES:
    MODIFIES: this
    EFFECTS: check if the list of courses given satisfies the requirements of the specialization given
     */
    public boolean filter(Major major) {
        if (courseList.contains(major)) {
            return true;
        } else {
            return false;
        }
    }


    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }
}
