package ui;

import model.StudentProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// making a  student profile application
// NOTE: Methods in the class are referenced from the repositories TellerApp and JsonSerializationDemo
public class CreateProfile {
    private static final String JSON_STORE = "./data/studentProfile.json";
    private StudentProfile student;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: create a student profile
    public CreateProfile() throws FileNotFoundException {
        input = new Scanner(System.in);
        student = new StudentProfile("first name", "last name", 12345678);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        makeProfile();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void makeProfile() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("ac")) {    // move the arrangement of this later behind doChangeMajor
            doAddCourse();
        } else if (command.equals("am")) {
            doAddMajor();
        } else if (command.equals("cm")) {
            doChangeMajor();
        } else if (command.equals("ck")) {
            doCheckEligibility();
        } else if (command.equals("s")) {
            saveStudentProfile();
        } else if (command.equals("l")) {
            loadStudentProfile();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes student profile
    private void init() {
        student = new StudentProfile("Celine", "Chen", 44176873);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tam -> add intended major");
        System.out.println("\tcm -> change intended major");
        System.out.println("\tac -> add a completed first year course");
        System.out.println("\tck -> check eligible to apply for the intended major");
        System.out.println("\ts -> save student profile to file");
        System.out.println("\tl -> load student profile from file");
        System.out.println("\tq -> quit");
    }


    // EFFECTS: add an intended major to student profile
    private void doAddMajor() {
        System.out.println("\nSelect from:");
        System.out.println("BIOL for Biology");
        System.out.println("CHEM for Chemistry");
        System.out.println("CPSC for Computer Science");
        System.out.println("MATH for Mathematics");
        String selection = input.next();
        selection = selection.toUpperCase();


        if (selection.equals("BIOL")) {
            student.addMajor("Biology");
            System.out.println("\n Biology has chosen to be your intended major\n");
        } else if (selection.equals("CHEM")) {
            student.addMajor("Chemistry");
            System.out.println("\n Chemistry has chosen to be your intended major\n");
        } else if (selection.equals("CPSC")) {
            student.addMajor("Computer Science");
            System.out.println("\n Computer Science has chosen to be your intended major\n");
        } else if (selection.equals("MATH")) {
            student.addMajor("Mathematics");
            System.out.println("\n Mathematics has chosen to be your intended major\n");
        } else {
            System.out.println("\n Major does not exist\n");
        }
    }


    // EFFECTS: Change a new intended major to student profile
    private void doChangeMajor() {
        System.out.print("Select the major you want to change to from the following majors \n");

        System.out.println("BIOL for Biology");
        System.out.println("CHEM for Chemistry");
        System.out.println("CPSC for Computer Science");
        System.out.println("MATH for Mathematics");

        String selection = input.next();
        selection = selection.toUpperCase();

        if (selection.equals("BIOL")) {
            student.addMajor("Biology");
            System.out.println("\n Biology has chosen to be your new intended major\n");
        } else if (selection.equals("CHEM")) {
            student.addMajor("Chemistry");
            System.out.println("\n Chemistry has chosen to be your new intended major\n");
        } else if (selection.equals("CPSC")) {
            student.addMajor("Computer Science");
            System.out.println("\n Computer Science has chosen to be your new intended major\n");
        } else if (selection.equals("MATH")) {
            student.addMajor("Mathematics");
            System.out.println("\n Mathematics has chosen to be your new intended major\n");
        } else {
            System.out.println("\n Major does not exist\n");
        }
    }

    // EFFECTS: add a course to the list of courses the student have already completed
    private void doAddCourse() {
        System.out.print("\n Enter the course name in the format of 4 letter subject code,");
        System.out.print("followed by a space, and then the 3 digit course number");
        System.out.print("\n For example: MATH 100 \n");
        String courseName = input.next();
        if (8 == courseName.length()) {
            student.addCourse(courseName);
            System.out.print("\n" + courseName + " successfully added\n");
        } else {
            System.out.print("\n course name invalid\n");
        }

    }

    // EFFECTS: check if the student is eligible to apply for their intended major
    private void doCheckEligibility() {
        if (student.getCourseList().isEmpty()) {
            System.out.print("\n courses you have already completed need to be added first\n");
        } else if (student.getMajor() == null) {
            System.out.print("\n an intended major need to be added first\n");
        } else if (student.checkEligibility()) {
            System.out.print("\n you are eligible to apply for you intended major this May\n");
        } else {
            System.out.print("\n you are not eligible to apply for you intended major this May\n");
        }
    }


    // EFFECTS: saves the student profile to file
    private void saveStudentProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(student);
            jsonWriter.close();
            System.out.println("Saved " + student.getFirstName() + student.getLastName() + " to " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads student file from file
    private void loadStudentProfile() {
        try {
            student = jsonReader.read();
            System.out.println("Loaded " + student.getFirstName() + " from " + JSON_STORE);
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + "\n");
        }
    }

}
