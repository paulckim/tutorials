package com.snippets.algorithms.sorts.merge;

import com.snippets.algorithms.sorts.ImmutableListSort;

import java.util.ArrayList;
import java.util.List;

public class RecursiveMergeSort<T extends Comparable<? super T>> implements ImmutableListSort<T> {

    private static final int FIRST_ELEMENT = 0;

    @Override
    public List<T> sort(List<T> unsorted) {
        return mergeSort(unsorted);
    }

    private List<T> mergeSort(List<T> unsorted) {
        if(1 == unsorted.size())
            return unsorted;
        int midpoint = calcMidPoint(unsorted.size());;
        List<T> lhsList = mergeSort(unsorted.subList(FIRST_ELEMENT, midpoint));
        List<T> rhsList = mergeSort(unsorted.subList(midpoint, unsorted.size()));
        return mergeLists(lhsList, rhsList);
    }

    private List<T> mergeLists(List<T> lhs, List<T> rhs) {
        List<T> mergedList = new ArrayList<>(lhs.size() + rhs.size());
        int lPtr = FIRST_ELEMENT, rPtr = FIRST_ELEMENT;
        // move left and right ptr(s) and add elements:
        while(lPtr < lhs.size() && rPtr < rhs.size()) {
            T lVal = lhs.get(lPtr);
            T rVal = rhs.get(rPtr);
            if(lVal.compareTo(rVal) <= 0)
                mergedList.add(lhs.get(lPtr++));
            else
                mergedList.add(rhs.get(rPtr++));
        }
        // fill the rest of the array with the lhs
        while(lPtr < lhs.size())
            mergedList.add(lhs.get(lPtr++));
        // fill the rest of the array with the rhs
        while(rPtr < rhs.size())
            mergedList.add(rhs.get(rPtr++));
        return mergedList;
    }

    private int calcMidPoint(int size) {
        return size / 2;
    }

}
