package alnero.innerClasses;

import alnero.Comment;
import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;
import alnero.abstractClasses.BaseAction;

import java.util.function.Consumer;

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
    /** Output used in menu and for user actions. */
    private Consumer<String> output;

    /**
     * For menu to work we need task tracker, input and to initialize user actions.
     * @param taskTracker tracker for tasks
     * @param input input for menu and user actions
     * @param output output for menu and user actions
     */
    public MenuTracker(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
        this.input = input;
        this.taskTracker = taskTracker;
        this.output = output;

        this.addUserActions();
    }

    /**
     * Initialize user actions. All further actions are added manually.
     */
    private void addUserActions() {
        // inner class
        this.userActions[0] = this.new AddNewTask(0, "0. Add new task");
        this.userActions[1] = this.new ShowAllTasks(1, "1. Show all tasks");
        this.userActions[2] = this.new EditTask(2, "2. Edit task");
        this.userActions[3] = this.new DeleteTask(3, "3. Delete task");
        // inner static class
        this.userActions[4] = new MenuTracker.FindTaskById(4, "4. Find task by ID");
        this.userActions[5] = new MenuTracker.FindTaskByName(5, "5. Find tasks by name");
        // inner class in same file
        this.userActions[6] = new AddCommentToTask(6, "6. Add comment to task");
        this.userActions[7] = new Exit(7, "7. Exit Program");
    }

    /**
     * Show menu to user.
     */
    public void showMenu() {
        output.accept(String.format("Use MENU from 0 to %s :", userActions.length - 1));
        output.accept("-----------------------");

        for (UserAction action : userActions) {
            output.accept(action.info());
        }

        output.accept("-----------------------");
        output.accept("Select:");
    }

    /**
     * Select menu item. User action selected via unique numeric key.
     * @param key numeric key of user action
     */
    public void select(int key) {
        userActions[key].execute(this.taskTracker, this.input, this.output);
    }

    // INNER CLASSES

    /**
     * Console interaction and adding new task to tracker,
     * name and description for new task to be entered.
     */
    private class AddNewTask extends BaseAction {
        /**
         * Send action key and action name to constructor in template class.
         * @param key action key
         * @param name action name
         */
        protected AddNewTask(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
            output.accept(this.info());

            Task newTask = new Task();

            System.out.print("Enter Task NAME: ");
            String taskName = input.readInputLine();
            newTask.setName(taskName);

            System.out.print("Enter Task DESCRIPTION: ");
            String taskDescription = input.readInputLine();
            newTask.setDescription(taskDescription);

            output.accept(System.lineSeparator() + newTask);

            taskTracker.add(newTask);

            output.accept("Task added to tracker successfully!" + System.lineSeparator());
        }
    }

    /**
     * Show all tasks in tracker, could be zero and more tasks.
     */
    private class ShowAllTasks extends BaseAction {
        /**
         * Send action key and action name to constructor in template class.
         * @param key action key
         * @param name action name
         */
        protected ShowAllTasks(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
            output.accept(this.info());

            Task[] allTasks = taskTracker.findAll();

            output.accept("Total number of tasks in tracker: " + allTasks.length + System.lineSeparator());

            for (Task task : allTasks) {
                output.accept(task.toString());
            }

            output.accept("That was list of all tasks in tracker!" + System.lineSeparator());
        }
    }

    /**
     * Console interaction and editing a task in tracker.
     * Task for editing is searched by id, if not found app returns to main menu.
     */
    private class EditTask extends BaseAction {
        /**
         * Send action key and action name to constructor in template class.
         * @param key action key
         * @param name action name
         */
        protected EditTask(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
            output.accept(this.info());

            output.accept("Do you have ID of the task? (Y/N)");
            boolean userHasIdToEdit = input.positiveAnswerGiven();

            if (userHasIdToEdit) {
                output.accept("Please enter task ID (e.g. 41731869482685287):");
                Long editTaskId = input.readInputId();

                Task editTask = taskTracker.findById(editTaskId);

                if (editTask == null) {
                    output.accept("Sorry no task found!" + System.lineSeparator());
                } else {
                    output.accept(editTask.toString());

                    System.out.print("Enter Task NAME: ");
                    String editName = input.readInputLine();
                    editTask.setName(editName);

                    System.out.print("Enter Task DESCRIPTION: ");
                    String editDescription = input.readInputLine();
                    editTask.setDescription(editDescription);

                    taskTracker.update(editTask);

                    output.accept(editTask.toString());

                    output.accept("Task changed successfully!" + System.lineSeparator());
                }
            } else {
                output.accept("Please find task ID!" + System.lineSeparator());
            }
        }
    }

    /**
     * Console interaction and deletion of a task in tracker.
     * Task for deleting is searched by id, if not found app returns to main menu.
     * Deletion can be aborted.
     */
    private class DeleteTask extends BaseAction {
        /**
         * Send action key and action name to constructor in template class.
         * @param key action key
         * @param name action name
         */
        protected DeleteTask(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
            output.accept(this.info());

            output.accept("Do you have ID of the task? (Y/N)");
            boolean userHasIdToDelete = input.positiveAnswerGiven();

            if (userHasIdToDelete) {
                output.accept("Please enter task ID (e.g. 41731869482685287):");
                Long editTaskId = input.readInputId();

                Task deleteTask = taskTracker.findById(editTaskId);

                if (deleteTask == null) {
                    output.accept("Sorry no task found!" + System.lineSeparator());
                } else {
                    output.accept(deleteTask.toString());

                    output.accept("Are you sure to delete? (Y/N)");
                    boolean userSureToDelete = input.positiveAnswerGiven();

                    if (userSureToDelete) {
                        taskTracker.delete(deleteTask);
                        output.accept("Task deleted successfully!" + System.lineSeparator());
                    } else {
                        output.accept("Abort deletion, return back to MENU" + System.lineSeparator());
                    }
                }
            } else {
                output.accept("Please find task ID!" + System.lineSeparator());
            }
        }
    }

    // INNER STATIC CLASSES

    /**
     * Console interaction and finding a task in tracker by id.
     * One or zero tasks can be found.
     */
    private static class FindTaskById extends BaseAction {
        /**
         * Send action key and action name to constructor in template class.
         * @param key action key
         * @param name action name
         */
        protected FindTaskById(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
            output.accept(this.info());

            output.accept("Please enter task ID (e.g. 41731869482685287):");
            long idToFind = input.readInputId();

            Task taskFoundById = taskTracker.findById(idToFind);

            if (taskFoundById == null) {
                output.accept("Sorry no task found!" + System.lineSeparator());
            } else {
                output.accept(taskFoundById.toString());
                output.accept("Task found!" + System.lineSeparator());
            }
        }
    }

    /**
     * Console interaction and finding a task in tracker by name.
     * Zero and more tasks can be found.
     */
    private static class FindTaskByName extends BaseAction {
        /**
         * Send action key and action name to constructor in template class.
         * @param key action key
         * @param name action name
         */
        protected FindTaskByName(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
            output.accept(this.info());

            output.accept("Please enter name to search for:");
            String nameToFind = input.readInputLine();

            Task[] tasksFoundByName = taskTracker.findByName(nameToFind);

            if (tasksFoundByName.length == 0) {
                output.accept("Sorry no task found!" + System.lineSeparator());
            } else {
                for (Task task : tasksFoundByName) {
                    output.accept(task.toString());
                }
                output.accept("Tasks found!" + System.lineSeparator());
            }
        }
    }
}

