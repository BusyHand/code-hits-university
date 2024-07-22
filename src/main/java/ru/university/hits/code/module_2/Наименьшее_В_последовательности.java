package ru.university.hits.code.module_2;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Наименьшее_В_последовательности {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int n;

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		System.out.println("! " + find());
		System.out.flush();
	}

	private static int find() throws Exception {
		int left = 1;
		int right = n;

		while (left < right) {
			int mid = (left + right) >>> 1;
			int midValue = sendQuery(mid);
			int result = mid - (midValue + 1);

			if (result == 0) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return sendQuery(right - 1) + 1;
	}

	private static int sendQuery(int index) throws Exception {

		if (index == 0) {
			return -1;
		}
		System.out.println("? " + index);
		System.out.flush();
		int value = Integer.parseInt(br.readLine());
		return value;
	}
}