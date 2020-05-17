package alnero.collectionStatistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analyse {
    /**
     * Return statistic for two collection of users.
     * @param previous old list of users
     * @param current new list of users
     * @return class with integer numbers showing added, deleted, modified users
     */
    public Info diff(List<User> previous, List<User> current) {
//        // answer via streams
//        int numOfNewUsers = (int) current.stream().filter(currentUser -> {
//            return !previous.contains(currentUser);
//        }).count();
//        int numOfDeletedUsers = (int) previous.stream().filter(previousUser -> {
//            return !current.contains(previousUser);
//        }).count();
//        int numOfModifiedUsers = (int) current.stream().filter(currentUser -> previous.stream().anyMatch(previousUser -> {
//            return currentUser.id == previousUser.id && !currentUser.name.equals(previousUser.name);
//                })
//        ).count();
//        Info result = new Info();
//        result.setAdded(numOfNewUsers);
//        result.setDeleted(numOfDeletedUsers);
//        result.setModified(numOfModifiedUsers);
//        return result;

//        // answer using loops
//        Info result = new Info();
//        int previousUsers = previous.size();
//        int currentUsers = current.size();
//        int modifiedUsers  = 0;
//        for (User currentUser : current) {
//            for (User previousUser : previous) {
//                if (previousUser.getId() == currentUser.getId()) {
//                    currentUsers--;
//                    previousUsers--;
//                    if (!previousUser.getName().equals(currentUser.getName())) {
//                        modifiedUsers++;
//                    }
//                }
//            }
//        }
//        result.setAdded(currentUsers);
//        result.setDeleted(previousUsers);
//        result.setModified(modifiedUsers);
//        return result;

        int numOfNewUsers = 0;
        int numOfDeletedUsers = 0;
        int numOfModifiedUsers = 0;
        Map<Integer, User> previosMap = new HashMap<>();
        for (User user : previous) {
            if (!current.contains(user)) {
                numOfDeletedUsers++;
            }
            previosMap.put(user.getId(), user);
        }
        for (User user : current) {
            if (!previous.contains(user)) {
                numOfNewUsers++;
            }
            if (previosMap.containsValue(user) && !user.getName().equals(previosMap.get(user.getId()).getName())) {
                numOfModifiedUsers++;
            }
        }
        Info result = new Info();
        result.setAdded(numOfNewUsers);
        result.setDeleted(numOfDeletedUsers);
        result.setModified(numOfModifiedUsers);
        return result;
    }

    /**
     * Simple user class. Ids do not change.
     */
    public static class User {
        /** User id. **/
        private int id;
        /** User name. **/
        private String name;

        /**
         * Simple user constructor.
         * @param id user id
         * @param name user name
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        /** Number of new users, comparing to previous collection. **/
        private int added;
        /** Number of modified users, comparing to previous collection. **/
        private int modified;
        /** Number of deleted users, comparing to previous collection. **/
        private int deleted;

        /**
         * Initial object shows that there is no data regarding added, deleted or modified users
         * by setting all values to -1.
         */
        public Info() {
            this.added = -1;
            this.modified = -1;
            this.deleted = -1;
        }

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public int getModified() {
            return modified;
        }

        public void setModified(int changed) {
            this.modified = changed;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }
}