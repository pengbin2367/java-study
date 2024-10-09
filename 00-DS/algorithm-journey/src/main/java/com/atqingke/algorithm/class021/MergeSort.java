package com.atqingke.algorithm.class021;

public class MergeSort {

    private static int MAXSIZE = 501;
    private static int[] nums = new int[MAXSIZE];
    private static int[] help = new int[MAXSIZE];

    public static void mergeSort(int left, int right) {
        if (left == right) {
            return ;
        }
        int mid = left + (right - left) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    public static void merge(int left, int mid, int right) {
        int i = left, a = left, b = mid + 1;
        while (a <= mid && b <= right) {
            help[i++] = nums[a] <= nums[b] ? nums[a++] : nums[b++];
        }
        while (a <= mid) {
            help[i++] = nums[a++];
        }
        while (b <= right) {
            help[i++] = nums[b++];
        }
        for (i = left; i <= right; i++) {
            nums[i] = help[i];
        }
    }

    public static void mergeSortIterative() {
        for (int left = 0, mid = 0, right = 0, step = 1; step < MAXSIZE; step <<= 1) {
            left = 0;
            while (left < MAXSIZE) {
                mid = left + step - 1;
                if (right + 1 >= MAXSIZE) break;
                right = Math.min(left + (step << 1) - 1, MAXSIZE - 1);
                merge(left, mid, right);
                left = right + 1;
            }
        }
    }
}
