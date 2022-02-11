package model;

// Represents a Degree Navigator for first year UBC Science students having a student id, student first name,
// student last name, and a list of first year courses the student have taken

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.List;

public class StudentProfile {
    private String firstName;              // first name of the user (of this degree navigator)
    private String lastName;               // last name of the user
    private int id;                        // student id
    private List<Course> courseList;  // list of 1st year courses the student have taken
    private Major major;                   // the intended major the student is planning to apply



    /*
     * REQUIRES: firstName and lastName has a non-zero length; id must be a length of 8 numbers
     * EFFECTS: construct a student profile with name on profile set to firstName;
     *          an 8 digits number not assigned to any other student profile;
     *          and an empty courList until Courses are added into it.
     */

    public StudentProfile(String userFirstName, String userLastName, int studentId) {
        this.firstName = userFirstName;
        lastName = userLastName;
        id = studentId;
        this.courseList = new ArrayList<>();
    }

    /*
    MODIFIES: this
    EFFECTS: add course given to the list of courses the student have already taken
    */
    public void addCourse(String myCourseCode, int myCourseNumber) {
        this.courseList.add(new Course(myCourseCode, myCourseNumber));
    }

    /*
    MODIFIES: this
    EFFECTS: add the intended major the student is planning to apply to the student profile
    */
    public void addMajor(String myMajor) {
        this.major = new Major(myMajor);
    }


    /*
    MODIFIES: this
    EFFECTS: check if the list of courses given satisfies the requirements of the specialization given
     */
    public boolean checkEligibility() {
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

    public List<Course> getCourseList() {
        return courseList;
    }

    public Major getMajor() {
        return major;
    }
}
