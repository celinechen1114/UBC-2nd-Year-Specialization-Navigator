package ui;

import model.StudentProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//
// Bibliography: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
public class MainGUI implements ActionListener {

    private static JButton BIOL;
    private static JButton CHEM;
    private static JButton CPSC;
    private static JButton MATH;

    private StudentProfile student;

    private JLabel major;
    private JPanel panel;
    private JPanel studentProfilePanel;
    private JPanel saveAndLoadPanel;

    private JFrame f1;


    public MainGUI() {

        // instantiate default student & display studentProfilePanel with text
        studentProfileSetUp();

        // set up the major options button
        majorButtonsSetUP();

        // instantiate default label for when intended major hasn't chosen yet
        major = new JLabel("No intended major chosen yet, please select from the following options");

        // set up the panel with the clickable major option buttons and text
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        panel.add(major);
        panel.add(BIOL);
        panel.add(CHEM);
        panel.add(CPSC);
        panel.add(MATH);

        // set up save and load panel
        saveAndLoadPanelSetUp();

        // set up the default main menu frame and display it
        f1 = new JFrame("Student Profile");
        frameSetUP();

    }


    @Override
    // process the button clicks
    //This is the method that is called when JButton CHEM, BIOL, CPSC or MATH is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("BIOL")) {
            student.addMajor("Biology");
            major.setText("Biology has chosen to be your intended major");
        } else if (e.getActionCommand().equals("CHEM")) {
            student.addMajor("Chemistry");
            major.setText("Chemistry has chosen to be your intended major");
        } else if (e.getActionCommand().equals("CPSC")) {
            student.addMajor("Computer Science");
            major.setText("Computer Science has chosen to be your intended major");
        } else if (e.getActionCommand().equals("MATH")) {
            student.addMajor("Mathematics");
            major.setText("Mathematics has chosen to be your intended major");
        }
    }

    // NOTE: following method is adapted from SpaceInvader
    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        f1.setLocation((scrn.width - f1.getWidth()) / 2, (scrn.height - f1.getHeight()) / 2);
    }

    // EFFECTS: set up the main menu frame and display it
    private void frameSetUP() {
        f1.add(panel, BorderLayout.CENTER);
        f1.add(studentProfilePanel, BorderLayout.NORTH);
        f1.add(saveAndLoadPanel, BorderLayout.AFTER_LAST_LINE);
        f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f1.pack();
        centreOnScreen();
        f1.setVisible(true);
    }


    // default student profile setup
    private void studentProfileSetUp() {
        student = new StudentProfile("Celine", "Chen", 44176873);
        studentProfilePanel = new StudentProfilePanel(student);
    }

    private void saveAndLoadPanelSetUp() {
        saveAndLoadPanel = new SaveAndLoadPanel(student);
    }

    //
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

    public void setStudent(StudentProfile sp) {
        this.student = sp;
    }
    // create one Frame
    public static void main(String[] args) {
        new MainGUI();
    }
}
