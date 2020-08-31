package com.kuma.stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author kuma 2020-09-01
 */
public class StackTest extends BaseStackTest {

    Stack<String> stack = new Stack<>();

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        for (String s : input.split(" ")) {
            if (!s.equals("-")) {
                stack.push(s);
            } else {
                list.add(stack.pop());
            }
        }
        assertArrayEquals(output, list.toArray());
        assertEquals(2, stack.size());
    }
}