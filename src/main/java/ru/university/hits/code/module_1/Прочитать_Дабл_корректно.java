package ru.university.hits.code.module_1;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

class Прочитать_Дабл_корректно {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		double n = sc.nextDouble();

		if ((int) n == n) {
			System.out.println((int) n);
		} else {
			System.out.println((int) n + 1);
		}
	}
}