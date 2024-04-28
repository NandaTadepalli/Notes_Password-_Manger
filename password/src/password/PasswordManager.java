package password;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PasswordManager extends JFrame {

    private DefaultTableModel model;
    private JTextField nameField, usernameField, passwordField, idField;

    public PasswordManager() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Password Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(90, 34, 34));

        JPanel allItemsPanel = new JPanel(new BorderLayout());
        model = new DefaultTableModel(new Object[][]{}, new String[]{"No.", "Name", "Username", "Password"});
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setBackground(new Color(44, 44, 44));
        table.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
        allItemsPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel addItemPanel = new JPanel(new BorderLayout());
        addItemPanel.setBackground(new Color(44, 44, 44));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(44, 44, 44));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(usernameLabel, gbc);
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(passwordField, gbc);

        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(255, 69, 0));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this::addButtonActionPerformed);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(addButton, gbc);

        addItemPanel.add(formPanel, BorderLayout.WEST);

        JPanel removeItemPanel = new JPanel(new FlowLayout());
        removeItemPanel.setBackground(new Color(44, 44, 44));

        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(Color.WHITE);
        removeItemPanel.add(idLabel);
        idField = new JTextField(5);
        removeItemPanel.add(idField);

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(new Color(255, 69, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(this::removeButtonActionPerformed);
        removeItemPanel.add(removeButton);

        addItemPanel.add(removeItemPanel, BorderLayout.EAST);

        tabbedPane.addTab("All Items", allItemsPanel);
        tabbedPane.addTab("Add Item", addItemPanel);

        add(tabbedPane);
        pack();
    }

    private void addButtonActionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!name.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
            model.addRow(new Object[]{model.getRowCount() + 1, name, username, password});
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeButtonActionPerformed(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            if (id > 0 && id <= model.getRowCount()) {
                model.removeRow(id - 1);
                renumberRows();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void renumberRows() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
        }
    }

    private void clearFields() {
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        idField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            new PasswordManager().setVisible(true);
        });
    }
}
