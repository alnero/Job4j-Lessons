package alnero.abstractClasses;

import alnero.TaskTracker;
import alnero.TrackerInput;
import alnero.innerClasses.UserAction;

/**
 * Template class for user actions in Tracker App.
 */
public abstract class BaseAction implements UserAction {
    /** Numeric key of an action. **/
    private final int actionKey;
    /** String name of an action. **/
    private final String actionName;

    /**
     * Common constructor for all actions.
     * @param actionKey action key
     * @param actionName action name
     */
    protected BaseAction(final int actionKey, final String actionName) {
        this.actionKey = actionKey;
        this.actionName = actionName;
    }

    @Override
    public abstract void execute(TaskTracker taskTracker, TrackerInput input);

    @Override
    public int key() {
        return this.actionKey;
    }

    @Override
    public String info() {
        return this.actionName;
    }
}
