package ru.university.hits.code.module_1;
import java.util.Scanner;

class Бинарный_поиск {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		int m = sc.nextInt();

		for (int i = 0; i < m; i++) {
			int value = sc.nextInt();
			sb.append(binarySearch(array, value)).append('\n');
		}
		System.out.println(sb);
	}

	private static int binarySearch(int[] array, int key) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int mid = (left + right) >>> 1;
			int value = array[mid];

			if (array[mid] == key) {
				return mid + 1;
			} else if (key > value) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}
}