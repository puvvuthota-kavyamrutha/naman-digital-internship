package taskmanager;

import javax.swing.*;
import java.awt.*;

public class TaskManagerFrame extends JFrame {
    private DefaultListModel<String> model;
    private JList<String> list;
    private JTextField titleField, dueField;
    private TaskStorage storage;

    public TaskManagerFrame() {
        setTitle("Task Manager");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Task Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Due Date:"));
        dueField = new JTextField();
        inputPanel.add(dueField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add Task");
        JButton doneBtn = new JButton("Mark Done");
        JButton delBtn = new JButton("Delete Task");

        // ✅ Fixed warnings here
        addBtn.addActionListener(evt -> addTask());
        doneBtn.addActionListener(evt -> toggleDone());
        delBtn.addActionListener(evt -> deleteTask());

        buttonPanel.add(addBtn);
        buttonPanel.add(doneBtn);
        buttonPanel.add(delBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        storage = new TaskStorage();
        loadTasks();
    }

    private void addTask() {
        String title = titleField.getText().trim();
        String due = dueField.getText().trim();
        if (!title.isEmpty()) {
            String task = title + " (Due: " + due + ")";
            model.addElement(task);
            storage.saveTasks(model);
            titleField.setText("");
            dueField.setText("");
        }
    }

    private void toggleDone() {
        int index = list.getSelectedIndex();
        if (index != -1) {
            String task = model.getElementAt(index);
            if (task.startsWith("[Done] ")) {
                task = task.substring(7); // remove "[Done] "
            } else {
                task = "[Done] " + task;
            }
            model.set(index, task);
            storage.saveTasks(model);
        }
    }

    private void deleteTask() {
        int index = list.getSelectedIndex();
        if (index != -1) {
            model.remove(index);
            storage.saveTasks(model);
        }
    }

    private void loadTasks() {
        java.util.List<String> tasks = storage.loadTasks();
        for (String task : tasks) {
            model.addElement(task);
        }
    }
}
