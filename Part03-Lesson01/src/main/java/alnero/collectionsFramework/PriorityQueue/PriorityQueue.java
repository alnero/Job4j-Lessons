package alnero.collectionsFramework.PriorityQueue;

//import java.util.Iterator;
import java.util.LinkedList;

/**
 * Queue for tasks with priorities.
 */
public class PriorityQueue {
    /**
     * Storage for tasks with priorities.
     */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Put task in appropriate position according to its priority.
     * @param newTask task to add
     */
    public void put(Task newTask) {
        // Short solution
        int newTaskPriority = newTask.getPriority();
        int queueIndexForNewTask = 0;
        int index = 0;
        for (Task task : this.tasks) {
            int addedTaskPriority = task.getPriority();
            int addedTaskIndex = index++;
            if (newTaskPriority > addedTaskPriority) {
                queueIndexForNewTask = addedTaskIndex + 1;
            }
        }
        this.tasks.add(queueIndexForNewTask, newTask);

       /* // Long solution
        int newTaskPriority = newTask.getPriority();
        int queueIndexForNewTask = 0;
        if (0 == tasks.size()) {
            this.tasks.add(queueIndexForNewTask, newTask);
        } else {
            Iterator<Task> tasksIterator = this.tasks.iterator();
            while (tasksIterator.hasNext()) {
                Task addedTask = tasksIterator.next();
                int addedTaskPriority = addedTask.getPriority();
                if (newTaskPriority > addedTaskPriority) {
                    queueIndexForNewTask = this.tasks.indexOf(addedTask) + 1;
                }
            }
            this.tasks.add(queueIndexForNewTask, newTask);
        }*/
    }

    /**
     * Remove first task from storage.
     * @return first task
     */
    public Task take() {
        return this.tasks.poll();
    }
}