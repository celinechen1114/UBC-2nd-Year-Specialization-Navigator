package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a Degree Navigator for first year UBC Science students having a student id, student first name,
// student last name, and a list of first year courses the student have taken

public class StudentProfile implements Writable {
    private String firstName;              // first name of the user (of this degree navigator)
    private String lastName;               // last name of the user
    private int id;                        // student id
    private List<Course> courseList;       // list of 1st year courses the student have taken
    private String major;                   // the intended major the student is planning to apply

    // Added in Phase three
    String myCourseList;      //courseList printed in String form



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
    public void addCourse(String myCourse) {
        this.courseList.add(new Course(myCourse));
        EventLog.getInstance().logEvent(new Event("Course " + myCourse + " has added to " + firstName + "'s profile."));
    }

    /*
    MODIFIES: this
    EFFECTS: add the intended major the student is planning to apply to the student profile
    */
    public void addMajor(String myMajor) {
        this.major = myMajor;
        EventLog.getInstance().logEvent(new Event(myMajor + " has assigned to be " + firstName + "'s intended major."));
    }

    /*
    EFFECTS: check if the list of courses given satisfies the requirements of the specialization given
             returns true if the prerequisites course exits on the student's completed courses
             returns false otherwise
     */
    public boolean checkEligibility() {
        List<String> myCourseNames = new ArrayList<>();
        Major myMajor = new Major(major);
        for (Course course : courseList) {
            String courseName = course.getName();
            myCourseNames.add(courseName);
        }

        Course prerequisiteCourse = myMajor.getPrerequisites();
        String namePrerequisiteCourse = prerequisiteCourse.getName();

        boolean eligibility = myCourseNames.contains(namePrerequisiteCourse);

        if (eligibility) {
            EventLog.getInstance().logEvent(new Event(firstName + " is eligible to apply for " + major));
        } else {
            EventLog.getInstance().logEvent(new Event(firstName + " is NOT eligible to apply for " + major));
        }

        return eligibility;
    }


    //EFFECTS:
    public String makeMyCourseList() {      // added in phase 3
        String myCourseList = null;

        for (Course c : courseList) {
            myCourseList += c.getName() + "  ";
        }

        return myCourseList;
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

    public String getMajor() {
        return this.major;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("first name", firstName);
        json.put("last name", lastName);
        json.put("id", id);
        json.put("course list", courseListToJson());
        json.put("intended major", major);
        return json;
    }

    // EFFECTS: returns course list in this student profile as a JSON array
    private JSONArray courseListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courseList) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
