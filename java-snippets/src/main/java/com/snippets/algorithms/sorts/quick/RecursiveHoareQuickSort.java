package com.snippets.algorithms.sorts.quick;

import com.snippets.algorithms.sorts.MutableListSort;

import java.util.List;

public class RecursiveHoareQuickSort<T extends Comparable<? super T>> implements MutableListSort<T> {

    private static final int FIRST_ELEMENT = 0;

    private final SeekerCallback lSeeker = new SeekerCallback<T>() {
        private static final int FIRST_ELEMENT = 0;

        @Override
        public int calcNextIdx(int ptrIdx) {
            return ++ptrIdx;
        }

        @Override
        public boolean isInBounds(int upperBound, int nextIdx) {
            return FIRST_ELEMENT <= nextIdx && nextIdx < upperBound;
        }

        @Override
        public boolean isNextMatch(T pivotVal, T nextVal) {
            return nextVal.compareTo(pivotVal) > 0;
        }
    };

    private final SeekerCallback rSeeker = new SeekerCallback<T>() {

        @Override
        public int calcNextIdx(int ptrIdx) {
            return --ptrIdx;
        }

        @Override
        public boolean isInBounds(int upperBound, int nextIdx) {
            return FIRST_ELEMENT <= nextIdx && nextIdx < upperBound;
        }

        @Override
        public boolean isNextMatch(T pivotVal, T nextVal) {
            return pivotVal.compareTo(nextVal) > 0;
        }
    };

    @Override
    public void sort(List<T> unsorted) {
        quickSort(unsorted);
    }

    private void quickSort(List<T> partition) {
        // break to prevent useless partition:
        if(1 >= partition.size()) return;
        // choose simple midpoint as pivot and re-organize:
        int pivotIdx = partition(partition);
        // left partition with bounds check:
        if(FIRST_ELEMENT < pivotIdx + 1)
            quickSort(partition.subList(FIRST_ELEMENT, pivotIdx + 1));
        // right partition with bounds check:
        if(pivotIdx + 1 < partition.size())
            quickSort(partition.subList(pivotIdx + 1, partition.size()));
    }

    private int partition(List<T> partition) {
        int pivotIdx = calcMidPoint(partition.size());
        T pivotVal = partition.get(pivotIdx);
        int lPtr = -1, rPtr = partition.size();
        while(true) {
            lPtr = seekNext(lSeeker, partition, pivotVal, lPtr);
            rPtr = seekNext(rSeeker, partition, pivotVal, rPtr);
            if(lPtr < rPtr) {
                swap(partition, lPtr, rPtr);
                continue;
            }
            if(isPivotUnordered(partition, pivotIdx, rPtr))
                swap(partition, rPtr, pivotIdx);
            return rPtr;
        }
    }

    private int seekNext(SeekerCallback cb, List<T> partition, T pivotVal, int ptrIdx) {
        while(true) {
            int nextIdx = cb.calcNextIdx(ptrIdx);
            // if out-of-bounds, return previous index:
            if(!cb.isInBounds(partition.size(), nextIdx))
                return ptrIdx;
            // if nextVal matches condition, return nextVal
            T nextVal = partition.get(nextIdx);
            if(cb.isNextMatch(pivotVal, nextVal))
                return nextIdx;
            // continue seeking nextVal:
            ptrIdx = nextIdx;
        }
    }

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private boolean isPivotUnordered(List<T> partition, int pivotIdx, int rIdx) {
        T pivotVal = partition.get(pivotIdx);
        T rVal = partition.get(rIdx);
        return (pivotIdx < rIdx && pivotVal.compareTo(rVal) > 0)
                || (rIdx < pivotIdx && rVal.compareTo(pivotVal) > 0);
    }

    private int calcMidPoint(int size) { return size / 2; }

    private interface SeekerCallback<T extends Comparable<? super T>> {
        int calcNextIdx(int ptrIdx);

        boolean isInBounds(int upperBound, int nextIdx);

        boolean isNextMatch(T pivotVal, T nextIdx);
    }
}
