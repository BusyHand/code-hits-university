package ru.university.hits.code.module_2.алгоритмы_поиска;
import java.util.Scanner;

class Нахождение_первого_и_последнего_вхождения_заданного_числа_в_массив {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n];

		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		int m = sc.nextInt();
		int[] query = new int[m];

		for (int i = 0; i < m; i++) {
			query[i] = sc.nextInt();
		}

		for (int key : query) {
			int left = getLeft(key, array);

			if (left == -1) {
				System.out.println("Not found");
				continue;
			}
			int right = getRight(key, array);
			System.out.println((left + 1) + " " + (right + 1));
		}
	}

	private static int getLeft(int key, int[] array) {
		int left = 0;
		int right = array.length - 1;
		int index = -1;

		while (left <= right) {
			int mid = (left + right) / 2;
			int midValue = array[mid];

			if (midValue == key) {
				index = mid;
				right = mid - 1;
			} else if (midValue < key) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return index;
	}

	private static int getRight(int key, int[] array) {
		int left = 0;
		int right = array.length - 1;
		int index = -1;

		while (left <= right) {
			int mid = (left + right) / 2;
			int midValue = array[mid];

			if (midValue == key) {
				index = mid;
				left = mid + 1;
			} else if (midValue < key) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return index;
	}
}