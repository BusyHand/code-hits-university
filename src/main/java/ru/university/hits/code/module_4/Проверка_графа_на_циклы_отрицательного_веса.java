package ru.university.hits.code.module_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

class Проверка_графа_на_циклы_отрицательного_веса {

    static class Edge {
        int start;
        int end;
        int weight;

        int whereIFrom;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    private final static int NO_WEIGHT = 100000;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        List<Edge> edges = new ArrayList<>();
        int[][] dpMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int weight = scan.nextInt();
                if (weight != NO_WEIGHT) {
                    edges.add(new Edge(i, j, weight));
                }
                if (i == j) {
                    dpMatrix[i][j] = 0;
                } else if (weight == NO_WEIGHT) {
                    dpMatrix[i][j] = Integer.MAX_VALUE;
                } else {
                    dpMatrix[i][j] = weight;
                }
            }
        }

        int start = doFloud(n, dpMatrix);
        System.out.println(doFord(n, edges, start));
    }

    private static String doFord(int n, List<Edge> edges, int start) {
        int m = edges.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] = 0;
        boolean OK = true;
        int count = 1;

        while (OK) {
            OK = false;
            count++;
            for (int j = 0; j < m; j++) {
                Edge edge = edges.get(j);
                int u = edge.start;
                int v = edge.end;
                int weight = edge.weight;
                if (dp[u] != Integer.MAX_VALUE && dp[u] + weight < dp[v]) {
                    edges.get(v).whereIFrom = u;
                    dp[v] = dp[u] + weight;
                    OK = true;
                    if (count > n) {
                        return answer(edges, v);
                    }
                }
            }
        }
        return "NO";
    }

    private static int doFloud(int n, int[][] dpMatrix) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dpMatrix[i][k] != Integer.MAX_VALUE && dpMatrix[k][j] != Integer.MAX_VALUE) {
                        dpMatrix[i][j] = min(dpMatrix[i][j], dpMatrix[i][k] + dpMatrix[k][j]);
                    }
                    if (dpMatrix[i][i] < 0) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    private static String answer(List<Edge> edges, int v) {
        String answer = "YES\n";
        List<Integer> resultList = new ArrayList<>();
        int current = v;
        while (!resultList.contains(current)) {
            resultList.add(current);
            current = edges.get(current).whereIFrom;
        }
        resultList.add(current);
        int i = 0;
        for (; i < resultList.size(); i++) {
            int vertexIndex = resultList.get(i);
            if (vertexIndex == current) {
                break;
            }
        }
        answer += (resultList.size() - i) + "\n";
        for (int k = resultList.size() - 1; k >= i; k--) {
            answer += (resultList.get(k) + 1) + " ";
        }
        return answer;
    }
}
