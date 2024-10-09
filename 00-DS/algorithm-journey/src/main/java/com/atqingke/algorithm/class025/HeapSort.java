package com.atqingke.algorithm.class025;

import com.atqingke.algorithm.class005.Validator;

public class HeapSort {

    private void heapSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        while (size > 1) {
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
        }
    }

    private void heapify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        while (left < size) {
            int best = left + 1 < size && arr[left] < arr[left + 1] ? left + 1 : left;
            best = arr[index] > arr[best] ? index : best;
            if (best == index) break;
            swap(arr, best, index);
            index = best;
            left = 2 * index + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        Validator.swap(arr, i, j);
    }
}
