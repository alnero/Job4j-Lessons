package alnero.bankTransactions;

/**
 * Bank account.
 */
public class Account {
    /**
     * Account requisites.
     */
    private String requisites;
    /**
     * Account value.
     */
    private double value;

    /**
     * To create bank account requisites and value are required.
     * @param requisites account requisites
     * @param value account value
     */
    public Account(String requisites, double value) {
        this.requisites = requisites;
        this.value = value;
    }

    /**
     * Get account requisites.
     * @return account requisites
     */
    public String getRequisites() {
        return this.requisites;
    }

    /**
     * Get account value.
     * @return account value
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Set account value.
     * @param value account value
     */
    public void setValue(double value) {
        this.value = value;
    }
}
