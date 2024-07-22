package ru.university.hits.code.module_2;

import java.util.Scanner;

import static java.lang.Math.round;

/*
#include <iostream>
#include <cmath>
using namespace std;

double a;
double b;
double c;
double d;

double calculate(double x) {
    return (a * x * x * x) + (b * x * x) + (c * x) + d;
}

int main() {
    cin >> a;
    double m = a > 0 ? 1 : -1;
    cin >> b;
    cin >> c;
    cin >> d;
    a = a * m;
    b = b * m;
    c = c * m;
    d = d * m;
    double left = -1000;
    double right = 10000;
    while (left != right) {
        double mid = (left + right) / 2;
        double result = calculate(mid);

        if (result == 0) {
            cout << mid << endl;
            return 0;
        }
        else if (result < 0) {
            left = mid;
        }
        else {
            right = mid;
        }
    }
    return 0;
}
 */
class Кубическое_Спомощью_Бинарного_Поиска {

	private static double a;

	private static double b;

	private static double c;

	private static double d;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextDouble();
		int m = a > 0 ? 1 : -1;
		a = a * m;
		b = sc.nextDouble() * m;
		c = sc.nextDouble() * m;
		d = sc.nextDouble() * m;
		double left = -10000;
		double right = 10000;

		while (left != right) {
			double mid = ((long) (((left + right) / 2) * 1000000)) / 1000000d;
			double result = calculate(mid);

			if (result == 0) {
				System.out.println(round(mid));
				return;
			} else if (result < 0) {
				left = mid;
			} else {
				right = mid;
			}
		}
	}

	private static double calculate(double x) {
		return (a * x * x * x) + (b * x * x) + (c * x) + d;
	}
}