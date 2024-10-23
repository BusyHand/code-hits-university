package ru.university.hits.code.module_1;
import java.util.Scanner;
/*
#include <iostream>
#include <string>

using namespace std;

int main() {
    int n;
    cin >> n;
    const int UNIQUE_SET_SIZE = 10;
    const int MAX_WORDS_PER_LENGTH = n;
    string uniqueSet[UNIQUE_SET_SIZE][MAX_WORDS_PER_LENGTH] = {};

    for (int i = 0; i < n; i++) {
        string word;
        cin >> word;
        int index = word.length() % UNIQUE_SET_SIZE;
        bool isDuplicate = false;

        for (int j = 0; j < MAX_WORDS_PER_LENGTH; j++) {
            if (uniqueSet[index][j] == word) {
                isDuplicate = true;
                break;
            } else if (uniqueSet[index][j].empty()) {
                uniqueSet[index][j] = word;
                break;
            }
        }

        if (!isDuplicate) {
            cout << word << " ";
        }
    }

    cout << endl;
    return 0;
}

 */
class Cловарь_слов_из_текста {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[][] uniqueSet = new String[10][n];

		for (int i = 0; i < n; i++) {
			String word = sc.next();
			String[] strings = uniqueSet[word.length() % uniqueSet.length];
			boolean isDuplicate = false;

			for (int j = 0; j < strings.length; j++) {

				if (word.equals(strings[j])) {
					isDuplicate = true;
					break;
				} else if (strings[j] == null) {
					strings[j] = word;
					break;
				}
			}

			if (!isDuplicate) {
				System.out.print(word + " ");
			}
		}
	}
}