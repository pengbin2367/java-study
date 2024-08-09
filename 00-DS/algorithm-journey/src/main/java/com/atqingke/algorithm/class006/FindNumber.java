package com.atqingke.algorithm.class006;

import org.junit.Test;

import java.util.Arrays;

public class FindNumber {

    @Test
    public void findNumberTest() {
        int N = 200, V = 2000;
        int testTimes = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N);
            int[] arr = randomArray(n, V);
            Arrays.sort(arr);
            int target = (int) (Math.random() * V);
            if (right(arr, target) != exist(arr, target)) System.out.println("出错了");
        }
        System.out.println("测试结束");
    }

    private int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v);
        }
        return arr;
    }

    private boolean right(int[] arr, int target) {
        for (int a : arr)
            if (a == target) return true;
        return false;
    }

    /**
     * 判断 nums 中是否存在元素 target
     * @param nums 升序数组
     * @param target 目标元素
     * @return 存在返回true,反之false
     */
    public static boolean exist(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
