package com.snippets.datastructures.lists.arraylist;

import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class VectorTest {

    private static final int[] INPUT_LIST = new int[] { 0, 1, 2, 3, 4 };

    @Test
    public void testAppendSingleElement() {
        Vector<Integer> list = new Vector<>();
        int expectedValue = 10;
        list.add(expectedValue);
        assertThat(list.size()).isEqualTo(1);
        Integer actualValue = list.get(0);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void testAppendMultipleElements() {
        Vector<Integer> list = new Vector<>();
        for(int i : INPUT_LIST)
            list.add(i);
        assertThat(list.size()).isEqualTo(INPUT_LIST.length);
        for(int i = 0; i < list.size(); ++i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = list.get(i);
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Test
    public void testCustomCapacityResizeVector() {
        Vector<Integer> list = new Vector<>(INPUT_LIST.length);
        for(int i : INPUT_LIST)
            list.add(i);
        assertThat(list.size()).isEqualTo(INPUT_LIST.length);
        for(int i = 0; i < list.size(); ++i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = list.get(i);
            assertThat(actualValue).isEqualTo(expectedValue);
        }
        int expectedOverflowValue = 9000;
        list.add(expectedOverflowValue);
        assertThat(list.size()).isEqualTo(INPUT_LIST.length + 1);
        assertThat(list.get(INPUT_LIST.length)).isEqualTo(expectedOverflowValue);
    }

    @Test
    public void testRemoveSingleElement() {
        Vector<Integer> list = new Vector<>();
        int expectedValue = 10;
        list.add(expectedValue);
        assertThat(list.size()).isEqualTo(1);
        Integer actualValue = list.get(0);
        assertThat(actualValue).isEqualTo(expectedValue);
        list.remove(0);
        assertThat(list.size()).isZero();
    }

    @Test
    public void testRemoveFirstMultipleElements() {
        Vector<Integer> list = new Vector<>();
        for(int i : INPUT_LIST)
            list.add(i);
        assertThat(list.size()).isEqualTo(INPUT_LIST.length);
        for(int i = 0; i < list.size(); ++i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = list.get(i);
            assertThat(actualValue).isEqualTo(expectedValue);
        }
        // Remove the first element
        list.remove(0);
        assertThat(list.size()).isEqualTo(INPUT_LIST.length - 1);
        Iterator<Integer> nodeIterator = list.iterator();
        for(int i = 1; i < list.size(); ++i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = nodeIterator.next();
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Test
    public void testRemoveLastMultipleElements() {
        Vector<Integer> list = new Vector<>();
        for(int i : INPUT_LIST)
            list.add(i);
        assertThat(list.size()).isEqualTo(INPUT_LIST.length);
        for(int i = 0; i < list.size(); ++i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = list.get(i);
            assertThat(actualValue).isEqualTo(expectedValue);
        }
        // Remove the first element
        list.remove(INPUT_LIST.length - 1);
        assertThat(list.size()).isEqualTo(INPUT_LIST.length - 1);
        for(int i = 0; i < list.size(); ++i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = list.get(i);
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Test
    public void testInsertSingleElement() {
        Vector<Integer> list = new Vector<>();
        int expectedValue = 10;
        list.insert(0, expectedValue);
        assertThat(list.size()).isEqualTo(1);
        Integer actualValue = list.get(0);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void testInsertFrontMultipleElements() {
        Vector<Integer> list = new Vector<>();
        /**
         * Prepend N elements into new LinkedList.
         * Because the elements are prepended in order, the resulting
         * LinkedList will contains a reversed ordering of the input
         * array.
         */
        for(int i : INPUT_LIST)
            list.insert(0, i);
        // Assert the length of input is equivalent to LinkedList
        assertThat(list.size()).isEqualTo(INPUT_LIST.length);
        // Assert LinkedList element ordering reflects input array
        Iterator<Integer> nodeIterator = list.iterator();
        for(int i = INPUT_LIST.length - 1; i >= 0; --i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = nodeIterator.next();
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Test
    public void testInsertMiddleMultipleElements() {
        Vector<Integer> list = new Vector<>();
        // Append N elements into new LinkedList
        for(int i : INPUT_LIST)
            if(i != 2) list.add(i);
        // Assert that the length indicates 1 element was excluded
        assertThat(list.size()).isEqualTo(INPUT_LIST.length - 1);
        // manually insert INPUT_LIST[2] into the 2 index:
        list.insert(2, INPUT_LIST[2]);
        // Assert the length of input is equivalent to LinkedList
        assertThat(list.size()).isEqualTo(INPUT_LIST.length);
        // Assert LinkedList element ordering reflects input array
        Iterator<Integer> nodeIterator = list.iterator();
        for(int i = 0; i < INPUT_LIST.length; ++i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = nodeIterator.next();
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Test
    public void testInsertBeforeLastMultipleElements() {
        Vector<Integer> list = new Vector<>();
        // Append N elements into new LinkedList
        for(int i : INPUT_LIST)
            if(i != INPUT_LIST.length - 2) list.add(i);
        // Assert that the length indicates 1 element was excluded
        assertThat(list.size()).isEqualTo(INPUT_LIST.length - 1);
        // manually insert INPUT_LIST[2] into the 2 index:
        list.insert(3, INPUT_LIST[3]);
        // Assert the length of input is equivalent to LinkedList
        assertThat(list.size()).isEqualTo(INPUT_LIST.length);
        // Assert LinkedList element ordering reflects input array
        Iterator<Integer> nodeIterator = list.iterator();
        for(int i = 0; i < INPUT_LIST.length; ++i) {
            int expectedValue = INPUT_LIST[i];
            int actualValue = nodeIterator.next();
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

}
