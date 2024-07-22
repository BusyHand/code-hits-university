package ru.university.hits.code.module_1;

import java.util.Scanner;

import static java.lang.Math.max;

class Замена_подстрок_в_строке {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		char[] lineArray = line.toCharArray();
		String firstWord = sc.nextLine();
		String secondWord = sc.nextLine();
		int firstIndex = kmpSearch(lineArray, firstWord.toCharArray());
		int secondIndex = kmpSearch(lineArray, secondWord.toCharArray());

		if (firstIndex > secondIndex) {
			int tempI = firstIndex;
			firstIndex = secondIndex;
			secondIndex = tempI;
			String tempS = firstWord;
			firstWord = secondWord;
			secondWord = tempS;
		}
		sb.append(line.substring(0, firstIndex))
			.append(secondWord)
			.append(line.substring(firstIndex + firstWord.length(), secondIndex))
			.append(firstWord)
			.append(line.substring(secondIndex + secondWord.length(), line.length()));
		System.out.println(sb);
	}

	private static int kmpSearch(char[] line, char[] subline) {
		int[] prefixFunc = getPrefixFunc(subline);
		int i = 0;
		int j = 0;

		while (i < line.length) {

			if (subline[j] == line[i]) {
				j++;
				i++;
			}

			if (j == subline.length) {
				return i - subline.length;
			} else if (i < line.length && subline[j] != line[i]) {

				if (j != 0) {
					j = prefixFunc[j - 1];
				} else {
					i = i + 1;
				}
			}
		}
		return -1;
	}

	private static int[] getPrefixFunc(char[] subLine) {
		int[] prefixFunc = new int[subLine.length];

		for (int i = 1; i < subLine.length; i++) {
			int j = 0;

			while (i + j < subLine.length && subLine[i] == subLine[i + j]) {
				prefixFunc[i + j] = max(prefixFunc[i + j], j + 1);
				j++;
			}
		}
		return prefixFunc;
	}
}
