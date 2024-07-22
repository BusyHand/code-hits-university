package ru.university.hits.code.module_1;

import java.util.Scanner;

import static java.lang.Math.*;

class Уравнение {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();

		if (a == 0) {

			if (b != 0) {
				System.out.print(1 + " " + -c / b);
			} else if (c != 0) {
				System.out.println(0);
			} else {
				System.out.println(-1);
			}
		} else {
			int d = b * b - 4 * a * c;

			if (d == 0) {
				System.out.print(1 + " " + -b / (2 * a));
			} else if (d > 0) {
				double x1 = (-b - sqrt(d)) / (2 * a);
				double x2 = (-b + sqrt(d)) / (2 * a);
				System.out.println(2 + " " + min(x1, x2) + " " + max(x1, x2));
			} else {
				System.out.println(0);
			}
		}
	}
}
