package ru.university.hits.code.module_3;

import java.util.Scanner;

class Количество_возможных_способов {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        int[] dp = new int[s + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= s; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }

        System.out.println(dp[s]);
    }
}
