package alnero.innerClasses;

import alnero.ConsoleInput;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Main start point for the Tracker App.
 * Menu implemented via separate class MenuTracker.
 * User actions are implemented via inner classes.
 */
public class StartUI {
    /** Some initial size of the tracker storage, will increase automatically if needed. */
    private static final int INITIAL_TASK_TRACKER_SIZE = 100;

    /** Main task tracker. */
    private TaskTracker taskTracker;
    /** Input used in UI interaction. */
    private TrackerInput input;

    /**
     * Setting main objects - tracker and input used in UI interaction.
     * @param taskTracker main tracker of tasks
     * @param input main input used for interaction
     */
    public StartUI(TaskTracker taskTracker, TrackerInput input) {
        this.taskTracker = taskTracker;
        this.input = input;
    }

    /**
     * Init method for user interface interaction.
     * Method includes welcome message and starts menu presentation.
     */
    public void init() {
        System.out.println("Welcome to Task Tracker!");
        System.out.println("Here you can manage your tasks: create, read, edit, delete.");
        System.out.println();

        MenuTracker menu = new MenuTracker(taskTracker, input);

        while (true) {
            menu.showMenu();
            String userInput = this.input.readInputLine();
            int menuItemKey;
            try {
                menuItemKey = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                continue;
            }
            menu.select(menuItemKey);
            if (MenuTracker.MAX_NUMBER_OF_TASKS - 1 == menuItemKey) {
                break;
            }
        }
    }

    /**
     * Main method to start the tracker app with user interaction via console input.
     * @param args args for main method
     */
    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker(INITIAL_TASK_TRACKER_SIZE);
        TrackerInput consoleInput = new ConsoleInput();

        new StartUI(taskTracker, consoleInput).init();
    }
}