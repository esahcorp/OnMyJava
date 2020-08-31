package com.kuma.stack;

/**
 * 定容字符串栈
 * 问题：
 * <ul>
 *     <li>不支持泛型</li>
 *     <li>无法动态调整长度</li>
 *     <li>数组引用导致无效内存无法回收</li>
 *     <li>无法迭代</li>
 * </ul>
 *
 * @author kuma 2020-08-27
 */
public class FixedCapacityStackOfStrings {

    private final String[] array;
    private int count;

    public FixedCapacityStackOfStrings(int cap) {
        this.array = new String[cap];
    }

    public void push(String item) {
        if (count == array.length) {
            throw new IndexOutOfBoundsException();
        }
        array[count++] = item;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        return array[--count];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }
}
