package ru.university.hits.code.module_3;

import java.util.Scanner;

class Задача_о_рюкзаке_3 {

    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int W = scanner.nextInt();

        int[] values = new int[n];
        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }
        knaspack(weights, values, n, W);
        System.out.println(result);
    }

    private static void knaspack(int[] weights, int[] values, int n, int W) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        findAns(dp, weights, n, W);
    }

    private static void findAns(int[][] dp, int[] wights, int n, int W) {
        if (dp[n][W] == 0) {
            return;
        }
        if (dp[n - 1][W] == dp[n][W]) {
            findAns(dp, wights, n - 1, W);
        } else {
            findAns(dp, wights, n - 1, W - wights[n - 1]);
            result.append(n).append(" ");
        }
    }
}
