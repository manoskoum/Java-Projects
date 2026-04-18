package TaskMananer;

import java.util.ArrayList;

public class TaskServiceImpl implements TaskService {

    ArrayList<Task> tasks= new ArrayList<>();

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added");
    }

    public Task searchTask(String title){

        for(Task t: tasks){
            if(t.getTitle().equalsIgnoreCase(title)){
                return t;
            }
        }
        return null;
    }

    @Override
    public void deleteTask(String title) {

        Task removeTask=searchTask(title);

        if(removeTask==null){
            System.out.println("Task with the name: " + title + "doesnt exist");
            return;
        }

        tasks.remove(removeTask);
        System.out.println("Task successfully deleted");
    }

    @Override
    public void showALLTask() {

        if(tasks.isEmpty()){
            System.out.println("❌ No existing tasks");
            return;
        }

        int count = 1;

        for(Task t:tasks){
            String status = t.isCompleted() ? "✔ DONE" : "⏳ PENDING";

            System.out.println(
                    count + ". " +
                            "Title: " + t.getTitle() +
                            " | Status: " + status +
                            " | Priority: " + t.getPriority()
            );
            count++;
        }
        System.out.println("=====================\n");
    }

    @Override
    public void markAsCompleted(String title) {

        Task taskCompleted=searchTask(title);

        if(taskCompleted==null){
            System.out.println("❌ No existing tasks");
            return;
        }

        taskCompleted.setCompleted(true);
        System.out.println("Task is completed");
    }

    @Override
    public void showCompletedTasks() {

        boolean found=false;

        for(Task t:tasks){
            if(t.isCompleted()){
                System.out.println( "Title: " + t.getTitle() +
                        " | Status: " + "✔ COMPLETED" +
                        " | Priority: " + t.getPriority());
                found=true;
            }
        }
        if(!found){
            System.out.println("❌ No COMPLETED tasks");
            }
        }


    @Override
    public void showPendingTasks() {

        boolean pending= false;

        for(Task t:tasks){
            if(!t.isCompleted()){
                System.out.println( "Title: " + t.getTitle() +
                        " | Status: " + "⏳ PENDING" +
                        " | Priority: " + t.getPriority());
                pending=true;
            }
        }
        if(!pending){
            System.out.println("❌ No PENDING tasks");
        }
    }


}
