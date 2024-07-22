package ru.university.hits.code.module_1;

import java.util.Scanner;

import static java.lang.Math.max;

class КМП_Поиск {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] line = sc.nextLine().toCharArray();
		char[] subLine = sc.nextLine().toCharArray();
		System.out.println(kmpSearch(line, subLine));
	}

	private static String kmpSearch(char[] line, char[] subline) {
		int[] prefixFunc = getPrefixFunc(subline);
		int i = 0;
		int j = 0;

		while (i < line.length) {

			if (subline[j] == line[i]) {
				j++;
				i++;
			}

			if (j == subline.length) {
				return toString(line, i - j, i);
			} else if (i < line.length && subline[j] != line[i]) {

				if (j != 0) {
					j = prefixFunc[j - 1];
				} else {
					i = i + 1;
				}
			}
		}
		return "";
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

	private static String toString(char[] chars, int left, int right) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < chars.length; i++) {

			if (i < left || i >= right) {
				sb.append(chars[i]);
			}
		}
		return sb.toString();
	}
}