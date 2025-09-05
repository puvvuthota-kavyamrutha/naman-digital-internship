package taskmanager;

import java.time.LocalDate;

public class Task {
    private String title;
    private LocalDate due;
    private boolean done;

    public Task(String title, LocalDate due) {
        this.title = title;
        this.due = due;
        this.done = false;
    }

    public String getTitle() { return title; }
    public LocalDate getDue() { return due; }
    public boolean isDone() { return done; }
    public void setDone(boolean d) { this.done = d; }

    @Override
    public String toString() {
        String status = done ? "✔" : "✗";
        String dueStr = (due != null) ? due.toString() : "no due";
        return status + " " + title + " (due " + dueStr + ")";
    }

    // Simple line storage: done|yyyy-MM-dd|title  (due may be empty)
    public static Task fromLine(String line) {
        String[] parts = line.split("\\|", 3);
        if (parts.length < 3) return null;
        boolean d = Boolean.parseBoolean(parts[0]);
        LocalDate due = parts[1].isEmpty() ? null : LocalDate.parse(parts[1]);
        Task t = new Task(parts[2], due);
        t.setDone(d);
        return t;
    }

    public String toLine() {
        String dueStr = (due != null) ? due.toString() : "";
        return done + "|" + dueStr + "|" + title;
    }
}
