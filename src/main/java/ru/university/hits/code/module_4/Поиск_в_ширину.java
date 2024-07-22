package ru.university.hits.code.module_4;

import java.util.*;

class Поиск_в_ширину {

    private static Node[] graph;

    private static class Node {
        boolean visited;

        int pathLength;

        StringBuilder path = new StringBuilder();

        int index;

        List<Node> nodes = new ArrayList<>();

        public void addIndex(int index) {
            path.append(index);
            this.index = index;
        }

        public void addNode(Node node) {
            nodes.add(node);
        }

        public void addPath(Node node) {
            path.insert(0, node.path + " ");
            pathLength = 1 + node.pathLength;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        initGraph(scan);
        int from = scan.nextInt();
        int to = scan.nextInt();

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(graph[from]);
        Node result = graph[to];
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            for (Node childNode : node.nodes) {
                if (!childNode.visited) {
                    childNode.addPath(node);
                    childNode.visited = true;
                    queue.add(childNode);
                }
            }
        }
        if (from == to) {
            System.out.println(0);
        } else if (result.pathLength == 0) {
            System.out.println(-1);
        } else {
            System.out.println(result.pathLength + "\n" + result.path);
        }
    }

    private static void initGraph(Scanner scan) {
        int n = scan.nextInt() + 1;
        graph = new Node[n];
        for (int i = 1; i < n; i++) {
            graph[i] = new Node();
        }
        for (int i = 1; i < n; i++) {
            graph[i].addIndex(i);
            for (int j = 1; j < n; j++) {
                if (scan.nextInt() != 0) {
                    graph[i].addNode(graph[j]);
                }
            }
        }
    }
}
