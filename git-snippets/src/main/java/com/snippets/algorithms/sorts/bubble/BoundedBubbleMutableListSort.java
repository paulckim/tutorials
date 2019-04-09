package com.snippets.algorithms.sorts.bubble;

import com.snippets.algorithms.sorts.MutableListSort;

import java.util.List;

public class BoundedBubbleMutableListSort<T extends Comparable<? super T>> implements MutableListSort<T> {

    @Override
    public void sort(List<T> unsorted) {
        for(int upper = unsorted.size(); upper >= 0; --upper) {
            boolean wasSwapped = bubble(unsorted, upper);
            if(!wasSwapped) return;
        }
    }

    private boolean bubble(List<T> unsorted, int upperBound) {
        boolean wasSwapped = false;
        for(int i = 1; i < upperBound; ++i) {
            T prevEle = unsorted.get(i - 1);
            T currEle = unsorted.get(i);
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
