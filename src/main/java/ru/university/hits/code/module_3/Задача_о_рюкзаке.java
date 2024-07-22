package ru.university.hits.code.module_3;

import java.util.Scanner;

class Задача_о_рюкзаке {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int W = scanner.nextInt();

        int[] wights = new int[n];
        for (int i = 0; i < n; i++) {
            wights[i] = scanner.nextInt();
        }

        System.out.println(knapsackMaxWeight(wights, n, W));
    }

    public static int knapsackMaxWeight(int[] weights, int n, int W) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(weights[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }
}