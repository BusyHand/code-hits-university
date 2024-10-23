package ru.university.hits.code.module_2.алгоритмы_сортировки;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.min;

class Лексикографическая_сортировка_заданного_набора_слов {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] array = new String[n];

		for (int i = 0; i < n; i++) {
			array[i] = sc.next();
		}
		sort(array, 0, array.length - 1);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			sb.append(array[i]).append(' ');
		}
		System.out.println(sb);
	}

	private static void sort(String[] array, int left, int right) {

		if (right - left + 1 <= 3) {
			sortLessThanThreeElements(array, left, right);
			return;
		}
		String pivot = randomPivot(array, left, right);
		int partition = doPartition(array, left, right, pivot);
		sort(array, left, partition - 1);
		sort(array, partition + 1, right);
	}

	private static void sortLessThanThreeElements(String[] array, int left, int right) {
		int size = right - left + 1;

		if (size == 3) {
			if (compareWords(array[left], array[right]) > 0)
				swap(array, left, right);
			if (compareWords(array[left], array[right - 1]) > 0)
				swap(array, left, right - 1);
			if (compareWords(array[right - 1], array[right]) > 0)
				swap(array, right - 1, right);
		} else if (size == 2) {
			if (compareWords(array[left], array[right]) > 0)
				swap(array, left, right);
		}
	}

	public static String randomPivot(String[] array, int left, int right) {
		int random = ThreadLocalRandom.current().nextInt(left, right);
		swap(array, random, right);
		return array[right];
	}

	private static int doPartition(String[] array, int left, int right, String pivot) {
		int pivotIndex = right;
		left--;

		while (left < right) {
			while (compareWords(array[++left], pivot) < 0);
			while (right > 0 && compareWords(array[--right], pivot) > 0);

			if (left < right) {
				swap(array, left, right);
			}
		}
		swap(array, left, pivotIndex);
		return left;
	}

	private static void swap(String[] array, int first, int second) {
		String temp = array[first];
		array[first] = array[second];
		array[second] = temp;
	}

	private static int compareWords(String first, String second) {
		int length = min(first.length(), second.length());
		int i;

		for (i = 0; i < length; i++) {

			if (first.charAt(i) != second.charAt(i)) {
				return first.charAt(i) > second.charAt(i) ? 1 : -1;
			}
		}

		if (i == first.length() && i == second.length()) {
			return 0;
		}

		if (i == first.length()) {
			return -1;
		} else {
			return 1;
		}
	}
}