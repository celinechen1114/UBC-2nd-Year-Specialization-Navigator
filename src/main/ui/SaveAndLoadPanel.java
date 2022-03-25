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

public class SaveAndLoadPanel extends JPanel implements ActionListener {

    private static final String JSON_STORE = "./data/studentProfile.json";

    private StudentProfilePanel spp;
    private StudentProfile sp;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton save;
    private JButton load;

    private JFrame f4;


    private JPanel panel;

    // Effects: Constructs a student profile:
    //          sets the background colour and draws the initial labels;
    //          updates this with courses and major added
    public SaveAndLoadPanel(StudentProfilePanel spp) {

        this.spp = spp;
        this.sp = spp.getSp();

        setLayout(new GridLayout(3, 1));
        setBackground(new Color(240, 240, 250));
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

    public void addSaveMessageFrame() {
        JButton button = new JButton("OK");
        button.setActionCommand("OK");
        button.addActionListener(this);

        // make a label indicating if users is eligible to apply for the chosen intended major or not
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

    public void addLoadMessageFrame() {
        JButton button = new JButton("OK");
        button.setActionCommand("OK");
        button.addActionListener(this);

        // make a label indicating if users is eligible to apply for the chosen intended major or not
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
            spp.updateCoursesLabelMessage(this.sp);
            spp.updateMajorLabelMessage(this.sp);
        }
    }
}
