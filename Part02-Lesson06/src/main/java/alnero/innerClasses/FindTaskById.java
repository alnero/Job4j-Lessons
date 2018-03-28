package alnero.innerClasses;

import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Console interaction and finding a task in tracker by id.
 * One or zero tasks can be found.
 */
class FindTaskById implements UserAction {

    @Override
    public int key() {
        return 4;
    }

    @Override
    public String info() {
        return "4. Find task by ID";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());

        System.out.println("Please enter task ID (e.g. 41731869482685287):");
        long idToFind = input.readInputId();

        Task taskFoundById = taskTracker.findById(idToFind);

        if (taskFoundById == null) {
            System.out.println("Sorry no task found!\n");
        } else {
            System.out.println(taskFoundById);
            System.out.println("Task found!\n");
        }
    }
}