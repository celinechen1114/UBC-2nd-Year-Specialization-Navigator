package gui;

import model.StudentProfile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a class of panels displaying different information in the student profile
public class StudentProfilePanel extends JPanel {


    private JLabel firstName;
    private JLabel lastName;
    private JLabel id;
    private JLabel courses;

    private JLabel myFirstName;
    private JLabel myLastName;
    private JLabel myId;
    private JLabel myCourses;


    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField idField;

    // Effects: Constructs a student profile:
    //          sets the background colour and draws/initiates the initial labels;
    //          updates this with courses and major added
    public StudentProfilePanel(StudentProfile sp) {
        setLayout(new GridLayout(3, 1));
        setBackground(new Color(100, 210, 200));

        // make defaulted version of student profile set up message
        firstName = new JLabel("First name:");
        lastName = new JLabel("Last name:");
        id = new JLabel("Student id:");
        courses = new JLabel("Courses you have completed:");

        makeFirstNamePanel();
        makeLastNamePanel();
        makeIdField();
        coursesField();
    }


    private void makeFirstNamePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(1, 2));
        add(firstName);

        firstNameField = new JTextField(5);
        setBorder(new EmptyBorder(13, 13, 13, 13));
        add(firstNameField);

        myFirstName = new JLabel();
        myFirstName.setText(firstNameField.getText());
        add(myFirstName);
    }

    private void makeLastNamePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));
        add(lastName);

        lastNameField = new JTextField(5);
        add(lastNameField);
        setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
    }

    private void makeIdField() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));
        add(id);

        idField = new JTextField(5);
        add(idField);
        setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
    }

    private void coursesField() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridLayout(0, 1));
        add(courses);

    }


}
