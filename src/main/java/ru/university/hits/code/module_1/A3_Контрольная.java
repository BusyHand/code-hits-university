package ru.university.hits.code.module_1;

import java.util.Scanner;

import static java.lang.Math.min;
/*
 Нужно вывести мин кол шагов 
 Если буквы не равны цена 1 иначе цена 0
 
 Intput
 10 2
 ABABBCACBC
 
 Output
 2
 */

class A3_Контрольная {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		char[] array = sc.next().toCharArray();
		int[] jumps = new int[n];
		jumps[0] = 0;

		for (int i = 1; i < n; i++) {
			int bestMin = Integer.MAX_VALUE;
			jumps[i] = Integer.MAX_VALUE;

			for (int j = i; j > 0 && j > i - k; j--) {
				int value = jumps[j - 1];

				if (array[i] != array[j - 1]) {
					value++;
				}
				bestMin = min(value, jumps[i]);
			}
			jumps[i] = bestMin;
		}
		System.out.println(jumps[n - 1]);
	}
}