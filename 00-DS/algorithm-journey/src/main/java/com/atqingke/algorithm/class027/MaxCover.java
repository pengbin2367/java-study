package com.atqingke.algorithm.class027;

import java.io.*;
import java.util.Arrays;

// https://www.nowcoder.com/practice/1ae8d0b6bb4e4bcdbf64ec491f63fc37
public class MaxCover {

    private static int MAXN = 10001;
    private static int[][] lines = new int[MAXN][2];
    private static int n;
    private static int size;
    private static int[] heap = new int[MAXN];

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        in.nextToken();
        n = (int) in.nval;
        lines = new int[n][2];
        for (int i = 0; i < n; i++) {
            in.nextToken();
            lines[i][0] = (int) in.nval;
            in.nextToken();
            lines[i][1] = (int) in.nval;
        }
        out.println(maxCover());
        out.flush();
        out.close();
    }

    private static int maxCover() {
        int ans = 0;
        size = 0;
        Arrays.sort(lines, 0, n, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            while (size > 0 && heap[0] <= lines[i][0]) {
                pop();
            }
            add(lines[i][1]);
            ans = Math.max(ans, size);
        }
        return ans;
    }

    private static void add(int x) {
        heap[size] = x;
        int i = size++;
        while (heap[i] < heap[(i - 1) / 2]) {
            swap(heap, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void pop() {
        swap(heap, 0, --size);
        int i = 0, l = 1;
        while (l < size) {
            int best = l + 1 < size && heap[l + 1] < heap[l] ? l + 1 : l;
            best = heap[best] < heap[i] ? best : i;
            if (best == i) break;
            swap(heap, best, i);
            i = best;
            l = i * 2 + 1;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
