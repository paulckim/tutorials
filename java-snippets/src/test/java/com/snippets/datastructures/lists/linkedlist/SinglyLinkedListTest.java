package com.snippets.datastructures.lists.linkedlist;

import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class SinglyLinkedListTest {

    private static final int[] INPUT_LIST = new int[] { 0, 1, 2, 3, 4 };

    @Test
    public void testAppendSingleElement() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        int expectedValue = 10;
        list.append(expectedValue);
        assertThat(list.size()).isEqualTo(1);
        Integer actualValue = list.get(0);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void testAppendMultipleElements() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        // Append N elements into new LinkedList
        for(int i : INPUT_LIST)
            list.append(i);
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
    public void testPrependSingleElement() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        int expectedValue = 10;
        list.prepend(expectedValue);
        assertThat(list.size()).isEqualTo(1);
        Integer actualValue = list.get(0);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void testPrependMultipleElements() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        /**
         * Prepend N elements into new LinkedList.
         * Because the elements are prepended in order, the resulting
         * LinkedList will contains a reversed ordering of the input
         * array.
         */
        for(int i : INPUT_LIST)
            list.prepend(i);
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
    public void testInsertMiddleSingleElement() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        // Append N elements into new LinkedList
        for(int i : INPUT_LIST)
            if(i != 2) list.append(i);
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
    public void testInsertFrontSingleElement() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        // Append N elements into new LinkedList
        for(int i : INPUT_LIST)
            if(i != 0) list.append(i);
        // Assert that the length indicates 1 element was excluded
        assertThat(list.size()).isEqualTo(INPUT_LIST.length - 1);
        // manually insert INPUT_LIST[2] into the 2 index:
        list.insert(0, INPUT_LIST[0]);
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