package ru.university.hits.code.module_2;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Количество_Циклических_Сдвигов {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int n;

	private static int[] cache;

	private static boolean[] isPresentInCache;

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		cache = new int[n + 1];
		isPresentInCache = new boolean[n + 1];
		System.out.println("! " + countRotations(n));
	}

	public static int countRotations(int n) throws Exception {
		int left = 1;
		int right = n;

		while (getQuery(left) > getQuery(right)) {
			int middle = (left + right) / 2;

			if (getQuery(middle) > getQuery(right)) {
				left = middle + 1;
			} else {
				right = middle;
			}
		}
		return left - 1;
	}

	private static int getQuery(int index) throws Exception {

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