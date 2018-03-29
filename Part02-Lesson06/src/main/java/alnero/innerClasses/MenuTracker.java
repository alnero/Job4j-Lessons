package alnero.innerClasses;

import alnero.Comment;
import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Separate implementation of menu for Tracker app.
 */
public class MenuTracker {

    /** Max number of user actions implemented in menu. */
    public static final int MAX_NUMBER_OF_TASKS = 8;
    /** Array with all user actions. **/
    private UserAction[] userActions = new UserAction[MAX_NUMBER_OF_TASKS];

    /** Tracker used for the tasks. */
    private TaskTracker taskTracker;
    /** Input used in menu and for user actions. */
    private TrackerInput input;

    /**
     * For menu to work we need task tracker, input and to initialize user actions.
     * @param taskTracker tracker for tasks
     * @param input input for menu and user actions
     */
    public MenuTracker(TaskTracker taskTracker, TrackerInput input) {
        this.input = input;
        this.taskTracker = taskTracker;

        this.addUserActions();
    }

    /**
     * Initialize user actions. All further actions are added manually.
     */
    private void addUserActions() {
        // inner class
        this.userActions[0] = this.new AddNewTask();
        this.userActions[1] = this.new ShowAllTasks();
        this.userActions[2] = this.new EditTask();
        this.userActions[3] = this.new DeleteTask();
        // inner static class
        this.userActions[4] = new MenuTracker.FindTaskById();
        this.userActions[5] = new MenuTracker.FindTaskByName();
        // inner class in same file
        this.userActions[6] = new AddCommentToTask();
        this.userActions[7] = new Exit();
    }

    /**
     * Show menu to user.
     */
    public void showMenu() {
        System.out.println(String.format("Use MENU from 0 to %s :", userActions.length - 1));
        System.out.println("-----------------------");

        for (UserAction action : userActions) {
            System.out.println(action.info());
        }

        System.out.println("-----------------------");
        System.out.println("Select:");
    }

    /**
     * Select menu item. User action selected via unique numeric key.
     * @param key numeric key of user action
     */
    public void select(int key) {
        userActions[key].execute(this.taskTracker, this.input);
    }

    // INNER CLASSES

    /**
     * Console interaction and adding new task to tracker,
     * name and description for new task to be entered.
     */
    private class AddNewTask implements UserAction {
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

    /**
     * Show all tasks in tracker, could be zero and more tasks.
     */
    private class ShowAllTasks implements UserAction {
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

    /**
     * Console interaction and editing a task in tracker.
     * Task for editing is searched by id, if not found app returns to main menu.
     */
    private class EditTask implements UserAction {
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

    /**
     * Console interaction and deletion of a task in tracker.
     * Task for deleting is searched by id, if not found app returns to main menu.
     * Deletion can be aborted.
     */
    private class DeleteTask implements UserAction {
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

    // INNER STATIC CLASSES

    /**
     * Console interaction and finding a task in tracker by id.
     * One or zero tasks can be found.
     */
    private static class FindTaskById implements UserAction {
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

    /**
     * Console interaction and finding a task in tracker by name.
     * Zero and more tasks can be found.
     */
    private static class FindTaskByName implements UserAction {
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
}

// INNER CLASSES IN SAME FILE

/**
 * Console interaction and adding a comment to task in tracker.
 * Task for adding a comment is searched by id, if not found app returns to main menu.
 */
class AddCommentToTask implements UserAction {
    @Override
    public int key() {
        return 6;
    }

    @Override
    public String info() {
        return "6. Add comment to task";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());

        System.out.println("Do you have ID of the task? (Y/N)");
        boolean userHasIdToAddComment = input.positiveAnswerGiven();

        if (userHasIdToAddComment) {
            System.out.println("Please enter task ID (e.g. 41731869482685287):");
            long addCommentTaskId = input.readInputId();

            Task addCommentTask = taskTracker.findById(addCommentTaskId);

            if (addCommentTask == null) {
                System.out.println("Sorry no task found!\n");
            } else {
                System.out.println(addCommentTask);

                System.out.print("Enter your comment for the task: ");
                String inputComment = input.readInputLine();
                Comment userComment = new Comment();
                userComment.setContent(inputComment);

                addCommentTask.addComment(userComment);

                taskTracker.update(addCommentTask);
                System.out.println(addCommentTask);

                System.out.println("Comment added to task successfully!\n");
            }
        } else {
            System.out.println("Please find task ID\n");
        }
    }
}

/**
 * Exit method, final words or methods before quit the app.
 */
class Exit implements UserAction {
    @Override
    public int key() {
        return 7;
    }

    @Override
    public String info() {
        return "7. Exit Program";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());
    }
}