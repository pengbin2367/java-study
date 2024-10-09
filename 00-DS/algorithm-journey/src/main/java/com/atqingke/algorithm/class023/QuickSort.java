package com.atqingke.algorithm.class023;

import com.atqingke.algorithm.class005.Validator;

public class QuickSort {

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) return ;
        int x = arr[left + (int) (Math.random() * (right - left + 1))];
        int mid = partition(arr, left, right, x);
        quickSort(arr, left, mid - 1);
        quickSort(arr, mid + 1, right);
    }
    private int partition(int[] arr, int left, int right, int x) {
        int a = left, xi = 0;
        for (int i = left; i <= right; i++) {
            if (arr[i] <= x) {
                swap(arr, a, i);
                if (arr[a] == x) xi = a;
                a++;
            }
        }
        swap(arr, a - 1, xi);
        return a - 1;
    }

    private int first, last;
    private void quickSort2(int[] arr, int left, int right) {
        if (left >= right) return;
        int x = arr[left + (int)(Math.random() * (right - left + 1))];
        partition2(arr, left, right, x);
        int l = first, r = last;
        quickSort2(arr, left, l - 1);
        quickSort2(arr, r + 1, right);
    }
    private void partition2(int[] arr, int left, int right, int x) {
        first = left;
        last = right;
        int i = left;
        while (i <= last) {
            if (arr[i] == x) {
                i++;
            } else if (arr[i] < x) {
                swap(arr, first++, i++);
            } else {
                swap(arr, i, last--);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        Validator.swap(arr, i, j);
    }
}
