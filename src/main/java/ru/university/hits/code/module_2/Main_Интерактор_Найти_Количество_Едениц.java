package ru.university.hits.code.module_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main_Интерактор_Найти_Количество_Едениц {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		int index = find(1);
		int count = index != -1 ? (n - index + 1) : 0;
		System.out.println("! " + count);
		System.out.flush();
	}

	private static int find(int key) throws NumberFormatException, IOException {
		int left = 1;
		int right = n;
		int index = -1;

		while (left <= right) {
			int mid = (left + right) >>> 1;
			int midValue = sendQuery(mid);

			if (midValue == key) {
				index = mid;
				right = mid - 1;
			} else if (midValue < key) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return index;
	}

	private static int sendQuery(int index) throws NumberFormatException, IOException {
		System.out.println("? " + index);
		System.out.flush();
		int value = Integer.parseInt(br.readLine());
		return value;
	}
}