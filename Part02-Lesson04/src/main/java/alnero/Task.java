package alnero;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for the task objects.
 */
public class Task {
    /**
     * Number of comments in task.
     */
    private static final int NUMBER_OF_COMMENTS_IN_TASK = 10;
    /**
     * ID of the task, can be used for search.
     */
    private long id;
    /**
     * Name of the task, can be used for search.
     */
    private String name;
    /**
     * Description of the task.
     */
    private String description;
    /**
     * Time of creation of the task.
     */
    private Date createDate;
    /**
     * Array of comments for the task.
     */
    private Comment[] comments;
    /**
     * Index of next empty slot in array of comments.
     */
    private int nextCommentIndex;

    /**
     * Id, time and array of comments with index are initialized on creation of the task.
     */
    public Task() {
        this.setId();
        this.createDate = new Date();
        this.comments = new Comment[NUMBER_OF_COMMENTS_IN_TASK];
        this.nextCommentIndex = 0;
    }

    /**
     * Get the id of a task object.
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Id for a task is generated randomly, type is long and only greater or equal to zero values.
     */
    private void setId() {
        this.id = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
    }

    /**
     * Get the name of a task.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of a task.
     * @param name name of a task, used for search as well
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description of a task, some info in String format.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of a task.
     * @param description description of a task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the time of task creation.
     * @return time of task creation
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Get copy of array with comments for the task.
     * @return copy of array with comments
     */
    public Comment[] getComments() {
        Comment[] arr = new Comment[this.nextCommentIndex];
        System.arraycopy(this.comments, 0,
                         arr, 0,
                         arr.length);
        return arr;
    }

    /**
     * Add comment to a task. If there is no space in array for the comments,
     * the size of the array becomes two times bigger.
     * Some comment can be added to a task several times - there is NO check for that behavior.
     * If null supplied - nothing happens.
     * @param comment comment for a task
     */
    public void addComment(Comment comment) {
        if (comment == null) {
            return;
        }

        if (this.nextCommentIndex == this.comments.length) {
            int newCommentsSize = this.comments.length * 2;
            Comment[] newComments = new Comment[newCommentsSize];
            System.arraycopy(this.comments, 0,
                             newComments, 0,
                             this.comments.length);
            this.comments = newComments;
        }

        this.comments[this.nextCommentIndex++] = comment;
    }

    /**
     * String representation of a Task object.
     * @return task name, id, create time, description and all comments
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[" + this.id + "/" + this.createDate + "] ");
        str.append(this.name + "\n" + this.description + "\n");

        for (Comment comment: this.getComments()) {
            str.append(comment.toString());
        }

        return  str.toString();
    }
}
