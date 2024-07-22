package ru.university.hits.code.module_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Наибольшая_последовательность_едениц {

	public static void main(String[] args) throws IOException {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			br.readLine();
			String[] array = br.readLine().split(" ");
			int left = 0;
			boolean hasZero = false;
			int maxLength = Integer.MIN_VALUE;
			int index = 0;
			int indexZero = 0;

			for (int right = 0; right < array.length; right++) {
				int value = Integer.valueOf(array[right]);

				if (value == 0) {

					if (hasZero) {

						while (Integer.valueOf(array[left]) != 0) {
							left++;
						}
						left++;
					}
					hasZero = true;
					indexZero = right;
				}
				int length = right - left + 1;

				if (maxLength < length) {
					maxLength = length;
					index = indexZero;
				}
			}
			System.out.println(index + 1);
		}
	}
}