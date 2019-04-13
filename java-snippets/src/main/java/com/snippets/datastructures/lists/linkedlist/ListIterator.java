package com.snippets.datastructures.lists.linkedlist;

import java.util.Iterator;

class ListIterator<T> implements Iterator<T> {
    private ListNode<T> currNode;

    public ListIterator(ListNode<T> currNode) {
        this.currNode = currNode;
    }

    @Override
    public boolean hasNext() {
        return null != this.currNode;
    }

    @Override
    public T next() {
        T value = currNode.getValue();
        currNode = currNode.getNextNode();
        return value;
    }
}
