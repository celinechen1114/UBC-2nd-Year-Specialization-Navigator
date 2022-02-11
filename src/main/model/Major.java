package model;

// Represents a major and its list of first-year prerequisites

import java.util.ArrayList;

public class Major {
    private String major;      //


    //prerequisites for biology major
    public static final Course[] BIOLOGY = {new Course("CHEM", 121),
            new Course("CHEM", 123),
            new Course("BIOL", 140),
            new Course("BIOL", 112)};

    //prerequisites for chemistry major
    public static final Course[] CHEMISTRY = {new Course("CHEM", 121),
            new Course("CHEM", 123),
            new Course("MATH", 101)};

    //prerequisites for computer science major
    public static final Course[] COMPUTER_SCIENCE = {new Course("CPSC", 110)};

    //prerequisites for math major
    public static final Course[] MATHEMATICS = {new Course("MATH", 101)};

}
