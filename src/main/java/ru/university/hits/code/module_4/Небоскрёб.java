package ru.university.hits.code.module_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

class Небоскрёб {

    static class Edge {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int c = scanner.nextInt();


        int[][] matrix = new int[n][n];
        for (int[] d : matrix) {
            Arrays.fill(d, MAX_VALUE);
        }

        for (int i = 0; i < n - 1; i++) {
            matrix[i][i + 1] = c;
        }
        for (int i = 1; i < n; i++) {
            matrix[i][i - 1] = c;
        }

        int start = scanner.nextInt() - 1;
        int end = scanner.nextInt() - 1;

        for (int k = 0; k < m; k++) {
            int i = scanner.nextInt() - 1;
            int j = scanner.nextInt() - 1;
            int weight = scanner.nextInt();
            if (matrix[i][j] == MAX_VALUE) {
                matrix[i][j] = matrix[j][i] = weight;
            } else {
                matrix[i][j] = matrix[j][i] = min(matrix[i][j], weight);
            }
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != MAX_VALUE) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, MAX_VALUE);
        dp[start] = 0;

        for (int i = 1; i < n; i++) {
            for (Edge edge : edges) {
                int u = edge.start;
                int v = edge.end;
                int weight = edge.weight;
                if (dp[u] != MAX_VALUE && dp[u] + weight < dp[v]) {
                    dp[v] = dp[u] + weight;
                }
            }
        }
        System.out.println(dp[end]);
    }
}