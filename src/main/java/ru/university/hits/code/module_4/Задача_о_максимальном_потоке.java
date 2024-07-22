package ru.university.hits.code.module_4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;

class Задача_о_максимальном_потоке {

    private static int[][] matrix;
    private static int minValue;
    private static int start;
    private static int end;
    private static int[] path;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        matrix = new int[n][n];
        path = new int[n];
        start = 0;
        end = n - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int result = 0;
        while (true) {
            int[] distance = new int[n];
            Arrays.fill(distance, MAX_VALUE);
            distance[0] = 0;

            Arrays.fill(path, -1);
            minValue = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                int i = queue.remove();
                if (i == end) break;
                for (int j = 0; j < n; j++)
                    if (matrix[i][j] > 0 && distance[j] == MAX_VALUE) {
                        distance[j] = distance[i] + 1;
                        queue.add(j);
                        path[j] = i;
                    }
            }

            DFSMinEdgeValue(end, MAX_VALUE);
            if (minValue == 0) {
                break;
            }
            result += minValue;

        }
        System.out.print(result);

    }

    private static void DFSMinEdgeValue(int i, int min) {
        if (i == start) {
            minValue = min;
            return;
        } else if (path[i] != -1) {
            DFSMinEdgeValue(path[i], Math.min(min, matrix[path[i]][i]));
            matrix[path[i]][i] -= minValue;
            matrix[i][path[i]] += minValue;
        }

    }

}