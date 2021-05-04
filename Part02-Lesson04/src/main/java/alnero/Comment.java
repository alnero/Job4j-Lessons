package alnero;

import java.util.Date;
import java.util.Objects;
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
     * Id for a comment can be changed by storage mechanism.
     * @param id new id for the comment
     */
    public void changeId(long id) {
        this.id = id;
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
     * Get the time of comment creation.
     * @return time of comment creation
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Set the time of comment creation.
     * @param createDate time of task creation
     */
    public void setCreateDate(Date createDate) {
        this.createDate =  createDate;
    }

    /**
     * String representation of the Comment object.
     * @return comment id, create time and contents
     */
    @Override
    public String toString() {
        return  "[" + this.id + "/" + this.createDate + "] " + this.content + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return getId() == comment.getId()
                && Objects.equals(getContent(), comment.getContent())
                && Objects.equals(getCreateDate(), comment.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getCreateDate());
    }
}
