package gui;

import model.StudentProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//
// Bibliography: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
public class Main extends JFrame implements ActionListener {

    private static JButton BIOL;
    private static JButton CHEM;
    private static JButton CPSC;
    private static JButton MATH;

    private StudentProfile student;
    private JLabel major;
    private JPanel panel;
    private JPanel studentProfilePanel;



    public Main() {

        // the frame
        super("Student Profile");

        // default student & display studentProfilePanel with text
        studentProfileSetUp();

        // the clickable buttons
        majorButtonsSetUP();

        // the label
        major = new JLabel("No intended major chosen yet, please select from the following options");

        // the panel with the button and text
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        panel.add(major);
        panel.add(BIOL);
        panel.add(CHEM);
        panel.add(CPSC);
        panel.add(MATH);

        //set up the frame and display it
        frameSetUP();

    }


    @Override // process the button clicks
    //This is the method that is called when JButton CHEM, BIOL, CPSC or MATH is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("BIOL")) {
            major.setText("Biology has chosen to be your new intended major");
        } else if (e.getActionCommand().equals("CHEM")) {
            major.setText("Chemistry has chosen to be your new intended major");
        } else if (e.getActionCommand().equals("CPSC")) {
            major.setText("Computer Science has chosen to be your new intended major");
        } else if (e.getActionCommand().equals("MATH")) {
            major.setText("Mathematics has chosen to be your new intended major");
        }
    }

    // NOTE: following method is exactly adapted from SpaceInvader
    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // Effects: set up the frame and display it
    private void frameSetUP() {
        add(panel, BorderLayout.CENTER);
        add(studentProfilePanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        centreOnScreen();
        setVisible(true);
    }


    private void studentProfileSetUp() {
        student = new StudentProfile("your first name", "your last name", 12345678);
        studentProfilePanel = new StudentProfilePanel(student);
    }


    public void majorButtonsSetUP() {
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

    // create one Frame
    public static void main(String[] args) {
        new Main();
    }


}