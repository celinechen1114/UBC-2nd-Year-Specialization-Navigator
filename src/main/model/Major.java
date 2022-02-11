package model;

// Represents a major and its list of first-year prerequisites

import java.util.Arrays;
import java.util.List;

public class Major {
    private  String majorName;             // the name of the major
    private  Course prerequisites;   // list of first-year prerequisites for the major

    // prerequisites for biology major
    public static final Course BIOLOGY = new Course("BIOL 112");

    // prerequisites for chemistry major
    public static final Course CHEMISTRY = new Course("CHEM 123");

    // prerequisites for computer science major
    public static final Course COMPUTER_SCIENCE = new Course("CPSC 110");

    // prerequisites for math major
    public static final Course MATHEMATICS = new Course("MATH 101");


    public Major(String name) {
        majorName = name;
        prerequisiteHelper();
    }

    // helper method: assign corresponding prerequisites to the given major name
    public boolean prerequisiteHelper() {
        if (majorName.equals("Biology")) {
            prerequisites = BIOLOGY;
            return true;
        } else if (majorName.equals("Chemistry")) {
            prerequisites = CHEMISTRY;
            return true;
        } else if (majorName.equals("Computer Science")) {
            prerequisites = COMPUTER_SCIENCE;
            return true;
        } else if (majorName.equals("Mathematics")) {
            prerequisites = MATHEMATICS;
            return true;
        } else {
            return false;
        }
    }

    //getter methods
    public String getMajorName() {
        return majorName;
    }

    /*
        public static Major getMajor(String majorName) {
            return new Major(majorName);
        }
    */
    public Course getPrerequisites() {
        return prerequisites;
    }
}
