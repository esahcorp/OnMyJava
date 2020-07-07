package com.kuma.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author kuma 2020-07-07
 */
public class InsertionTest extends BaseSortTest {

    @Test
    public void sort() {
        Insertion.sort(given);
        assertArrayEquals(sorted, given);
    }
}