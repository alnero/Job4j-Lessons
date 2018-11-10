package alnero.collectionsFramework.PriorityQueue;

/**
 * Tasks with priorities.
 */
public class Task {
    /**
     * Task description.
     */
    private String desc;
    /**
     * Task priority.
     */
    private int priority;

    /**
     * Initialise task with description and priority.
     * @param desc task description
     * @param priority task priority
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    /**
     * Get task description.
     * @return task description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Get task priority.
     * @return task priority.
     */
    public int getPriority() {
        return priority;
    }
}
