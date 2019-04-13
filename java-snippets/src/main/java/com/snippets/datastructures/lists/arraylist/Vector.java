package com.snippets.datastructures.lists.arraylist;

import java.util.Iterator;

public class Vector<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] values;

    public Vector() {
        this.values = new Object[DEFAULT_CAPACITY];
    }

    public Vector(int capacity) {
        values = new Object[capacity];
    }

    public T get(int index) {
        if(!inBounds(index))
            throw new IndexOutOfBoundsException(
                    "Cannot retrieve non-existent index=" + index
            );
        return (T) values[index];
    }

    public void add(T value) {
        resize();
        values[size++] = value;
    }

    public void insert(int index, T value) {
        resize();
        for(int i = (++size) - 1; i > index; --i)
            // Overwrite current with next element:
            values[i] = values[i - 1];
        /**
         * Overwrite the last element to:
         * 1) prevent memory leaks
         * 2) decrement visible SIZE when exact index is removed
         */
        values[index] = value;
    }

    public void remove(int index) {
        if(!inBounds(index))
            throw new IndexOutOfBoundsException(
                    "Cannot retrieve non-existent index=" + index
            );
        for(int i = index; i < size - 1; ++i)
            // Overwrite current with next element:
            values[i] = values[i + 1];
        /**
         * Overwrite the last element to:
         * 1) prevent memory leaks
         * 2) decrement visible SIZE when exact index is removed
         */
        values[--size] = null;
    }

    public void resize() {
        if(size < values.length)
            return; // resizing not necessary
        int capacity = values.length * 2;
        Object[] temp = new Object[capacity];
        for(int i = 0; i < values.length; ++ i)
            temp[i] = values[i];
        values = temp;
    }

    public int size() {
        return size;
    }

    private boolean inBounds(int index) {
        return 0 <= index && index < size;
    }

    @Override
    public Iterator<T> iterator() {
        return new VectorIterator<>(values, size);
    }
}
