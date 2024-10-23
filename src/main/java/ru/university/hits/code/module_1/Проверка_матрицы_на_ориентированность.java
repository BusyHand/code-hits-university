package ru.university.hits.code.module_1;
import java.io.IOException;
import java.util.Scanner;
/*
#include <iostream>
#include <vector>

using namespace std;

bool isOrientation(const vector<vector<int>>& matrix) {
    int n = matrix.size();

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] != matrix[j][i]) {
                return true;
            }
        }
    }
    return false;
}

int main() {
    int n;
    cin >> n;
    vector<vector<int>> matrix(n, vector<int>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> matrix[i][j];
        }
    }

    cout << (isOrientation(matrix) ? "YES" : "NO") << endl;

    return 0;
}

 */
class Проверка_матрицы_на_ориентированность {

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