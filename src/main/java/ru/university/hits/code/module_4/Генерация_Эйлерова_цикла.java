package ru.university.hits.code.module_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Генерация_Эйлерова_цикла {

    static class Vertex {

        int index;

        boolean visited;
        List<Edge> edges = new ArrayList<>();

        public Vertex(int index) {
            this.index = index;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }
    }

    static class Edge {

        Vertex start;

        Vertex end;

        boolean visited;

        public Edge(Vertex start, Vertex end) {
            this.start = start;
            this.end = end;
        }

        public Vertex getAnthorOfThisVertex(Vertex vertex) {
            return vertex.index != start.index ? start : end;
        }
    }

    static class Graph {

        List<Vertex> vertices = new ArrayList<>();

        public Graph() {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            for (int i = 1; i < n + 1; i++) {
                vertices.add(new Vertex(i));
            }

            for (int i = 0; i < n; i++) {
                Vertex vertex = vertices.get(i);
                for (int j = 0; j < n; j++) {
                    if (scan.nextInt() != 0 && j > i) {
                        Vertex endVertex = vertices.get(j);
                        Edge edge = new Edge(vertex, endVertex);
                        vertex.addEdge(edge);
                        endVertex.addEdge(edge);
                    }
                }
            }
        }

        private StringBuilder answer = new StringBuilder();

        public String getEulerCycle() {
            if (!isCorrectGraph()) {
                return -1 + "";
            }
            modifiedDFS(vertices.get(0));
            answer.append(vertices.get(0).index);
            return answer.toString();
        }

        private void modifiedDFS(Vertex vertex) {
            for (Edge edge : vertex.edges) {
                if (edge.visited) {
                    continue;
                }
                edge.visited = true;
                modifiedDFS(edge.getAnthorOfThisVertex(vertex));
                answer.append(edge.getAnthorOfThisVertex(vertex).index + " ");
            }
        }

        private boolean isCorrectGraph() {
            DFS(vertices.get(0));
            for (Vertex v : vertices) {
                if (!v.visited || v.edges.size() % 2 != 0) {
                    return false;
                }
            }
            return true;
        }

        private void DFS(Vertex vertex) {
            for (int i = 0; i < vertex.edges.size(); i++) {
                Vertex childVertex = vertex.edges.get(i).getAnthorOfThisVertex(vertex);
                if (!childVertex.visited) {
                    childVertex.visited = true;
                    DFS(childVertex);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        System.out.println(graph.getEulerCycle());
    }
}
