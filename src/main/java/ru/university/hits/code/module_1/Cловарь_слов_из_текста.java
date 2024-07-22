package ru.university.hits.code.module_1;
import java.util.Scanner;

class Cловарь_слов_из_текста {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[][] uniqset = new String[10][n];

		for (int i = 0; i < n; i++) {
			String word = sc.next();
			String[] strings = uniqset[word.length() % uniqset.length];
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