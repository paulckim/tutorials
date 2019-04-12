package com.snippets.datastructures.lists;

public class ListNode<T> {
    private T value;

    private ListNode<T> nextNode;

    public ListNode() {}

    public ListNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListNode<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(ListNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    public boolean hasNext() {
        return null != nextNode;
    }
}
