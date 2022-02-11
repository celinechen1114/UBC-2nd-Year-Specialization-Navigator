package ui;

import model.StudentProfile;

import java.util.Scanner;

import static jdk.internal.dynalink.support.Guards.isNull;

public class CreateProfile {
    private StudentProfile student;
    private Scanner input;

    // EFFECTS: create a student profile
    public CreateProfile() {
        makeProfile();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void makeProfile() {
        boolean keepGoing = true;
        String command = null;

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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
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
        System.out.println("\tck -> check if I am eligible to apply for my intended major");
        System.out.println("\tq -> quit");
    }


    // EFFECTS: add an intended major to student profile
    private void doAddMajor() {
        System.out.print("Select the major you intend to apply to from the following majors");

        String selection = "";  // force entry into loop

        while (!(selection.equals("BIOL") || selection.equals("CHEM") || selection.equals("CPSC")
                || selection.equals("MATH"))) {
            System.out.println("\nBIOL for Biology");
            System.out.println("CHEM for Chemistry");
            System.out.println("CPSC for Computer Science");
            System.out.println("MATH for Mathematics");
            selection = input.next();
            selection = selection.toUpperCase();
        }

        if (selection.equals("BIOL")) {
            student.addMajor("Biology");
            System.out.println("Biology has chosen to be your intended major\n");
        } else if (selection.equals("CHEM")) {
            student.addMajor("Chemistry");
            System.out.println("Chemistry has chosen to be your intended major\n");
        } else if (selection.equals("CPSC")) {
            student.addMajor("Computer Science");
            System.out.println("Computer Science has chosen to be your intended major\n");
        } else if (selection.equals("MATH")) {
            student.addMajor("Mathematics");
            System.out.println("Mathematics has chosen to be your intended major\n");
        } else {
            System.out.println("Major does not exist\n");
        }
    }


    // EFFECTS: Change a new intended major to student profile
    // !!! Celine: later add in a function to ensure that you can not change to your current major
    private void doChangeMajor() {
        System.out.print("Select the major you want to change to from the following majors");
        String selection = "";  // force entry into loop
        while (!(selection.equals("BIOL") || selection.equals("CHEM") || selection.equals("CPSC")
                || selection.equals("MATH"))) {
            System.out.println("BIOL for Biology");
            System.out.println("CHEM for Chemistry");
            System.out.println("CPSC for Computer Science");
            System.out.println("MATH for Mathematics");
        }
        selection = input.next();
        if (selection.equals("BIOL")) {
            student.addMajor("Biology");
            System.out.println("\n Biology has chosen to be your new intended major\n");
        } else if (selection.equals("CHEM")) {
            student.addMajor("Chemistry");
            System.out.println("Chemistry has chosen to be your new intended major\n");
        } else if (selection.equals("CPSC")) {
            student.addMajor("Computer Science");
            System.out.println("Computer Science has chosen to be your new intended major\n");
        } else if (selection.equals("MATH")) {
            student.addMajor("Mathematics");
            System.out.println("Mathematics has chosen to be your new intended major\n");
        } else {
            System.out.println("Major does not exist\n");
        }
    }

    // EFFECTS: add a course to the list of courses the student have already completed
    private void doAddCourse() {
        System.out.print("\n Enter the course name in the format of 4 letter subject code,");
        System.out.print("followed by a space, and then the 3 digit course number");
        System.out.print("for example: MATH 100 \n");
        String courseName = input.next();
        if (8 == courseName.length()) {
            student.addCourse(courseName);
            System.out.print("\n" + courseName + " successfully added");
        } else {
            System.out.print("\n course name invalid");
        }

    }

    private void doCheckEligibility() {
        if (student.getCourseList().isEmpty()) {
            System.out.print("\n courses you have already completed need to be added first");
        } else {
            if (student.checkEligibility()) {
                System.out.print("\n you are eligible to apply for you intended major this May");
            } else {
                System.out.print("\n you are not eligible to apply for you intended major this May");
            }
        }

    }
    ////(student.getMajor().isEmpty()) {
    //            System.out.print("\n an intended major need to be added first");
    //            return;
    //        } else

}
