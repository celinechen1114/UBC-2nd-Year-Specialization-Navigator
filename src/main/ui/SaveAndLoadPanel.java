package ui;

import model.StudentProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a class that saves information input in the student profile panel
// and load
public class SaveAndLoadPanel extends JPanel implements ActionListener {

    private static final String JSON_STORE = "./data/studentProfile.json";

    private StudentProfilePanel spp;
    private StudentProfile sp;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton save;
    private JButton load;

    private JFrame f4;

    // Effects: Constructs a student profile:
    //          sets the background colour and draws the initial labels;
    //          updates this with courses and major added
    public SaveAndLoadPanel(StudentProfilePanel spp) {
        this.spp = spp;
        this.sp = spp.getSp();

        setLayout(new GridLayout(3, 1));
        setBackground(new Color(230, 230, 250));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));

        // the clickable button
        save = new JButton("Save");
        save.setActionCommand("save");
        save.addActionListener(this);
        add(save);

        load = new JButton("load");
        load.setActionCommand("load");
        load.addActionListener(this);
        add(load);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: saves the student profile to file
    public void saveStudentProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(sp);
            jsonWriter.close();
            System.out.println("Saved " + sp.getFirstName() + sp.getLastName() + " to " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads student file from file
    public void loadStudentProfile() {
        try {
            sp = jsonReader.read();
            System.out.println("Loaded " + sp.getFirstName() + sp.getLastName() + " from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + "\n");
        }
    }

    //EFFECTS: Create a pop-up frame with indicating the file is successfully saved
    public void addSaveMessageFrame() {
        JButton button = new JButton("OK");
        button.setActionCommand("OK");
        button.addActionListener(this);

        JLabel message = new JLabel("File successfully saved");

        // make a new panel with text, text field, and button
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(message);
        panel.add(button);

        // make a new Frame
        f4 = new JFrame("System Message");
        f4.add(panel, BorderLayout.CENTER);
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f4.pack();
        f4.setVisible(true);
    }

    //EFFECTS: Create a pop-up frame with indicating the file is successfully loaded
    public void addLoadMessageFrame() {
        JButton button = new JButton("OK");
        button.setActionCommand("OK for Load");
        button.addActionListener(this);

        JLabel message = new JLabel("File successfully loaded");

        // make a new panel with text, text field, and button
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(message);
        panel.add(button);

        // make a new Frame
        f4 = new JFrame("System Message");
        f4.add(panel, BorderLayout.CENTER);
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f4.pack();
        f4.setVisible(true);
    }


    // EFFECTS: when save button is clicked, save current student profile(sp) to file, and load pop-up frame
    //          when load button is clicked, load the sp on file,
    //          load pop-up frame and display the loaded sp after the "OK" button is clicked
    //          and automatically close the pop-frame when "OK' button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            saveStudentProfile();
            addSaveMessageFrame();
        } else if (e.getActionCommand().equals("load")) {
            loadStudentProfile();
            addLoadMessageFrame();
        } else if (e.getActionCommand().equals("OK")) {
            f4.dispose();
        } else if (e.getActionCommand().equals("OK for Load")) {
            spp.updateCoursesLabelMessage(this.sp);
            spp.updateMajorLabelMessage(this.sp);
            f4.dispose();
        }
    }
}
