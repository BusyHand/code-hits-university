package ru.university.hits.code.module_4;

import java.util.Scanner;

class Из_списка_рёбер_в_список_смежности {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt() + 1;
        int m = scanner.nextInt();
        boolean[][] correspondenceMatrix = new boolean[n][n];
        for (int k = 0; k < m; k++) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            correspondenceMatrix[i][j] = true;
            correspondenceMatrix[j][i] = true;
        }

        for (int i = 1; i < n; i++) {
            System.out.print(i + ": ");
            for (int j = 1; j < n; j++) {
                if (correspondenceMatrix[i][j])
                    System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}