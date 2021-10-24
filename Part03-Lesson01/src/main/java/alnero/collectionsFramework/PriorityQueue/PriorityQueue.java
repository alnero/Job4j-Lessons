package alnero.collectionsFramework.PriorityQueue;

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
     *
     * There is also a long solution for this task:
     *
     *         int newTaskPriority = newTask.getPriority();
     *         int queueIndexForNewTask = 0;
     *         if (0 == tasks.size()) {
     *             this.tasks.add(queueIndexForNewTask, newTask);
     *         } else {
     *             Iterator<Task> tasksIterator = this.tasks.iterator();
     *             while (tasksIterator.hasNext()) {
     *                 Task addedTask = tasksIterator.next();
     *                 int addedTaskPriority = addedTask.getPriority();
     *                 if (newTaskPriority > addedTaskPriority) {
     *                     queueIndexForNewTask = this.tasks.indexOf(addedTask) + 1;
     *                 }
     *             }
     *             this.tasks.add(queueIndexForNewTask, newTask);
     *         }
     *
     * @param newTask task to add
     */
    public void put(Task newTask) {
        var newTaskPriority = newTask.getPriority();
        var queueIndexForNewTask = 0;
        var index = 0;
        for (var task : this.tasks) {
            var addedTaskPriority = task.getPriority();
            var addedTaskIndex = index++;
            if (newTaskPriority > addedTaskPriority) {
                queueIndexForNewTask = addedTaskIndex + 1;
            }
        }
        this.tasks.add(queueIndexForNewTask, newTask);
    }

    /**
     * Remove first task from storage.
     * @return first task
     */
    public Task take() {
        return this.tasks.poll();
    }
}