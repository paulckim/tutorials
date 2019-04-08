package com.snippets.algorithms.sorts;

import java.util.List;

public interface SortAlgorithm<T extends Comparable<? super T>> {

    void sort(List<T> unsorted);

}
