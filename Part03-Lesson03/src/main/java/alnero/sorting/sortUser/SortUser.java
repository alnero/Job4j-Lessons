package alnero.sorting.sortUser;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sorting users.
 */
public class SortUser {
    /**
     * Convert list of users into sorted set of users.
     * Sorting depends on User implementation.
     * @param list list of users
     * @return sorted set of users
     */
    public Set<User> sort(List<User> list) {
        Set<User> resultSet = new TreeSet<>();
        resultSet.addAll(list);
        return resultSet;
    }
}
