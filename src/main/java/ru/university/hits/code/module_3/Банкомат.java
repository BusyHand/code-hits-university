package ru.university.hits.code.module_3;

import java.util.Arrays;
import java.util.Scanner;

class Банкомат {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }
        int[] dp = new int[s + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= s; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= coins[j]) {
                    int sub_res = dp[i - coins[j]];
                    if (sub_res != Integer.MAX_VALUE && sub_res + 1 < dp[i]) {
                        dp[i] = sub_res + 1;
                    }
                }
            }
        }
        if (dp[s] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[s]);
        }
    }
}