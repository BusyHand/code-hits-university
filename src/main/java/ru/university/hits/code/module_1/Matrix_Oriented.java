package ru.university.hits.code.module_1;
import java.io.IOException;
import java.util.Scanner;

class Matrix_Oriented {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] matrix = new int[n][n];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		System.out.println(isOrientation(matrix) ? "YES" : "NO");
	}

	private static boolean isOrientation(int[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix.length; j++) {

				if (matrix[i][j] != matrix[j][i]) {
					return true;
				}
			}
		}
		return false;
	}
}