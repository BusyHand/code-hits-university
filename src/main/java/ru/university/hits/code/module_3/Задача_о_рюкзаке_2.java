package ru.university.hits.code.module_3;

import java.util.Scanner;

class Задача_о_рюкзаке_2 {
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
        System.out.println(knaspack(weights, values, n, W));
    }

    private static int knaspack(int[] weights, int[] values, int n, int W) {
        if (n <= 0 || W <= 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }
}
