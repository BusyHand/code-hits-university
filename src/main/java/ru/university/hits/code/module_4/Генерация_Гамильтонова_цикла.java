package ru.university.hits.code.module_4;

import java.util.Scanner;

class Генерация_Гамильтонова_цикла {
    private static boolean[][] matrix;
    private static boolean[] isBeenHere;

    private static String result = "";

    private static int count = 0;

    private static int n;
    private static int start;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        start = scanner.nextInt() - 1;
        matrix = new boolean[n][n];
        isBeenHere = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt() == 1;
            }

        }
        for (int j = 0; j < n; j++) {
            if (matrix[start][j] && DFS(j)) {
                System.out.println(result + (start + 1));
                return;
            }
        }
        System.out.println(-1);
    }

    private static boolean DFS(int m) {
        if (m == start) {
            if (count == n - 1) {
                result += (m + 1) + " ";
                return true;
            }
            return false;
        }

        if (isBeenHere[m]) {
            return false;
        }
        isBeenHere[m] = true;
        count++;
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[m][j] && !isBeenHere[j]) {
                if (DFS(j)) {
                    result += (m + 1) + " ";
                    return true;
                }
            }
        }
        isBeenHere[m] = false;
        count--;
        return false;
    }
}