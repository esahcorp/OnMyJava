package com.kuma.stack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author kuma 2020-08-27
 */
public class FixedCapacityStackOfStringsTest {

    FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);

    @Test
    public void push() {
        assertEquals(0, stack.size());
        stack.push("");
        assertEquals(1, stack.size());
    }

    @Test
    public void pop() {
        stack.push("");
        assertEquals(1, stack.size());
        String item = stack.pop();
        assertSame("", item);
        assertEquals(0, stack.size());
    }

}