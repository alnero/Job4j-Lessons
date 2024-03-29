package alnero.school;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
            Student student = new Student(i, "blank" + i);
            this.unfilteredStudents.add(student);
        });
        /**
         * Fill expected list for range [0, 50) with 5 students with grades 0, 10, 20, 30, 40
         */
        IntStream.iterate(0, i -> i + 10).limit(5).forEach(i -> {
            Student student = new Student(i, "blank" + i);
            this.correctClassC.add(student);
        });
        /**
         * Fill expected list for range [50, 70) with 2 students with grades 50, 60
         */
        IntStream.iterate(50, i -> i + 10).limit(2).forEach(i -> {
            Student student = new Student(i, "blank" + i);
            this.correctClassB.add(student);
        });
        /**
         * Fill expected list for range [70, 100] with 4 students with grades 70, 80, 90, 100
         */
        IntStream.iterate(70, i -> i + 10).limit(4).forEach(i -> {
            Student student = new Student(i, "blank" + i);
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

    /**
     * Testing conversion of student list to student map <surname: student>.
     */
    @Test
    public void whenTransformListOfStudentsToMapOfStudentsThenCorrectMapCreated() {
        School school = new School();
        List<Student> listOfStudentsWithDuplicates = new ArrayList<>();
        listOfStudentsWithDuplicates.addAll(unfilteredStudents);
        listOfStudentsWithDuplicates.addAll(unfilteredStudents);
        Map<String, Student> studentMap = school.toMap(listOfStudentsWithDuplicates);
        Set<String> studentSurnames = studentMap.keySet();
        Set<String> expectedStudentSurnames = unfilteredStudents.stream().map(student -> student.getSurname()).collect(Collectors.toSet());
        assertEquals(studentSurnames, expectedStudentSurnames);
        Set<Student> studentSet = studentMap.values().stream().collect(Collectors.toSet());
        Set<Student> expectedStudentSet = unfilteredStudents.stream().collect(Collectors.toSet());
        assertEquals(studentSet, expectedStudentSet);
    }

    /**
     * Test that list of students with scores more than bound is received.
     */
    @Test
    public void whenCollectStudentsWithScoreMoreThan60ThenReturnedListIsClassAOfStudents() {
        School school = new School();
        List<Student> studentsWithScoreMoreThan60 = school.levelOf(unfilteredStudents, 60);
        assertEquals(studentsWithScoreMoreThan60.size(), correctClassA.size());
        assertTrue(studentsWithScoreMoreThan60.containsAll(correctClassA));
    }

    /**
     * Test that list of students with scores more than bound is sorted.
     */
    @Test
    public void whenCollectStudentsWithScoreMoreThan69ThenReturnedListIsSorted() {
        School school = new School();
        List<Student> studentsWithScoreMoreThan60 = school.levelOf(unfilteredStudents, 60);
        Collections.reverse(studentsWithScoreMoreThan60);
        Assert.assertEquals(studentsWithScoreMoreThan60, correctClassA);
    }

    /**
     * Test that list of students with scores more than bound does not contain null even if supplied list has them.
     */
    @Test
    public void whenStudentListHasNullsAndWeCollectStudentsWithScoreMoreThan60ThenReturnedListDoesNotHaveNulls() {
        List<Student> unfilteredStudentsWithNulls = unfilteredStudents
                .stream()
                .map(student -> Arrays.asList(student, null))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        School school = new School();
        List<Student> studentsWithScoreMoreThan60 = school.levelOf(unfilteredStudentsWithNulls, 60);
        assertTrue(!studentsWithScoreMoreThan60.contains(null));
    }
}
