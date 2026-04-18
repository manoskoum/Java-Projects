package TaskMananer;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        TaskServiceImpl taskService=new TaskServiceImpl();

        int choice;

        do{
            System.out.println("\n1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Show All Tasks");
            System.out.println("4. Set Task to Completed");
            System.out.println("5. Show Completed Tasks");
            System.out.println("6. Show Pending tasks");
            System.out.println("7. Exit");

            choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Give name of the task");
                    String taskName=scanner.nextLine().trim();
                    System.out.println("Give priority");
                    String priority=scanner.nextLine().trim();
                    taskService.addTask(new Task(taskName,false,priority));
                    break;
                case 2:
                    System.out.println("Give name from the task tha you want to delete");
                    String taskDeleted=scanner.nextLine().trim();
                    taskService.deleteTask(taskDeleted);
                    break;
                case 3:
                    taskService.showALLTask();
                    break;
                case 4:
                    System.out.println("Give name from the task that you want to make completed");
                    String completedTask=scanner.nextLine().trim();
                    taskService.markAsCompleted(completedTask);
                    break;
                case 5:
                    taskService.showCompletedTasks();
                    break;
                case 6:
                    taskService.showPendingTasks();
                    break;
                case 7:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Wrong choice");
            }

        }while(choice!=7);
        scanner.close();

    }
}
