package ru.university.hits.code.module_4;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.max;
import static java.lang.Math.min;

class Встреча_двух_шахматных_коней {

    static class Vertex {
        int countMoveA;
        int countMoveB;
        boolean visitedA;
        boolean visitedB;

        @Override
        public String toString() {
            String[] splitA = resultA.split("\n");
            String[] splitB = resultB.split("\n");
            String result = (max(splitA.length, splitB.length) - 1) + "\n";
            for (int i = min(splitA.length, splitB.length) - 2; i >= 0; i--) {
                result += splitA[i] + " " + splitB[i] + "\n";
            }
            return result;
        }

        int i;
        int j;

        String resultA;
        String resultB;

        public Vertex(int i, int j) {
            this.i = i;
            this.j = j;
            resultA = i + " " + j;
            resultB = i + " " + j;
        }

        public void addResultA(Vertex oldVertex) {
            resultA += "\n" + oldVertex.resultA;
        }

        public void addResultB(Vertex oldVertex) {
            resultB += "\n" + oldVertex.resultB;
        }
    }

    private static final int EIGHT = 9;
    private static Vertex[][] chessBouard;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        chessBouard = new Vertex[EIGHT][EIGHT];
        for (int i = 1; i < EIGHT; i++) {
            for (int j = 1; j < EIGHT; j++) {
                chessBouard[i][j] = new Vertex(i, j);
            }
        }

        int i1 = scan.nextInt();
        int j1 = scan.nextInt();
        int i2 = scan.nextInt();
        int j2 = scan.nextInt();
        BFS(i1, j1, i2, j2);
        int min = MAX_VALUE;
        Vertex result = null;
        for (int i = 1; i < EIGHT; i++) {
            for (int j = 1; j < EIGHT; j++) {
                Vertex vertex = chessBouard[i][j];
                if (vertex.countMoveA == vertex.countMoveB && min > vertex.countMoveA) {
                    min = vertex.countMoveA;
                    result = vertex;
                }
            }
        }
        System.out.println(i1 == i2 && j1 == j2 ? 0 :
                result != null ? result : -1);


    }

    public static void BFS(int i1, int j1, int i2, int j2) {
        Queue<Vertex> queueA = new ArrayDeque<>();
        Queue<Vertex> queueB = new ArrayDeque<>();
        queueA.add(chessBouard[i1][j1]);
        queueB.add(chessBouard[i2][j2]);
        chessBouard[i1][j1].visitedA = true;
        chessBouard[i2][j2].visitedB = true;


        while (!queueA.isEmpty()) {
            Vertex vertexA = queueA.remove();
            Vertex vertexB = queueB.remove();
            addToQueue(queueA, vertexA, true);
            addToQueue(queueB, vertexB, false);
        }
    }

    private static void addToQueue(Queue<Vertex> queue, Vertex vertex, boolean isMoveA) {
        add(queue, vertex.i - 2, vertex.j - 1, isMoveA, vertex);
        add(queue, vertex.i - 2, vertex.j + 1, isMoveA, vertex);
        add(queue, vertex.i - 1, vertex.j - 2, isMoveA, vertex);
        add(queue, vertex.i + 1, vertex.j - 2, isMoveA, vertex);

        add(queue, vertex.i + 2, vertex.j + 1, isMoveA, vertex);
        add(queue, vertex.i + 2, vertex.j - 1, isMoveA, vertex);
        add(queue, vertex.i + 1, vertex.j + 2, isMoveA, vertex);
        add(queue, vertex.i - 1, vertex.j + 2, isMoveA, vertex);
    }

    private static void add(Queue<Vertex> queue, int i, int j, boolean isMoveA, Vertex oldVertex) {
        if (i > 0 && i < EIGHT && j > 0 && j < EIGHT) {
            Vertex vertex = chessBouard[i][j];
            if (isMoveA) {
                if (!vertex.visitedA) {
                    queue.add(vertex);
                    vertex.addResultA(oldVertex);
                    vertex.countMoveA = oldVertex.countMoveA + 1;
                    vertex.visitedA = true;
                }
            } else {
                if (!vertex.visitedB) {
                    queue.add(vertex);
                    vertex.addResultB(oldVertex);
                    vertex.countMoveB = oldVertex.countMoveB + 1;
                    vertex.visitedB = true;
                }
            }
        }
    }

}