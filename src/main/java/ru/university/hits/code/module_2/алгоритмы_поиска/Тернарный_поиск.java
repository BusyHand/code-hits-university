package ru.university.hits.code.module_2.алгоритмы_поиска;

import java.util.Scanner;

import static java.lang.Math.sqrt;

class Тернарный_поиск {

	private static int vp;

	private static int vf;

	private static double a;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		vp = sc.nextInt();
		vf = sc.nextInt();
		a = sc.nextDouble();
		double l = 0;
		double r = 1;
		double e = 1e-8;

		while (r - l > e) {
			double m1 = l + (r - l) / 3;
			double m2 = r - (r - l) / 3;
			double t1 = function(m1);
			double t2 = function(m2);

			if (t1 > t2) {
				l = m1;
			} else {
				r = m2;
			}
		}
		System.out.println((l + r) / 2);
	}

	private static double function(double x) {
		return sqrt(x * x + (1 - a) * (1 - a)) / vp +
			sqrt(a * a + (1 - x) * (1 - x)) / vf;
	}
}