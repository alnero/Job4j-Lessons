package alnero.innerClasses;

import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Show all tasks in tracker, could be zero and more tasks.
 */
class ShowAllTasks implements UserAction {

    @Override
    public int key() {
        return 1;
    }

    @Override
    public String info() {
        return "1. Show all tasks";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());

        Task[] allTasks = taskTracker.findAll();

        System.out.println("Total number of tasks in tracker: " + allTasks.length + "\n");

        for (Task task : allTasks) {
            System.out.println(task);
        }

        System.out.println("That was list of all tasks in tracker!\n");
    }
}