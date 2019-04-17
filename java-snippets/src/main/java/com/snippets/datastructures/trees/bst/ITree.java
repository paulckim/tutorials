package com.snippets.datastructures.trees.bst;

public interface ITree<T extends Comparable<? super T>> {
    void add(T value);

    void remove(T value);

    boolean contains(T value);

    int height();

    int size();
}
