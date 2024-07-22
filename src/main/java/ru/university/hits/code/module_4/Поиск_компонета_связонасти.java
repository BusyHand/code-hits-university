package ru.university.hits.code.module_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Поиск_компонета_связонасти {

    private static Node[] graph;

    private static class Node {
        boolean visited;

        int index;

        List<Integer> nodes = new ArrayList<>();

        Node(int index) {
            this.index = index;
        }

        public void addNode(int index) {
            nodes.add(index);
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt() + 1;
        graph = new Node[n];
        for (int i = 1; i < n; i++) {
            graph[i] = new Node(i);
            for (int j = 1; j < n; j++) {
                if (scan.nextInt() != 0) {
                    graph[i].addNode(j);
                }
            }
        }
        List<List<Integer>> componentsList = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            Node node = graph[i];
            if (!node.visited) {
                node.visited = true;
                List<Integer> nodesList = new ArrayList<>();
                componentsList.add(nodesList);
                nodesList.add(node.index);
                DFS(nodesList, node);
            }
        }
        print(componentsList);
    }

    private static void DFS(List<Integer> nodesList, Node node) {
        for (int i = 0; i < node.nodes.size(); i++) {
            Node childNode = graph[node.nodes.get(i)];
            if (!childNode.visited) {
                childNode.visited = true;
                nodesList.add(childNode.index);
                DFS(nodesList, childNode);
            }
        }
    }

    private static void print(List<List<Integer>> componentsList) {
        System.out.println(componentsList.size());
        componentsList.forEach(list -> {
            System.out.print(list.size());
            list.forEach(index -> {
                System.out.print(" " + index);
            });
            System.out.println();
        });
    }
}
