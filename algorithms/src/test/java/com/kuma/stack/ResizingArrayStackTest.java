package com.kuma.stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author kuma 2020-08-31
 */
public class ResizingArrayStackTest extends BaseStackTest {

    ResizingArrayStack<String> stack = new ResizingArrayStack<>();

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