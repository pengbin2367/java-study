package com.atqingke.algorithm.class027;

import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/
public class HalveArray {

    @Test
    public void halve() {
        int[] nums = new int[] { 5, 19, 8, 1 };
        System.out.println(halveArray(nums));
    }

    public int halveArray(int[] nums) {
        int ans = 0;
        PriorityQueue<Double> heap = new PriorityQueue<>(Collections.reverseOrder());
        double sum = 0.0;
        for (double num : nums) {
            heap.add(num);
            sum += num;
        }
        double cur = 0.0;
        while (cur < sum / 2) {
            double tmp = heap.poll() / 2;
            cur += tmp;
            heap.add(tmp);
            ans++;
        }
        return ans;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
