package alnero;

/**
 * Main start point for the Tracker App including console interaction.
 */
public class StartUI {

    /**
     * Some initial size of the tracker storage, will increase automatically if needed.
     */
    private static final int INITIAL_TASK_TRACKER_SIZE = 100;

    /**
     * Console constant for adding new task.
     */
    private static final String ADD_NEW_TASK = "0";
    /**
     * Console constant for showing all tasks.
     */
    private static final String SHOW_ALL_TASKS = "1";
    /**
     * Console constant for editing a task.
     */
    private static final String EDIT_TASK = "2";
    /**
     * Console constant for deleting a task.
     */
    private static final String DELETE_TASK = "3";
    /**
     * Console constant for finding a task by id.
     */
    private static final String FIND_TASK_BY_ID = "4";
    /**
     * Console constant for finding a task by name.
     */
    private static final String FIND_TASK_BY_NAME = "5";
    /**
     * Console constant for adding a comment to a task.
     */
    private static final String ADD_COMMENT_TO_TASK = "6";
    /**
     * Console constant for quitting app.
     */
    private static final String EXIT = "7";

    /**
     * Print main menu in console.
     */
    public static void showMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("Use MENU from 0 to 7:\n");
        menu.append("-----------------------\n");
        menu.append("0. Add new task\n");
        menu.append("1. Show all tasks\n");
        menu.append("2. Edit task\n");
        menu.append("3. Delete task\n");
        menu.append("4. Find task by ID\n");
        menu.append("5. Find tasks by name\n");
        menu.append("6. Add comment to task\n");
        menu.append("7. Exit Program\n");
        menu.append("-----------------------\n");
        menu.append("Select:");

