package alnero.innerClasses;

import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Console interaction and adding new task to tracker,
 * name and description for new task to be entered.
 */
class AddNewTask implements UserAction {

    @Override
    public int key() {
        return 0;
    }

    @Override
    public String info() {
        return "0. Add new task";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());

        Task newTask = new Task();

        System.out.print("Enter Task NAME: ");
        String taskName = input.readInputLine();
        newTask.setName(taskName);

        System.out.print("Enter Task DESCRIPTION: ");
        String taskDescription = input.readInputLine();
        newTask.setDescription(taskDescription);

        System.out.println("\n" + newTask);

        taskTracker.add(newTask);

        System.out.println("Task added to tracker successfully!\n");
    }
}