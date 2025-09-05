package taskmanager;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class TaskStorage {
    private static final String FILE_NAME = "tasks.txt";

    // Save all tasks from the list model into a file
    public void saveTasks(DefaultListModel<String> model) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < model.size(); i++) {
                writer.println(model.getElementAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load tasks from file into a list
    public List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    tasks.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }
}
