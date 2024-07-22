package ru.university.hits.code.module_4;

import java.util.*;

class Алгоритм_Прима {

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

        public boolean isAllVerticesVisited() {
            return start.visited && end.visited;
        }

        public Vertex getUnvisitedVertex() {
            return !start.visited ? start : end;
        }

    }

    static class Graph {

        List<Vertex> vertices = new ArrayList<>();

        StringBuilder answer = new StringBuilder();

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));

        public Graph() {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();

            vertices.add(new Vertex(-1));
            for (int i = 1; i < n + 1; i++) {
                vertices.add(new Vertex(i));
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int weight = scan.nextInt();
                    if (j > i && weight > 0) {
                        Vertex firstVertex = vertices.get(i + 1);
                        Vertex secondVertex = vertices.get(j + 1);
                        Edge edge = new Edge(weight, firstVertex, secondVertex);
                        firstVertex.addEdge(edge);
                        secondVertex.addEdge(edge);
                    }
                }
            }
        }

        public void prim() {
            int[][] matrixConnectivity = new int[vertices.size() - 1][vertices.size() - 1];
            int sum = 0;
            Vertex vertex = vertices.get(1);
            vertex.visited = true;
            priorityQueue.addAll(vertex.edges);
            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.remove();
                if (edge.isAllVerticesVisited()) {
                    continue;
                }
                matrixConnectivity[edge.start.index - 1][edge.end.index - 1] = edge.weight;
                matrixConnectivity[edge.end.index - 1][edge.start.index - 1] = edge.weight;
                sum += edge.weight;
                Vertex unvisitedVertex = edge.getUnvisitedVertex();
                unvisitedVertex.visited = true;
                for (Edge vertexEdges : unvisitedVertex.edges) {
                    if (!vertexEdges.isAllVerticesVisited()) {
                        priorityQueue.add(vertexEdges);
                    }
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
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.prim();
        System.out.println(graph.answer);
    }
}
