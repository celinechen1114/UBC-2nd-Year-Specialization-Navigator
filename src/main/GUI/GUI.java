package GUI;

import model.StudentProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//
// Bibliography: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
public class GUI extends JFrame implements ActionListener {

    private StudentProfile student;
    private int count = 0;
    private JLabel major;
    private JPanel panel;
    private JPanel studentProfilePanel;
    private JButton button;

    public GUI() {

        // the frame
        super("Student Profile");

        // default student
        student = new StudentProfile("your first name", "your last name", 12345678);

        // the clickable button
        button = new JButton("Add/Change Intended Major");
        button.addActionListener(this);

        // the label
        major = new JLabel("No intended major");

        // the panel with the button and text
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(major);

        // the studentProfilePanel with text
        studentProfilePanel = new StudentProfilePanel(student);

        // set up the frame and display it
        add(panel, BorderLayout.CENTER);
        add(studentProfilePanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        centreOnScreen();
        setVisible(true);

    }



    @Override // process the button clicks
    public void actionPerformed(ActionEvent e) {
        count++;
        major.setText("Number of clicks: " + count);
    }

    // NOTE: following method is exactly adapted from SpaceInvader
    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // create one Frame
    public static void main(String[] args) {
        new GUI();
    }


}
