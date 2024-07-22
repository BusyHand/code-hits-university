package ru.university.hits.code.module_1;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Решето́ Эратосфе́на — алгоритм нахождения всех простых чисел, 
 * не превышающих некоторое натуральное число n.
 */
class Найти_первые_n_простых_чисел {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		boolean[] primes = new boolean[15485864];
		int count = 0;
		StringBuilder sb = new StringBuilder();
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;

		for (int i = 2; i < primes.length; ++i) {

			if (primes[i]) {
				count++;
				sb.append(i).append(' ');

				if (count >= n) {
					System.out.println(sb);
					return;
				}

				for (int j = 2; i * j < primes.length; j++) {
					primes[i * j] = false;
				}
			}
		}
	}
}