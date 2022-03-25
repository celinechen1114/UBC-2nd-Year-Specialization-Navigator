package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//
class StudentProfileTest {

    @Test //test Course Constructor
    public void CourseTest() {
        Course course = new Course("MATH 101");
        assertEquals("MATH 101", course.getName());
    }

    @Test //test StudentProfile Constructor
    public void StudentProfileConstructorTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);
        assertEquals("Celine", sp.getFirstName());
        assertEquals("Chen", sp.getLastName());
        assertEquals(44176873, sp.getId());
    }

    @Test //add a course to student profile
    public void addCourseTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);
        sp.addCourse("MATH 101");
        sp.addCourse("BIOL 112");
        List<Course> courseList = sp.getCourseList();
        assertEquals(2, courseList.size());
        assertEquals("MATH 101", courseList.get(0).getName());
        assertEquals("BIOL 112", courseList.get(1).getName());
    }

    @Test //add a Biology major to student profile
    public void addBiologyMajorTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);

        sp.addMajor("Biology");
        assertEquals("Biology", sp.getMajor());

        Major myMajor = new Major(sp.getMajor());
        assertTrue(myMajor.prerequisiteHelper());
    }

    @Test //add a Chemistry major to student profile
    public void addChemistryMajorTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);

        sp.addMajor("Chemistry");
        assertEquals("Chemistry", sp.getMajor());

        Major myMajor = new Major(sp.getMajor());
        assertTrue(myMajor.prerequisiteHelper());
    }

    @Test //add a Math major to student profile
    public void addMathMajorTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);
        sp.addMajor("Mathematics");
        assertEquals("Mathematics", sp.getMajor());

        Major myMajor = new Major(sp.getMajor());
        assertTrue(myMajor.prerequisiteHelper());
    }

    @Test //add a non-existing major to student profile
    public void addFailedMajorTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);
        sp.addMajor("Law");

        Major myMajor = new Major(sp.getMajor());
        assertFalse(myMajor.prerequisiteHelper());
    }

    @Test //check if the student is eligible to apply for the major given the courses the student have taken
    // [false case]
    public void checkEligibilityFalseTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);
        sp.addMajor("Computer Science");
        sp.addCourse("MATH 101");
        sp.addCourse("BIOL 112");
        assertFalse(sp.checkEligibility());
    }


    @Test //check if the student is eligible to apply for the major given the courses the student have taken
    // [true case]
    public void checkEligibilityTrueTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);
        sp.addMajor("Computer Science");
        sp.addCourse("CPSC 110");
        sp.addCourse("BIOL 112");
        assertTrue(sp.checkEligibility());
    }

    @Test //method added in phase three:
    public void checkMakeMyCourseListTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873);
        sp.addCourse("CPSC 110");
        sp.addCourse("BIOL 112");
        assertEquals("CPSC 110  BIOL 112  ",sp.makeMyCourseList());
    }


}