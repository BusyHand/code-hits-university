package ru.university.hits.code.module_1;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.max;

class Кузнечик {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] grass = new long[n];
		long[] jumps = new long[n];

		for (int i = 0; i < n; i++) {
			grass[i] = sc.nextInt();
		}

		if (n < 3) {

			switch (n) {
			case 0:
				System.out.println(0);
				break;

			case 1:
			case 2:
				System.out.println(grass[0]);
				break;
			}
			return;
		}
		jumps[0] = grass[0];
		jumps[2] = grass[2] + grass[0];
		grass[1] = Long.MIN_VALUE;
		jumps[1] = Long.MIN_VALUE;

		for (int i = 3; i < n; i++) {
			jumps[i] = max(jumps[i - 2], jumps[i - 3]) + grass[i];
		}
		System.out.println(jumps[n - 1]);
	}
}