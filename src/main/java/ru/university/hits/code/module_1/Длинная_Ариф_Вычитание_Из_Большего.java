package ru.university.hits.code.module_1;

import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

class Длинная_Ариф_Вычитание_Из_Большего {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int max = max(n, m);
		int min = min(n, m);
		int[] firstNum = getArray(sc, max, min, n);
		int[] secondNum = getArray(sc, max, min, m);

		if (!isFirstBigerThanSecond(firstNum, secondNum)) {
			int[] temp = firstNum;
			firstNum = secondNum;
			secondNum = temp;
			sb.append('-');
		}
		int[] result = new int[max];

		for (int i = max - 1; i >= 0; i--) {
			int value = firstNum[i] - secondNum[i];

			if (value < 0) {
				value += 10;
				int j = i - 1;

				while (firstNum[j] == 0) {
					firstNum[j] = 9;
					j--;
				}
				firstNum[j] = firstNum[j] - 1;
			}
			result[i] = value;
		}
		int i = 0;
		for (; result[i] == 0 && i < max - 1; i++);

		for (; i < max; i++) {
			sb.append(result[i]);
		}
		System.out.println(sb);
	}

	private static boolean isFirstBigerThanSecond(int[] firstNum, int[] secondNum) {

		for (int i = 0; i < firstNum.length; i++) {

			if (firstNum[i] != secondNum[i]) {
				return firstNum[i] > secondNum[i] ? true : false;
			}
		}
		return true;
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
