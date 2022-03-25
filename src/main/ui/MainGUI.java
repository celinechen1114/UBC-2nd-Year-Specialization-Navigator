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

//
// Bibliography: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
public class MainGUI implements ActionListener {

    private static String pathToIcon = "./data/IMG_4527.PNG";

    private StudentProfile student;

    private StudentProfilePanel studentProfilePanel;
    private SaveAndLoadPanel saveAndLoadPanel;

    private JFrame f1;


    public MainGUI() {

        // instantiate default student & display studentProfilePanel with text
        studentProfileSetUp();

        // set up save and load panel
        saveAndLoadPanelSetUp();

        // set up the default main menu frame and display it
        f1 = new JFrame("Student Profile");
        frameSetUP();


    }


    @Override
    public void actionPerformed(ActionEvent e) {
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

        // print ubc label on the frame one !!!
        BufferedImage img = new BufferedImage(240, 240, BufferedImage.TYPE_3BYTE_BGR);
        try {
            img = ImageIO.read(new File(pathToIcon));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(img);
        JLabel ubcLabel = new JLabel(icon);

        f1.add(ubcLabel, BorderLayout.EAST);
        f1.add(studentProfilePanel, BorderLayout.CENTER);
        f1.add(saveAndLoadPanel, BorderLayout.AFTER_LAST_LINE);
        f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //f1.add(ubcLabel);

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
        saveAndLoadPanel = new SaveAndLoadPanel(studentProfilePanel);
    }

    // create one Frame
    public static void main(String[] args) {
        new MainGUI();
    }
}
