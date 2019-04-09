package com.snippets.algorithms.sorts.insertion;

import com.snippets.algorithms.sorts.MutableListSort;

import java.util.List;

public class InsertionMutableListSort<T extends Comparable<? super T>> implements MutableListSort<T> {

    private static final int LOWER_BOUND = 0;
    private static final int START_INDEX = 1;

    @Override
    public void sort(List<T> unsorted) {
        for(int currEleIdx = START_INDEX; currEleIdx < unsorted.size(); ++currEleIdx) {
            T insertEle = unsorted.get(currEleIdx);
            List<T> sortedPartition = unsorted.subList(LOWER_BOUND, currEleIdx);
            int targetIdx = search(sortedPartition, insertEle);
            List<T> affectedPartition = unsorted.subList(targetIdx, currEleIdx + 1);
            reorderSortedPartition(affectedPartition, insertEle);
        }
    }

    private void reorderSortedPartition(List<T> affectedPartition, T insertEle) {
        // No need to reorder a sorted sub partition of size 1
        if(affectedPartition.size() == 1) return;
        /**
         * Move element from left to right because the last element (T insertEle)
         * is expected to be moved to the first index. In order to perform a useful
         * sort operation on a sub-partition, there must be at least 2 elements,
         * that's why it's safe to hard-code a 2 in the initialization step.
         */
        for(int prevIdx = affectedPartition.size() - 2; prevIdx >= 0; --prevIdx) {
            T prevEle = affectedPartition.get(prevIdx);
            int currIdx = prevIdx + 1;
            // Overwrite the current Element with previous Element:
            affectedPartition.set(currIdx, prevEle);
        }
        affectedPartition.set(0, insertEle);
    }

    /**
     * Performs a naive linear search.
     *
     * @param sorted
     * @param insertEle
     * @return
     */
    private int search(List<T> sorted, T insertEle) {
        int targetIndex;
        for(targetIndex = 0; targetIndex < sorted.size(); ++targetIndex) {
            T currEle = sorted.get(targetIndex);
            if(currEle.compareTo(insertEle) >= 0)
                return targetIndex;
        }
        /**
         * returns past the last position of the sorted partition if the element
         * is already in place.
         */
        return targetIndex;
    }

}
