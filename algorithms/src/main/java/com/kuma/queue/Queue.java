package com.kuma.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列实现
 *
 * @author kuma 2020-09-01
 */
public class Queue<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int count;

    public void enqueue(T item) {
        Node<T> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        // 空队列添加第一个元素
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        count++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T temp = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        count--;
        return temp;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator(first);
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;
    }

    private class LinkedQueueIterator implements Iterator<T> {

        private Queue.Node<T> current;

        public LinkedQueueIterator(Node<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Queue underflow");
            }
            T temp = current.item;
            current = current.next;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
