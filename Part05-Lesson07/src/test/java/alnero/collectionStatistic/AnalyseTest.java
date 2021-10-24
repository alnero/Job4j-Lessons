package alnero.collectionStatistic;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

/**
 * Testing user collection statistics.
 */
public class AnalyseTest {
    /**
     * Number of new users is correct.
     */
    @Test
    public void whenAddNewUsersToCollectionThenCorrectStatisticsReturned() {
        Analyse analyse = new Analyse();
        Analyse.User user1 = new Analyse.User(1, "One");
        Analyse.User user2 = new Analyse.User(2, "Two");
        Analyse.User user3 = new Analyse.User(3, "Three");
        Analyse.User user4 = new Analyse.User(4, "Four");
        List<Analyse.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);
        List<Analyse.User> current = new ArrayList<>();
        current.add(user1);
        current.add(user2);
        current.add(user3);
        current.add(user4);
        Analyse.Info collectionStat = analyse.diff(previous, current);
        assertThat(current.size() - previous.size(), is(2));
        assertThat(collectionStat.getAdded(), is(2));
    }

    /**
     * Number of deleted users is correct.
     */
    @Test
    public void whenDeleteUsersFromCollectionThenCorrectStatisticsReturned() {
        Analyse analyse = new Analyse();
        Analyse.User user1 = new Analyse.User(1, "One");
        Analyse.User user2 = new Analyse.User(2, "Two");
        Analyse.User user3 = new Analyse.User(3, "Three");
        Analyse.User user4 = new Analyse.User(4, "Four");
        List<Analyse.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);
        previous.add(user3);
        previous.add(user4);
        List<Analyse.User> current = new ArrayList<>();
        current.add(user1);
        current.add(user2);
        Analyse.Info collectionStat = analyse.diff(previous, current);
        assertThat(previous.size() - current.size(), is(2));
        assertThat(collectionStat.getDeleted(), is(2));
    }

    /**
     * Number of modified users is correct.
     */
    @Test
    public void whenUsersAreModifiedThenCorrectStatisticsReturned() {
        Analyse analyse = new Analyse();
        Analyse.User user1 = new Analyse.User(1, "One");
        Analyse.User user2 = new Analyse.User(2, "Two");
        Analyse.User user3 = new Analyse.User(3, "Three");
        Analyse.User user4 = new Analyse.User(4, "Four");
        List<Analyse.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);
        previous.add(user3);
        previous.add(user4);
        user3 = new Analyse.User(3, "3");
        user4 = new Analyse.User(4, "4");
        List<Analyse.User> current = new ArrayList<>();
        current.add(user1);
        current.add(user2);
        current.add(user3);
        current.add(user4);
        Analyse.Info collectionStat = analyse.diff(previous, current);

        assertThat(previous.get(2).getId(), is(current.get(2).getId()));
        assertThat(previous.get(2).getName(), not(current.get(2).getName()));
        assertThat(previous.get(3).getId(), is(current.get(3).getId()));
        assertThat(previous.get(3).getName(), not(current.get(3).getName()));

        assertThat(collectionStat.getModified(), is(2));
    }

    /**
     * Number of new, deleted and modified users is correct.
     * two modified users - user2, user3.
     * two new users - user6, user7.
     * two deleted users - user4, user5.
     */
    @Test
    public void whenUsersAreAddedDeletedModifiedThenCorrectStatisticsReturned() {
        Analyse analyse = new Analyse();
        Analyse.User user1 = new Analyse.User(1, "One");
        Analyse.User user2 = new Analyse.User(2, "Two");
        Analyse.User user3 = new Analyse.User(3, "Three");
        Analyse.User user4 = new Analyse.User(4, "Four");
        Analyse.User user5 = new Analyse.User(4, "Five");
        List<Analyse.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);
        previous.add(user3);
        previous.add(user4);
        previous.add(user5);

        user2 = new Analyse.User(2, "2");
        user3 = new Analyse.User(3, "3");
        Analyse.User user6 = new Analyse.User(6, "Six");
        Analyse.User user7 = new Analyse.User(7, "Seven");
        List<Analyse.User> current = new ArrayList<>();
        current.add(user1);
        current.add(user2);
        current.add(user3);
        current.add(user6);
        current.add(user7);

        Analyse.Info collectionStat = analyse.diff(previous, current);
        assertThat(collectionStat.getAdded(), is(2));
        assertThat(collectionStat.getDeleted(), is(2));
        assertThat(collectionStat.getModified(), is(2));
    }

    /**
     * Statistics for equal collections.
     */
    @Test
    public void whenUsersAreNotAddedDeletedModifiedThenCorrectStatisticsReturned() {
        Analyse analyse = new Analyse();
        Analyse.User user1 = new Analyse.User(1, "One");
        Analyse.User user2 = new Analyse.User(2, "Two");
        Analyse.User user3 = new Analyse.User(3, "Three");
        Analyse.User user4 = new Analyse.User(4, "Four");
        List<Analyse.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);
        previous.add(user3);
        previous.add(user4);
        List<Analyse.User> current = new ArrayList<>();
        current.add(user1);
        current.add(user2);
        current.add(user3);
        current.add(user4);
        Analyse.Info collectionStat = analyse.diff(previous, current);
        assertThat(collectionStat.getAdded(), is(0));
        assertThat(collectionStat.getDeleted(), is(0));
        assertThat(collectionStat.getModified(), is(0));
    }
}