        System.out.println(menu);
    }

    /**
     * Console interaction and adding new task to tracker, name and description for new task to be entered.
     *
     * @param taskTracker tracker in which new task will be added
     * @param consoleInput object with methods for console inputs
     */
    private static void addNewTask(TaskTracker taskTracker, ConsoleInput consoleInput) {
        System.out.println("0. Add new task\n");

        Task newTask = new Task();

        System.out.print("Enter Task NAME: ");
        String taskName = consoleInput.readConsoleInputLine();
        newTask.setName(taskName);

        System.out.print("Enter Task DESCRIPTION: ");
        String taskDescription = consoleInput.readConsoleInputLine();
        newTask.setDescription(taskDescription);

        System.out.println("\n" + newTask);

        taskTracker.add(newTask);

        System.out.println("Task added to tracker successfully!\n");
    }

    /**
     * Show all tasks in tracker, could be zero and more tasks.
     *
     * @param taskTracker tracker in which tasks are searched for
     */
    private static void showAllTasks(TaskTracker taskTracker) {
        System.out.println("1. Show all tasks\n");

        Task[] allTasks = taskTracker.findAll();

        System.out.println("Total number of tasks in tracker: " + allTasks.length + "\n");

        for (Task task : allTasks) {
            System.out.println(task);
        }

        System.out.println("That was list of all tasks in tracker!\n");
    }

    /**
     * Console interaction and editing a task in tracker.
     * Task for editing is searched by id, if not found app returns to main menu.
     *
     * @param taskTracker tracker in which a task will be edited
     * @param consoleInput object with methods for console inputs
     */
    private static void editTask(TaskTracker taskTracker, ConsoleInput consoleInput) {
        System.out.println("2. Edit task\n");

        System.out.println("Do you have ID of the task? (Y/N)");
        boolean userHasIdToEdit = consoleInput.userAnsweredYes();

        if (userHasIdToEdit) {
            System.out.println("Please enter task ID (e.g. 41731869482685287):");
            Long editTaskId = consoleInput.readConsoleInputId();

            Task editTask = taskTracker.findById(editTaskId);

            if (editTask == null) {
                System.out.println("Sorry no task found!\n");
            } else {
                System.out.println(editTask);

                System.out.print("Enter Task NAME: ");
                String editName = consoleInput.readConsoleInputLine();
                editTask.setName(editName);

                System.out.print("Enter Task DESCRIPTION: ");
                String editDescription = consoleInput.readConsoleInputLine();
                editTask.setDescription(editDescription);

                taskTracker.update(editTask);

                System.out.println(editTask);

                System.out.println("Task changed successfully!\n");
            }
        } else {
            System.out.println("Please find task ID!\n");
        }
    }

    /**
     * Console interaction and deleting a task in tracker.
     * Task for deleting is searched by id, if not found app returns to main menu.
     * Deletion can be aborted.
     *
     * @param taskTracker tracker in which a task will be deleted
     * @param consoleInput object with methods for console inputs
     */
    private static void deleteTask(TaskTracker taskTracker, ConsoleInput consoleInput) {
        System.out.println("3. Delete task\n");

        System.out.println("Do you have ID of the task? (Y/N)");
        boolean userHasIdToDelete = consoleInput.userAnsweredYes();

        if (userHasIdToDelete) {
            System.out.println("Please enter task ID (e.g. 41731869482685287):");
            Long editTaskId = consoleInput.readConsoleInputId();

            Task deleteTask = taskTracker.findById(editTaskId);

            if (deleteTask == null) {
                System.out.println("Sorry no task found!\n");
            } else {
                System.out.println(deleteTask);

                System.out.println("Are you sure to delete? (Y/N)");
                boolean userSureToDelete = consoleInput.userAnsweredYes();

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

    /**
     * Console interaction and finding a task in tracker by id.
     * One or zero tasks can be found.
     *
     * @param taskTracker tracker in which a task will be searched by id
     * @param consoleInput object with methods for console inputs
     */
    private static void findTaskById(TaskTracker taskTracker, ConsoleInput consoleInput) {
        System.out.println("4. Find task by ID\n");

        System.out.println("Please enter task ID (e.g. 41731869482685287):");
        long idToFind = consoleInput.readConsoleInputId();

        Task taskFoundById = taskTracker.findById(idToFind);

        if (taskFoundById == null) {
            System.out.println("Sorry no task found!\n");
        } else {
            System.out.println(taskFoundById);
            System.out.println("Task found!\n");
        }
    }

    /**
     * Console interaction and finding a task in tracker by name.
     * Zero and more tasks can be found.
     *
     * @param taskTracker tracker in which a task will be searched by name
     * @param consoleInput object with methods for console inputs
     */
    private static void findTaskByName(TaskTracker taskTracker, ConsoleInput consoleInput) {
        System.out.println("5. Find tasks by name\n");

        System.out.println("Please enter name to search for:");
        String nameToFind = consoleInput.readConsoleInputLine();

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

    /**
     * Console interaction and adding a comment to task in tracker.
     * Task for adding a comment is searched by id, if not found app returns to main menu.
     *
     * @param taskTracker tracker in which a comment will be added to some task
     * @param consoleInput object with methods for console inputs
     */
    private static void addCommentToTask(TaskTracker taskTracker, ConsoleInput consoleInput) {
        System.out.println("6. Add comment to task\n");

        System.out.println("Do you have ID of the task? (Y/N)");
        boolean userHasIdToAddComment = consoleInput.userAnsweredYes();

        if (userHasIdToAddComment) {
            System.out.println("Please enter task ID (e.g. 41731869482685287):");
            long addCommentTaskId = consoleInput.readConsoleInputId();

            Task addCommentTask = taskTracker.findById(addCommentTaskId);

            if (addCommentTask == null) {
                System.out.println("Sorry no task found!\n");
            } else {
                System.out.println(addCommentTask);

                System.out.print("Enter your comment for the task: ");
                String inputComment = consoleInput.readConsoleInputLine();
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

    /**
     * Exit method, final words or methods before quit the app.
     */
    private static void exit() {
        System.out.println("7. Exit Program\n");
    }

    /**
     * Message when input of menu constant is unknown, as a default for switch cases.
     */
    private static void menuInputError() {
        System.out.println("Input Error, please choose menu item from 0 to 7.\n");
    }

    /**
     * Main method with welcome message, initialization of tracker and object with methods for console inputs.
     *
     * @param args some parameters supplied when starting an app, not used for the moment
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Task Tracker!");
        System.out.println("Here you can manage your tasks: create, read, edit, delete.");
        System.out.println();

        TaskTracker taskTracker = new TaskTracker(INITIAL_TASK_TRACKER_SIZE);
        ConsoleInput consoleInput = new ConsoleInput();

        while (true) {
            showMenu();

            String menuItem = consoleInput.readConsoleInputLine();

            switch (menuItem) {
                case ADD_NEW_TASK:
                    addNewTask(taskTracker, consoleInput);
                    break;
                case SHOW_ALL_TASKS:
                    showAllTasks(taskTracker);
                    break;
                case EDIT_TASK:
                    editTask(taskTracker, consoleInput);
                    break;
                case DELETE_TASK:
                    deleteTask(taskTracker, consoleInput);
                    break;
                case FIND_TASK_BY_ID:
                    findTaskById(taskTracker, consoleInput);
                    break;
                case FIND_TASK_BY_NAME:
                    findTaskByName(taskTracker, consoleInput);
                    break;
                case ADD_COMMENT_TO_TASK:
                    addCommentToTask(taskTracker, consoleInput);
                    break;
                case EXIT:
                    exit();
                    return;
                default:
                    menuInputError();
                    break;
            }
        }
    }
}