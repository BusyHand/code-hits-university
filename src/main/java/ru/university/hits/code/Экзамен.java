package ru.university.hits.code;


import java.util.*;

class Экзамен {

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
//ыфва

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            int w = s.nextInt();
            Edge e = new Edge(w, vertices[a - 1], vertices[b - 1]);
            vertices[a - 1].addEdge(e);
            vertices[b - 1].addEdge(e);
        }

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        int sum = 0;
        Vertex vertex = vertices[0];
        vertex.visited = true;
        priorityQueue.addAll(vertex.edges);
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.remove();
            if (edge.isAllVerticesVisited()) {
                continue;
            }
            sum += edge.weight;
            Vertex unvisitedVertex = edge.getUnvisitedVertex();
            unvisitedVertex.visited = true;
            for (Edge vertexEdges : unvisitedVertex.edges) {
                if (!vertexEdges.isAllVerticesVisited()) {
                    priorityQueue.add(vertexEdges);
                }
            }
        }
        System.out.println(sum);
    }


}


