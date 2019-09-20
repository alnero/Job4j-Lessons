package alnero.school;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


/**
 * Testing creation of students with correct scores.
 */
public class StudentTest {
    /**
     * When create student with score in range [0,100] then correct student is created and score is in required range.
     */
    @Test
    public void whenCreateStudentWithScoreInRangeFrom0To100ThenStudentWithCorrectScoreCreated() {
        int randomScore = ThreadLocalRandom.current().nextInt(0, 101);
        Student student = new Student(randomScore, "blank");
        int studentScore = student.getScore();
        assertTrue(0 <= studentScore && studentScore <= 100);
        assertEquals(studentScore, randomScore);
    }

    /**
     * When create student with score < 0, then student is created with 0 score.
     */
    @Test
    public void whenCreateStudentWithScoreLessThanZeroThenStudentWithZeroScoreCreated() {
        int negativeRandomScore = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE) * (-1);
        Student student = new Student(negativeRandomScore, "blank");
        int studentScore = student.getScore();
        assertEquals(studentScore, 0);
    }

    /**
     * When create student with score > 100, then student is created with 100 score.
     */
    @Test
    public void whenCreateStudentWithScoreMoreThanOneHundredThenStudentWithOneHundredScoreCreated() {
        int randomMoreThanOneHundredScore = ThreadLocalRandom.current().nextInt(101, Integer.MAX_VALUE);
        Student student = new Student(randomMoreThanOneHundredScore, "blank");
        int studentScore = student.getScore();
        assertEquals(studentScore, 100);
    }
}
