package ru.university.hits.code.module_4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Задача_о_раскраске {

    static class Vertex {

        boolean deleted;

        int index;

        List<Integer> edges = new ArrayList<>();

        public Vertex(int index) {
            this.index = index;
        }

        public void addEdge(int edge) {
            edges.add(edge);
        }

        public int getVertexDegree() {
            return edges.size();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        if (k >= n) {
            System.out.println("YES");
            return;
        }

        List<Vertex> vertices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex(i));
        }

        for (int i = 0; i < n; i++) {
            Vertex vertex = vertices.get(i);
            for (int j = 0; j < n; j++) {
                if (scan.nextInt() != 0) {
                    vertex.addEdge(j);
                }
            }
        }

        vertices.sort(Comparator.comparing(Vertex::getVertexDegree).reversed());
        List<Integer> untouchable = new ArrayList<>();

        while (k > 0) {
            for (int i = 0; i < vertices.size(); i++) {
                Vertex vertex = vertices.get(i);
                if (!untouchable.contains(i) && !vertex.deleted) {
                    vertex.deleted = true;
                    untouchable.addAll(vertex.edges);
                }
            }
            untouchable = new ArrayList<>();
            k--;
        }

        for (Vertex vertex : vertices) {
            if (!vertex.deleted) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");


    }
}
