package com.snippets.algorithms.sorts;

import java.util.List;

public interface MutableListSort<T extends Comparable<? super T>> {

    void sort(List<T> unsorted);

}
