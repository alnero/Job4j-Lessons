package alnero.innerClasses;

import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Console interaction and deletion of a task in tracker.
 * Task for deleting is searched by id, if not found app returns to main menu.
 * Deletion can be aborted.
 */
class DeleteTask implements UserAction {

    @Override
    public int key() {
        return 3;
    }

    @Override
    public String info() {
        return "3. Delete task";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());

        System.out.println("Do you have ID of the task? (Y/N)");
        boolean userHasIdToDelete = input.positiveAnswerGiven();

        if (userHasIdToDelete) {
            System.out.println("Please enter task ID (e.g. 41731869482685287):");
            Long editTaskId = input.readInputId();

            Task deleteTask = taskTracker.findById(editTaskId);

            if (deleteTask == null) {
                System.out.println("Sorry no task found!\n");
            } else {
                System.out.println(deleteTask);

                System.out.println("Are you sure to delete? (Y/N)");
                boolean userSureToDelete = input.positiveAnswerGiven();

                if (userSureToDelete) {
                    taskTracker.delete(deleteTask);
                    System.out.println("Task deleted successfully!\n");
                } else {
                    System.out.println("Abort deletion, return back to MENU\n");
                }
            }
        } else {
            System.out.println("Please find task ID!\n");
        }
    }
}