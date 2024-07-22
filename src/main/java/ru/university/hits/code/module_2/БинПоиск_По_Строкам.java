package ru.university.hits.code.module_2;

import java.util.Scanner;

import static java.lang.Math.min;

class БинПоиск_По_Строкам {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        StringBuilder sb = new StringBuilder();
        String[] array;
        array = sc.nextLine().split(" ");
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            sb.append(findWord(sc.next(), array) + 1).append('\n');
        }
        System.out.print(sb);
    }

    private static int findWord(String keyWord, String[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            String midValue = array[mid];
            int result = compareWords(midValue, keyWord);

            if (result == 0) {
                return mid;
            } else if (result < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -2;
    }

    private static int compareWords(String first, String second) {
        int length = min(first.length(), second.length());
        int i;

        for (i = 0; i < length; i++) {

            if (first.charAt(i) != second.charAt(i)) {
                return first.charAt(i) > second.charAt(i) ? 1 : -1;
            }
        }

        if (i == first.length() && i == second.length()) {
            return 0;
        }

        if (i == first.length()) {
            return -1;
        } else {
            return 1;
        }
    }
}
