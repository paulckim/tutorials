package com.snippets.datastructures.trees.bst;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BSTTest {

    private static final int[] INPUT_LIST = new int[] { 0, 1, 2, 3, 4 };

    private static final int[] UNORDERED_LIST = new int[] { 3, 1, 0, 2, 5, 4, 6 };

    @Test
    public void testAddSingleNode() {
        BST<Integer> tree = new BST<>();
        int expectedValue = 1;
        tree.add(expectedValue);
        assertThat(tree.size()).isEqualTo(1);
        assertThat(tree.contains(expectedValue)).isTrue();
    }

    @Test
    public void testAddMultipleNodes() {
        BST<Integer> tree = new BST<>();
        for(int i : INPUT_LIST)
            tree.add(i);
        assertThat(tree.size()).isEqualTo(INPUT_LIST.length);
        for(int expectedValue : INPUT_LIST)
            assertThat(tree.contains(expectedValue)).isTrue();
    }

    @Test
    public void testAddSingleNodeRemove() {
        BST<String> tree = new BST<>();
        String expectedValue = "Kappa123";
        tree.add(expectedValue);
        assertThat(tree.size()).isEqualTo(1);
        assertThat(tree.contains(expectedValue)).isTrue();
        tree.remove(expectedValue);
        assertThat(tree.size()).isZero();
        assertThat(tree.contains(expectedValue)).isFalse();
    }

    @Test
    public void testAddMultipleNodesRemoveAllByRoot() {
        BST<Integer> tree = new BST<>();
        for(int i : INPUT_LIST)
            tree.add(i);
        assertThat(tree.size()).isEqualTo(INPUT_LIST.length);
        for(int expectedValue : INPUT_LIST)
            assertThat(tree.contains(expectedValue)).isTrue();

        for(int i = 0; i < INPUT_LIST.length; ++i) {
            tree.remove(INPUT_LIST[i]);
            assertThat(tree.size()).isEqualTo(INPUT_LIST.length - (i + 1));
        }
    }

    @Test
    public void testAddMultipleNodesRemoveAllByLeaf() {
        BST<Integer> tree = new BST<>();
        for(int i : INPUT_LIST)
            tree.add(i);
        assertThat(tree.size()).isEqualTo(INPUT_LIST.length);
        for(int expectedValue : INPUT_LIST)
            assertThat(tree.contains(expectedValue)).isTrue();

        for(int i = INPUT_LIST.length - 1; i >= 0; --i) {
            tree.remove(INPUT_LIST[i]);
            assertThat(tree.size()).isEqualTo(i);
        }
    }

    @Test
    public void testAddMultipleElementsUnordered() {
        BST<Integer> tree = new BST<>();
        for(int i : UNORDERED_LIST)
            tree.add(i);
        assertThat(tree.size()).isEqualTo(UNORDERED_LIST.length);
        for(int expectedValue : UNORDERED_LIST)
            assertThat(tree.contains(expectedValue)).isTrue();
    }

    @Test
    public void testAddMultipleElementsUnorderedRemoveMiddle() {
        BST<Integer> tree = new BST<>();
        for(int i : UNORDERED_LIST)
            tree.add(i);
        assertThat(tree.size()).isEqualTo(UNORDERED_LIST.length);
        for(int expectedValue : UNORDERED_LIST)
            assertThat(tree.contains(expectedValue)).isTrue();
        tree.remove(1);
        assertThat(tree.size()).isEqualTo(UNORDERED_LIST.length - 1);
        assertThat(tree.contains(1)).isFalse();
        tree.remove(5);
        assertThat(tree.size()).isEqualTo(UNORDERED_LIST.length - 2);
        assertThat(tree.contains(5)).isFalse();
    }

}