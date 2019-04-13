package com.snippets.datastructures.lists.arraylist;

import java.util.Iterator;

class VectorIterator<T> implements Iterator<T> {
    private Object[] values;
    private int size;
    private int index;

    public VectorIterator(Object[] values, int size) {
        this.values = values;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return 0 <= index && index < size;
    }

    @Override
    public T next() {
        return (T) values[index++];
    }
}
