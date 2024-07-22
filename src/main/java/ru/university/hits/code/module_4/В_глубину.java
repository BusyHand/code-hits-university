package ru.university.hits.code.module_4;

import java.util.Scanner;

class В_глубину {
    private static boolean[][] matrix;
    private static boolean[] isBeenHere;

    private static String result = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt() + 1;
        int m = scanner.nextInt();
        matrix = new boolean[n][n];
        isBeenHere = new boolean[n];
        isBeenHere[m] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = scanner.nextInt() == 1;
            }
        }
        DFS(m);
        System.out.println(m + " " + result);
    }


    private static void DFS(int m) {
        for (int j = 1; j < matrix.length; j++) {
            if (matrix[m][j] && !isBeenHere[j]) {
                isBeenHere[j] = true;
                result += j + " ";
                DFS(j);
            }
        }
    }


}