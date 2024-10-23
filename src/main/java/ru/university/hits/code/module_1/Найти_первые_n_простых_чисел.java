package ru.university.hits.code.module_1;
import java.util.Arrays;
import java.util.Scanner;

/*
Решето́ Эратосфе́на — алгоритм нахождения всех простых чисел,
не превышающих некоторое натуральное число n.

#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

int main() {
    int n;
    cin >> n;
    const int MAX = 15485864;
    vector<bool> primes(MAX, true);
    int count = 0;
    string result;

    primes[0] = false;
    primes[1] = false;

    for (int i = 2; i < MAX; ++i) {
        if (primes[i]) {
            count++;
            result += to_string(i) + ' ';

            if (count >= n) {
                cout << result << endl;
                return 0;
            }

            for (int j = 2; i * j < MAX; j++) {
                primes[i * j] = false;
            }
        }
    }

    return 0;
}

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