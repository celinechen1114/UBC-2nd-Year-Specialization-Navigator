package persistence;

import model.Course;
import model.Major;
import model.StudentProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            StudentProfile sp = new StudentProfile("first name", "last name", 12345678);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStudentProfile() {
        try {
            StudentProfile sp = new StudentProfile("first name", "last name", 12345678);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStudentProfile.json");
            writer.open();
            writer.write(sp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStudentProfile.json");
            sp = reader.read();
            assertEquals("first name", sp.getFirstName());
            assertEquals("last name", sp.getLastName());
            assertEquals(12345678, sp.getId());
            assertNull(sp.getMajor());
            assertEquals(0, sp.getCourseList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterTypicalStudentProfile() {
        try {
            StudentProfile sp = new StudentProfile("first name", "last name", 12345678);
            sp.addCourse("MATH 101");
            sp.addCourse("BIOL 112");
            sp.addMajor("Biology");
            JsonWriter writer = new JsonWriter("./data/testWriterTypicalStudentProfile.json");
            writer.open();
            writer.write(sp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTypicalStudentProfile.json");
            sp = reader.read();
            Major myMajor = new Major("Biology");
            assertEquals("first name", sp.getFirstName());
            assertEquals("last name", sp.getLastName());
            assertEquals(12345678, sp.getId());
            assertEquals("Biology", myMajor.getMajorName());
            List<Course> courseList = sp.getCourseList();
            assertEquals(2, courseList.size());
            checkCourse("MATH 101", courseList.get(0));
            checkCourse("BIOL 112", courseList.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }


    }
}

