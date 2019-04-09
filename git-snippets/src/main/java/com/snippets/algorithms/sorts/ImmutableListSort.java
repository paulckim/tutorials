package com.snippets.algorithms.sorts;

import java.util.List;

public interface ImmutableListSort<T extends Comparable<? super T>> {

    List<T> sort(List<T> unsorted);

}
