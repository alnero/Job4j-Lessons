package alnero;

import java.util.Date;

/**
 * Ancestor class for Teacher, Engineer, Doctor and other profession classes.
 */
public class Profession {
    /**
     * Diploma name and maybe number.
     */
    private String diploma;
    /**
     * Academic/Scientific degree, e.g. Professor, Dean, M.D.
     */
    private String degree;
    /**
     * Experience in profession in full years.
     */
    private int experience;
    /**
     * Salary paid.
     */
    private double salary;

    /**
     * In most of the professions specialists consult their colleagues.
     *
     * @param colleague object of class Profession
     * @return some info represented by String
     */
    public String consult(Profession colleague) {
        return "Some info on the field...";
    }

    /**
     * Specialists commonly visit meet-ups, conferences, meetings etc.
     *
     * @param time     time when the conference will be held
     * @param title    name of the conference
     * @param location where the conference will be, e.g. town, place
     */
    public void attendConference(Date time, String title, String location) {

    }

}
