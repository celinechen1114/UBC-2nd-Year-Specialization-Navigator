package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a course having a name
public class Course implements Writable {

    private String name;         // name of the course

    /*
    REQUIRES: courseName must be 8 characters long
    MODIFIES: this
    EFFECT: construct a course with the given course name.
     */
    public Course(String courseName) {
        this.name = courseName;
    }

    // getters
    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course name", name);
        return json;
    }
}
