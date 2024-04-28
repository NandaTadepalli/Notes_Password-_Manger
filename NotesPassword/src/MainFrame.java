import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    // Declare the home page panel as a field
    private HomePage homePage;
    private EditorPage editor;
    private PasswordManagerPage passwordManager;

    // Constructor to initialize the frame and add the home page panel to it
    public MainFrame() {
        // Set the title of the frame
        setTitle("My Desktop Application");

        // Create the home page panel
        homePage = new HomePage();
        editor = new EditorPage();
        passwordManager = new PasswordManagerPage();

        // Add the home page panel to the frame
        add(homePage);

        // Set the size of the frame
        setSize(800, 600);

        // Set the location of the frame to the center of the screen
        setLocationRelativeTo(null);

        // Set the default close operation of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to switch to the editor page
    public void showEditorPage() {
        getContentPane().removeAll();
        getContentPane().add(editor);
        revalidate();
        repaint();
    }

    // Method to switch to the password manager page
    public void showPasswordManagerPage() {
        getContentPane().removeAll();
        getContentPane().add(passwordManager);
        revalidate();
        repaint();
    }

    // Main method to create and show the frame
    public static void main(String[] args) {
        // Set look and feel to Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the frame
        MainFrame frame = new MainFrame();

        // Make the frame visible
        frame.setVisible(true);
    }
}

class HomePage extends JPanel {

    // Buttons for the home page
    private JButton editorButton, passwordManagerButton;

    // Constructor to initialize the home page panel
    public HomePage() {
        // Set the layout to BorderLayout
        setLayout(new BorderLayout());

        // Create buttons
        editorButton = new JButton("Editor");
        passwordManagerButton = new JButton("Password Manager");

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(editorButton);
        buttonPanel.add(passwordManagerButton);

        // Add button panel to the center of the home page panel
        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners to the buttons
        editorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the editor page
                ((MainFrame) SwingUtilities.getWindowAncestor(HomePage.this)).showEditorPage();
            }
        });

        passwordManagerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the password manager page
                ((MainFrame) SwingUtilities.getWindowAncestor(HomePage.this)).showPasswordManagerPage();
            }
        });
    }
}

class EditorPage extends JPanel {
    public EditorPage() {
        JLabel label = new JLabel("This is the Editor Page");
        add(label);
    }
}

class PasswordManagerPage extends JPanel {
    public PasswordManagerPage() {
        JLabel label = new JLabel("This is the Password Manager Page");
        add(label);
    }
}