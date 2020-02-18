package alnero.simpleList;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Testing cycles in nodes.
 */
public class NodeCycleTest {
    /** Node for testing. */
    private Node<Integer> one;
    /** Node for testing. */
    private Node<Integer> two;
    /** Node for testing. */
    private Node<Integer> three;
    /** Node for testing. */
    private Node<Integer> four;
    /** Node for testing. */
    private Node<Integer> five;
    /** Node for testing. */
    private Node<Integer> six;

    /**
     * Initialize nodes for testing.
     */
    @Before
    public void before() {
        this.one = new Node<>(1);
        this.two = new Node<>(2);
        this.three = new Node<>(3);
        this.four = new Node<>(4);
        this.five = new Node<>(5);
        this.six = new Node<>(6);
    }

    /**
     * Test four nodes with cycle.
     */
    @Test
    public void whenFourNodesHaveFullCycleThenResultIsTrue() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(one);

        assertThat(one.hasCycle(one), is(true));
    }

    /**
     * Test one node with cycle.
     */
    @Test
    public void whenOneNodeHasCycleThenResultIsTrue() {
        one.setNext(one);

        assertThat(one.hasCycle(one), is(true));
    }

    /**
     * Test six nodes with cycle.
     */
    @Test
    public void whenSixNodesHaveFullCycleThenResultIsTrue() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);
        six.setNext(one);

        assertThat(one.hasCycle(one), is(true));
    }

    /**
     * Test six nodes with five nodes cycle.
     */
    @Test
    public void whenSixNodesHaveFiveNodeCycleThenResultIsTrue() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);
        six.setNext(two);

        assertThat(one.hasCycle(one), is(true));
    }

    /**
     * Test six nodes with four nodes cycle.
     */
    @Test
    public void whenSixNodesHaveFourNodeCycleThenResultIsTrue() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);
        six.setNext(three);

        assertThat(one.hasCycle(one), is(true));
    }

    /**
     * Test six nodes with three nodes cycle.
     */
    @Test
    public void whenSixNodesHaveThreeNodeCycleThenResultIsTrue() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);
        six.setNext(four);

        assertThat(one.hasCycle(one), is(true));
    }

    /**
     * Test six nodes with two nodes cycle.
     */
    @Test
    public void whenSixNodesHaveTwoNodeCycleThenResultIsTrue() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);
        six.setNext(five);

        assertThat(one.hasCycle(one), is(true));
    }

    /**
     * Test six nodes with one node cycle.
     */
    @Test
    public void whenSixNodesHaveOneNodeCycleThenResultIsTrue() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);
        six.setNext(six);

        assertThat(one.hasCycle(one), is(true));
    }

    /**
     * Test four nodes without cycle.
     */
    @Test
    public void whenFourNodesDoNotHaveCycleThenResultIsFalse() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);

        assertThat(one.hasCycle(one), is(false));
    }

    /**
     * Test one node without cycle.
     */
    @Test
    public void whenOneNodeDoesNotHaveCycleThenResultIsFalse() {
        Node one = new Node(1);

        assertThat(one.hasCycle(one), is(false));
    }

    /**
     * Test six nodes without cycle.
     */
    @Test
    public void whenSixNodesDoNotHaveCycleThenResultIsFalse() {
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);

        assertThat(one.hasCycle(one), is(false));
    }
}
