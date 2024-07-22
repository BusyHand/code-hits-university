package ru.university.hits.code.module_4;

import java.util.Scanner;

import static java.lang.Long.MAX_VALUE;
import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

class Проблема_коммивояжёра {
    private static int n;
    private static long[][] graph;
    private static long[][] dp;
    private static int[][] path;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        graph = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextLong();
            }
        }

        dp = new long[n][1 << n];
        stream(dp).forEach(d -> fill(d, MAX_VALUE));
        for (int i = 0; i < n; i++) {
            dp[i][1 << i] = 0;
        }

        path = new int[n][1 << n];
        for (int mask = 1; mask < 1 << n; mask++) {
            for (int v = 0; v < n; v++) {
                for (int u = 0; u < n; u++) {
                    if (v != u && (mask >> u & 1) == 0) {
                        int newMask = mask | (1 << u);
                        long oldValue = dp[u][newMask];
                        long newValue = dp[v][mask] == MAX_VALUE ? MAX_VALUE : (dp[v][mask] + graph[v][u]);
                        if (newValue < oldValue) {
                            dp[u][newMask] = newValue;
                            path[u][newMask] = v;
                        }
                    }
                }
            }
        }

        long result = MAX_VALUE;
        int startIndex = 0;
        for (int i = 0; i < n; i++) {
            long value = dp[i][(1 << n) - 1];
            if (value <= result) {
                result = value;
                startIndex = i;
            }
        }

        System.out.println(result);

        int mask = ((1 << n) - 1);
        String resultPath = "";
        for (int i = 0; i < n; i++) {
            resultPath = (startIndex + 1) + " " + resultPath;
            int lastIndex = startIndex;
            startIndex = path[startIndex][mask];
            mask ^= (1 << lastIndex);
        }
        System.out.println(resultPath);
    }
}