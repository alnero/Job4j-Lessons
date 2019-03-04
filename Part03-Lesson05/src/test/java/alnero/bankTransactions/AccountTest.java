package alnero.bankTransactions;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Account class in bank transactions.
 */
public class AccountTest {
    /**
     * Test correct withdrawal.
     */
    @Test
    public void whenWithdrawMoneyFromAccountThenCorrectAmountWithdrawn() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenEnoughMoneyAndCorrectAmount = account.withdrawMoney(0.5);
        assertThat(resultWhenEnoughMoneyAndCorrectAmount, is(true));
        assertThat(account.getValue(), is(0.5));
    }

    /**
     * Trying to withdraw less than possible amount.
     */
    @Test
    public void whenWithdrawLessThanPossibleAmountFromAccountThenNoWithdrawalHappensAndFalseReturned() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenWithdrawLessThanPossible = account.withdrawMoney(0.0004);
        assertThat(resultWhenWithdrawLessThanPossible, is(false));
        assertThat(account.getValue(), is(1.0));
    }

    /**
     * Trying to withdraw minimum possible amount.
     */
    @Test
    public void whenWithdrawMinimumPossibleAmountFromAccountThenWithdrawalHappensAndTrueReturned() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenWithdrawMinimumPossibleAmount = account.withdrawMoney(0.0005);
        assertThat(resultWhenWithdrawMinimumPossibleAmount, is(true));
        assertThat(account.getValue(), is(0.999));
    }

    /**
     * Trying to withdraw more than account has.
     */
    @Test
    public void whenTryingToWithdrawMoreThanAccountValueThenNoWithdrawalHappensAndFalseReturned() {
        Account account = new Account("00112233445566", 0.5);
        boolean resultWhenWithdrawMoreThanAccountValue = account.withdrawMoney(1.0);
        assertThat(resultWhenWithdrawMoreThanAccountValue, is(false));
        assertThat(account.getValue(), is(0.5));
    }

    /**
     * Trying to withdraw negative amount.
     */
    @Test
    public void whenTryingToWithdrawNegativeAmountThenNoWithdrawalHappensAndFalseReturned() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenWithdrawNegativeAmount = account.withdrawMoney(-0.5);
        assertThat(resultWhenWithdrawNegativeAmount, is(false));
        assertThat(account.getValue(), is(1.0));
    }

    /**
     * Trying to withdraw zero amount.
     */
    @Test
    public void whenTryingToWithdrawZeroAmountThenNoWithdrawalHappensAndFalseReturned() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenWithdrawZeroAmount = account.withdrawMoney(0);
        assertThat(resultWhenWithdrawZeroAmount, is(false));
        assertThat(account.getValue(), is(1.0));
    }

    /**
     * Test correct addition.
     */
    @Test
    public void whenAddMoneyToAccountThenCorrectAmountAdded() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenAddCorrectAmount = account.addMoney(0.5);
        assertThat(resultWhenAddCorrectAmount, is(true));
        assertThat(account.getValue(), is(1.5));
    }

    /**
     * Trying to add less than possible amount.
     */
    @Test
    public void whenAddLessThanPossibleAmountThenAccountValueDoesNotChangeAndFalseReturned() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenAddLessThenPossibleAmount = account.addMoney(0.0004);
        assertThat(resultWhenAddLessThenPossibleAmount, is(false));
        assertThat(account.getValue(), is(1.0));
    }

    /**
     * Trying to add minimum possible amount.
     */
    @Test
    public void whenAddMinimumPossibleAmountThenAccountValueChangesAndTrueReturned() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenAddMinimumPossibleAmount = account.addMoney(0.0005);
        assertThat(resultWhenAddMinimumPossibleAmount, is(true));
        assertThat(account.getValue(), is(1.001));
    }

    /**
     * Trying to add negative amount.
     */
    @Test
    public void whenTryingToAddNegativeAmountThenAccountValueDoesNotChangeAndFalseReturned() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenAddNegativeAmount = account.addMoney(-0.5);
        assertThat(resultWhenAddNegativeAmount, is(false));
        assertThat(account.getValue(), is(1.0));
    }

    /**
     * Trying to add zero amount.
     */
    @Test
    public void whenTryingToAddZeroAmountThenAccountValueDoesNotChangeAndFalseReturned() {
        Account account = new Account("00112233445566", 1.0);
        boolean resultWhenAddZeroAmount = account.addMoney(0);
        assertThat(resultWhenAddZeroAmount, is(false));
        assertThat(account.getValue(), is(1.0));
    }
}
