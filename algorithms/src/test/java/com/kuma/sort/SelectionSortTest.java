package com.kuma.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author kuma 2020-07-06
 */
public class SelectionSortTest extends BaseSortTest {

    @Test
    public void sort() {
        SelectionSort.sort(given);
        assertArrayEquals(sorted, given);
    }
}