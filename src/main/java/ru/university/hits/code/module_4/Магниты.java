package ru.university.hits.code.module_4;

import java.util.Scanner;

import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

class Магниты {
    private static int M;
    private static int N;
    private static int[] top;
    private static int[] bottom;
    private static int[] left;
    private static int[] right;
    private static char[][] rules;
    private static char[][] result;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        M = s.nextInt();
        N = s.nextInt();
        top = new int[N];
        bottom = new int[N];
        left = new int[M];
        right = new int[M];
        rules = new char[M][N];
        result = new char[M][N];
        stream(result).forEach(t -> fill(t, 'X'));
        initArrays(s, M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++) rules[i][j] = s.next().toCharArray()[0];

        if (solve(0, 0)) {
            System.out.println(getStringResult());
        } else {
            System.out.println("Solution does not exist");
        }
    }

    private static String getStringResult() {
        StringBuilder sb = new StringBuilder();
        for (char[] result : result) {
            for (char ch : result) {
                sb.append(ch).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }


    private static boolean solve(int row, int column) {
        if (column == rules[0].length) {
            column = 0;
            row++;
        }
        if (row == rules.length) {
            return isResultValid();
        }

        if (rules[row][column] == 'L') {
            if (isSafe(row, column, '+') && isSafe(row, column + 1, '-')) {
                changeValue(row, column, row, column + 1, '+', '-');
                if (solve(row, column + 2)) {
                    return true;
                }
            } else if (isSafe(row, column, '-') && isSafe(row, column + 1, '+')) {
                changeValue(row, column, row, column + 1, '-', '+');
                if (solve(row, column + 2)) {
                    return true;
                }
            }
            changeValue(row, column, row, column + 1, 'X', 'X');

        } else if (rules[row][column] == 'T') {
            if (isSafe(row, column, '+') && isSafe(row + 1, column, '-')) {
                changeValue(row, column, row + 1, column, '+', '-');
                if (solve(row, column + 1)) {
                    return true;
                }
            } else if (isSafe(row, column, '-') && isSafe(row + 1, column, '+')) {
                changeValue(row, column, row + 1, column, '-', '+');
                if (solve(row, column + 1)) {
                    return true;
                }
            }
            changeValue(row, column, row + 1, column, 'X', 'X');
        }
        return solve(row, column + 1);
    }

    private static void changeValue(int row, int col, int rowChanged, int colChanged, char firstMetric, char secondMetric) {
        result[row][col] = firstMetric;
        result[rowChanged][colChanged] = secondMetric;
    }

    private static boolean isSafe(int row, int column, char ch) {
        if ((row - 1 >= 0 && result[row - 1][column] == ch) ||
                (column + 1 < N && result[row][column + 1] == ch) ||
                (row + 1 < M && result[row + 1][column] == ch) ||
                (column - 1 >= 0 && result[row][column - 1] == ch)) {
            return false;
        }

        int rowCount = rowCount(ch, row);
        int columnCount = columnCount(ch, column);
        if (ch == '+') {
            if (left[row] != -1 && rowCount >= left[row]) return false;
            return top[column] == -1 || columnCount < top[column];

        } else {
            if (right[row] != -1 && rowCount >= right[row]) return false;
            return bottom[column] == -1 || columnCount < bottom[column];
        }
    }

    public static int rowCount(char ch, int row) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (result[row][i] == ch) {
                count++;
            }
        }
        return count;
    }

    public static int columnCount(char ch, int column) {
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (result[i][column] == ch) {
                count++;
            }
        }
        return count;
    }

    private static boolean isResultValid() {

        for (int i = 0; i < N; i++) {
            int columnCount = columnCount('+', i);
            if (top[i] != -1 && top[i] != columnCount) {
                return false;
            }
        }
        for (int i = 0; i < N; i++) {
            int columnCount = columnCount('-', i);
            if (bottom[i] != -1 && bottom[i] != columnCount) {
                return false;
            }
        }
        for (int i = 0; i < M; i++) {
            int rowCount = rowCount('+', i);
            if (left[i] != -1 && left[i] != rowCount) {
                return false;
            }
        }
        for (int i = 0; i < M; i++) {
            int rowCount = rowCount('-', i);
            if (right[i] != -1 && right[i] != rowCount) {
                return false;
            }
        }
        return true;
    }

    private static void initArrays(Scanner s, int m, int n) {
        initArray(s, top, n);
        initArray(s, bottom, n);
        initArray(s, left, m);
        initArray(s, right, m);
    }

    private static void initArray(Scanner s, int[] array, int m) {
        for (int i = 0; i < m; i++) array[i] = s.nextInt();
    }
}
