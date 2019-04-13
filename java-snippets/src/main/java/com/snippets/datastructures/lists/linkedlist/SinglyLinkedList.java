package com.snippets.datastructures.lists.linkedlist;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    private ListNode<T> head;
    private int size;

    public Iterator<T> iterator() {
        return new ListIterator<>(this.head);
    }

    /**
     * Prepends a ListNode<T> to the beginning of the LinkedList.
     *
     * @param value
     */
    public void prepend(T value) {
        ++size;
        if(null == head) {
            head = new ListNode<>(value);
            return;
        }
        ListNode<T> insertNode = new ListNode<>(value);
        insertNode.setNextNode(head);
        head = insertNode;
    }

    /**
     * Appends a ListNode<T> to the end of the LinkedList.
     *
     * @param value
     */
    public void append(T value) {
        ++size;
        if(null == head) {
            head = new ListNode<>(value);
            return;
        }
        ListNode<T> currNode = head;
        while(currNode.hasNext())
            currNode = currNode.getNextNode();
        currNode.setNextNode(new ListNode<>(value));
    }

    public void insert(int index, T value) {
        if(!inBounds(index))
            throw new IndexOutOfBoundsException(
                    "Cannot retrieve non-existent index=" + index
            );
        if(0 == index) {
            prepend(value);
            return;
        }
        ++size;
        ListNode<T> currNode = head;
        for(int i = 1; i < index; ++i)
            currNode = currNode.getNextNode();
        ListNode<T> insertNode = new ListNode<>(value);
        insertNode.setNextNode(currNode.getNextNode());
        currNode.setNextNode(insertNode);
    }

    public void remove(int index) {
        if(!inBounds(index))
            throw new IndexOutOfBoundsException(
                    "Cannot retrieve non-existent index=" + index
            );
        if(1 == size--) {
            head = null;
            return;
        }
        if(0 == index) {
            ListNode<T> nextNode = head.getNextNode();
            head.setNextNode(null);
            head = nextNode;
            return;
        }
        ListNode<T> currNode = head;
        for(int i = 1; i < index; ++i)
            currNode = currNode.getNextNode();
        ListNode<T> deletedNode = currNode.getNextNode();
        currNode.setNextNode(deletedNode.getNextNode());
        deletedNode.setNextNode(null);
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if(!inBounds(index))
            throw new IndexOutOfBoundsException(
                "Cannot retrieve non-existent index=" + index
            );

        Iterator<T> nodeIterator = iterator();
        T value = null;
        for(int i = 0; i <= index; ++i)
        while(nodeIterator.hasNext())
            value = nodeIterator.next();
        return value;
    }

    public int size() {
        return this.size;
    }

    private boolean inBounds(int index) {
        return 0 <= index && index < this.size;
    }
}
