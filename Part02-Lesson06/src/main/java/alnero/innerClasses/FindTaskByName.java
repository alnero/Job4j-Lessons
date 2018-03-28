package alnero.innerClasses;

import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Console interaction and finding a task in tracker by name.
 * Zero and more tasks can be found.
 */
class FindTaskByName implements UserAction {

    @Override
    public int key() {
        return 5;
    }

    @Override
    public String info() {
        return "5. Find tasks by name";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());

        System.out.println("Please enter name to search for:");
        String nameToFind = input.readInputLine();

        Task[] tasksFoundByName = taskTracker.findByName(nameToFind);

        if (tasksFoundByName.length == 0) {
            System.out.println("Sorry no task found!\n");
        } else {
            for (Task task : tasksFoundByName) {
                System.out.println(task);
            }
            System.out.println("Tasks found!\n");
        }
    }
}
