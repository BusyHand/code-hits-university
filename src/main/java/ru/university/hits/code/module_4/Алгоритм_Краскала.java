package ru.university.hits.code.module_4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Алгоритм_Краскала {

    static class Vertex {

        int index;

        int component;

        boolean visited;
        List<Edge> edges = new ArrayList<>();

        public Vertex(int index) {
            this.index = index;
            this.component = index;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }
    }

    static class Edge {

        Vertex start;

        Vertex end;

        int weight;

        public Edge(int weight, Vertex start, Vertex end) {
            this.weight = weight;
            this.start = start;
            this.end = end;
        }

        public int getWeight() {
            return weight;
        }

    }

    static class Graph {

        List<Vertex> vertices = new ArrayList<>();

        List<Edge> allEdges = new ArrayList<>();

        StringBuilder answer = new StringBuilder();

        public Graph() {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();

            vertices.add(new Vertex(-1));
            for (int i = 1; i < n + 1; i++) {
                vertices.add(new Vertex(i));
            }

            int m = scan.nextInt();
            int[][] matrixConnectivity = new int[n][n];
            for (int k = 0; k < m; k++) {
                int i = scan.nextInt();
                int j = scan.nextInt();
                int weight = scan.nextInt();
                if (matrixConnectivity[i - 1][j - 1] == 0) {
                    matrixConnectivity[i - 1][j - 1] = weight;
                    matrixConnectivity[j - 1][i - 1] = weight;
                    Vertex firstVertex = vertices.get(i);
                    Vertex secondVertex = vertices.get(j);
                    Edge edge = new Edge(weight, firstVertex, secondVertex);
                    firstVertex.addEdge(edge);
                    secondVertex.addEdge(edge);
                    allEdges.add(edge);
                }
            }
        }

        public void kruskal() {
            int[][] matrixConnectivity = new int[vertices.size() - 1][vertices.size() - 1];
            int sum = 0;
            allEdges.sort(Comparator.comparing(Edge::getWeight));
            for (Edge edge : allEdges) {
                if (edge.start.component != edge.end.component) {
                    matrixConnectivity[edge.start.index - 1][edge.end.index - 1] = edge.weight;
                    matrixConnectivity[edge.end.index - 1][edge.start.index - 1] = edge.weight;
                    sum += edge.weight;
                    union(edge);
                }
            }
            answer.append(sum);
            for (int[] matrix : matrixConnectivity) {
                answer.append('\n');
                for (int a : matrix) {
                    answer.append(a).append(' ');
                }
            }
        }

        private void union(Edge edge) {
            if ((edge.start.visited && edge.end.visited) || (!edge.start.visited && !edge.end.visited)) {
                edge.start.component = edge.end.component;
            } else if (edge.start.visited) {
                edge.start.component = edge.end.component;
            } else if (edge.end.visited) {
                edge.end.component = edge.start.component;
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.kruskal();
        System.out.println(graph.answer);
    }
}
