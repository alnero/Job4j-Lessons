package alnero.innerClasses;

import alnero.TaskTracker;
import alnero.TrackerInput;

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
