package ru.university.hits.code.module_2.алгоритмы_сортировки;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.min;

class Составление_максимального_числа_из_набора_цифр {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] array = new String[n];

		for (int i = 0; i < n; i++) {
			array[i] = sc.next();
		}
		sort(array, 0, array.length - 1);
		StringBuilder sb = new StringBuilder();

		for (int i = n - 1; i >= 0; i--) {
			sb.append(array[i]);
		}
		System.out.println(sb);
	}

	private static void sort(String[] array, int left, int right) {

		if (right - left <= 0) {
			return;
		}
		String pivot = randomPivot(array, left, right);
		int partition = doPartition(array, left, right, pivot);
		sort(array, left, partition - 1);
		sort(array, partition + 1, right);
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
			while (compareNums(array[++left] + pivot, pivot + array[left]) < 0);
			while (right > 0 &&
				compareNums(array[--right] + pivot, pivot + array[right]) > 0);

			if (left < right) {
				swap(array, left, right);
			}
		}
		swap(array, left, pivotIndex);
		return left;
	}

	private static int compareNums(String first, String second) {
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

	private static void swap(String[] array, int first, int second) {
		String temp = array[first];
		array[first] = array[second];
		array[second] = temp;
	}
}