package alnero;

/**
 * Class for tracking Task objects.
 */
public class TaskTracker {
    /**
     * Storage for all tasks.
     */
    private Task[] taskStorage;
    /**
     * Initial size of task storage.
     */
    private int initSize;
    /**
     * Indicates next empty space in storage.
     */
    private int nextTaskIndex;

    /**
     * Create new TaskTracker and initialize the size of storage.
     *
     * @param initSize initial size of task storage inside tracker.
     */
    public TaskTracker(int initSize) {
        if (initSize <= 0) {
            this.initSize = 1;
        } else {
            this.initSize = initSize;
        }
        this.taskStorage = new Task[this.initSize];
        this.nextTaskIndex = 0;
    }

    /**
     * Add new task to the tracker. If there is no space in array for the tasks,
     * the size of the array becomes two times bigger. If null is added - nothing happens, null is returned.
     *
     * @param task object been added to tracker
     * @return the same task been added or null
     */
    public Task add(Task task) {
        if (task == null) {
            return null;
        }

        if (this.nextTaskIndex == this.taskStorage.length) {
            int newStorageSize = this.taskStorage.length * 2;
            Task[] newStorage = new Task[newStorageSize];
            System.arraycopy(this.taskStorage, 0,
                    newStorage, 0,
                    this.taskStorage.length);
            this.taskStorage = newStorage;
        }

        this.taskStorage[this.nextTaskIndex++] = task;
        return task;
    }

    /**
     * Add comment to a task, which is in tracker.
     * If task is not found in tracker than same task object without comment is returned.
     * If null is supplied instead of task and/or comment then null is returned.
     *
     * @param task    task to which a comment will be added
     * @param comment comment to add to a task
     * @return a task object with added comment or without if task is not in a tracker or null
     */
    public Task addComment(Task task, Comment comment) {
        if (task == null || comment == null) {
            return null;
        }

        long taskId = task.getId();
        Task taskInTracker = this.findById(taskId);

        if (taskInTracker != null) {
            taskInTracker.addComment(comment);
        }

        return task;
    }

    /**
     * Update a task in tracker. If the supplied task is in tracker it will be updated,
     * id is used to find the task in tracker.
     * If task is not found - nothing happens.
     * If null is used as argument - nothing happens.
     *
     * @param task a task which is need to be updated
     */
    public void update(Task task) {
        if (task == null) {
            return;
        }

        long taskId = task.getId();

        for (int i = 0; i < this.nextTaskIndex; i++) {
            if (this.taskStorage[i].getId() == taskId) {
                this.taskStorage[i] = task;
                break;
            }
        }
    }

    /**
     * Delete a task from tracker. If the supplied task is in tracker it will be removed,
     * id is used to find the task in tracker. If task is not found - nothing happens.
     * If null is used as argument - nothing happens.
     *
     * @param task a task which is need to be deleted
     */
    public void delete(Task task) {
        if (task == null) {
            return;
        }

        long taskId = task.getId();

        for (int i = 0; i < this.nextTaskIndex; i++) {
            if (this.taskStorage[i].getId() == taskId) {
                if (this.nextTaskIndex == 1) {
                    this.taskStorage = new Task[this.initSize];
                    this.nextTaskIndex = 0;
                } else if (this.nextTaskIndex != i + 1) {
                    System.arraycopy(this.taskStorage, i + 1,
                            this.taskStorage, i,
                            this.nextTaskIndex - i - 1);
                    this.taskStorage[--nextTaskIndex] = null;
                } else {
                    this.taskStorage[i] = null;
                    this.nextTaskIndex--;
                }
            }
        }

    }

    /**
     * Get a copy of task storage from tracker.
     *
     * @return copy of task storage
     */
    public Task[] findAll() {
        Task[] arr = new Task[this.nextTaskIndex];
        System.arraycopy(this.taskStorage, 0,
                arr, 0,
                arr.length);
        return arr;
    }

    /**
     * Get tasks by name. Only tasks with exact match in name are returned.
     *
     * @param key name of the task
     * @return array containing tasks with the same name
     */
    public Task[] findByName(String key) {
        if (key == null) {
            return null;
        }

        Task[] temporaryArr = new Task[this.nextTaskIndex];
        int arrIndex = 0;

        for (int i = 0; i < this.nextTaskIndex; i++) {
            String taskName = this.taskStorage[i].getName();

            if (taskName != null && taskName.contains(key)) {
                temporaryArr[arrIndex++] = this.taskStorage[i];
            }
        }

        Task[] resultArr = new Task[arrIndex];
        System.arraycopy(temporaryArr, 0,
                resultArr, 0,
                arrIndex);

        return resultArr;
    }

    /**
     * Get task by id. If there is no task, null is returned
     *
     * @param id id of the task
     * @return the found task or null if otherwise
     */
    public Task findById(long id) {
        for (int i = 0; i < this.nextTaskIndex; i++) {
            if (this.taskStorage[i].getId() == id) {
                return this.taskStorage[i];
            }
        }

        return null;
    }
}