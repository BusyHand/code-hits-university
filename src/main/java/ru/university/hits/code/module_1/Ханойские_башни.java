package ru.university.hits.code.module_1;
import java.util.Scanner;

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