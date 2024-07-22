package ru.university.hits.code.module_2;
import java.util.Scanner;

class Сортировка_По_нескольким_кретериям {

	private static class Team {

		int number;

		int tasks;

		int wrongCount;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Team[] teams = new Team[n];

		for (int i = 0; i < n; i++) {
			Team team = new Team();
			team.number = i + 1;
			team.tasks = sc.nextInt();
			team.wrongCount = sc.nextInt();
			teams[i] = team;
		}
		sort(teams, new Team[n], 0, teams.length - 1);
		StringBuilder sb = new StringBuilder();

		for (int i = n - 1; i >= 0; i--) {
			sb.append(teams[i].number).append(' ');
		}
		System.out.println(sb);
	}

	private static void sort(Team[] array, Team[] workSpace, int lowerBound, int upperBound) {

		if (lowerBound == upperBound)
			return;
		else {
			int mid = (lowerBound + upperBound) / 2;
			sort(array, workSpace, lowerBound, mid);
			sort(array, workSpace, mid + 1, upperBound);
			merge(array, workSpace, lowerBound, mid + 1, upperBound);
		}
	}

	private static void merge(Team[] array, Team[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int j = 0;
		int lowerBound = lowPtr;
		int mid = highPtr - 1;
		int n = upperBound - lowerBound + 1;

		while (lowPtr <= mid && highPtr <= upperBound) {

			if (compare(array[highPtr], array[lowPtr])) {
				workSpace[j++] = array[lowPtr++];
			} else
				workSpace[j++] = array[highPtr++];
		}

		while (lowPtr <= mid) {
			workSpace[j++] = array[lowPtr++];
		}

		while (highPtr <= upperBound) {
			workSpace[j++] = array[highPtr++];
		}

		for (j = 0; j < n; j++) {
			array[lowerBound + j] = workSpace[j];
		}
	}

	private static boolean compare(Team a, Team b) {

		if (a.tasks == b.tasks) {

			if (a.wrongCount == b.wrongCount) {
				return a.number < b.number;
			}
			return a.wrongCount < b.wrongCount;
		}
		return a.tasks > b.tasks;
	}
}