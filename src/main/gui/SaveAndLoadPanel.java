package gui;

import model.StudentProfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAndLoadPanel extends JPanel implements ActionListener {
    JButton save;
    JButton load;

    // Effects: Constructs a student profile:
    //          sets the background colour and draws the initial labels;
    //          updates this with courses and major added
    public SaveAndLoadPanel(StudentProfile sp) {

        // the clickable button
        save = new JButton("Save");
        save.addActionListener(this);

        load = new JButton("load");
        load.addActionListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
