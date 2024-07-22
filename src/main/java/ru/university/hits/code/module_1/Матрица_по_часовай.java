package ru.university.hits.code.module_1;
import java.util.Scanner;

class Матрица_по_часовай {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String[][] matrix = new String[n][m];
		sc.nextLine();

		for (int i = 0; i < n; i++) {
			matrix[i] = sc.nextLine().split(" ");
		}
		System.out.println(toString(matrix, n, m));
	}

	private static String toString(String[][] matrix, int n, int m) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j = 0;
		int offsetLeft = 0;
		int offsetUp = 0;
		int count = 0;
		int size = n * m;

		while (count < size) {

			while (j < m - offsetLeft) {
				count++;
				sb.append(matrix[i][j]).append(' ');
				j++;
			}
			j--;
			i++;

			while (i < n - offsetUp) {
				count++;
				sb.append(matrix[i][j]).append(' ');
				i++;
			}
			i--;
			j--;

			while (j >= offsetLeft) {
				count++;
				sb.append(matrix[i][j]).append(' ');
				j--;
			}
			j++;
			i--;

			while (i > offsetUp) {
				count++;
				sb.append(matrix[i][j]).append(' ');
				i--;
			}
			i++;
			j++;
			offsetUp++;
			offsetLeft++;
		}
		return sb.toString();
	}
}