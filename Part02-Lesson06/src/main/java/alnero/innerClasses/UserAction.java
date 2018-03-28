package alnero.innerClasses;

import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Common methods for all user actions in Tracker app.
 */
public interface UserAction {
    /**
     * Returns unique key of user action.
     * @return numeric key of user action
     */
    int key();

    /**
     * Provides info about user action.
     * @return info about user action in String format
     */
    String info();

    /**
     * Execution of user action. Task tracker and input to be supplied.
     * @param taskTracker tracker for tasks
     * @param input input used in user action
     */
    void execute(TaskTracker taskTracker, TrackerInput input);
}
