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

    /**
     * Withdraw certain amount from account value.
     * Return true or false depending on result of withdrawal.
     * To overcome float arithmetic tricks value of account and amount of withdrawal
     * are rounded to 3 decimal places.
     * Minimum value to withdraw is 0.0005.
     * It is not possible to withdraw negative or zero amount.
     * @param amount amount to withdraw from account
     * @return true or false depending on result of withdrawal
     */
    public boolean withdrawMoney(double amount) {
        double roundedAmount = Math.round(amount * 1000.0) / 1000.0;
        if (roundedAmount <= 0) {
            return false;
        }
        double accountValueAfterWithdrawal = Math.round(this.value * 1000.0) / 1000.0 - roundedAmount;
        if (accountValueAfterWithdrawal < 0) {
            return false;
        }
        this.setValue(accountValueAfterWithdrawal);
        return true;
    }

    /**
     * Add certain amount to account value.
     * Always returns true as a result of adding.
     * To overcome float arithmetic tricks value of account and amount of addition
     * are rounded to 3 decimal places.
     * Minimum value to add is 0.0005.
     * It is not possible to add negative or zero amount.
     * @param amount amount to add to account
     * @return true or false depending on result of adding
     */
    public boolean addMoney(double amount) {
        double roundedAmount = Math.round(amount * 1000.0) / 1000.0;
        if (roundedAmount <= 0) {
            return false;
        }
        this.setValue(Math.round(this.value * 1000.0) / 1000.0 + roundedAmount);
        return true;
    }
}
