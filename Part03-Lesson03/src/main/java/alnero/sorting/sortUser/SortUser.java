package alnero.sorting.sortUser;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



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

    /**
     * Sort list of users according to the name length.
     * @param list list of users
     * @return sorted copy of list of users
     */
    public List<User> sortNameLength(List<User> list) {
        List<User> resultList = new ArrayList<>();
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return new Integer(o1.getName().length()).compareTo(o2.getName().length());
            }
        });
        resultList.addAll(list);
        return resultList;
    }

    /**
     * Sort list of users according to name and then according ot age.
     * @param list list of users
     * @return sorted copy of list of users
     */
    public List<User> sortByAllFields(List<User> list) {
        List<User> resultList = new ArrayList<>();
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int i = o1.getName().compareTo(o2.getName());
                if (i != 0) {
                    return i;
                }
                return new Integer(o1.getAge()).compareTo(o2.getAge());
            }
        });
        resultList.addAll(list);
        return resultList;
    }
}
