package ru.university.hits.code.module_3;

import java.util.Scanner;

class Возвращение_кузнечика {

    private static Node[] tree;
    private static StringBuilder result = new StringBuilder();

    private static class Node {
        int value;
        int level;
        Node parent;

        public Node(int value, int level, Node parent) {
            this.value = value;
            this.level = level;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        tree = new Node[n];
        tree[0] = new Node(1, 1, null);
        for (int i = 1; i < n; i++) {
            int parentIndex = sc.nextInt();
            Node parent = tree[parentIndex - 1];
            tree[i] = new Node(i + 1, parent.level + 1, parent);
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            find(tree[first - 1], tree[second - 1]);
        }
        System.out.println(result);
    }

    private static void find(Node first, Node second) {
        if (first == second) {
            result.append(first.value).append("\n");
            return;
        }

        if (first.level == second.level) {
            first = first.parent;
            second = second.parent;
        } else if (first.level > second.level) {
            first = first.parent;
        } else {
            second = second.parent;
        }
        find(first, second);

    }

}












