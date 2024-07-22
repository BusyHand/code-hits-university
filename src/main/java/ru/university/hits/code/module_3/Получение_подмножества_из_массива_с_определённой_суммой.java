package ru.university.hits.code.module_3;

import java.util.Scanner;

class Получение_подмножества_из_массива_с_определённой_суммой {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int needSum = sc.nextInt();
        int[] set = new int[n];
        long maxSum = 0;
        for (int i = 0; i < n; i++) {
            set[i] = sc.nextInt();
            maxSum += set[i];
        }
        if (maxSum < needSum) {
            System.out.println("No");
            return;
        }

        boolean[][] dp = new boolean[n + 1][needSum + 1];
        dp[0][0] = true;
        for (int i = 1; i < n + 1; i++) {
            for (int s = 0; s <= needSum; s++) {
                dp[i][s] = dp[i - 1][s];
                if (s - set[i - 1] >= 0) {
                    dp[i][s] = dp[i][s] || dp[i - 1][s - set[i - 1]];
                }
            }
        }
        System.out.println(dp[n][needSum] ? "Yes" : "No");
    }
}