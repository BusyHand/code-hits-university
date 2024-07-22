package ru.university.hits.code.module_2;

import java.util.Scanner;

class Heap_sort {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] array = new long[n];

		for (int i = 0; i < n; i++) {
			array[i] = sc.nextLong();
		}
		sort(array);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			sb.append(array[i]).append(' ');
		}
		System.out.println(sb);
	}

	private static void sort(long[] array) {
		int mid = array.length / 2;

		for (int i = mid - 1; i >= 0; i--) {
			heapSort(array, array.length, i);
		}

		for (int i = array.length - 1; i > 0; i--) {
			swap(array, 0, i);
			heapSort(array, i, 0);
		}
	}

	private static void heapSort(long[] array, int n, int i) {
		int largest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left < n && array[left] > array[largest]) {
			largest = left;
		}

		if (right < n && array[right] > array[largest]) {
			largest = right;
		}

		if (largest != i) {
			swap(array, i, largest);
			heapSort(array, n, largest);
		}
	}

	private static void swap(long[] array, int first, int second) {
		long temp = array[first];
		array[first] = array[second];
		array[second] = temp;
	}
}