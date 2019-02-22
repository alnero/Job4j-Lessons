package alnero.bankTransactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bank keeps users' accounts and makes transactions.
 */
public class Bank {
    /**
     * Storage for deposits, one user can have multiple accounts.
     */
    private Map<User, List<Account>> deposits;

    /**
     * Constructor creates storage for deposits.
     */
    public Bank() {
        this.deposits = new HashMap<>();
    }

    /**
     * Adding user with empty list of accounts to bank.
     * If null used as argument no user added.
     * @param user user to add
     */
    public void addUser(User user) {
        if (user == null) {
            return;
        }
        this.deposits.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Deleting user from bank.
     * Is null used as argument no deletion happens.
     * @param user user to delete
     */
    public void deleteUser(User user) {
        if (user == null) {
            return;
        }
        this.deposits.remove(user);
    }

    /**
     * Adding account to user. User is searched by passport.
     * If user already has this account or user not found account is not added.
     * If null is used as any of arguments account is not added.
     * @param passport user passport
     * @param account account to add
     */
    public void addAccountToUser(String passport, Account account) {
        if (passport == null || account == null) {
            return;
        }
        List<Account> userAccounts = null;
        for (User user : this.deposits.keySet()) {
            if (passport.equals(user.getPassport())) {
                userAccounts = this.deposits.get(user);
            }
        }
        if (userAccounts == null || userAccounts.contains(account)) {
            return;
        }
        userAccounts.add(account);
    }

    /**
     * Deleting account from user. User is searched by passport.
     * If no user not found no deletion happens.
     * If null is used as any of arguments no deletion happens.
     * @param passport user passport
     * @param account user account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (passport == null || account == null) {
            return;
        }
        for (User user : this.deposits.keySet()) {
            if (passport.equals(user.getPassport())) {
                this.deposits.get(user).remove(account);
            }
        }
    }

    /**
     * Get copy of list of user accounts. User is searched by passport.
     * If user not found or null is used as argument null is returned.
     * @param passport user passport
     * @return copy of list of user account or null
     */
    public List<Account> getUserAccounts(String passport) {
        if (passport == null) {
            return null;
        }
        List<Account> userAccounts = null;
        for (User user : this.deposits.keySet()) {
            if (passport.equals(user.getPassport())) {
                userAccounts = new ArrayList<>();
                userAccounts.addAll(this.deposits.get(user));
            }
        }
        return userAccounts;
    }

    /**
     * Transfer amounts from one account to other one.
     * Transfer can happen between accounts of one user.
     * Accounts are searched by user passports and accounts requisites.
     * Transfer not done and false is returned when:
     * - null is used as any of parameters
     * - negative or zero amount is transferred
     * - source of destination account is not found
     * - amount of source account is not enough for transfer
     * To overcome float arithmetic tricks value of source account and amount of transfer
     * are rounded to 3 decimal places, thus minimum value to transfer is 0.0005.
     * @param srcPassport source user passport
     * @param srcRequisite source account requisites
     * @param destPassport destination user passport
     * @param destRequisite destination account requisites
     * @param amount amount to transfer
     * @return true or false, when true transfer happens, when false no transfer
     */
    public boolean transferMoney(
            String srcPassport,
            String srcRequisite,
            String destPassport,
            String destRequisite,
            double amount) {
        if (srcPassport == null
            || srcRequisite == null
            || destPassport == null
            || destRequisite == null
            || amount <= 0) {
            return false;
        }

        Account srcAccount = null;
        for (Account account : this.getUserAccounts(srcPassport)) {
            if (srcRequisite == account.getRequisites()) {
                srcAccount = account;
            }
        }
        if (srcAccount == null) {
            return false;
        }

        Account destAccount = null;
        for (Account account : this.getUserAccounts(destPassport)) {
            if (destRequisite == account.getRequisites()) {
                destAccount = account;
            }
        }
        if (destAccount == null) {
            return false;
        }

        double srcAccountValue = srcAccount.getValue();
        if (Math.round(srcAccountValue * 1000.0) / 1000.0 - Math.round(amount * 1000.0) / 1000.0 < 0) {
            return false;
        }

        srcAccount.setValue(Math.round(srcAccountValue * 1000.0) / 1000.0 - Math.round(amount * 1000.0) / 1000.0);
        double destAccountValue = destAccount.getValue();
        destAccount.setValue(Math.round(destAccountValue * 1000.0) / 1000.0 + Math.round(amount * 1000.0) / 1000.0);
        return true;
    }
}
