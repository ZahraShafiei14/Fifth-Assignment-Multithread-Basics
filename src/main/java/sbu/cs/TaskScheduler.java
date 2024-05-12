package sbu.cs;

import java.util.ArrayList;
import java.util.Comparator;

public class TaskScheduler
{

    public static class Task implements Runnable
    {
        String taskName;
        int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
            this.processingTime = processingTime;
        }
        @Override
        public void run() {
            try { Thread.sleep(processingTime); }
            catch (InterruptedException e){ e.printStackTrace(); }
        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks)
    {
        ArrayList<String> finishedTasks = new ArrayList<>();
        tasks.sort(Comparator.comparingInt(task -> -task.processingTime));

        for (Task task: tasks){
            Thread t = new Thread(task);
            t.start();
            try { t.join(); }
            catch (InterruptedException e){ e.printStackTrace(); }

            finishedTasks.add(task.taskName);
        }
        return finishedTasks;
    }

    public static void main(String[] args) {
    }
}
