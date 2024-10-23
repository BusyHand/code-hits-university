package ru.university.hits.code.module_2.алгоритмы_поиска;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Нахождение_элемента_в_массиве_который_больше_своих_соседей {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int n;

	private static int[] cache;

	private static boolean[] isPresentInCache;

	private static int count = 0;

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		cache = new int[n + 1];
		isPresentInCache = new boolean[n + 1];
		System.out.println("! " + findElement(n));
	}

	public static int findElement(int n) throws Exception {
		int left = 1;
		int right = n;

		if (getQuery(left) > getQuery(left + 1)) {
			return left;
		}

		if (getQuery(right) > getQuery(right - 1)) {
			return right;
		}

		while (left < right) {
			int middle = (left + right) / 2;

			if (getQuery(middle) > getQuery(middle - 1)
				&& getQuery(middle) > getQuery(middle + 1)) {
				return middle;
			} else if (getQuery(middle) < getQuery(middle - 1)) {
				right = middle;
			} else {
				left = middle;
			}
		}
		return left;
	}

	private static int getQuery(int index) throws Exception {

		if (isPresentInCache[index]) {
			return cache[index];
		} else {
			count++;

			if (count == 50) {
				throw new RuntimeException();
			}
			System.out.println("? " + index);
			System.out.flush();
			int value = Integer.parseInt(br.readLine());
			cache[index] = value;
			isPresentInCache[index] = true;
			return value;
		}
	}
}