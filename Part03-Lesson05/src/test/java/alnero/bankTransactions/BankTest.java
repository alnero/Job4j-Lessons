package alnero.bankTransactions;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

/**
 * Test Bank class in bank transactions.
 */
public class BankTest {
    /**
     * When adding new user to bank his list of accounts is empty.
     */
    @Test
    public void whenAddUserThenHisListOfAccountsIsEmpty() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        bank.addUser(user);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.size(), is(0));
    }

    /**
     * When adding null instead of user, no user added and his list of account is null.
     */
    @Test
    public void whenAddNullInsteadOfUserThenHisListOfAccountsIsNull() {
        Bank bank = new Bank();
        bank.addUser(null);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts, nullValue());
    }

    /**
     * When trying to add same user twice, user is not added for the second time
     * and his list of accounts stays the same.
     */
    @Test
    public void whenAddSameUserTwiceThenOnlyOneUserAddedToBankAndHisAccountsAreTheSame() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00112233445566", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", account);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        bank.addUser(user);
        assertThat(userAccounts.size(), is(1));
    }

    /**
     * When deleting user from bank his list of accounts become null.
     */
    @Test
    public void whenDeleteUserThenHisListOfAccountsIsNull() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        bank.addUser(user);
        bank.deleteUser(user);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts, nullValue());
    }

    /**
     * When deleting null instead of user, user is not deleted and his list of account stays the same.
     */
    @Test
    public void whenDeleteNullInsteadOfUserThenHisListOfAccountsIsEmpty() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        bank.addUser(user);
        bank.deleteUser(null);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.size(), is(0));
    }

    /**
     * When trying to delete user who is not added to bank nothing happens.
     */
    @Test
    public void whenDeleteUserWhichIsNotInBankThenHisListOfAccountsIsNull() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        bank.deleteUser(user);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts, nullValue());
    }

    /**
     * When adding one account to user, user will have one account.
     */
    @Test
    public void whenAddOneAccountToUserThenHisListOfAccountsHasOneAccount() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00112233445566", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", account);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.size(), is(1));
        assertThat(userAccounts.get(0), is(account));
    }

    /**
     * When trying to add same account twice, account is not added and user has only one account.
     */
    @Test
    public void whenAddTwoSameAccountsToUserThenHisListOfAccountsHasOneAccount() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00112233445566", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", account);
        bank.addAccountToUser("1234567890", account);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.size(), is(1));
        assertThat(userAccounts.get(0), is(account));
    }

    /**
     * When trying to use null as arguments while adding account to user, nothing happens.
     */
    @Test
    public void whenUseNullAsPassportOrAccountWhileAddingAccountToUserThenNoNewAccountAddedToUser() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00112233445566", 1.0);
        bank.addUser(user);
        bank.addAccountToUser(null, account);
        bank.addAccountToUser("1234567890", null);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.size(), is(0));
    }

    /**
     * Deleting one account from user having one account.
     */
    @Test
    public void whenDeleteAccountFromUserHavingOneAccountThenHisListOfAccountsIsEmpty() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00112233445566", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", account);
        bank.deleteAccountFromUser("1234567890", account);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.size(), is(0));
    }

    /**
     * Trying to delete account which is not in the list of user accounts.
     */
    @Test
    public void whenDeleteAccountWhichIsNotInUserAccountsThenHisListOfAccountsIsEmpty() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00112233445566", 1.0);
        bank.addUser(user);
        bank.deleteAccountFromUser("1234567890", account);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.size(), is(0));
    }

    /**
     * When trying to use null as arguments while deleting account from user, nothing happens.
     */
    @Test
    public void whenUseNullAsPassportOrAccountWhileDeletingAccountFromUserThenNoAccountDeletedFromUser() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00112233445566", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", account);
        bank.deleteAccountFromUser(null, account);
        bank.deleteAccountFromUser("1234567890", null);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.size(), is(1));
    }

    /**
     * Getting list of user accounts from bank.
     */
    @Test
    public void whenGetUserAccountsThenProperUserAccountsReturned() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account accountOne = new Account("00111111111111", 1.0);
        Account accountTwo = new Account("00222222222222", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", accountOne);
        bank.addAccountToUser("1234567890", accountTwo);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        assertThat(userAccounts.get(0), is(accountOne));
        assertThat(userAccounts.get(1), is(accountTwo));
    }

    /**
     * When getting list of user accounts, copy is returned.
     */
    @Test
    public void whenGetUserAccountsThenCopyOfUserAccountsReturned() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account accountOne = new Account("00111111111111", 1.0);
        Account accountTwo = new Account("00222222222222", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", accountOne);
        List<Account> userAccounts = bank.getUserAccounts("1234567890");
        bank.addAccountToUser("1234567890", accountTwo);
        assertThat(userAccounts.size(), is(1));
        assertThat(userAccounts.get(0), is(accountOne));
    }

    /**
     * When using null as passport to get user accounts, null is returned.
     */
    @Test
    public void whenUseNullAsPassportToGetUserAccountsThenNullIsReturned() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00111111111111", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", account);
        List<Account> userAccounts = bank.getUserAccounts(null);
        assertThat(userAccounts, nullValue());
    }

    /**
     * When account is added to user it is found by user passport and requisites.
     */
    @Test
    public void whenFindUserAccountThenCorrectUserAccountReturned() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00111111111111", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", account);
        Account userAccount = bank.findUserAccount("1234567890", "00111111111111");
        assertThat(userAccount, is(account));
    }

    /**
     * When user does not have a required account, null is returned.
     */
    @Test
    public void whenFindAccountNotAddedToUserThenNullReturned() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00111111111111", 1.0);
        bank.addUser(user);
        Account userAccount = bank.findUserAccount("1234567890", "00111111111111");
        assertThat(userAccount, nullValue());
    }

    /**
     * Find user account among multiple accounts.
     */
    @Test
    public void whenAddThreeAccountsToUserThenCorrectAccountFound() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account accountOne = new Account("00111111111111", 1.0);
        Account accountTwo = new Account("00222222222222", 1.0);
        Account accountThree = new Account("00333333333333", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", accountOne);
        bank.addAccountToUser("1234567890", accountTwo);
        bank.addAccountToUser("1234567890", accountThree);
        Account userAccountTwo = bank.findUserAccount("1234567890", "00222222222222");
        assertThat(userAccountTwo, is(accountTwo));
    }

    /**
     * When using null as arguments to find user account null is returned.
     */
    @Test
    public void whenUseNullAsPassportOrRequisitesToFindUserAccountThenNullReturned() {
        Bank bank = new Bank();
        User user = new User("Alan", "1234567890");
        Account account = new Account("00111111111111", 1.0);
        bank.addUser(user);
        bank.addAccountToUser("1234567890", account);
        Account userAccountWithNullPassport = bank.findUserAccount(null, "00111111111111");
        assertThat(userAccountWithNullPassport, nullValue());
        Account userAccountWithNullRequisites = bank.findUserAccount("1234567890", null);
        assertThat(userAccountWithNullRequisites, nullValue());
    }

    /**
     * When using null as arguments to transfer amounts false is returned.
     */
    @Test
    public void whenUseNullAsPassportOrRequisitesWhileTransferMoneyThenFalseReturned() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        Account srcAccount = new Account("11223344556677889900", 1.0);
        bank.addUser(srcUser);
        bank.addAccountToUser("1234567890", srcAccount);
        User destUser = new User("Destination", "0987654321");
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addUser(destUser);
        bank.addAccountToUser("0987654321", destAccount);
        boolean resultWhenSrcPassportNull = bank.transferMoney(
                null,
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                1.0);
        assertThat(resultWhenSrcPassportNull, is(false));
        boolean resultWhenSrcRequisitesNull = bank.transferMoney(
                "1234567890",
                null,
                "0987654321",
                "00998877665544332211",
                1.0);
        assertThat(resultWhenSrcRequisitesNull, is(false));
        boolean resultWhenDstPassportNull = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                null,
                "00998877665544332211",
                1.0);
        assertThat(resultWhenDstPassportNull, is(false));
        boolean resultWhenDstRequisitesNull = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                null,
                1.0);
        assertThat(resultWhenDstRequisitesNull, is(false));
    }

    /**
     * When trying to transfer negative amount false is returned.
     */
    @Test
    public void whenUseNegativeAmountWhileTransferMoneyThenFalseReturned() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        Account srcAccount = new Account("11223344556677889900", 1.0);
        bank.addUser(srcUser);
        bank.addAccountToUser("1234567890", srcAccount);
        User destUser = new User("Destination", "0987654321");
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addUser(destUser);
        bank.addAccountToUser("0987654321", destAccount);
        boolean resultWhenAmountNegative = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                -1.0);
        assertThat(resultWhenAmountNegative, is(false));
    }

    /**
     * When trying to transfer zero amount false is returned.
     */
    @Test
    public void whenUseZeroAsAmountWhileTransferMoneyThenFalseReturned() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        Account srcAccount = new Account("11223344556677889900", 1.0);
        bank.addUser(srcUser);
        bank.addAccountToUser("1234567890", srcAccount);
        User destUser = new User("Destination", "0987654321");
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addUser(destUser);
        bank.addAccountToUser("0987654321", destAccount);
        boolean resultWhenAmountZero = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                0);
        assertThat(resultWhenAmountZero, is(false));
    }

    /**
     * When source account not found while transfer amounts false is returned.
     */
    @Test
    public void whenSourceAccountNotFoundWhileTransferMoneyThenFalseReturned() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        bank.addUser(srcUser);
        User destUser = new User("Destination", "0987654321");
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addUser(destUser);
        bank.addAccountToUser("0987654321", destAccount);
        boolean resultWhenSrcAccountNotFound = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                1.0);
        assertThat(resultWhenSrcAccountNotFound, is(false));
    }

    /**
     * When destination account not found while transfer amounts false is returned.
     */
    @Test
    public void whenDestAccountNotFoundWhileTransferMoneyThenFalseReturned() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        Account srcAccount = new Account("11223344556677889900", 1.0);
        bank.addUser(srcUser);
        bank.addAccountToUser("1234567890", srcAccount);
        User destUser = new User("Destination", "0987654321");
        bank.addUser(destUser);
        boolean resultWhenDestAccountNotFound = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                1.0);
        assertThat(resultWhenDestAccountNotFound, is(false));
    }

    /**
     * When there is not enough amount at source account false is returned.
     */
    @Test
    public void whenSrcValueLessThenAmountWhileTransferMoneyThenFalseReturned() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        Account srcAccount = new Account("11223344556677889900", 0.0);
        bank.addUser(srcUser);
        bank.addAccountToUser("1234567890", srcAccount);
        User destUser = new User("Destination", "0987654321");
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addUser(destUser);
        bank.addAccountToUser("0987654321", destAccount);
        boolean resultWhenSrcValueLessThenAmount = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                1.0);
        assertThat(resultWhenSrcValueLessThenAmount, is(false));
    }

    /**
     * When trying to transfer less amount than possible true is returned and no transfer happens.
     */
    @Test
    public void whenAmountIsLessThenMinTransferValueWhileTransferMoneyThenFalseReturnedAndNoTransferHappens() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        Account srcAccount = new Account("11223344556677889900", 1.0);
        bank.addUser(srcUser);
        bank.addAccountToUser("1234567890", srcAccount);
        User destUser = new User("Destination", "0987654321");
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addUser(destUser);
        bank.addAccountToUser("0987654321", destAccount);
        boolean resultWhenSrcValueLessThenAmount = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                0.0004);
        assertThat(resultWhenSrcValueLessThenAmount, is(false));
        double srcAccountValue = bank.getUserAccounts("1234567890").get(0).getValue();
        assertThat(srcAccountValue, is(1.0));
    }


    /**
     * To avoid floating point arithmetic effects like
     * 0.10000000000000014 - 0.1 = 1.3877787807814457E-16
     * values are rounded.
     */
    @Test
    public void whenTransferAmountThatCausesBadMathThenRoundingWorksAndTrueReturned() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        Account srcAccount = new Account("11223344556677889900", 0.10000000000000014);
        bank.addUser(srcUser);
        bank.addAccountToUser("1234567890", srcAccount);
        User destUser = new User("Destination", "0987654321");
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addUser(destUser);
        bank.addAccountToUser("0987654321", destAccount);
        boolean resultWhenTransferCausesBadMath = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                0.1);
        assertThat(resultWhenTransferCausesBadMath, is(true));
    }

    /**
     * Transferring between accounts of different users.
     */
    @Test
    public void whenTransferMoneyFromAccountOfOneUserToAccountOfOtherUserThenTrueReturnedAndAmountIsTransferred() {
        Bank bank = new Bank();
        User srcUser = new User("Source", "1234567890");
        Account srcAccount = new Account("11223344556677889900", 1.0);
        bank.addUser(srcUser);
        bank.addAccountToUser("1234567890", srcAccount);
        User destUser = new User("Destination", "0987654321");
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addUser(destUser);
        bank.addAccountToUser("0987654321", destAccount);
        boolean resultWhenAmountZero = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "0987654321",
                "00998877665544332211",
                1.0);
        assertThat(resultWhenAmountZero, is(true));
        double destAccountValue = bank.getUserAccounts("0987654321").get(0).getValue();
        assertThat(destAccountValue, is(1.0));
    }

    /**
     * Transferring between accounts of one user.
     */
    @Test
    public void whenTransferMoneyFromOneAccountToOtherAccountOfOneUserThenTrueReturnedAndAmountIsTransferred() {
        Bank bank = new Bank();
        User srcAndDestUser = new User("SourceAndDestination", "1234567890");
        bank.addUser(srcAndDestUser);
        Account srcAccount = new Account("11223344556677889900", 1.0);
        bank.addAccountToUser("1234567890", srcAccount);
        Account destAccount = new Account("00998877665544332211", 0.0);
        bank.addAccountToUser("1234567890", destAccount);
        boolean resultWhenAmountZero = bank.transferMoney(
                "1234567890",
                "11223344556677889900",
                "1234567890",
                "00998877665544332211",
                1.0);
        assertThat(resultWhenAmountZero, is(true));
        double destAccountValue = bank.getUserAccounts("1234567890").get(1).getValue();
        assertThat(destAccountValue, is(1.0));
    }
}