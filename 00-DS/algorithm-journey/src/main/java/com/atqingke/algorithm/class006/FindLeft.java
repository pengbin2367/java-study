package com.atqingke.algorithm.class006;

import org.junit.Test;

import java.util.Arrays;

public class FindLeft {

    @Test
    public void findLeftTest() {
        int N = 200, V = 2000;
        int testTimes = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N);
            int[] arr = randomArray(n, V);
            Arrays.sort(arr);
            int target = (int) (Math.random() * V);
            if (right(arr, target) != findLeft(arr, target)) System.out.println("出错了");
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

    private int right(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
        }
        return -1;
    }

    /**
     * 给出 nums 中大于等于 target 的最左元素位置
     * @param nums 升序数组
     * @param target 目标元素
     * @return 存在这个元素返回位置，不存在则返回-1
     */
    private int findLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0, res = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
