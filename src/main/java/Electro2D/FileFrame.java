package main.java.Electro2D;
/**
 * Electro2D.FileFrame.java
 * <p>
 * This class encapsulates all the functionality required to pop up a frame
 * and load protein data from a file.
 *
 * @author John Manning
 * @author Jill Zapoticznyj
 * @author Adam Bazinet
 */

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.*;

import main.java.Utilities.GenomeFileParser;
import main.java.Utilities.MessageFrame;

public class FileFrame extends JFrame implements ActionListener {

    /** variables for the file reading pop-up frame **/
    private Electro2D electro2D;          //reference to calling applet
    private WindowListener ffwl;          //listen for window closing, etc.
    private int fileNum;
    private final String directoryString = "." + File.separator + ".." + File.separator + "data";
    private JTextArea instructions;
    private JLabel select;
    private JComboBox choice;
    private JButton button;
    private JPanel center;
    private JPanel south;
    private String[] sa;

    public FileFrame(Electro2D e, int i) {

        fileNum = i;
        electro2D = e;

        setTitle("Load Protein Data File");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        instructions = new JTextArea();
        instructions.append(
                "Instructions: Select the name of the file that contains your protein sequence data.\n" +
                        "Please note: Some files may take longer to load.");
        instructions.setEditable(false);
        instructions.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

        select = new JLabel("Select Filename: ", JLabel.RIGHT);

        String[] files = {"file1", "file2", "file3", "file4"};
        choice = new JComboBox();
        for (String f : files) choice.addItem(f);

        button = new JButton("Load");
        button.addActionListener(this);

        // layout

        getContentPane().add(instructions, BorderLayout.NORTH);

        center = new JPanel();
        center.add(select);
        center.add(choice);

        getContentPane().add(center, BorderLayout.CENTER);

        south = new JPanel();
        south.add(button);

        getContentPane().add(south, BorderLayout.SOUTH);

        pack();
        refreshFileList();
    }

    public void refreshFileList() {

        choice.removeAllItems();
        File fl = new File("data");

        if (!fl.exists()) {
            System.err.println("Warning: No data files found!");
            fl.mkdir();
        }

        sa = fl.list(new ImageFilter());

        for (int file = 0; file < sa.length; file++) {
            choice.addItem(sa[file]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        // change the cursor image
        setCursor(new Cursor(Cursor.WAIT_CURSOR));

        //try to read the contents of the file
        loadFile();

        // set the cursor image back to normal
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        // display the protein titles from the file
        if (fileNum == 1) {
            electro2D.refreshProteinList();
        } else if (fileNum == 2) {
            electro2D.refreshProteinList2();
        }

        //close the frame
        dispose();

        refreshFileList();
    }

    public void loadFile() {

        //first, get filename from textbox
        String filename = sa[choice.getSelectedIndex()];

        if (filename == null || filename.equals("")) {
            MessageFrame error = new MessageFrame();
            error.setMessage("Please enter a file name.");
            error.setVisible(true);
        } else {

            String extension = filename.substring(filename.lastIndexOf(".") + 1);

            // if the file's extention is not one of the supported types
            // display an error message
            if (!extension.equalsIgnoreCase("faa") &&
                    !extension.equalsIgnoreCase("fasta") &&
                    !extension.equalsIgnoreCase("pdb") &&
                    !extension.equalsIgnoreCase("gbk") &&
                    !extension.equalsIgnoreCase("e2d")) {

                MessageFrame error = new MessageFrame();
                error.setMessage("File extension is not valid.");
                error.setVisible(true);
            } else {

                //call the proper method to read the file depending on
                // its type
                if (extension.equalsIgnoreCase("faa") ||
                        extension.equalsIgnoreCase("fasta")) {

                    GenomeFileParser.fastaParse(filename, electro2D, "", fileNum);

                } else if (extension.equalsIgnoreCase("pdb")) {

                    GenomeFileParser.pdbParse(filename, electro2D, "", fileNum);

                } else if (extension.equalsIgnoreCase("gbk")) {

                    GenomeFileParser.gbkParse(filename, electro2D, "", fileNum);

                } else if (extension.equalsIgnoreCase("e2d")) {
                    GenomeFileParser.e2dParse(filename, electro2D, "", fileNum);
                }

                JOptionPane.showMessageDialog(null, "Proteins loaded.");

            }
        }
    }
}

