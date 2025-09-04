// AddTask.java

// Import necessary libraries
package taskmanagerdesktopapp;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Addtask class responsible for creating the user interface components for adding tasks
class Addtask {
    // Instance variables to hold UI components
    private JPanel panel;
    private JTextField taskTextField;
    private JButton addButton;

    // Constructor to initialize the UI components
    public Addtask() {
        // Create a panel and set its layout to null
        panel = new JPanel();
        panel.setLayout(null);

        // Create a label for the task input
        JLabel label = new JLabel("Task:");
        label.setBounds(10, 10, 50, 20);

        // Create a text field for user input
        taskTextField = new JTextField();
        taskTextField.setBounds(70, 10, 200, 20);

        // Create an "Add Task" button with an icon
        ImageIcon icon = new ImageIcon("images/add.png");
        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        addButton = new JButton("Add Task", scaledIcon);
        addButton.setBounds(10, 40, 120, 30);

        // Set the margin to zero and remove focus border
        addButton.setMargin(new Insets(0, 0, 0, 0));
        addButton.setFocusPainted(false);

        // Add components to the panel
        panel.add(label);
        panel.add(taskTextField);
        panel.add(addButton);
    }

    // Getter methods to access UI components
    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTaskTextField() {
        return taskTextField;
    }

    public JButton getAddButton() {
        return addButton;
    }
}

