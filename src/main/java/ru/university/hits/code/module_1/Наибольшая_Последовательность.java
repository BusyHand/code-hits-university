package ru.university.hits.code.module_1;
import java.util.Scanner;

class Наибольшая_Последовательность {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] array = new long[n];
		int size = 0;

		for (int i = 0; i < n; i++) {
			long value = sc.nextLong();
			int j = 0;

			while (j < size && isBigger(array[j], value)) {
				j++;
			}
			long prev = array[j];

			for (int k = j + 1; k < size + 1; k++) {
				long current = array[k];
				array[k] = prev;
				prev = current;
			}
			array[j] = value;
			size++;
		}
		int i = 0;

		while (array[i] == 0 && i < n - 1) {
			i++;
		}

		for (; i < n; i++) {
			sb.append(array[i]);
		}
		System.out.println(sb);
	}

	private static boolean isBigger(long a, long b) {
		long aWithB = getUnitedNums(a, b);
		long bWithA = getUnitedNums(b, a);
		return aWithB > bWithA;
	}

	private static long getUnitedNums(long a, long b) {

		if (a == 0) {
			return b;
		}

		if (b == 0) {
			return a * 10;
		}

		while (b > 0) {
			a = a * 10 + b % 10;
			b /= 10;
		}
		return a;
	}
}