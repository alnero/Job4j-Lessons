package alnero;

/**
 * Class for the profession of Doctor.
 */
public class Doctor {
    /**
     * Doctor medical specialization.
     */
    private String specialization;

    /**
     * Write prescription for patient.
     *
     * @param patient human receiving the prescription
     * @return prescription in String format
     */
    public String writePrescription(Human patient) {
        return "Names of some medicines...";
    }

    /**
     * Determine which disease a patient has.
     *
     * @param patient human been diagnosed
     * @return diagnosis in String formant
     */
    public String makeDiagnosis(Human patient) {
        return "Some diagnosis...";
    }

    /**
     * Create new medicines.
     *
     * @param disease medicine is made for some definite disease
     * @return some new object of Medicine class
     */
    public Medicine createMedicine(String disease) {
        return new Medicine();
    }

    /**
     * Medicine created by a doctor.
     */
    private class Medicine {
    }

    /**
     * Give consultation to a patient.
     * @param patient person been consulted by a doctor
     * @return some info in String format
     */
    public String consult(Human patient) {
        return "Some info on the patient state...";
    }

    /**
     * Take class or lecture on various medical problems.
     *
     * @param field some topic of the class or lecture
     */
    public void takeClass(String field) {

    }

    /**
     * Update records of the patient on some database.
     *
     * @param patient human whose records are been updated
     * @return array of updated records in String format
     */
    public String[] updateRecord(Human patient) {
        return new String[5];
    }
}
