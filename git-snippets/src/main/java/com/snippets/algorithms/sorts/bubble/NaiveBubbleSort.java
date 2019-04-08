package com.snippets.algorithms.sorts.bubble;

import com.snippets.algorithms.sorts.SortAlgorithm;

import java.util.List;

public class NaiveBubbleSort<T extends Comparable<? super T>> implements SortAlgorithm<T> {

    @Override
    public void sort(List<T> unsorted) {
        boolean wasSwapped = true;
        while(wasSwapped) {
            wasSwapped = bubble(unsorted);
        }
    }

    private boolean bubble(List<T> unsorted) {
        boolean wasSwapped = false;
        for(int i = 1; i < unsorted.size(); ++i) {
            T prevEle = unsorted.get(i - 1);
            T currEle = unsorted.get((i));
            if(prevEle.compareTo(currEle) > 0) {
                swap(unsorted, i - 1, i);
                wasSwapped = true;
            }
        }
        return wasSwapped;
    }

    private void swap(List<T> unsorted, int i, int j) {
        T temp = unsorted.get(i);
        unsorted.set(i, unsorted.get(j));
        unsorted.set(j, temp);
    }

}
