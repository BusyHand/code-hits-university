package ru.university.hits.code.module_1;

import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

class Длинная_Ариф_Сложение {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int max = max(n, m);
		int min = min(n, m);
		int[] firstNum = getArray(sc, max, min, n);
		int[] secondNum = getArray(sc, max, min, m);
		int[] result = new int[max + 1];
		int remainder = 0;

		for (int i = max - 1; i >= 0; i--) {
			int sum = firstNum[i] + secondNum[i] + remainder;
			int value;

			if (sum > 9) {
				value = sum % 10;
				remainder = 1;
			} else {
				value = sum;
				remainder = 0;
			}
			result[i + 1] = value;
		}
		int k = 1;

		if (remainder != 0) {
			result[0] = remainder;
			k = 0;
		}

		for (int i = k; i < max + 1; i++) {
			sb.append(result[i]);
		}
		System.out.println(sb);
	}

	private static int[] getArray(Scanner sc, int max, int min, int size) {
		int[] array = new int[max];
		int i = max != size ? max - min : 0;

		for (; i < max; i++) {
			array[i] = sc.nextInt();
		}
		return array;
	}
}
