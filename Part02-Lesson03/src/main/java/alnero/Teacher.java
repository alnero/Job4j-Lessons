package alnero;

import java.util.Date;
import java.util.Random;

/**
 * Class for the profession of Teacher.
 */
public class Teacher extends Profession {
    /**
     * Subject a teacher teaches.
     */
    private String subject;
    /**
     * Grade level in which a teacher works: school, institute.
     */
    private String gradeLevel;

    /**
     * Check presence of pupils at lessons.
     *
     * @param pupils Original list of pupils to be present at the lesson
     * @return List of pupils actually present
     */
    public Human[] checkPresence(Human[] pupils) {
        return pupils;
    }

    /**
     * Give lectures.
     *
     * @param pupils pupils present at the lecture
     * @return some info represented by String
     */
    public String lecture(Human[] pupils) {
        return "Some info on the " + this.subject;
    }

    /**
     * Checks homeworks and provide marks.
     *
     * @param pupil person whom homework is been checked
     * @return some mark for the homework from 1 to 5
     */
    public int checkHomework(Human pupil) {
        return new Random().nextInt(5) + 1;
    }

    /**
     * Participate in meeting.
     *
     * @param time      time of meeting
     * @param classroom location of meeting, usually some classroom
     */
    public void attendMeeting(Date time, String classroom) {

    }
}
