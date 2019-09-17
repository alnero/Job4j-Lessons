package alnero.school;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * School class.
 */
public class School {
    /**
     * Separate students to different classes according to predicate.
     * @param students common list of students
     * @param predicate some logical condition to get part of students from the list
     * @return list of students successfully passing required condition
     */
    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        List<Student> result = students.stream().filter(predicate).collect(Collectors.toList());
        return result;
    }
}
