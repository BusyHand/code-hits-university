package ru.university.hits.code.module_4;

import java.io.IOException;
import java.util.*;

class Алгоритм_Дейкстры {

    static class Vertex {

        boolean visited;

        int minWeight;

        int index;

        List<Edge> edges = new ArrayList<>();

        public Vertex(int index) {
            this.index = index;
        }

        public void addEdge(Vertex start, Vertex end, int weight) {
            edges.add(new Edge(start, end, weight));
        }
    }

    static class Edge {

        int weight;

        int weightToStart;

        Vertex start;
        Vertex end;

        public Edge(Vertex start, Vertex end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getPath() {
            return weight + weightToStart;
        }
    }

    static class Graph {

        List<Vertex> vertices = new ArrayList<>();

        int fromVertex;

        int toVertex;

        public Graph() {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            fromVertex = scan.nextInt() - 1;
            toVertex = scan.nextInt() - 1;

            for (int i = 1; i <= n; i++) {
                vertices.add(new Vertex(i));
            }

            for (int i = 0; i < n; i++) {
                Vertex vertex = vertices.get(i);
                for (int j = 0; j < n; j++) {
                    int weight = scan.nextInt();
                    if (weight > -1 && i != j) {
                        vertex.addEdge(vertex, vertices.get(j), weight);
                    }
                }
            }
        }

        public int doDejkstra() {
            PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(Comparator.comparing(Edge::getPath));
            Vertex vertex = vertices.get(fromVertex);
            vertex.visited = true;
            vertex.edges.forEach(e -> e.weightToStart = 0);
            edgePriorityQueue.addAll(vertex.edges);

            while (!edgePriorityQueue.isEmpty()) {
                Edge edge = edgePriorityQueue.remove();
                vertex = edge.end;
                if (vertex.visited) {
                    continue;
                }
                vertex.visited = true;
                vertex.edges.forEach(e -> e.weightToStart = edge.getPath());
                vertex.minWeight = edge.getPath();
                edgePriorityQueue.addAll(vertex.edges);
            }

            return vertices.get(toVertex).minWeight;
        }


    }


    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        System.out.println(graph.doDejkstra());
    }
}
