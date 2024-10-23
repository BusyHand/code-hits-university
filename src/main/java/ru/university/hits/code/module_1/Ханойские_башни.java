package ru.university.hits.code.module_1;
import java.util.Scanner;
/*
#include <iostream>
#include <sstream>

using namespace std;

void getTowerOfHanoi(int upperDisk, int fromT, int tempT, int toT, stringstream& result) {
    if (upperDisk == 1) {
        result << "1 " << fromT << " " << toT << "\n";
    } else {
        getTowerOfHanoi(upperDisk - 1, fromT, toT, tempT, result);
        result << upperDisk << " " << fromT << " " << toT << "\n";
        getTowerOfHanoi(upperDisk - 1, tempT, fromT, toT, result);
    }
}

int main() {
    int n;
    cin >> n;
    stringstream sb;
    getTowerOfHanoi(n, 1, 2, 3, sb);
    cout << sb.str();

    return 0;
}

 */
class Ханойские_башни {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		getTowerOfHanoi(sc.nextInt(), 1, 2, 3);
		System.out.println(sb);
	}

	public static void getTowerOfHanoi(int upperDisk, int fromT, int tempT, int toT) {

		if (upperDisk == 1)
			sb.append("1 " + fromT + " " + toT + "\n");
		else {
			getTowerOfHanoi(upperDisk - 1, fromT, toT, tempT);
			sb.append(upperDisk + " " + fromT + " " + toT + "\n");
			getTowerOfHanoi(upperDisk - 1, tempT, fromT, toT);
		}
	}
}