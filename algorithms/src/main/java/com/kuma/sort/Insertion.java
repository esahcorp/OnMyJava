package com.kuma.sort;

/**
 * @author kuma 2020-07-07
 */
public class Insertion {

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int position = i;
            while (position > 0 && array[position - 1] > temp) {
                array[position] = array[position - 1];
                position--;
            }
            array[position] = temp;
        }
    }
}
