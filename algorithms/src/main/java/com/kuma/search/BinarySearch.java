package com.kuma.search;

/**
 * @author kuma 2020-07-03
 */
public class BinarySearch {

    public static int search(int[] array, int value) {
        int lowerIndex = 0;
        int upperIndex = array.length - 1;
        while (lowerIndex <= upperIndex) {
            int midIndex = upperIndex - (upperIndex - lowerIndex) / 2;
            int midValue = array[midIndex];
            if (value < midValue) {
                upperIndex = midIndex - 1;
            } else if (value > midValue) {
                lowerIndex = midIndex + 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }
}
