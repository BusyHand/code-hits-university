package ru.university.hits.code.module_4;

import java.util.Scanner;

class Контрольная {


    private static char[][] field;
    private static boolean[][] isVisited;
    private static int n;
    private static int m;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        field = new char[n][m];
        isVisited = new boolean[n][m];
        scan.nextLine();

        for (int i = 0; i < n; i++) {
            field[i] = scan.nextLine().toCharArray();
        }

        int resultCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isVisited[i][j] || field[i][j] == '.') {
                    continue;
                }
                DFS(i, j);
                resultCount++;
            }
        }
        System.out.println(resultCount);
    }

    private static void DFS(int i, int j) {

        if (!checkIndexes(i,j) || isVisited[i][j] || field[i][j] == '.') {
            return;
        }

        isVisited[i][j] = true;
        DFS(i - 1, j);
        DFS(i + 1, j);
        DFS(i, j - 1);
        DFS(i, j + 1);
    }


    private static boolean checkIndexes(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
