// Task.java

// Import necessary libraries
package taskmanagerdesktopapp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

// Task class representing an individual task in the to-do list
class Task {
    // Instance variables to hold UI components and parent panel reference
    private JPanel taskPanel;
    private JCheckBox checkBox;
    private JLabel taskLabel;
    private JPanel parentPanel; // Field to store the parent panel reference

    // Constructor to initialize the task with text and parent panel reference
    public Task(String taskText, JPanel parentPanel) {
        this.parentPanel = parentPanel; // Store the parent panel reference
        taskPanel = new JPanel();
        taskPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create a checkbox and a label for the task
        checkBox = new JCheckBox();
        taskLabel = new JLabel(taskText);

        // Add an ActionListener to the checkbox for updating task status and moving completed tasks
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTaskStatus();
                moveCompletedTaskToEnd();
            }
        });

        // Add components to the task panel
        taskPanel.add(checkBox);
        taskPanel.add(taskLabel);

        // Add a right-click menu for deleting and renaming the task
        JPopupMenu popupMenu = new JPopupMenu();
        
        // Add a "Delete" option to the right-click menu
        popupMenu.add("Delete").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });
        
        // Add a "Rename" option to the right-click menu
        popupMenu.add("Rename").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renameTask();
            }
        });

        // Add a mouse listener to show the menu on right-click
        taskPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(taskPanel, e.getX(), e.getY());
                }
            }
        });
    }

    // Method to update the visual status of the task based on the checkbox
    private void updateTaskStatus() {
        if (checkBox.isSelected()) {
            // If the checkbox is selected, set visual properties for completed tasks
            taskLabel.setForeground(Color.GRAY);
            taskLabel.setFont(new Font(taskLabel.getFont().getName(), Font.ITALIC, taskLabel.getFont().getSize()));
        } else {
            // If the checkbox is not selected, set visual properties for incomplete tasks
            taskLabel.setForeground(Color.BLACK);
            taskLabel.setFont(new Font(taskLabel.getFont().getName(), Font.PLAIN, taskLabel.getFont().getSize()));
        }
    }

    // Method to move a completed task to the end of the list
    private void moveCompletedTaskToEnd() {
        if (checkBox.isSelected()) {
            // If the task is completed, move it to the end of the list
            parentPanel.remove(taskPanel);
            parentPanel.add(taskPanel);
            parentPanel.revalidate();
            parentPanel.repaint();
        }
    }

    // Method to delete the task
    private void deleteTask() {
        // Remove the task from the parent panel
        parentPanel.remove(taskPanel);
        parentPanel.revalidate();
        parentPanel.repaint();
    }

    // Method to rename the task
    private void renameTask() {
        // Prompt the user to enter a new task name
        String newTaskText = JOptionPane.showInputDialog(null, "Enter new task name:", taskLabel.getText());
        // If the user provides a valid input, update the task label
        if (newTaskText != null && !newTaskText.isEmpty()) {
            taskLabel.setText(newTaskText);
        }
    }

    // Getter method to access the task panel
    public JPanel getTaskPanel() {
        return taskPanel;
    }
}

