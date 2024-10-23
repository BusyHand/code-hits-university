package ru.university.hits.code.module_1;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.max;
/*
#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

int main() {
    int n;
    cin >> n;

    vector<long> grass(n);
    vector<long> jumps(n, 0);

    for (int i = 0; i < n; i++) {
        cin >> grass[i];
    }

    if (n < 3) {
        switch (n) {
            case 0:
                cout << 0 << endl;
                return 0;

            case 1:
            case 2:
                cout << grass[0] << endl;
                return 0;
        }
    }

    jumps[0] = grass[0];
    jumps[1] = numeric_limits<long>::lowest();
    jumps[2] = grass[2] + grass[0];

    for (int i = 3; i < n; i++) {
        jumps[i] = max(jumps[i - 2], jumps[i - 3]) + grass[i];
    }

    cout << jumps[n - 1] << endl;

    return 0;
}
 */
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