package com.snippets.datastructures.lists;

import com.snippets.datastructures.lists.linkedlist.ListNode;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListNodeTest {

    @Test
    public void testListNodeIntegerValue() {
        int expectedValue = 10;
        ListNode<Integer> node = new ListNode<>(expectedValue);
        assertThat(node.getValue()).isEqualTo(expectedValue);
        assertThat(node.getNextNode()).isNull();
    }

    @Test
    public void testListNodeStringValue() {
        String expectedValue = "His Name is John Cena!!!";
        ListNode<String> node = new ListNode<>(expectedValue);
        assertThat(node.getValue()).isEqualTo(expectedValue);
        assertThat(node.getNextNode()).isNull();
    }

    @Test
    public void testListNodeSetIntegerValue() {
        int expectedValue = 10;
        ListNode<Integer> node = new ListNode<>();
        node.setValue(expectedValue);
        assertThat(node.getValue()).isEqualTo(expectedValue);
        assertThat(node.getNextNode()).isNull();
    }

    @Test
    public void testListNodeSetStringValue() {
        String expectedValue = "Kappa123";
        ListNode<String> node = new ListNode<>();
        node.setValue(expectedValue);
        assertThat(node.getValue()).isEqualTo(expectedValue);
        assertThat(node.getNextNode()).isNull();
    }

    @Test
    public void testEmptyListNode() {
        ListNode<Integer> node = new ListNode<>();
        assertThat(node.getValue()).isNull();
        assertThat(node.getNextNode()).isNull();
    }

    @Test
    public void testListNodeSetNextInteger() {
        Integer currValue = 1;
        Integer nextValue = 2;
        ListNode<Integer> currNode = new ListNode<>(currValue);
        ListNode<Integer> nextNode = new ListNode<>(nextValue);
        currNode.setNextNode(nextNode);

        assertThat(currNode.getValue()).isEqualTo(currValue);
        assertThat(currNode.getNextNode()).isNotNull();

        assertThat(currNode.hasNext()).isTrue();
        ListNode<Integer> actualNextNode = currNode.getNextNode();
        assertThat(actualNextNode.getValue()).isEqualTo(nextValue);
        assertThat(actualNextNode.getNextNode()).isNull();
    }

    @Test
    public void testListNodeSetNextString() {
        String currValue = "string value 1";
        String nextValue = "string value 2";
        ListNode<String> currNode = new ListNode<>(currValue);
        ListNode<String> nextNode = new ListNode<>(nextValue);
        currNode.setNextNode(nextNode);

        assertThat(currNode.getValue()).isEqualTo(currValue);
        assertThat(currNode.getNextNode()).isNotNull();

        assertThat(currNode.hasNext()).isTrue();
        ListNode<String> actualNextNode = currNode.getNextNode();
        assertThat(actualNextNode.getValue()).isEqualTo(nextValue);
        assertThat(actualNextNode.getNextNode()).isNull();
    }

}