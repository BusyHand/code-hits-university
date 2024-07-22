package ru.university.hits.code.module_3;

import java.util.*;

class Сравнение_деревьев {
    private static class Node {
        int value;

        List<Node> childs = new ArrayList<>();

        public int getValue() {
            return value;
        }
    }

    private static Node[] initTree(Scanner sc, int n) {
        Node[] tree = new Node[n];

        for (int i = 0; i < n; i++) {
            Node parent = tree[i];
            if (parent == null) {
                parent = new Node();
                tree[i] = parent;
            }
            parent.value = sc.nextInt();
            int childsNum = sc.nextInt();
            for (int j = 0; j < childsNum; j++) {
                int childIndex = sc.nextInt();
                Node child = tree[childIndex - 1];
                if (child == null) {
                    child = new Node();
                    tree[childIndex - 1] = child;
                }
                parent.childs.add(child);
            }
        }
        Arrays.stream(tree)
                .forEach(node -> node.childs.sort(
                        Comparator.comparing(Node::getValue)));
        return tree;
    }

    private static boolean compareTree(Node firstNode, Node secondNode) {
        if (firstNode.value != secondNode.value) {
            return false;
        }
        int i = 0;
        int j = 0;

        while (i < firstNode.childs.size()) {
            Node firstNodeChild = firstNode.childs.get(i);
            if (j >= secondNode.childs.size()) {
                return false;
            }
            Node secondNodeChild = secondNode.childs.get(j);

            if (firstNodeChild.value == secondNodeChild.value) {
                if (!compareTree(firstNodeChild, secondNodeChild)) {
                    return false;
                }
            } else {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] firstTree = initTree(sc, n);
        Node[] secondTree = initTree(sc, n);
        System.out.println(compareTree(firstTree[0], secondTree[0]) ? "YES" : "NO");
    }
}
