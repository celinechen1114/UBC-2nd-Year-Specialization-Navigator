package model;

// Represents a Degree Navigator for first year UBC Science students having a student id, student first name,
// student last name, and a list of first year courses the student have taken

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentProfile implements Writable {
    private String firstName;              // first name of the user (of this degree navigator)
    private String lastName;               // last name of the user
    private int id;                        // student id
    private List<Course> courseList;       // list of 1st year courses the student have taken
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
    public void addCourse(String myCourse) {
        this.courseList.add(new Course(myCourse));
    }

    /*
    MODIFIES: this
    EFFECTS: add the intended major the student is planning to apply to the student profile
    */
    public void addMajor(String myMajor) {
        this.major = new Major(myMajor);
    }

    /*
    EFFECTS: check if the list of courses given satisfies the requirements of the specialization given
             returns true if the prerequisites course exits on the student's completed courses
             returns false otherwise
     */
    public boolean checkEligibility() {
        List<String> myCourseNames = new ArrayList<String>();

        for (Course course:courseList) {
            String courseName = course.getName();
            myCourseNames.add(courseName);
        }

        Course prerequisiteCourse = major.getPrerequisites();
        String namePrerequisiteCourse = prerequisiteCourse.getName();

        if (myCourseNames.contains(namePrerequisiteCourse)) {
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
        return this.major;
    }

    //EFFECTS: get the major name of the major assigned to this profile
    public String getMajorName() {
        return major.getMajorName();
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
