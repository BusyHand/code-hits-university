package ru.university.hits.code.module_4;

import java.util.Scanner;

class Из_Матрицы_смежности_в_список_смежности {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[][] correspondenceMatrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                correspondenceMatrix[i][j] = scanner.nextInt() == 1;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ": ");
            for (int j = 0; j < n; j++) {
                if (correspondenceMatrix[i][j]) {
                    System.out.print((j + 1) + " ");
                }
            }
            System.out.println();
        }
    }
}