// INNER CLASSES IN SAME FILE

/**
 * Console interaction and adding a comment to task in tracker.
 * Task for adding a comment is searched by id, if not found app returns to main menu.
 */
class AddCommentToTask extends BaseAction {
    /**
     * Send action key and action name to constructor in template class.
     * @param key action key
     * @param name action name
     */
    protected AddCommentToTask(final int key, final String name) {
        super(key, name);
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
        output.accept(this.info());

        output.accept("Do you have ID of the task? (Y/N)");
        boolean userHasIdToAddComment = input.positiveAnswerGiven();

        if (userHasIdToAddComment) {
            output.accept("Please enter task ID (e.g. 41731869482685287):");
            long addCommentTaskId = input.readInputId();

            Task addCommentTask = taskTracker.findById(addCommentTaskId);

            if (addCommentTask == null) {
                output.accept("Sorry no task found!" + System.lineSeparator());
            } else {
                output.accept(addCommentTask.toString());

                System.out.print("Enter your comment for the task: ");
                String inputComment = input.readInputLine();
                Comment userComment = new Comment();
                userComment.setContent(inputComment);

                addCommentTask.addComment(userComment);

                taskTracker.update(addCommentTask);
                output.accept(addCommentTask.toString());

                output.accept("Comment added to task successfully!" + System.lineSeparator());
            }
        } else {
            output.accept("Please find task ID" + System.lineSeparator());
        }
    }
}

/**
 * Exit method, final words or methods before quit the app.
 */
class Exit extends BaseAction {
    /**
     * Send action key and action name to constructor in template class.
     * @param key action key
     * @param name action name
     */
    protected Exit(final int key, final String name) {
        super(key, name);
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input, Consumer<String> output) {
        output.accept(this.info());
    }
}