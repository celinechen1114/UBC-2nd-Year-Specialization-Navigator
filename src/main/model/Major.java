package model;

// Represents a major and its list of first-year prerequisites

import java.util.Arrays;
import java.util.List;

public class Major {
    private String majorName;             // the name of the major
    private List<Course> prerequisites;   // list of first-year prerequisites for the major

    // prerequisites for biology major
    public static final Course[] BIOLOGY = {new Course("CHEM", 121),
            new Course("CHEM", 123),
            new Course("BIOL", 140),
            new Course("BIOL", 112)};

    // prerequisites for chemistry major
    public static final Course[] CHEMISTRY = {new Course("CHEM", 121),
            new Course("CHEM", 123),
            new Course("MATH", 101)};

    // prerequisites for computer science major
    public static final Course[] COMPUTER_SCIENCE = {new Course("CPSC", 110)};

    // prerequisites for math major
    public static final Course[] MATHEMATICS = {new Course("MATH", 101)};


    public Major(String name) {
        this.majorName = name;
        this.prerequisites = (List<Course>) prerequisiteHelper(name);
    }

    // helper method: assign corresponding prerequisites to the given major name
    public Object prerequisiteHelper(String name) {
        if (name.equals("Biology")) {
            this.prerequisites = Arrays.asList(BIOLOGY);
            return true;
        } else if (name.equals("Chemistry")) {
            this.prerequisites = Arrays.asList(CHEMISTRY);
            return true;
        } else if (name.equals("Computer Science")) {
            this.prerequisites = Arrays.asList(COMPUTER_SCIENCE);
            return true;
        } else if (name.equals("Mathematics")) {
            this.prerequisites = Arrays.asList(MATHEMATICS);
            return true;
        } else {
            return false;
        }
    }

    //getter methods
    public String getMajorName() {
        return this.majorName;
    }

    public List<Course> getPrerequisites() {
        return this.prerequisites;
    }
}
