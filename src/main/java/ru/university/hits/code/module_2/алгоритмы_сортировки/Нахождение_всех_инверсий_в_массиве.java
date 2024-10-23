package ru.university.hits.code.module_2.алгоритмы_сортировки;
import java.util.Scanner;

class Нахождение_всех_инверсий_в_массиве {

	private static long inversionCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] array = new long[n];

		for (int i = 0; i < n; i++) {
			array[i] = sc.nextLong();
		}
		sort(array, new long[n], 0, array.length - 1);
		System.out.println(inversionCount);
	}

	private static void sort(long[] array, long[] workSpace, int lowerBound, int upperBound) {

		if (lowerBound == upperBound)
			return;
		else {
			int mid = (lowerBound + upperBound) / 2;
			sort(array, workSpace, lowerBound, mid);
			sort(array, workSpace, mid + 1, upperBound);
			merge(array, workSpace, lowerBound, mid + 1, upperBound);
		}
	}

	private static void merge(long[] array, long[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int j = 0;
		int lowerBound = lowPtr;
		int mid = highPtr - 1;
		int n = upperBound - lowerBound + 1;

		while (lowPtr <= mid && highPtr <= upperBound) {

			if (array[lowPtr] <= array[highPtr]) {
				workSpace[j++] = array[lowPtr++];
			} else {
				inversionCount += (mid + 1) - lowPtr;
				workSpace[j++] = array[highPtr++];
			}
		}

		while (lowPtr <= mid) {
			workSpace[j++] = array[lowPtr++];
		}

		while (highPtr <= upperBound) {
			workSpace[j++] = array[highPtr++];
		}

		for (j = 0; j < n; j++) {
			array[lowerBound + j] = workSpace[j];
		}
	}
}