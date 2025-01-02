package com.bmcotuk.dsaa.algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(MockitoExtension.class)
class SortsTest {

    @InjectMocks
    private Sorts sorts;

    @Test
    void test_sortWithRadixSort() {
        int[] arr = {51, 12, 61, 21, 38, 49};
        int[] expected = {12, 21, 38, 49, 51, 61};
        sorts.radixSort(arr);
        assertArrayEquals(expected, arr);

        int[] arr2 = {59, 17};
        int[] expected2 = {17, 59};
        sorts.radixSort(arr2);
        assertArrayEquals(expected2, arr2);

        int[] arr3 = {51};
        int[] expected3 = {51};
        sorts.radixSort(arr3);
        assertArrayEquals(expected3, arr3);

        int[] arr4 = {};
        int[] expected4 = {};
        sorts.radixSort(arr4);
        assertArrayEquals(expected4, arr4);

        int[] arr5 = {351, 412, 161, 521, 38, 9};
        int[] expected5 = {9, 38, 161, 351, 412, 521};
        sorts.radixSort(arr5);
        assertArrayEquals(expected5, arr5);
    }

    @Test
    void test_sortWithQuickSort() {
        int[] arr = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        sorts.quickSort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);

        int[] arr2 = {5, 1, 2, 3, 4};
        int[] expected2 = {1, 2, 3, 4, 5};
        sorts.quickSort(arr2, 0, arr2.length - 1);
        assertArrayEquals(expected2, arr2);

        int[] arr3 = {5};
        int[] expected3 = {5};
        sorts.quickSort(arr3, 0, arr3.length - 1);
        assertArrayEquals(expected3, arr3);

        int[] arr4 = {};
        int[] expected4 = {};
        sorts.quickSort(arr4, 0, 0);
        assertArrayEquals(expected4, arr4);
    }

    @Test
    void test_sortWithSelectionSort() {
        int[] arr = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        sorts.selectionSort(arr);
        assertArrayEquals(expected, arr);

        int[] arr2 = {5, 1, 2, 3, 4};
        int[] expected2 = {1, 2, 3, 4, 5};
        sorts.selectionSort(arr2);
        assertArrayEquals(expected2, arr2);

        int[] arr3 = {5};
        int[] expected3 = {5};
        sorts.selectionSort(arr3);
        assertArrayEquals(expected3, arr3);

        int[] arr4 = {};
        int[] expected4 = {};
        sorts.selectionSort(arr4);
        assertArrayEquals(expected4, arr4);
    }

    @Test
    void test_sortWithInsertionSort() {
        int[] arr = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        sorts.insertionSort(arr);
        assertArrayEquals(expected, arr);

        int[] arr2 = {5, 1, 2, 3, 4};
        int[] expected2 = {1, 2, 3, 4, 5};
        sorts.insertionSort(arr2);
        assertArrayEquals(expected2, arr2);

        int[] arr3 = {5};
        int[] expected3 = {5};
        sorts.insertionSort(arr3);
        assertArrayEquals(expected3, arr3);

        int[] arr4 = {};
        int[] expected4 = {};
        sorts.insertionSort(arr4);
        assertArrayEquals(expected4, arr4);
    }

    @Test
    void test_sortWithMergeSort() {
        int[] arr = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        sorts.mergeSort(arr);
        assertArrayEquals(expected, arr);

        int[] arr2 = {5, 1, 2, 3, 4};
        int[] expected2 = {1, 2, 3, 4, 5};
        sorts.mergeSort(arr2);
        assertArrayEquals(expected2, arr2);

        int[] arr3 = {5};
        int[] expected3 = {5};
        sorts.mergeSort(arr3);
        assertArrayEquals(expected3, arr3);

        int[] arr4 = {};
        int[] expected4 = {};
        sorts.mergeSort(arr4);
        assertArrayEquals(expected4, arr4);
    }

    @Test
    void test_sortWithBubbleSort() {
        int[] arr = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        sorts.bubbleSort(arr);
        assertArrayEquals(expected, arr);

        int[] arr2 = {5, 1, 2, 3, 4};
        int[] expected2 = {1, 2, 3, 4, 5};
        sorts.bubbleSort(arr2);
        assertArrayEquals(expected2, arr2);

        int[] arr3 = {5};
        int[] expected3 = {5};
        sorts.bubbleSort(arr3);
        assertArrayEquals(expected3, arr3);

        int[] arr4 = {};
        int[] expected4 = {};
        sorts.bubbleSort(arr4);
        assertArrayEquals(expected4, arr4);
    }

    @Test
    void test_sortWithBucketSort() {
        int[] arr = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        sorts.bucketSort(arr);
        assertArrayEquals(expected, arr);

        int[] arr2 = {5, 1, 2, 3, 4};
        int[] expected2 = {1, 2, 3, 4, 5};
        sorts.bucketSort(arr2);
        assertArrayEquals(expected2, arr2);

        int[] arr3 = {5};
        int[] expected3 = {5};
        sorts.bucketSort(arr3);
        assertArrayEquals(expected3, arr3);

        int[] arr4 = {};
        int[] expected4 = {};
        sorts.bucketSort(arr4);
        assertArrayEquals(expected4, arr4);
    }
}
