package ru.university.hits.code.module_1;

import java.util.Scanner;

/*
#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;
    vector<vector<int>> matrix(n, vector<int>(n, 0));
    int value = 1;

    for (int i = 0; i < n; i++) {
        for (int k = i, j = 0; j <= i; k--, j++) {
            matrix[k][j] = value++;
        }
    }

    int z = 1;
    while (value <= n * n) {
        for (int k = n - 1, i = z; i < n; k--, i++) {
            matrix[k][i] = value++;
        }
        z++;
    }

    for (const auto& row : matrix) {
        for (int el : row) {
            cout << el << ' ';
        }
        cout << '\n';
    }

    return 0;
}

 */
class Диагональный_обход_матрицы {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        int value = 1;

        for (int i = 0; i < n; i++) {

            for (int k = i, j = 0; j <= i; k--, j++) {
                matrix[k][j] = value++;
            }
        }
        int z = 1;

        while (value <= n * n) {

            for (int k = n - 1, i = z; i < n; k--, i++) {
                matrix[k][i] = value++;
            }
            z++;
        }
        StringBuilder sb = new StringBuilder();

        for (int[] v : matrix) {

            for (int el : v) {
                sb.append(el).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}