package alnero.generics.userConvert;

import java.util.HashMap;
import java.util.List;

/**
 * Convert list of users to map with IDs used as keys.
 */
public class UserConvert {
    /**
     * Process list of users and convert it to map with IDs as keys.
     * @param list list of Users
     * @return mar of ID -> User
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            int userId = user.getId();
            map.putIfAbsent(userId, user);
        }
        return map;
    }
}
