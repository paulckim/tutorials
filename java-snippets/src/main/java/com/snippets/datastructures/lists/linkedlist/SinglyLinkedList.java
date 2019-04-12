package com.snippets.datastructures.lists.linkedlist;

import com.snippets.datastructures.lists.ListIterator;
import com.snippets.datastructures.lists.ListNode;

public class SinglyLinkedList<T> {
    private ListNode<T> head;
    private int size;

    public ListIterator<T> iterator() {
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
        //
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if(!inBounds(index))
            throw new IndexOutOfBoundsException(
                "Cannot retrieve non-existent index=" + index
            );

        ListIterator<T> nodeIterator = iterator();
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
