package TaskMananer;

public class Task {

    private String title;
    private boolean completed;
    private String priority;

    public Task(String title, boolean completed, String priority) {
        this.title = title;
        this.completed = completed;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", completed=" + completed +
                ", priority='" + priority + '\'' +
                '}';
    }
}
