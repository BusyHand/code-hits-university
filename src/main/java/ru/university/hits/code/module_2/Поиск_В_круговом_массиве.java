package ru.university.hits.code.module_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Поиск_В_круговом_массиве {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int n;

	private static int[] cache;

	private static boolean[] isPresentInCache;

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		cache = new int[n + 1];
		isPresentInCache = new boolean[n + 1];
		int m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			int index = find(Integer.parseInt(br.readLine()));
			System.out.println("! " + index);
			System.out.flush();
		}
	}

	private static int find(int key) throws NumberFormatException, IOException {
		int left = 1;
		int right = n;

		while (left <= right) {
			int mid = (left + right) >>> 1;
			int midValue = getQuery(mid);

			if (midValue == key) {
				return mid;
			} else if (key < midValue) {
				int leftValue = getQuery(left);

				if (leftValue <= midValue && leftValue > key) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				int rightValue = getQuery(right);

				if (midValue <= rightValue && key > rightValue) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		return -1;
	}

	private static int getQuery(int index) throws NumberFormatException, IOException {

		if (isPresentInCache[index]) {
			return cache[index];
		} else {
			System.out.println("? " + index);
			System.out.flush();
			int value = Integer.parseInt(br.readLine());
			cache[index] = value;
			isPresentInCache[index] = true;
			return value;
		}
	}
}