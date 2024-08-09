package com.atqingke.algorithm.class006;

public class FindPeakElement {

    /**
     * <a href="https://leetcode.cn/problems/find-peak-element/">162. 寻找峰值元素</a>
     */
    private int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;
        int left = 1, right = n - 2, mid = 0, res = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid - 1] > nums[mid]) {
                right = mid - 1;
            } else if (nums[mid + 1] > nums[mid]) {
                left = mid + 1;
            } else {
                res = mid;
                break;
            }
        }
        return res;
    }
}
