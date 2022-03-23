package gui;

import model.StudentProfile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a class of panels displaying different information in the student profile
public class StudentProfilePanel extends JPanel implements ActionListener {

    private StudentProfile sp;

    private JLabel firstName;
    private JLabel lastName;
    private JLabel id;
    private JLabel courses;

    private JButton addCourses;

    private JTextField myCourse;
    private JFrame f2;

    // Effects: Constructs a student profile:
    //          sets the background colour and draws/initiates the initial labels;
    //          updates this with courses and major added
    public StudentProfilePanel(StudentProfile sp) {
        this.sp = sp;
        setLayout(new GridLayout(3, 1));
        setBackground(new Color(100, 210, 200));

        // make defaulted version of student profile set up message
        firstName = new JLabel("First Name: " + sp.getFirstName());
        lastName = new JLabel("Last Name: " + sp.getLastName());
        id = new JLabel("Student ID: " + sp.getId());
        courses = new JLabel("Courses Completed: " + courseListDisplayHelper());

        makeFirstNamePanel();
        makeLastNamePanel();
        makeIdField();
        coursesField();
        makeAddCourseButton();
    }


    private String courseListDisplayHelper() {
        if (sp.getCourseList().isEmpty()) {
            return "courses needed to be added first";
        } else {
            return String.valueOf(sp.getCourseList());
        }
    }

    private void makeFirstNamePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(1, 2));
        add(firstName);

    }

    private void makeLastNamePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));
        add(lastName);

    }

    private void makeIdField() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));
        add(id);

    }

    private void coursesField() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridLayout(0, 1));
        add(courses);

    }

    private void makeAddCourseButton() {
        addCourses = new JButton("Click here to add a completed course to your course list");
        addCourses.setActionCommand("AddCourse");
        addCourses.addActionListener(this);
        add(addCourses);
    }

    @Override
    // process the button clicks
    //This is the method that is called when JButton CHEM, BIOL, CPSC or MATH is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("AddCourse")) {
            addCourseFrame();
        } else if (e.getActionCommand().equals("add")) {
            // REQUIRES the course name input is correctly entered
            sp.addCourse(myCourse.getText());
            f2.dispose();
            courses = new JLabel("Courses Completed: " + courseListDisplayHelper());
        }
    }



    private void addCourseFrame() {
        JButton button = new JButton("add");
        button.setActionCommand("add");
        button.addActionListener(this);

        // make a label indicating user to add course
        JLabel addCourseDescription = new JLabel(" Enter the course name in the format of 4 letter subject code, "
                + "followed by a space, and then the 3 digit course number." + " For example: MATH 100 ");

        // make a new text field
        myCourse = new JTextField(5);

        // make a new panel with text, text field, and button
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(addCourseDescription);
        panel.add(myCourse);
        panel.add(button);

        // make a new Frame
        f2 = new JFrame("Add your completed course");
        f2.add(panel, BorderLayout.CENTER);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setTitle("GUI");
        f2.pack();
        f2.setVisible(true);
    }
}
