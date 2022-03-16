package GUI;

import model.StudentProfile;

import javax.swing.*;
import java.awt.*;

// Represents a class of panels displaying different information in the student profile
public class StudentProfilePanel extends JPanel {

    private JLabel name;
    private JLabel id;
    private JLabel courses;

    // Effects: Constructs a student profile:
    //          sets the background colour and draws the initial labels;
    //          updates this with courses and major added
    public StudentProfilePanel(StudentProfile sp) {
        name = new JLabel(sp.getFirstName() + " " + sp.getLastName());
        id = new JLabel(String.valueOf(sp.getId()));
        courses = new JLabel(String.valueOf(sp.getCourseList()));
        setBackground(new Color(100, 210, 200));

        makePanel();

    }

    //
    private void makePanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridLayout(0, 1));
        add(name);
        add(id);
        add(courses);
    }
}
