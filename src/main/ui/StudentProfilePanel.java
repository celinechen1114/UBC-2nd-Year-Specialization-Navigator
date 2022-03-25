package ui;

import model.StudentProfile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Represents a class of panels displaying different information in the student profile
public class StudentProfilePanel extends JPanel implements ActionListener {

    private static String pathToMajorMessage = "./data/IMG_4536.JPG";
    private static String pathToCongratsMessage = "./data/IMG_4523.JPG";

    private StudentProfile sp;

    // default frame setups
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

    // MajorPanel and the clickable buttons
    private JLabel major;
    private static JButton BIOL;
    private static JButton CHEM;
    private static JButton CPSC;
    private static JButton MATH;


    // Effects: Constructs a student profile (sp):
    //          sets the background colour and draws/initiates the initial labels;
    //          updates this with courses and major added
    public StudentProfilePanel(StudentProfile sp) {
        this.sp = sp;
        setLayout(new GridLayout(5, 1));
        setBackground(new Color(212, 250, 250));

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
        makeMajorPanel();
    }

    // MODIFIES: this
    // EFFECTS: make a panel that displays the first name of the sp
    public void makeFirstNamePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 2));
        add(firstName);
    }

    // MODIFIES: this
    // EFFECTS: make a panel that displays the last name of the sp
    public void makeLastNamePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));
        add(lastName);
    }

    // MODIFIES: this
    // EFFECTS: make a panel that displays the id of the sp
    public void makeIdPanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));
        add(id);
    }

    // MODIFIES: this
    // EFFECTS: make a panel that displays the completed courses added to the sp
    public void makeCoursesPanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridLayout(0, 1));
        add(courses);
    }

    // MODIFIES: this
    // EFFECTS: make a button that adds a completed course to the sp
    public void makeAddCourseButton() {
        addCourses = new JButton("1) Click here to add a completed course to your course list");
        addCourses.setActionCommand("AddCourse");
        addCourses.addActionListener(this);
        add(addCourses);
    }

    // MODIFIES: this
    // EFFECTS: make a button that checks the eligibility to apply for the intended major
    public void makeCheckEligibilityButton() {
        checkEligibility = new JButton("3) Click here to check the eligibility to apply for your intended major");
        checkEligibility.setActionCommand("checkEligibility");
        checkEligibility.addActionListener(this);
        add(checkEligibility);
    }


    /*
     methods below related to add Courses
      */

    // MODIFIES: this
    // EFFECTS: construct a pop-up frame that allow user to add a completed course to sp
    public void addCourseFrame() {
        JButton button = new JButton("add");
        button.setActionCommand("add");
        button.addActionListener(this);
        JButton button2 = new JButton("cancel");
        button2.setActionCommand("cancel");
        button2.addActionListener(this);


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
        panel.add(button2);

        // make a new Frame
        f2 = new JFrame("Add Your Completed UBC Courses");
        f2.add(panel, BorderLayout.CENTER);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.pack();
        f2.setVisible(true);
    }

    // EFFECTS: returns the completed courses added to sp, or the message of no courses has added yet
    public String courseListDisplayHelper() {
        if (sp.getCourseList().isEmpty()) {
            return "Courses needed to be ADDED first";
        } else {
            return sp.makeMyCourseList();
        }
    }

    // MODIFIES: this
    // Effects: updating/displaying courses completed of the given student
    public void updateCoursesLabelMessage(StudentProfile sp) {
        this.sp = sp;
        courses.setText("Courses Completed: " + courseListDisplayHelper());
    }

    /*
    methods below related to check Eligibility
     */

    // MODIFIES: this
    // EFFECTS: construct a pop-up frame that display whether the user is
    //         eligible to apply for the chosen intended major or not
    public void addEligibilityFrame() {
        JButton button = new JButton("OK");
        button.setActionCommand("OK");
        button.addActionListener(this);

        // make a label indicating if users is eligible to apply for the chosen intended major or not
        JLabel eligibilityStatus = new JLabel();
        eligibilityStatus.setText(doCheckEligibilityMessage());

        // make a new panel with text, text field, and button
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        // display the congrats Image if the user is eligible
        panel.add(doCheckEligibilityImage());

        panel.add(eligibilityStatus);
        panel.add(button);

        // make a new Frame
        f3 = new JFrame("Eligibility To Apply For The Intended Major");
        f3.add(panel, BorderLayout.CENTER);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f3.pack();
        f3.setVisible(true);
    }


    // EFFECTS: return a congrats Image if the user is eligible, else display an empty label
    public JLabel doCheckEligibilityImage() {
        JLabel empty = new JLabel();
        if (sp.getCourseList().isEmpty()) {
            return empty;
        } else if (sp.getMajor() == null) {
            return empty;
        } else if (sp.checkEligibility()) {
            return makeImageHelper(pathToCongratsMessage);
        } else {
            return empty;
        }
    }

    // EFFECTS: check if the sp is eligible to apply for the intended major, and return corresponding messages
    public String doCheckEligibilityMessage() {
        if (sp.getCourseList().isEmpty()) {
            return "Courses you have already completed NEED TO BE ADDED first";
        } else if (sp.getMajor() == null) {
            return "An intended major NEED TO BE CHOSEN first";
        } else if (sp.checkEligibility()) {
            return "You are ELIGIBLE to apply for your intended major this May! :) ";
        } else {
            return "You are NOT ELIGIBLE to apply for your intended major this May! :( ";
        }
    }

      /*
    methods below related to creating the major panel and buttons
     */

    // EFFECTS: constructs a panel displays the intended major status and possible major options to choose from
    public void makeMajorPanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        setLayout(new GridLayout(0, 1));

        // spacing label
        JLabel space = new JLabel("          ");

        // instantiate default label for when intended major hasn't chosen yet
        major = new JLabel("2) No intended major chosen yet, please SELECT from the following options");
        majorButtonsSetUP();

        add(space);
        add(makeImageHelper(pathToMajorMessage));
        add(major);
        add(BIOL);
        add(CHEM);
        add(CPSC);
        add(MATH);
    }

    // MODIFIES: this
    // EFFECTS: instantiate major option buttons; allow user to choose from BIOL, CHEM, CPSC and MATH
    private void majorButtonsSetUP() {
        BIOL = new JButton("BIOL");
        BIOL.setActionCommand("BIOL");
        BIOL.addActionListener(this);

        CHEM = new JButton("CHEM");
        CHEM.setActionCommand("CHEM");
        CHEM.addActionListener(this);

        CPSC = new JButton("CPSC");
        CPSC.setActionCommand("CPSC");
        CPSC.addActionListener(this);

        MATH = new JButton("MATH");
        MATH.setActionCommand("MATH");
        MATH.addActionListener(this);
    }

    //EFFECTS: accepts the path to an image file and returns it as JLabel
    private JLabel makeImageHelper(String pathToFile) {
        BufferedImage img = new BufferedImage(20, 20, BufferedImage.TYPE_3BYTE_BGR);
        try {
            img = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(img);
        return new JLabel(icon);
    }

    //MODIFIES: this
    //EFFECTS: accepts the path to an image file and returns it as JLabel
    public void updateMajorLabelMessage(StudentProfile sp) {
        if (sp.getMajor() == null) {
            major.setText("2) No intended major chosen yet, please SELECT from the following options");
        } else {
            major.setText("2)" + sp.getMajor() + " has chosen to be your intended major~ ");
        }
    }

    //MODIFIES: this
    //EFFECTS: display a success message after choosing a major
    public void addMajorHelper(String majorName) {
        sp.addMajor(majorName);
        major.setText("You have chosen " + majorName + " to be your intended major~");
    }

    //getter methods
    public StudentProfile getSp() {
        return sp;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: process the button clicks;
    // This is the method is called when JButton AddCourse, add, checkEligibility, OK
    //  CHEM, BIOL, CPSC or MATH is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("AddCourse")) {
            addCourseFrame();
        } else if (e.getActionCommand().equals("add")) {
            // REQUIRES: the course name input is valid and correctly entered
            sp.addCourse(myCourse.getText());
            f2.dispose();
            updateCoursesLabelMessage(sp);
        } else if (e.getActionCommand().equals("checkEligibility")) {
            addEligibilityFrame();
        } else if (e.getActionCommand().equals("OK")) {
            f3.dispose();
        } else if (e.getActionCommand().equals("BIOL")) {
            addMajorHelper("Biology");
        } else if (e.getActionCommand().equals("CHEM")) {
            addMajorHelper("Chemistry");
        } else if (e.getActionCommand().equals("CPSC")) {
            addMajorHelper("Computer Science");
        } else if (e.getActionCommand().equals("MATH")) {
            addMajorHelper("Mathematics");
        } else if (e.getActionCommand().equals("cancel")) {
            f2.dispose();
        }
    }
}
