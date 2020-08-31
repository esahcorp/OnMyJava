package com.kuma.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 动态调整数组大小的栈
 *
 * @author kuma 2020-08-28
 */
public class ResizingArrayStack<T> implements Iterable<T> {

    private T[] array;
    private int count;

    public ResizingArrayStack() {
        array = (T[]) new Object[2];
        count = 0;
    }

    public void push(T item) {
        if (count == array.length) {
            resize(array.length * 2);
        }
        array[count++] = item;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        T item = array[--count];
        // 避免无用内存无法回收
        array[count] = null;
        // 避免弹出前 count = 1 的情况
        if (count > 0 && count == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return array[count - 1];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    private void resize(int capacity) {
        assert capacity >= count;
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(array, 0, temp, 0, count);
        array = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {

        private int i;

        public ReverseArrayIterator() {
            i = count - 1;
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[i--];
        }

        @Override
        public void remove() {
            // 避免迭代时修改数据结构
            throw new UnsupportedOperationException();
        }
    }
}




























