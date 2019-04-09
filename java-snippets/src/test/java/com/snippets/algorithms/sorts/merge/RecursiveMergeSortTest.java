package com.snippets.algorithms.sorts.merge;

import com.snippets.algorithms.sorts.ImmutableListSort;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecursiveMergeSortTest {

    private static List<Integer> simpleUnsorted = new ArrayList<>(Arrays.asList(3, 5, 2, 1, 7, 6, 3));
    private static List<Integer> simpleSortedASC;
    private static List<Integer> simpleSortedDES;

    @BeforeClass
    public static void setUpClass() {
        simpleSortedASC = new ArrayList<>(simpleUnsorted);
        simpleSortedDES = new ArrayList<>(simpleUnsorted);
        Collections.sort(simpleSortedASC);
        Collections.sort(simpleSortedDES, (lhs, rhs) -> -lhs.compareTo(rhs));
    }

    @Test
    public void testRandomElementsASC() {
        List<Integer> unsorted = new ArrayList<>(simpleUnsorted);
        ImmutableListSort<Integer> sortImpl = new RecursiveMergeSort<>();
        List<Integer> sorted = sortImpl.sort(unsorted);
        assertThat(sorted).isEqualTo(simpleSortedASC);
    }

    @Test
    public void testBestCaseASC() {
        List<Integer> unsorted = new ArrayList<>(simpleSortedDES);
        ImmutableListSort<Integer> sortImpl = new RecursiveMergeSort<>();
        List<Integer> sorted = sortImpl.sort(unsorted);
        assertThat(sorted).isEqualTo(simpleSortedASC);
    }

    @Test
    public void testWorstCaseASC() {
        List<Integer> unsorted = new ArrayList<>(simpleUnsorted);
        ImmutableListSort<Integer> sortImpl = new RecursiveMergeSort<>();
        List<Integer> sorted = sortImpl.sort(unsorted);
        assertThat(sorted).isEqualTo(simpleSortedASC);
    }

}
