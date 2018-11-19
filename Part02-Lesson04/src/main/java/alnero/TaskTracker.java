package alnero;

import java.util.ArrayList;

/**
 * Class for tracking Task objects.
 */
public class TaskTracker {
    /**
     * Storage for all tasks.
     */
    private ArrayList<Task> taskStorage;
    /**
     * Initial size of task storage.
     */
    private int initSize;

    /**
     * Create new TaskTracker and initialize the size of storage.
     *
     * @param initSize initial size of task storage inside tracker.
     */
    public TaskTracker(int initSize) {
        // check for negative and zero initial size;
        if (initSize <= 0) {
            this.initSize = 1;
        } else {
            this.initSize = initSize;
        }
        this.taskStorage = new ArrayList<>(this.initSize);
    }

    /**
     * Add new task to the tracker. If null is added - nothing happens, null is returned.
     *
     * @param task object been added to tracker
     * @return the same task been added or null
     */
    public Task add(Task task) {
        if (task == null) {
            return null;
        }

        this.taskStorage.add(task);
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

        for (int i = 0; i < this.taskStorage.size(); i++) {
            Task addedTask = this.taskStorage.get(i);
            if (addedTask.getId() == taskId) {
                this.taskStorage.set(i, task);
            }
        }
    }

    /**
     * Delete a task from tracker. If the supplied task is in tracker it will be removed,
     * id is used to find the task in tracker.
     * If task is not found - nothing happens.
     * If null is used as argument - nothing happens.
     *
     * @param task a task which is need to be deleted
     */
    public void delete(Task task) {
        if (task == null) {
            return;
        }

        long taskId = task.getId();

        for (int i = 0; i < this.taskStorage.size(); i++) {
            Task addedTask = this.taskStorage.get(i);
            if (addedTask.getId() == taskId) {
                this.taskStorage.remove(i);
            }
        }
    }

    /**
     * Get a copy of task storage from tracker.
     *
     * @return copy of task storage
     */
    public ArrayList<Task> findAll() {
        ArrayList<Task> list = new ArrayList<>(this.taskStorage.size());
        for (Task addedTask : this.taskStorage) {
            list.add(addedTask);
        }
        return list;
    }

    /**
     * Get tasks by name. Only tasks with exact match in name are returned.
     *
     * @param key name of the task
     * @return list containing tasks with the same name
     */
    public ArrayList<Task> findByName(String key) {
        if (key == null) {
            return null;
        }

        ArrayList<Task> list = new ArrayList<>();
        for (Task addedTask : this.taskStorage) {
            String taskName = addedTask.getName();
            if (taskName != null && taskName.contains(key)) {
                list.add(addedTask);
            }
        }
        return list;
    }

    /**
     * Get task by id. If there is no task, null is returned
     *
     * @param id id of the task
     * @return the found task or null if otherwise
     */
    public Task findById(long id) {
        for (Task addedTask : this.taskStorage) {
            if (addedTask.getId() == id) {
                return addedTask;
            }
        }

        return null;
    }
}