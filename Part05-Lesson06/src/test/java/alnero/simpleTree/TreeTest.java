package alnero.simpleTree;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Testing simple tree.
 */
public class TreeTest {
    @Test
    public void whenTreeHas6ElemenetsThenAddAndFindAll6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(1).isPresent(), is(true));
        assertThat(tree.findBy(2).isPresent(), is(true));
        assertThat(tree.findBy(3).isPresent(), is(true));
        assertThat(tree.findBy(4).isPresent(), is(true));
        assertThat(tree.findBy(5).isPresent(), is(true));
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void whenTreeHasNoRequiredElementThenFindReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent(), is(false)
        );
    }
}