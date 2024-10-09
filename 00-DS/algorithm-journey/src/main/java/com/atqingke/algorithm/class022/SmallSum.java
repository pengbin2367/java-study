package com.atqingke.algorithm.class022;

import java.io.*;

// https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469
public class SmallSum {

    private static int MAXN = 100001;
    private static int[] arr = new int[MAXN];
    private static int[] help = new int[MAXN];
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.println(smallSum(0, n - 1));
        }
        out.flush();
        out.close();
    }

    private static long smallSum(int l, int r) {
        if (l == r) return 0;
        int m = (l + r) / 2;
        return smallSum(l, m) + smallSum(m + 1, r) + merge(l, m, r);
    }

    private static long merge(int l, int m, int r) {
        long ans = 0;
        for (int j = m + 1, i = l, sum = 0; j <= r; j++) {
            while (i <= m && arr[i] <= arr[j]) sum += arr[i++];
            ans += sum;
        }
        int i = l;
        int a = l;
        int b = m + 1;
        while (a <= m && b <= r) help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        while (a <= m) help[i++] = arr[a++];
        while (b <= r) help[i++] = arr[b++];
        for (i = l; i <= r; i++) arr[i] = help[i];
        return ans;
    }
}
