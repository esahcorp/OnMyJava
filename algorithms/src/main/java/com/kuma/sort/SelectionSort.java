package com.kuma.sort;

/**
 * 选择排序
 *
 * @author kuma 2020-07-06
 */
public class SelectionSort {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int lowerIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[lowerIndex]) {
                    lowerIndex = j;
                }
            }
            if (lowerIndex != i) {
                int temp = array[i];
                array[i] = array[lowerIndex];
                array[lowerIndex] = temp;
            }
        }
    }
}
