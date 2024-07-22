package ru.university.hits.code.module_3;

import java.util.Scanner;

class Разделение_множества_на_два_подмножества_чтобы_была_мин_разница_между_суммой_двух_подмножеств {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] set = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = sc.nextInt();
        }
        System.out.println(findMin(set, n));
    }

    static int findMin(int arr[], int n) {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];

        boolean dp[][] = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
        }

        int result = Integer.MAX_VALUE;
        for (int j = sum / 2; j >= 0; j--) {
            if (dp[n][j] == true) {
                result = sum - 2 * j;
                break;
            }
        }
        return result;
    }

}