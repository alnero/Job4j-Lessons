package alnero.innerClasses;

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
        this.userActions[0] = new AddNewTask();
        this.userActions[1] = new ShowAllTasks();
        this.userActions[2] = new EditTask();
        this.userActions[3] = new DeleteTask();
        this.userActions[4] = new FindTaskById();
        this.userActions[5] = new FindTaskByName();
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
}
