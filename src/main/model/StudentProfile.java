package model;

// Represents a Degree Navigator for UBC Science students having a student id, student name (last name, first name)
// and list of future courses intended to take

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.List;

public class StudentProfile {
    private String firstName;        // first name of the user (of this degree navigator)
    private String lastName;         // last name of the user
    private int id;                  // student id
    private ArrayList<Course> courseList;  // list of future courses the user intended to take


    /*
    REQUIRES: ArrayList is not be empty;
    EFFECTS:
     */

    public StudentProfile(String userFirstName, String userLastName, int studentId, ArrayList<Course> courses) {
        this.firstName = userFirstName;
        lastName = userLastName;
        id = studentId;
        this.courseList = courses;
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
    public void filter() {
        for (Course course : courseList) {
            //
        }
    }
}
