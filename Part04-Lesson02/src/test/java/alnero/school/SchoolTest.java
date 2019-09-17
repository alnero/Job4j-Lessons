package alnero.school;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Testing collect method in School class.
 */
public class SchoolTest {
    /**
     * Common list of student with different scores.
     */
    private List<Student> unfilteredStudents = new ArrayList<>();
    /**
     * Expected list of students with scores in range [70, 100].
     */
    private List<Student> correctClassA = new ArrayList<>();
    /**
     * Expected list of students with scores in range [50, 70).
     */
    private List<Student> correctClassB = new ArrayList<>();
    /**
     * Expected list of students with scores in range [0, 50).
     */
    private List<Student> correctClassC = new ArrayList<>();

    /**
     * Populate common list of students and expected classes.
     */
    @Before
    public void createInitialClassAndCorrectClassesOfStudents() {
        /**
         * Fill common list with 11 students with grades 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100
         */
        IntStream.iterate(0, i -> i + 10).limit(11).forEach(i -> {
            Student student = new Student(i);
            this.unfilteredStudents.add(student);
        });
        /**
         * Fill expected list for range [0, 50) with 5 students with grades 0, 10, 20, 30, 40
         */
        IntStream.iterate(0, i -> i + 10).limit(5).forEach(i -> {
            Student student = new Student(i);
            this.correctClassC.add(student);
        });
        /**
         * Fill expected list for range [50, 70) with 2 students with grades 50, 60
         */
        IntStream.iterate(50, i -> i + 10).limit(2).forEach(i -> {
            Student student = new Student(i);
            this.correctClassB.add(student);
        });
        /**
         * Fill expected list for range [70, 100] with 4 students with grades 70, 80, 90, 100
         */
        IntStream.iterate(70, i -> i + 10).limit(4).forEach(i -> {
            Student student = new Student(i);
            this.correctClassA.add(student);
        });
    }

    /**
     * Testing predicate for score in range [70, 100). Expect to have 4 students in class.
     */
    @Test
    public void whenAddStudentsToClassAThenFourStudentsAdded() {
        School school = new School();
        List<Student> classA = school.collect(unfilteredStudents, student -> student.getScore() >= 70 && student.getScore() <= 100);
        assertEquals(classA, correctClassA);
        assertThat(classA.size(), is(4));
    }

    /**
     * Testing predicate for score in range [50, 70). Expect to have 2 students in class.
     */
    @Test
    public void whenAddStudentsToClassBThenTwoStudentsAdded() {
        School school = new School();
        List<Student> classB = school.collect(unfilteredStudents, student -> student.getScore() >= 50 && student.getScore() < 70);
        assertEquals(classB, correctClassB);
        assertThat(classB.size(), is(2));
    }

    /**
     * Testing predicate for score in range [0, 50). Expect to have 5 students in class.
     */
    @Test
    public void whenAddStudentsToClassCThenFiveStudentsAdded() {
        School school = new School();
        List<Student> classC = school.collect(unfilteredStudents, student -> student.getScore() >= 0 && student.getScore() < 50);
        assertEquals(classC, correctClassC);
        assertThat(classC.size(), is(5));
    }
}
