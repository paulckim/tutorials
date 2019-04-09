package com.snippets.algorithms.sorts.bubble;

import com.snippets.algorithms.sorts.MutableListSort;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoundedBubbleSortTest {

    private static List<Integer> simpleUnsorted = new ArrayList<>(Arrays.asList(3, 5, 2, 1, 7, 6, 3));
    private static List<Integer> simpleSortedASC;
    private static List<Integer> simpleSortedDES;

    @BeforeClass
    public static void setUpClass() {
        simpleSortedASC = new ArrayList<>(simpleUnsorted);
        simpleSortedDES = new ArrayList<>(simpleUnsorted);
        Collections.sort(simpleSortedASC);
        Collections.sort(simpleSortedDES);
    }

    @Test
    public void testRandomElementsASC() {
        List<Integer> unsorted = new ArrayList<>(simpleUnsorted);
        MutableListSort<Integer> sortImpl = new BoundedBubbleSort<>();
        sortImpl.sort(unsorted);
        assertThat(unsorted).isEqualTo(simpleSortedASC);
    }

    @Test
    public void testBestCaseASC() {
        List<Integer> unsorted = new ArrayList<>(simpleSortedDES);
        MutableListSort<Integer> sortImpl = new BoundedBubbleSort<>();
        sortImpl.sort(unsorted);
        assertThat(unsorted).isEqualTo(simpleSortedASC);
    }

    @Test
    public void testWorstCaseASC() {
        List<Integer> unsorted = new ArrayList<>(simpleUnsorted);
        MutableListSort<Integer> sortImpl = new BoundedBubbleSort<>();
        sortImpl.sort(unsorted);
        assertThat(unsorted).isEqualTo(simpleSortedASC);
    }

}
