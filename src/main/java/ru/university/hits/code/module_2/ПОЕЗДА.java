package ru.university.hits.code.module_2;

import java.util.Scanner;

import static java.lang.Math.min;

class ПОЕЗДА {

	private static int[] time;

	private static int[] sumTime;

	private static int[] speed;

	private static int[] sumDistance;

	private static double maxTime;

	private static int L;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		int n = sc.nextInt();
		time = new int[n];
		speed = new int[n];
		sumTime = new int[n];
		sumDistance = new int[n];
		time[0] = sc.nextInt();
		speed[0] = sc.nextInt();
		maxTime += time[0];
		sumTime[0] = time[0];
		sumDistance[0] = speed[0] * time[0];

		for (int i = 1; i < n; i++) {
			time[i] = sc.nextInt();
			speed[i] = sc.nextInt();
			maxTime += time[i];
			sumTime[i] = time[i] + sumTime[i - 1];
			sumDistance[i] = speed[i] * time[i] + sumDistance[i - 1];
		}
		System.out.printf("%.3f", find());
	}

	private static double find() {
		double left = 0;
		double right = maxTime;
		double e = 1e-3;
		double lastResult = maxTime;
		double minLenght = Integer.MAX_VALUE;

		while (right - left > e) {
			double mid = (right + left) / 2;
			double lenght = getMinLenght(mid);

			if (lenght >= L && lenght < minLenght) {
				lastResult = mid;
				right = mid;
				minLenght = lenght;
			} else if (lenght >= L) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return lastResult;
	}

	private static double getMinLenght(double mid) {
		double firstSumTime = 0;
		int firstSumDistance = 0;
		int i = 0;

		for (; i < time.length && sumTime[i] < mid; i++) {
			firstSumDistance += speed[i] * time[i];
		}
		firstSumDistance += speed[i] * (i > 0 ? (mid - sumTime[i - 1]) : mid);
		firstSumTime = mid;
		double maxDistance = firstSumDistance;
		double secondSumTime = 0;
		double secondSumDistance = 0;
		int j = 0;

		while (i < time.length) {
			double firstNextTime = sumTime[i] - firstSumTime;
			double secondNextTime = sumTime[j] - secondSumTime;

			if (firstNextTime == secondNextTime) {
				firstSumTime = sumTime[i];
				secondSumTime = sumTime[j];
				firstSumDistance = sumDistance[i];
				secondSumDistance = sumDistance[j];
				i++;
				j++;
			} else if (firstNextTime < secondNextTime) {
				firstSumTime = sumTime[i];
				firstSumDistance = sumDistance[i];
				secondSumDistance += firstNextTime * speed[j];
				secondSumTime += firstNextTime;
				i++;
			} else {
				secondSumTime = sumTime[j];
				secondSumDistance = sumDistance[j];
				firstSumDistance += secondNextTime * speed[i];
				firstSumTime += secondNextTime;
				j++;
			}
			maxDistance = min(firstSumDistance - secondSumDistance, maxDistance);
		}
		return maxDistance;
	}
}