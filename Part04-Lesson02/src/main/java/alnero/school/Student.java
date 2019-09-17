package alnero.school;

import java.util.Objects;

/**
 * Student class.
 */
public class Student {
    /**
     * Students have scores.
     */
    private int score;

    /**
     * Student is created with a score. Score is checked to be in range [0,100].
     * @param score score is in range [0, 100]
     */
    public Student(int score) {
        if (score < 0) {
            score = 0;
        }
        if (score > 100) {
            score = 100;
        }
        this.score = score;
    }

    /**
     * Get student score.
     * @return student score
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
