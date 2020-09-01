package com.kuma.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 背包实现
 *
 * @author kuma 2020-09-01
 */
public class Bag<T> implements Iterable<T> {

    private Node<T> first;
    private int count;

    public void add(T item) {
        Node<T> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        count++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedBagIterator(first);
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;
    }

    private class LinkedBagIterator implements Iterator<T> {

        private Node<T> current;

        public LinkedBagIterator(Node<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.next.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
