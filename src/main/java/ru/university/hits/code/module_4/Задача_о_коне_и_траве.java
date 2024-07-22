package ru.university.hits.code.module_4;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Задача_о_коне_и_траве {


    static class Vertex {

        int i;
        int j;

        int length;

        public Vertex(int i, int j) {
            this.i = i;
            this.j = j;
            answer += i + " " + j + "\n";
        }

        boolean visited;

        String answer = "";

    }

    private static Vertex[][] chessBoard;
    private static Queue<Vertex> queue;
    private static int n;
    private static int m;
    private static Vertex currentVertex;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt() + 1;
        m = scan.nextInt() + 1;
        chessBoard = new Vertex[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                chessBoard[i][j] = new Vertex(i, j);
            }
        }

        queue = new ArrayDeque<>();
        int startI = scan.nextInt();
        int startJ = scan.nextInt();
        int endI = scan.nextInt();
        int endJ = scan.nextInt();

        currentVertex = chessBoard[startI][startJ];
        currentVertex.length = 0;
        currentVertex.visited = true;
        queue.add(currentVertex);

        while (!queue.isEmpty()) {
            currentVertex = queue.remove();
            int i = currentVertex.i;
            int j = currentVertex.j;

            if (i == endI && j == endJ) {
                System.out.println(currentVertex.length);
                System.out.println(currentVertex.answer);
                break;
            }

            addIfCorrect(i - 2, j - 1);
            addIfCorrect(i - 1, j - 2);
            addIfCorrect(i + 1, j - 2);
            addIfCorrect(i + 2, j - 1);
            addIfCorrect(i + 2, j + 1);
            addIfCorrect(i + 1, j + 2);
            addIfCorrect(i - 1, j + 2);
            addIfCorrect(i - 2, j + 1);

        }
        if(queue.isEmpty()){
            System.out.println(-1);
        }
    }

    private static void addIfCorrect(int i, int j) {
        Vertex movedVertex;
        if (i > 0 && j > 0 && i < n && j < m && !(movedVertex = chessBoard[i][j]).visited) {
            movedVertex.answer = currentVertex.answer + movedVertex.answer;
            movedVertex.length = currentVertex.length + 1;
            movedVertex.visited = true;
            queue.add(movedVertex);
        }
    }

}
