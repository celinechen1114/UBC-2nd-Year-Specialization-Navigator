package ui;

import model.Event;
import model.EventLog;
import model.StudentProfile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Represents a student profile making application
public class MainGUI implements ActionListener {

    private static String pathToIcon = "./data/IMG_4527.PNG";

    private StudentProfile student;

    private StudentProfilePanel studentProfilePanel;
    private SaveAndLoadPanel saveAndLoadPanel;

    private JFrame f1;


    // EFFECTS: instantiate default student & display studentProfilePanel with text
    //          set up save and load panel
    //          set up the default main menu frame and display it
    public MainGUI() {
        studentProfilePanelSetUp();
        saveAndLoadPanelSetUp();
        f1 = new JFrame("Student Profile");
        frameSetUP();
    }

    // NOTE: following method is adapted from SpaceInvader
    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        f1.setLocation((scrn.width - f1.getWidth()) / 2, (scrn.height - f1.getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: set up the main menu frame(f1) and display it;
    //          print ubc label on right-hand corner, and
    //          add StudentProfilePanel and SaveAndLoadPanel to f1
    private void frameSetUP() {
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

        // Note: method adapted from Wayan on Kode
        // EFFECTS: Print EventLog
        // "Add window listener by implementing WindowAdapter class to
        // the frame instance. To handle the close event we just need
        // to implement the windowClosing() method."
        f1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });

        f1.pack();
        centreOnScreen();
        f1.setVisible(true);
    }



    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.print(next.toString() + "\n\n");
        }
    }

    // EFFECTS: instantiates default student profile and the StudentProfilePanel
    private void studentProfilePanelSetUp() {
        student = new StudentProfile("Celine", "Chen", 44176873);
        studentProfilePanel = new StudentProfilePanel(student);
    }

    // EFFECTS: instantiates SaveAndLoadPanel
    private void saveAndLoadPanelSetUp() {
        saveAndLoadPanel = new SaveAndLoadPanel(studentProfilePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // create the main menu Frame
    public static void main(String[] args) {
        new MainGUI();
    }
}
