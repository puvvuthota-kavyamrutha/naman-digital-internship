/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taskmanagerdesktopapp;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kavya
 */
public class TaskManagerDesktopApp {
    // Static variable to hold the panel containing tasks
    private static JPanel taskPanel;

    // Main method to start the application
    public static void main(String[] args) {
        // Create the main frame for the application
        JFrame frame = new JFrame("SwiftList Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        // Create a panel to hold the tasks and add a scroll pane for overflow
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 10, 460, 400);

        // Create an instance of Addtask to handle task addition
        Addtask addTask = new Addtask();
        JButton addButton = addTask.getAddButton();

        // Add an ActionListener to the "Add Task" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewTask();
            }
        });

        // Create a "Clear All" button and add an ActionListener to handle clearing all tasks
        JButton clearButton = new JButton("Clear All");
        clearButton.setBounds(250, 420, 120, 30);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllTasks();
            }
        });

        // Set the positions and add components to the frame
        addButton.setBounds(120, 420, 120, 30);
        frame.add(scrollPane);
        frame.add(addButton);
        frame.add(clearButton);

        // Set frame properties and make it visible
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // Method to handle adding a new task
    private static void addNewTask() {
        // Create an instance of Addtask to get user input
        Addtask addTask = new Addtask();
        int result = JOptionPane.showConfirmDialog(null, addTask.getPanel(), "Create New Task", JOptionPane.OK_CANCEL_OPTION);

        // If the user clicks "OK," create a new task with the entered text and add it to the task panel
        if (result == JOptionPane.OK_OPTION) {
            String taskText = addTask.getTaskTextField().getText();
            if (!taskText.isEmpty()) {
                Task newTask = new Task(taskText, taskPanel);
                taskPanel.add(newTask.getTaskPanel());
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        }
    }

    // Method to handle clearing all tasks
    private static void clearAllTasks() {
        // Remove all components from the task panel and refresh the view
        taskPanel.removeAll();
        taskPanel.revalidate();
        taskPanel.repaint();

    /**
     * @param args the command line arguments
     */
    
}
}
