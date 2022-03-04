package persistence;

import model.Course;
import model.StudentProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StudentProfile sp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStudentProfile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudentProfile.json");
        try {
            StudentProfile sp = reader.read();
            assertEquals("first name", sp.getFirstName());
            assertEquals("last name", sp.getLastName());
            assertEquals(12345678, sp.getId());
            assertNull(sp.getMajor());
            assertEquals(0, sp.getCourseList().size());
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderTypicalStudentProfile() {
        JsonReader reader = new JsonReader("./data/testReaderTypicalStudentProfile.json");
        try {
            StudentProfile sp = reader.read();
            assertEquals("first name", sp.getFirstName());
            assertEquals("last name", sp.getLastName());
            assertEquals(12345678, sp.getId());
            List<Course> courseList = sp.getCourseList();
            assertEquals(2, courseList.size());
            checkCourse("MATH 101", courseList.get(0));
            checkCourse("BIOL 112", courseList.get(1));
        } catch (IOException e) {
            // pass
        }

    }
}
