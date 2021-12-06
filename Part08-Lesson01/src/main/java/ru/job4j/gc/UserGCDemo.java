package ru.job4j.gc;

public class UserGCDemo {
    public static void main(String[] args) {
        /*
         * All calculations are for Java 8 in 64-bit system.
         *
         * object user1
         *  object header -                 16 bytes
         *  long id -                       8 bytes
         *  int age -                       4 bytes
         *  String name ref -               8 bytes
         *  String surname ref -            8 bytes
         *  padding -                       4 bytes
         * -------------------------------------------- 48 bytes
         *  string "name1"
         *      object header -             16 bytes
         *      int hash -                  4 bytes
         *      char array reference -      8 bytes
         *          char array header -     16 bytes
         *          char array length -     4 bytes
         *          5 chars -               10 bytes
         *          padding -               2 bytes
         *      padding -                   4 bytes
         * -------------------------------------------- 64 bytes
         *  string "surname1"
         *      object header -             16 bytes
         *      int hash reference -        4 bytes
         *      char array reference -      8 bytes
         *          char array header -    16 bytes
         *          char array length -     4 bytes
         *          8 chars -               16 bytes
         *          padding -               4 bytes
         *      padding -                   4 bytes
         * -------------------------------------------- 72 bytes
         * Total:                                       184 bytes
         */
        User user1 = new User(1L, "name1", "surname1", 10);

        for (int i = 0; i < 257_996; i++) {
            new User(i, "N" + i, "S" + i, i);
        }
    }
}
