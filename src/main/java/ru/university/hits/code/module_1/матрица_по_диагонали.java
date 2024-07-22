package ru.university.hits.code.module_1;

import java.util.Scanner;

class матрица_по_диагонали {

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