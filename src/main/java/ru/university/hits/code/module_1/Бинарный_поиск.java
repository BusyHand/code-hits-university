package ru.university.hits.code.module_1;
import java.util.Scanner;
/*
#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

int main() {
    string line;
    getline(cin, line);
    getline(cin, line);

    stringstream ss(line);
    vector<int> array;
    int num;

    while (ss >> num) {
        array.push_back(num);
    }

    int left = 0;
    bool hasZero = false;
    int maxLength = 0;
    int index = 0;
    int indexZero = 0;

    for (int right = 0; right < array.size(); right++) {
        int value = array[right];

        if (value == 0) {
            if (hasZero) {
                while (array[left] != 0) {
                    left++;
                }
                left++;
            }
            hasZero = true;
            indexZero = right;
        }

        int length = right - left + 1;
        if (maxLength < length) {
            maxLength = length;
            index = indexZero;
        }
    }

    cout << index + 1 << endl;

    return 0;
}

 */
class Бинарный_поиск {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		int m = sc.nextInt();

		for (int i = 0; i < m; i++) {
			int value = sc.nextInt();
			sb.append(binarySearch(array, value)).append('\n');
		}
		System.out.println(sb);
	}

	private static int binarySearch(int[] array, int key) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int mid = (left + right) >>> 1;
			int value = array[mid];

			if (array[mid] == key) {
				return mid + 1;
			} else if (key > value) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}
}