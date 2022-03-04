package persistence;

import model.Course;
import model.Major;
import model.StudentProfile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads student profile from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StudentProfile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses student profile from JSON object and returns it
    private StudentProfile parseWorkRoom(JSONObject jsonObject) {
        String firstname = jsonObject.getString("first name");
        String lastname = jsonObject.getString("last name");
        int id = jsonObject.getInt("id");

        StudentProfile sp = new StudentProfile(firstname, lastname, id);
        addCourses(sp, jsonObject);
        addMajor(sp, jsonObject);
        return sp;
    }

    // MODIFIES: sp
    // EFFECTS: parses major from JSON object and adds them to student profile
    private void addMajor(StudentProfile sp, JSONObject jsonObject) {
        String majorName = jsonObject.getString("intended major");
        sp.addMajor(majorName);
    }


    // MODIFIES: sp
    // EFFECTS: parses courses from JSON object and adds them to student profile
    private void addCourses(StudentProfile sp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("course list");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(sp, nextCourse);
        }
    }

    // MODIFIES: sp
    // EFFECTS: parses course from JSON object and adds it to student profile
    private void addCourse(StudentProfile sp, JSONObject jsonObject) {
        String courseName = jsonObject.getString("course name");
        sp.addCourse(courseName);
    }
}
