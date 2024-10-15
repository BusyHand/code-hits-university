package ru.university.hits.code.module_2;
import java.util.Scanner;

class Вхождение_И_Конец_Интервала {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n];

		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		int m = sc.nextInt();

		for (int i = 0; i < m; i++) {
			getInterval(sc.nextInt(), array);
		}
	}

	private static void getInterval(int key, int[] array) {
		int index = findIndex(key, array);

		if (index == -1) {
			System.out.println("Not found");
			return;
		}
		int left = index;
		int right = index;
		while (left - 1 >= 0 && array[left - 1] == key) left--;
		while (right + 1 < array.length && array[right + 1] == key) right++;
		System.out.println((left + 1) + " " + (right + 1));
	}

	private static int findIndex(int key, int[] array) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			int midValue = array[mid];

			if (midValue == key) {
				return mid;
			} else if (midValue < key) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}
}