package alnero;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;


/**
 * Testing methods in Comment class.
 */
public class CommentTest {

    /**
     * Test that id of created comment is of long type and is greater or equal to zero.
     */
    @Test
    public void whenCreateCommentThenItsIdOfTypeLong() {
        Comment comment = new Comment();
        Assert.assertThat(comment.getId(), instanceOf(Long.class));
        Assert.assertThat(comment.getId(), greaterThanOrEqualTo(0L));
    }

    /**
     * Test that creation time of a comment is correct.
     */
    @Test
    public void whenCreateCommentThenItsTimeOfCreationIsCorrect() {
        Comment comment = new Comment();
        Date now = new Date();
        Assert.assertThat(now.compareTo(comment.getCreateDate()), greaterThanOrEqualTo(0));
    }


    /**
     * Test that correct string is returned from comment.
     */
    @Test
    public void whenSetTheContentOfCommentThenItsContentIsReturnedFromComment() {
        Comment comment = new Comment();
        comment.setContent("Bender: Kill All Humans!");
        Assert.assertThat(comment.getContent(), is("Bender: Kill All Humans!"));
    }

    /**
     * When null is set as comment content then null is returned from comment.
     */
    @Test
    public void whenSetNullAsCommentContentThenNullIsReturnedAsCommetnContent() {
        Comment comment = new Comment();
        comment.setContent(null);
        Assert.assertThat(comment.getContent(), is(nullValue()));
    }

    /**
     * Test that comment object is printed out in proper format defined in toString method.
     */
    @Test
    public void whenCommentIsPrintedThenPrintoutIsOfProperFormat() {
        Comment comment = new Comment();
        Assert.assertThat(comment.toString().matches("\\[.+\\/.+\\]*\n"), is(true));
    }
}