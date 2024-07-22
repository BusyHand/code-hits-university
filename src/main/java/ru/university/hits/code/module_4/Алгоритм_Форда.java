package ru.university.hits.code.module_4;

import java.util.Arrays;
import java.util.Scanner;

class Алгоритм_Форда {

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
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int start = scan.nextInt() - 1;
            int end = scan.nextInt() - 1;
            int weight = scan.nextInt();
            edges[i] = new Edge(start, end, weight);
        }

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int u = edges[j].start;
                int v = edges[j].end;
                int weight = edges[j].weight;
                if (dp[u] != Integer.MAX_VALUE && dp[u] + weight < dp[v]) {
                    dp[v] = dp[u] + weight;
                }
            }
        }

        for (int value : dp) {
            System.out.print((value == Integer.MAX_VALUE ? "No" : value) + " ");
        }

    }
}
