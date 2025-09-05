package taskmanager;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TaskManagerFrame().setVisible(true);
        });
    }
}
