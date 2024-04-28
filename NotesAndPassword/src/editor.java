import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class editor extends JFrame implements ActionListener {
    // Text component
    JTextArea t;
    // Line numbers component
    JTextArea lines;

    // Frame
    JFrame f;

    // Constructor
    editor() {
        // Create a frame
        f = new JFrame("Editor");

        try {
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        // Text component
        t = new JTextArea();
        // Line numbers component
        lines = new JTextArea("1");
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        lines.setFocusable(false);

        // Create a scroll pane for the text area
        JScrollPane scroll = new JScrollPane(t);
        JScrollPane scrollLines = new JScrollPane(lines);

        // Synchronize scrolling of text area and line numbers
        scroll.getVerticalScrollBar().addAdjustmentListener(e -> {
            scrollLines.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getValue());
        });

        // Add listener to update line numbers
        t.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = t.getDocument().getLength();
                Element root = t.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }

            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
        });

        // Create a menubar
        JMenuBar mb = new JMenuBar();

        // Create a menu for file
        JMenu m1 = new JMenu("File");

        // Create menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create a menu for edit
        JMenu m2 = new JMenu("Edit");

        // Create menu items
        JMenuItem mi4 = new JMenuItem("Cut");
        JMenuItem mi5 = new JMenuItem("Copy");
        JMenuItem mi6 = new JMenuItem("Paste");

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        // Create a menu for mode
        JMenu m3 = new JMenu("Mode");

        // Create menu items for mode
        JMenuItem mi7 = new JMenuItem("Light Mode");
        JMenuItem mi8 = new JMenuItem("Dark Mode");

        // Add action listener
        mi7.addActionListener(this);
        mi8.addActionListener(this);

        m3.add(mi7);
        m3.add(mi8);

        JMenuItem mc = new JMenuItem("Close");
        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(mc);

        f.setJMenuBar(mb);
        f.add(scrollLines, BorderLayout.WEST);
        f.add(scroll, BorderLayout.CENTER);
        f.setSize(500, 500);
        f.setVisible(true);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Cut")) {
            t.cut();
        } else if (s.equals("Copy")) {
            t.copy();
        } else if (s.equals("Paste")) {
            t.paste();
        } else if (s.equals("Save")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write
                    w.write(t.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "The user cancelled the operation");
        } else if (s.equals("Print")) {
            try {
                // Print the file
                t.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // String
                    String s1 = "", sl = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    // Initialize sl
                    sl = br.readLine();

                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text
                    t.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "The user cancelled the operation");
        } else if (s.equals("New")) {
            t.setText("");
        } else if (s.equals("Light Mode")) {
            setLightMode();
        } else if (s.equals("Dark Mode")) {
            setDarkMode();
        } else if (s.equals("Close")) {
            f.setVisible(false);
        }
    }

    private void setLightMode() {
        t.setBackground(Color.WHITE);
        t.setForeground(Color.BLACK);
        lines.setBackground(Color.LIGHT_GRAY);
    }

    private void setDarkMode() {
        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);
        lines.setBackground(Color.DARK_GRAY);
    }

    // Main class
    public static void main(String args[]) {
        editor e = new editor();
    }
}
