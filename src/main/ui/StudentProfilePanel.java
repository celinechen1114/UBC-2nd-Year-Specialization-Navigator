package ui;

import model.StudentProfile;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a class of panels displaying different information in the student profile
public class StudentProfilePanel extends JPanel implements ActionListener {

    private StudentProfile sp;

    // default frame
    private JLabel firstName;
    private JLabel lastName;
    private JLabel id;
    private JLabel courses;

    private JButton addCourses;            // allows user to add a course to course list
    private JButton checkEligibility;      // checks if the user is eligible to apply to the chosen intended major

    // pop up frame for the addCourses button
    private JFrame f2;
    private JTextField myCourse;

    // pop up frame for the checkEligibility button
    private JFrame f3;


    // Effects: Constructs a student profile:
    //          sets the background colour and draws/initiates the initial labels;
    //          updates this with courses and major added
    public StudentProfilePanel(StudentProfile sp) {
        this.sp = sp;
        setLayout(new GridLayout(3, 1));
        setBackground(new Color(100, 210, 200));

        // instantiate defaulted version of student profile & displayed messages
        firstName = new JLabel("First Name: " + sp.getFirstName());
        lastName = new JLabel("Last Name: " + sp.getLastName());
        id = new JLabel("Student ID: " + sp.getId());
        courses = new JLabel("Courses Completed: " + courseListDisplayHelper());

        // make the student profile panel & add the clickable buttons
        makeFirstNamePanel();
        makeLastNamePanel();
        makeIdPanel();
        makeCoursesPanel();
        makeAddCourseButton();
        makeCheckEligibilityButton();
    }


    public String courseListDisplayHelper() {
        if (sp.getCourseList().isEmpty()) {
            return "courses needed to be added first";
        } else {
            return sp.makeMyCourseList();
        }
    }

    public void makeFirstNamePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(1, 2));
        add(firstName);

    }

    public void makeLastNamePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));
        add(lastName);

    }

    public void makeIdPanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));
        add(id);

    }

    public void makeCoursesPanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridLayout(0, 1));
        add(courses);

    }

    public void makeAddCourseButton() {
        addCourses = new JButton("Click here to add a completed course to your course list");
        addCourses.setActionCommand("AddCourse");
        addCourses.addActionListener(this);
        add(addCourses);
    }

    public void makeCheckEligibilityButton() {
        checkEligibility = new JButton("Click here to check the eligibility to apply for your intended major");
        checkEligibility.setActionCommand("checkEligibility");
        checkEligibility.addActionListener(this);
        add(checkEligibility);
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
            updateCoursesLabelMessage(sp);
        } else if (e.getActionCommand().equals("checkEligibility")) {
            addEligibilityFrame();
        } else if (e.getActionCommand().equals("OK")) {
            f3.dispose();
        }
    }


    public void addCourseFrame() {
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
        f2 = new JFrame("Add Your Completed UBC Course");
        f2.add(panel, BorderLayout.CENTER);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.pack();
        f2.setVisible(true);
    }

    public void updateCoursesLabelMessage(StudentProfile sp) {
        this.sp = sp;
        courses.setText("Courses Completed: " + courseListDisplayHelper());
    }

    public void addEligibilityFrame() {
        JButton button = new JButton("OK");
        button.setActionCommand("OK");
        button.addActionListener(this);

        // make a label indicating if users is eligible to apply for the choose intended major or not
        JLabel eligibilityStatus = new JLabel();
        eligibilityStatus.setText(doCheckEligibility());


        // make a new panel with text, text field, and button
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(eligibilityStatus);
        panel.add(button);

        // make a new Frame
        f3 = new JFrame("Eligibility To Apply For The Intended Major");
        f3.add(panel, BorderLayout.CENTER);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f3.pack();
        f3.setVisible(true);
    }

    public String doCheckEligibility() {
        if (sp.getCourseList().isEmpty()) {
            return "\n courses you have already completed need to be added first\n";
        } else if (sp.getMajor() == null) {
            return "\n an intended major need to be added first\n";
        } else if (sp.checkEligibility()) {
            return "\n you are eligible to apply for you intended major this May\n";
        } else {
            return "\n you are not eligible to apply for you intended major this May\n";
        }
    }
}
