package com.atqingke.algorithm.class022;

// https://leetcode.cn/problems/reverse-pairs/
public class ReversePairs {

    private int MAXN = 50001;
    private int[] help = new int[MAXN];

    public int reversePairs(int[] nums) {
        return counts(nums, 0, nums.length - 1);
    }

    private int counts(int[] nums, int l, int r) {
        if (l == r) return 0;
        int m = (l + r) / 2;
        return counts(nums, l, m) + counts(nums, m + 1, r) + merge(nums, l, m, r);
    }

    private int merge(int[] nums, int l, int m, int r) {
        int res = 0;
        for (int i = l, j = m + 1; i <= m; i++) {
            while (j <= r && (long) nums[i] > (long) nums[j] * 2) j++;
            res += j - m - 1;
        }
        int i = l, a = l, b = m + 1;
        while (a <= m && b <= r) help[i++] = nums[a] <= nums[b] ? nums[a++] : nums[b++];
        while (a <= m) help[i++] = nums[a++];
        while (b <= r) help[i++] = nums[b++];
        for (i = l; i<= r; i++) nums[i] = help[i];
        return res;
    }
}
