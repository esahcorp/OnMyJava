package com.kuma.sort;


/**
 * 冒泡
 *
 * @author kuma 2020-07-05
 */
public class BubbleSort {

    public static void sort(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
