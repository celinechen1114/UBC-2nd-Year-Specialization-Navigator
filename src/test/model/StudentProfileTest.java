package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentProfileTest {

    @Test //test Course Constructor
    public void CourseTest() {
        Course course = new Course("MATH",101);
        assertEquals("MATH", course.getCode());
        assertEquals(101, course.getNumber());
    }

    @Test //test StudentProfile Constructor
    public void StudentProfileTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873),);
        assertEquals("Celine",sp.getFirstName());
        assertEquals("Chen", sp.getLastName());
        assertEquals(44176873, sp.getId());
        assertEquals();
    }

    @Test //add a course to student profile
    public void addCourseTest() {
        StudentProfile sp = new StudentProfile("Celine", "Chen", 44176873),
        {new Course("MATH",101); new Course("BIOL",112)}
    }
}