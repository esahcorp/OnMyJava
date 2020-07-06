package com.kuma.sort;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author kuma 2020-07-06
 */
public class BubbleSortTest extends BaseSortTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sort() {
        BubbleSort.sort(given);
        assertArrayEquals(sorted, given);
    }
}