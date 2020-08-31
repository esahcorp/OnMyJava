package com.kuma.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author kuma 2020-08-31
 */
public class Stack<T> implements Iterable<T> {

    private Node<T> first;
    private int count;

    public void push(T item) {
        Node<T> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        count++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        T temp = first.item;
        first = first.next;
        count--;
        return temp;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
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
        StringBuilder sb = new StringBuilder();
        for (T item : this) {
            sb.append(item);
            sb.append(' ');
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedStackIterator(first);
    }

    private class LinkedStackIterator implements Iterator<T> {

        private Node<T> current;

        public LinkedStackIterator(Node<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Stack underflow");
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

    private static class Node<T> {
        private T item;
        private Node<T> next;
    }
}
