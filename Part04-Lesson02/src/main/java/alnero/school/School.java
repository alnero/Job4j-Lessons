package alnero.school;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Transform list of students to map <surname: student>. In case of key collisions only first created pair survives.
     * @param students list of students
     * @return map of students <surname: student>
     */
    public Map<String, Student> toMap(List<Student> students) {
        Map<String, Student> result = students.stream().collect(Collectors.toMap(
                student -> student.getSurname(), student -> student, (s1, s2) -> s1
        ));
        return result;
    }


    /**
     * From list of students get another list of students with a score more than bound. Returned list will be sorted descending.
     * @param students initial list of students
     * @param bound bound score
     * @return list of students with scores more than bound
     */
    public List<Student> levelOf(List<Student> students, int bound) {
        List<Student> result = students
                .stream()
                .flatMap(Stream::ofNullable)
                .sorted(Comparator.comparing(student -> student.getScore(), Comparator.reverseOrder()))
                .takeWhile(student -> student.getScore() > bound)
                .collect(Collectors.toList());
        return result;
    }
}
