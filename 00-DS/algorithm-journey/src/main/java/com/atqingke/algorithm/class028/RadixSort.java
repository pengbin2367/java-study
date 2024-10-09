package com.atqingke.algorithm.class028;

import java.io.*;
import java.util.Arrays;

public class RadixSort {

    private static final int BASE = 10;
    private static final int MAXN = 100001;
    private static final int[] arr = new int[MAXN];
    private static final int[] help = new int[MAXN];
    private static final int[] cnts = new int[BASE];
    private static int n;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        for (int i = 0; i < n; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
        }
        sort();
        for (int i = 0; i < n - 1; i++) {
            out.print(arr[i] + " ");
        }
        out.println(arr[n - 1]);
        out.flush();
        out.close();
    }

    private static void sort() {
        int min = arr[0];
        for (int i = 0; i < n; i++) {
            min = Math.min(min, arr[i]);
        }
        int max = arr[0];
        for (int i = 0; i < n; i++) {
            arr[i] -= min;
            max = Math.max(max, arr[i]);
        }
        radixSort(bits(max));
        for (int i = 0; i < n; i++) {
            arr[i] += min;
        }
    }

    // 返回 num 在 BASE 进制下有几位
    private static int bits(int num){
        int ans = 0;
        while (num > 0) {
            ans++;
            num /= BASE;
        }
        return ans;
    }

    private static void radixSort(int bits) {
        for (int offset = 1; bits > 0; offset *= BASE, bits--) {
            Arrays.fill(cnts, 0);
            for (int i = 0; i < n; i++) {
                cnts[(arr[i] / offset) % BASE]++;
            }
            for (int i = 1; i < BASE; i++) {
                cnts[i] = cnts[i] + cnts[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                help[--cnts[(arr[i] / offset) % BASE]] = arr[i];
            }
            for (int i = 0; i < n; i++) {
                arr[i] = help[i];
            }
        }
    }
}
