package alnero;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;


/**
 * Testing methods in Task class.
 */
public class TaskTest {

    /**
     * Test that id of created task is of long type and is greater or equal to zero.
     */
    @Test
    public void whenCreateTaskThenItsIdOfTypeLong() {
        Task task = new Task();
        Assert.assertThat(task.getId(), instanceOf(Long.class));
        Assert.assertThat(task.getId(), greaterThanOrEqualTo(0L));
    }

    /**
     * When name of the task is not set it is Null.
     */
    @Test
    public void whenCreateTaskAndNameIsNotSetThenTaskNameIsNull() {
        Task task = new Task();
        Assert.assertNull(task.getName());
    }

    /**
     * When name of the task is set it is properly returned.
     */
    @Test
    public void whenSetNameOfTheTaskThenCorrectNameIsReturned() {
        Task task = new Task();
        task.setName("Task For Bender");
        Assert.assertThat(task.getName(), is("Task For Bender"));
    }

    /**
     * When use null value as a name then null returned when you get name from a task.
     */
    @Test
    public void whenNullIsSetAsNameOfTaskThenNullReturnedAsNameOfTask() {
        Task task = new Task();
        task.setName(null);
        Assert.assertThat(task.getName(), is(nullValue()));
    }

    /**
     * When description of the task is not set it is Null.
     */
    @Test
    public void whenCreateTaskAndDescriptionIsNotSetThenTaskDescriptionIsNull() {
        Task task = new Task();
        Assert.assertNull(task.getDescription());
    }

    /**
     * When description of the task is set it is correctly returned.
     */
    @Test
    public void whenSetDescriptionOfTheTaskThenCorrectDescriptionIsReturned() {
        Task task = new Task();
        task.setDescription("Kill All Humans!");
        Assert.assertThat(task.getDescription(), is("Kill All Humans!"));
    }

    /**
     * When use null value as a description to task then null returned when you ask description from task.
     */
    @Test
    public void whenNullIsSetAsDescriptionOfTaskThenNullReturnedAsDescriptionOfTask() {
        Task task = new Task();
        task.setDescription(null);
        Assert.assertThat(task.getDescription(), is(nullValue()));
    }

    /**
     * Test that creation time of a task is correct.
     */
    @Test
    public void whenCreateTaskThenItsTimeOfCreationIsCorrect() {
        Task task = new Task();
        Date now = new Date();
        Assert.assertThat(now.compareTo(task.getCreateDate()), greaterThanOrEqualTo(0));
    }

    /**
     * When comments are added then empty array of Comments is returned.
     */
    @Test
    public void whenCreateTaskAndNoCommentsAddedThenCommentsArrayForTheTaskIsEmpty() {
        Task task = new Task();
        Assert.assertThat(task.getComments().length, is(0));
    }

    /**
     * When comment is added to a task it is correctly returned.
     */
    @Test
    public void whenCommentIsAddedToTaskThenItIsCorrectlyReturned() {
        Task task = new Task();
        Comment comment = new Comment();

        comment.setContent("Fry: Help me!");
        task.addComment(comment);

        Assert.assertThat(task.getComments()[0], is(comment));
    }

    /**
     * When random number of comments [0 to 50] is added to a task, then all comments are returned correctly.
     */
    @Test
    public void whenRandomCommentsAreAddedToTaskThenAllCommentsAreCorrectlyReturned() {
        int numOfComments = ThreadLocalRandom.current().nextInt(51);

        Task task = new Task();
        Comment[] testComments = new Comment[numOfComments];

        for (int i = 0; i < testComments.length; i++) {
            Comment comment = new Comment();
            comment.setContent("Comment " + i);

            testComments[i] = comment;
            task.addComment(comment);
        }

        Assert.assertArrayEquals(task.getComments(), testComments);
    }

    /**
     * When null is added as a comment to a new task, then there is no comments in that task.
     */
    @Test
    public void whenAddNullAsCommentToTaskThenCommentIsNotAdded() {
        Task task = new Task();

        task.addComment(null);

        Assert.assertThat(task.getComments().length, is(0));
    }


    /**
     * Size of array for comments in task is increased when it is full.
     */
    @Test
    public void whenArrayForCommentsInTaskIsFullThenItsSizeIsIncreasedAndAllCommentsStoredCorrectly() {
        int numOfComments = ThreadLocalRandom.current().nextInt(11, 50); // should be more than 10

        Task task = new Task();
        Comment[] testComments = new Comment[numOfComments];

        for (int i = 0; i < testComments.length; i++) {
            Comment comment = new Comment();
            comment.setContent("Comment " + i);

            testComments[i] = comment;
            task.addComment(comment);
        }

        Assert.assertArrayEquals(task.getComments(), testComments);
    }

    /**
     * Test that task object is printed out in proper format as defined in toString method.
     */
    @Test
    public void whenTaskIsPrintedThenPrintoutIsOfProperFormat() {
        Task task = new Task();
        task.setName("Task For Bender");
        task.setDescription("Kill All Humans!");

        Comment fryComment = new Comment();
        fryComment.setContent("Fry: Help me!");
        task.addComment(fryComment);

        Comment liloComment = new Comment();
        liloComment.setContent("Lilo: I am not a human.");
        task.addComment(liloComment);

        Comment fransworthComment = new Comment();
        fransworthComment.setContent("Fransworth: Let's do it together Bender!.");
        task.addComment(fransworthComment);

        Assert.assertThat(task.toString().matches("\\[.+\\/.+\\].+\\n.+(\\s|.)*"), is(true));
    }

}
