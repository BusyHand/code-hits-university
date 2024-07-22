package ru.university.hits.code.module_3;

import java.util.Scanner;

class Конь_2_0 {

    private static boolean[][] isBeenHeer;
    private static int[][] matrix;
    private static int n;
    private static int m;
    private static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        matrix = new int[n][m];
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < m; l++) {
                matrix[k][l] = sc.nextInt();
            }
        }
        n += 2;
        m += 2;
        dp = new int[n][m];
        isBeenHeer = new boolean[n][m];
        isBeenHeer[2][2] = true;
        int i = 2;
        while (i < n) {
            int x = i;
            int y = 2;
            while (x >= 2 && y < m) {
                if (isBeenHeer[x][y]) {
                    int value = getMax(x, y);
                    dp[x][y] = matrix[x - 2][y - 2] + (value != Integer.MIN_VALUE ? value : 0);
                    fillNextXY(x, y);
                }
                x--;
                y++;
            }
            i++;
        }
        int j = 3;
        while (j < m) {
            int x = n - 1;
            int y = j;
            while (x >= 2 && y < m) {
                if (isBeenHeer[x][y]) {
                    int value = getMax(x, y);
                    dp[x][y] = matrix[x - 2][y - 2] + (value != Integer.MIN_VALUE ? value : 0);
                    fillNextXY(x, y);
                }
                x--;
                y++;
            }
            j++;
        }

        System.out.println(isBeenHeer[n - 1][m - 1] ? dp[n - 1][m - 1] : "-");
    }

    private static void fillNextXY(int i, int j) {
        if (i + 2 < n && j - 1 < m) {
            isBeenHeer[i + 2][j - 1] = true;
        }
        if (i + 2 < n && j + 1 < m) {
            isBeenHeer[i + 2][j + 1] = true;
        }
        if (i - 1 < n && j + 2 < m) {
            isBeenHeer[i - 1][j + 2] = true;
        }
        if (i + 1 < n && j + 2 < m) {
            isBeenHeer[i + 1][j + 2] = true;
        }
    }

    private static int dp(int i, int j) {
        if (i >= 2 && i < dp.length && j >= 2 && j < dp[0].length) {
            return dp[i][j];
        } else return Integer.MIN_VALUE;
    }

    private static int getMax(int i, int j) {
        return Math.max(dp(i + 1, j - 2),
                Math.max(dp(i - 1, j - 2),
                        Math.max(dp(i - 2, j - 1), dp(i - 2, j + 1))));
    }

}

