package ru.university.hits.code.module_4;

import java.util.Scanner;

import static java.lang.Math.*;

class Пираты {

    static class Vertex {
        int x;

        int y;

        int r;

        public Vertex(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        double[][] dp = new double[n][n];
        for (int i = 0; i < n; i++) {
            Vertex v = vertices[i];
            for (int j = 0; j < n; j++) {
                Vertex u = vertices[j];
                if (i != j) {
                    double lenght = sqrt(pow(v.x - u.x, 2) + pow(v.y - u.y, 2)) - v.r - u.r;
                    dp[i][j] = dp[j][i] = lenght >= 0 ? lenght : 0;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.println(dp[scanner.nextInt() - 1][scanner.nextInt() - 1]);
        }
    }
}