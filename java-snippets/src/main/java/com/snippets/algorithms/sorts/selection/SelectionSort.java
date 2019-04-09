package com.snippets.algorithms.sorts.selection;

import com.snippets.algorithms.sorts.MutableListSort;

import java.util.List;

public class SelectionSort<T extends Comparable<? super T>> implements MutableListSort<T> {

    @Override
    public void sort(List<T> unsorted) {
        for(int i = 0; i < unsorted.size(); ++i) {
            List<T> unsortedPartition = unsorted.subList(i, unsorted.size());
            int selectedIdx = seekNextIndex(unsortedPartition);
            if(0 == selectedIdx) continue;
            swap(unsorted, i, selectedIdx + i);
        }
    }

    private int seekNextIndex(List<T> unsorted) {
        int minIndex = 0;
        for(int i = 0; i < unsorted.size(); ++i) {
            T currVal = unsorted.get(i);
            /**
             * if the current value is less than the previously recorded
             * min value, update the min index.
             */
            if(currVal.compareTo(unsorted.get(minIndex)) < 0)
                minIndex = i;
        }
        return minIndex;
    }

    private void swap(List<T> unsorted, int i, int j) {
        T temp = unsorted.get(i);
        unsorted.set(i, unsorted.get(j));
        unsorted.set(j, temp);
    }

}
