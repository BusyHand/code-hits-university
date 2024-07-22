package ru.university.hits.code.module_3;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

//https://www.youtube.com/watch?v=HQwwMCVeGqs - решение
class Китайские_часы {

    private static final int SECOND_OF_DAY = 12 * 60 * 60;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] time = new int[n];
        for (int i = 0; i < n; i++) {
            String timeString = sc.next();
            String[] parts = timeString.split(":");

            time[i] = parseInt(parts[0]) * 60 * 60 + parseInt(parts[1]) * 60 + parseInt(parts[2]);
        }
        Arrays.sort(time);
        int t = time[0];
        int delta = time[n - 1] - time[0];
        long ans = (n - 1) * delta - (SECOND_OF_DAY - delta);
        long min = ans;
        for (int i = 1; i < n; i++) {
            delta = time[i] - time[i - 1];
            ans += (n - 1) * delta - (SECOND_OF_DAY - delta);
            if (ans < min) {
                min = ans;
                t = time[i];
            }
        }
        System.out.print(String.format("%d:%02d:%02d", t / 60 / 60, t / 60 % 60, t % 60) + " ");
    }
}