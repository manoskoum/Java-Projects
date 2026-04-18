package TaskMananer;

public interface TaskService {

     void addTask(Task task);
     void deleteTask(String title);
     void showALLTask();
     void markAsCompleted(String title);
     void showCompletedTasks();
     void showPendingTasks();
}
