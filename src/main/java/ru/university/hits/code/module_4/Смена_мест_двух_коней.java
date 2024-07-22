package ru.university.hits.code.module_4;

import java.util.*;

class Смена_мест_двух_коней {
    private final static int[][] MOVES = {
            {0, -2, -2, -1, -1, 1, 1, 2, 2},
            {0, -1, 1, -2, 2, -2, 2, -1, 1}};
    private static final int N = 9;

    static class Vertex {
        int y1;
        int x1;
        int y2;
        int x2;
        Vertex prev = null;

        public Vertex(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }


        public ArrayList<Vertex> getMoves() {
            ArrayList<Vertex> movesList = new ArrayList<>();
            for (int i = 1; i < N; i++) {
                int newY1 = y1 + MOVES[0][i];
                int newX1 = x1 + MOVES[1][i];
                if (!isCorrect(newX1, newY1, x2, y2)) continue;

                for (int j = 1; j < N; j++) {
                    int newY2 = y2 + MOVES[0][j];
                    int newX2 = x2 + MOVES[1][j];
                    if (!isCorrect(newX2, newY2, newX1, newY1)) continue;
                    Vertex newMove = new Vertex(newX1, newY1, newX2, newY2);
                    movesList.add(newMove);
                }
            }
            return movesList;
        }

        private boolean isCorrect(int x, int y, int notEqualX, int notEqualY) {
            if (x > 0 && x < N && y > 0 && y < N && (x != notEqualX || y != notEqualY)) {
                return true;
            } else return false;
        }

        @Override
        public String toString() {
            return "1 " + x1 + " " + y1 + "\n2 " + x2 + " " + y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Vertex)) return false;

            Vertex vertex = (Vertex) o;

            if (y1 != vertex.y1) return false;
            if (x1 != vertex.x1) return false;
            if (y2 != vertex.y2) return false;
            return x2 == vertex.x2;
        }

        @Override
        public int hashCode() {
            int result = y1;
            result = 31 * result + x1;
            result = 31 * result + y2;
            result = 31 * result + x2;
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int x1 = s.nextInt();
        int y1 = s.nextInt();
        int x2 = s.nextInt();
        int y2 = s.nextInt();

        Set<Vertex> isVisited = new HashSet<>();
        Queue<Vertex> queue = new ArrayDeque<>();
        Vertex start = new Vertex(x1, y1, x2, y2);
        Vertex stop = new Vertex(x2, y2, x1, y1);
        queue.add(start);
        isVisited.add(start);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();

            if (vertex.equals(stop)) {
                stop = vertex;
                break;
            }

            for (Vertex nextVertex : vertex.getMoves()) {
                if (isVisited.add(nextVertex)) {
                    nextVertex.prev = vertex;
                    queue.add(nextVertex);
                }
            }
        }
        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = stop;
        while (vertex != null) {
            stack.push(vertex);
            vertex = vertex.prev;
        }

        stack.pop();
        if (stack.isEmpty()) {
            System.out.println(-1);
            return;
        }
        System.out.println(stack.size() * 2);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}


