package com.kuma.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.kuma.search.BinarySearch.*;
import static org.junit.Assert.*;

/**
 * @author kuma 2020-07-06
 */
public class BinarySearchTest {

    int[] given = {1, 2, 3, 4, 5};

    @Before
    public void setup() {}

    @Test
    public void search_element_exist() {
        assertEquals(2, search(given, 3));
    }

    @Test
    public void search_element_not_exist() {
        assertEquals(-1, search(given, 6));
    }
}