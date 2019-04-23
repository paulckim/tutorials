package com.snippets.datastructures.trees.bst.redblack;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RecursiveRedBlackTreeTest {
    private static final int[] UNORDERED_LIST = new int[] { 5, 8, 7, 4, 6, 3, 9 };
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

    @Test
    public void testAddMultipleRandomNodes() {
        RecursiveRedBlackTree<Integer> tree = new RecursiveRedBlackTree<>();
        for(int i : UNORDERED_LIST)
            tree.add(i);
        assertThat(tree.size()).isEqualTo(UNORDERED_LIST.length);
        assertThat(tree.height()).isEqualTo(4);
        for(int expectedValue : UNORDERED_LIST)
            assertThat(tree.contains(expectedValue)).isTrue();
    }

    @Test
    public void testZigZagInputNodes() {
        // 48=B, 49=B, 50=B, 51=R, 52=B, 53=R
        int[] input = new int[] { 50, 49, 48, 51, 52, 53 };
        RecursiveRedBlackTree<Integer> tree = new RecursiveRedBlackTree<>();
        for(int i : input)
            tree.add(i);
        assertThat(tree.size()).isEqualTo(input.length);
        assertThat(tree.height()).isEqualTo(4);
        for(int expectedValue : input)
            assertThat(tree.contains(expectedValue)).isTrue();
    }
}