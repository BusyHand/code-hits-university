package ru.university.hits.code.module_4;

import java.util.Scanner;

class Проверка_базовых_свойств_графа {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt() + 1;
        long[][] matrix = new long[n][n];
        int isCertainty = -1;
        int isBalance = -1;
        int isTransitivity = 1;
        int isCompleteness = 1;


        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                long value = scanner.nextLong();
                if (value != 0 && value != 1) {
                    isBalance = 1;
                }
                matrix[i][j] = value;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (isCertainty == -1 && matrix[i][j] != matrix[j][i]) {
                    isCertainty = 1;
                }

                if (isCompleteness == 1 && matrix[i][j] != matrix[j][i] && (matrix[i][j] == 0 || matrix[j][i] == 0)) {
                    isCompleteness = -1;
                }

                if (isTransitivity == 1 && matrix[i][j] != 0) {
                    for (int k = 1; k < n; k++) {
                        if (i != j && i != k && j != k && matrix[j][k] != 0 && matrix[i][k] == 0) {
                            isTransitivity = -1;
                        }
                    }
                }
            }
        }
        System.out.println(isCertainty);
        System.out.println(isBalance);
        System.out.println(isTransitivity);
        System.out.println(isCompleteness);
    }
}