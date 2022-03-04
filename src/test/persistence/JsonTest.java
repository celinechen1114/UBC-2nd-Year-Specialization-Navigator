package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(String courseName, Course course) {
        assertEquals(courseName, course.getName());
    }
}
