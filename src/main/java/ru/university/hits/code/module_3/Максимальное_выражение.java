package ru.university.hits.code.module_3;

import java.util.Arrays;
import java.util.Scanner;

class Максимальное_выражение {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] set = new long[n];
        for (int i = 0; i < n; i++) {
            set[i] = sc.nextLong();
        }
        System.out.println(findMaxValue(set, n));
    }

    static long findMaxValue(long[] arr, int n) {
        if (n < 4) {
            return 0;
        }

        long table1[] = new long[n + 1];
        long table2[] = new long[n];
        long table3[] = new long[n - 1];
        long table4[] = new long[n - 2];

        Arrays.fill(table1, Integer.MIN_VALUE);
        Arrays.fill(table2, Integer.MIN_VALUE);
        Arrays.fill(table3, Integer.MIN_VALUE);
        Arrays.fill(table4, Integer.MIN_VALUE);

        for (int i = n - 1; i >= 0; i--) {
            table1[i] = Math.max(table1[i + 1], arr[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            table2[i] = Math.max(table2[i + 1],
                    table1[i + 1] - arr[i]);
        }

        for (int i = n - 3; i >= 0; i--)
            table3[i] = Math.max(table3[i + 1],
                    table2[i + 1] + arr[i]);

        for (int i = n - 4; i >= 0; i--)
            table4[i] = Math.max(table4[i + 1],
                    table3[i + 1] - arr[i]);

        return table4[0];
    }

}


