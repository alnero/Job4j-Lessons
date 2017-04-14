package alnero;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for the task comments.
 */
public class Comment {
    /**
     * Every comment has an id.
     */
    private long id;
    /**
     * String contents of the comment.
     */
    private String content;
    /**
     * Time when the comment was created.
     */
    private Date createDate;

    /**
     * Id and time are set for every created comment.
     */
    public Comment() {
        this.setId();
        this.createDate = new Date();
    }

    /**
     * Get the id of comment.
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Id for the comment is created randomly.
     * It's type is long and it is always greater than or equal to zero.
     */
    private void setId() {
        this.id = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
    }

    /**
     * Get the contents of the comment.
     * @return comment contents
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the string contents of the comment.
     * @param content comment contents
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Only getter for the time of creation.
     * @return time of comment creation
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * String representation of the Comment object.
     * @return comment id, create time and contents
     */
    @Override
    public String toString() {
        return  "[" + this.id + "/" + this.createDate + "] " + this.content + "\n";
    }
}
