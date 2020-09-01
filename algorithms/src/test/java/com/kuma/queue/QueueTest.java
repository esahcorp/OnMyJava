package com.kuma.queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author kuma 2020-09-01
 */
public class QueueTest extends BaseQueueTest {

    Queue<String> queue = new Queue<>();

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        for (String s : input.split(" ")) {
            if (s.equals("-")) {
                list.add(queue.dequeue());
            } else {
                queue.enqueue(s);
            }
        }
        assertArrayEquals(output, list.toArray());
        assertEquals(2, queue.size());
    }

}