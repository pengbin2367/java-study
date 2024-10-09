package com.atqingke.algorithm.class024;

import com.atqingke.algorithm.class005.Validator;

// https://leetcode.cn/problems/kth-largest-element-in-an-array/
public class RandomizedSelect {

    public int findKthLargest(int[] nums, int k) {
        return randomizedSelect(nums, nums.length - k);
    }

    private int first, last;

    private int randomizedSelect(int[] nums, int k) {
        int left = 0, right = nums.length - 1, res = 0;
        while (left <= right) {
            partition(nums, left, right, nums[left + (int)(Math.random() * (right - left + 1))]);
            if (k < first) {
                right = first - 1;
            } else if (k > last) {
                left = last + 1;
            } else {
                res = nums[k];
                break;
            }
        }
        return res;
    }

    private void partition(int[] nums, int left, int right, int x) {
        first = left;
        last = right;
        int i = left;
        while (i <= last) {
            if (nums[i] == x) {
                i++;
            } else if (nums[i] < x) {
                Validator.swap(nums, i++, first++);
            } else {
                Validator.swap(nums, i, last--);
            }
        }
    }
}
