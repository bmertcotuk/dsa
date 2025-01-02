package com.bmcotuk.dsaa.algorithms;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Mert Cotuk
 */
@Component
public class Sorts {

    /**
     * ----> d: number of digits
     * ----> b: base of numbers (e.g. 10)
     * ----> k is the largest element
     * <p>
     * time: O(d*(n+b))
     * space: O(k+n)
     */
    public void radixSort(int[] arr) {
        int base = 10;
        LinkedList<Integer>[] digits = new LinkedList[base];
        for (int i = 0; i < base; ++i) {
            digits[i] = new LinkedList<>();
        }
        int maxValue = findMaxInArray(arr);
        int digitCount = getDigitCount(maxValue);
        for (int currentDigit = 0; currentDigit < digitCount; currentDigit++) {
            distribute(arr, digits, currentDigit);
            collect(arr, digits);
        }
    }

    private int findMaxInArray(int[] arr) {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    private int getDigitCount(int n) {
        int digitCount = 0;
        while (n > 0) {
            digitCount++;
            n /= 10;
        }
        return digitCount;
    }

    private void distribute(int[] arr, LinkedList<Integer>[] digits, int currentDigit) {
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            for (int j = 0; j < currentDigit; j++) {
                value /= 10;
            }
            digits[value % 10].addLast(arr[i]);
        }
    }

    private void collect(int[] arr, LinkedList<Integer>[] digits) {
        int i = 0;
        for (int number = 0; number < digits.length; number++) {
            while (!digits[number].isEmpty()) {
                arr[i++] = digits[number].removeFirst();
            }
        }
    }

    /**
     * Recursively pick a pivot and slide elements accordingly towards correct positions
     * <p>
     * time: O(n*logn) - best case or O(n^2) - worst case
     * space: O(logn)
     */
    public void quickSort(int[] arr, int left, int right) {
        if (arr.length == 0) {
            return;
        }
        int index = partition(arr, left, right); // this is just to divide array, pivot is in sub arrays
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (right > index) {
            quickSort(arr, index, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            // find elements to be swapped
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            // swap elements
            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return left; // where to split array and probably, left = right
    }

    /**
     * For each element select the minimum at the right remaining sub array and swap.
     * <p>
     * time: O(n^2)
     * space: O(1) - IN PLACE
     */
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { // -1, because last sub array of size 1 will already be sorted
            int minIndex = i; // better than having the value, index will give the value
            for (int j = i + 1; j < arr.length; j++) { // +1, because we want to check one by one
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * Keep growing left partition as a sorted array as you slide elements to right when necessary.
     * <p>
     * time: O(n^2)
     * space: O(1) - IN PLACE
     */
    public void insertionSort(int[] arr) {
        // insert starting from index 1
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            // slide into insertion sub array
            int j = i - 1;
            while (j > -1 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // +1, because we incremented one more
        }
    }

    /**
     * Input is divided (logn) and each step does (n/2) comparisons
     * <p>
     * time: O(n*logn)
     * space: O(n*logn)
     */
    public void mergeSort(int[] arr) {
        // base case and error prevention
        int size = arr.length;
        if (size < 2) {
            return;
        }

        int mid = size / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, size);

        mergeSort(leftArr);
        mergeSort(rightArr);
        merge(arr, leftArr, rightArr);
    }

    // replaces elements on one step bigger piece (i.e. arr itself)
    // merging of two already sorted arrays, parallel linear iterations
    private void merge(int[] arr, int[] leftArr, int[] rightArr) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    /**
     * Similar to selection sort. In selection sort, we put the smallest element in the beginning and deal with the rest. Here, we put the biggest element in the end and deal with the rest.
     * <p>
     * time: O(n^2)
     * space: O(1) - IN PLACE
     */
    public void bubbleSort(int[] arr) {
        for (int pass = 1; pass < arr.length; pass++) {
            // ensures the max values will be placed at the end one by one in each iteration, therefore pass in [1, arr.length)
            for (int i = 0; i < arr.length - pass; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }

    /**
     * Bucket sort can be implemented in different sizes of buckets where collisions are handled with another sort algorithm such as insertion sort. Here, time is preferred over space and a bucket of size max value in the array is used.
     * <p>
     * m = element with max value in arr
     * time: O(n+m)
     * space: O(m)
     */
    public void bucketSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int maxValue = findMaxInArray(arr);
        // tally counts on buckets
        int[] bucket = new int[maxValue + 1]; // all will be zeros so nothing to clear, time and space complexity of this step is maxValue!!!
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        // iterate over buckets and update the original array
        // total number of iterations will be equal to the array size because each element in arr contributes exactly one count to one of the buckets
        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                arr[outPos++] = i;
            }
        }
    }

}
