import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JPanel {

    // Declare the components as fields
    private JLabel welcomeLabel;
    private JButton notesButton;
    private JButton passwordsButton;

    // Constructor to initialize the components and add them to the panel
    public HomePage() {
        // Set the layout of the panel to a grid with 3 rows and 1 column
        setLayout(new GridLayout(3, 1));

        // Create the welcome label with some text and font
        welcomeLabel = new JLabel("<html><div style='font-family:sans-serif; font-size:18px; border: 7px solid #2bc7b3; padding: 10px;'>Welcome to My Application<hr style='border-top: 3px dashed #8c8b8b; margin-round: px;'></div></html>");
        welcomeLabel.setFont(new Font("sans-serif", Font.TRUETYPE_FONT, 60));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create the notes button with some text and an action listener
        notesButton = new JButton("Notes");
        notesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Notes page
                openNotesPage();
            }
        });

        // Create the passwords button with some text and an action listener
        passwordsButton = new JButton("Passwords");
        passwordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Passwords page
                openPasswordsPage();
            }
        });

        // Add the components to the panel
        add(welcomeLabel);
        add(notesButton);
        add(passwordsButton);
    }

    // Method to open the Notes page
    private void openNotesPage() {
        // Write your code here to open the Notes page
        System.out.println("Notes page opened");
        
        
       
;    }

    // Method to open the Passwords page
    private void openPasswordsPage() {
        // Write your code here to open the Passwords page
        System.out.println("Passwords page opened");
        
    }
}
