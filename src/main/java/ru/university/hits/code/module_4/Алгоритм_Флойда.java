package ru.university.hits.code.module_4;

import java.util.Scanner;

import static java.lang.Math.min;

class Алгоритм_Флойда {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int start = scan.nextInt() - 1;
        int end = scan.nextInt() - 1;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int weight = scan.nextInt();
                if (i == j) {
                    dp[i][j] = 0;
                } else if (weight == -1) {
                    dp[i][j] = Integer.MAX_VALUE;
                } else {
                    dp[i][j] = weight;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE)
                        dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        System.out.println(dp[start][end]);

    }
}
