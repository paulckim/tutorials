package com.snippets.datastructures.trees.bst.redblack;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RecursiveRedBlackTreeTest {
    private static final int[] INPUT_LIST = new int[] { 0, 1, 2, 3, 4 };

    @Test
    public void testAddSingleNode() {
        RecursiveRedBlackTree<Integer> tree = new RecursiveRedBlackTree<>();
        int expectedValue = 1;
        tree.add(expectedValue);
        assertThat(tree.size()).isEqualTo(1);
        assertThat(tree.height()).isEqualTo(1);
        assertThat(tree.contains(expectedValue)).isTrue();
    }

    @Test
    public void testAddMultipleNodes() {
        RecursiveRedBlackTree<Integer> tree = new RecursiveRedBlackTree<>();
        for(int i : INPUT_LIST)
            tree.add(i);
        assertThat(tree.size()).isEqualTo(INPUT_LIST.length);
        assertThat(tree.height()).isEqualTo(3);
        for(int expectedValue : INPUT_LIST)
            assertThat(tree.contains(expectedValue)).isTrue();
    }

}