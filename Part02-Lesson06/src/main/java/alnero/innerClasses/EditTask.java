package alnero.innerClasses;

import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Console interaction and editing a task in tracker.
 * Task for editing is searched by id, if not found app returns to main menu.
 */
class EditTask implements UserAction {

    @Override
    public int key() {
        return 2;
    }

    @Override
    public String info() {
        return "2. Edit task";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());

        System.out.println("Do you have ID of the task? (Y/N)");
        boolean userHasIdToEdit = input.positiveAnswerGiven();

        if (userHasIdToEdit) {
            System.out.println("Please enter task ID (e.g. 41731869482685287):");
            Long editTaskId = input.readInputId();

            Task editTask = taskTracker.findById(editTaskId);

            if (editTask == null) {
                System.out.println("Sorry no task found!\n");
            } else {
                System.out.println(editTask);

                System.out.print("Enter Task NAME: ");
                String editName = input.readInputLine();
                editTask.setName(editName);

                System.out.print("Enter Task DESCRIPTION: ");
                String editDescription = input.readInputLine();
                editTask.setDescription(editDescription);

                taskTracker.update(editTask);

                System.out.println(editTask);

                System.out.println("Task changed successfully!\n");
            }
        } else {
            System.out.println("Please find task ID!\n");
        }
    }
}