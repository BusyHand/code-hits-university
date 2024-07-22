package ru.university.hits.code.module_3;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class AVL {
    private static class Node {
        int key;
        int height;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }
    }

    private static class AVLTree {

        private Node root;

        public void initTree(List<Integer> nodes) {
            for (int value : nodes) {
                if (value != -1) {
                    insert(value, true);
                }
            }
        }


        void updateHeight(Node n) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
        }

        int height(Node n) {
            return n == null ? -1 : n.height;
        }

        int getBalance(Node n) {
            return (n == null) ? 0 : height(n.right) - height(n.left);
        }

        private void insert(int key, boolean isInit) {
            root = insert(root, key, isInit);
        }

        private Node insert(Node root, int key, boolean isInit) {
            if (root == null) {
                return new Node(key);
            } else if (root.key >= key) {
                root.left = insert(root.left, key, isInit);
            } else if (root.key < key) {
                root.right = insert(root.right, key, isInit);
            }
            if (isInit) {
                updateHeight(root);
                return root;
            } else {
                return rebalance(root);
            }
        }

        private void delete(int key) {
            root = delete(root, key);
        }

        private Node delete(Node node, int key) {
            if (node == null) {
                return node;
            } else if (node.key > key) {
                node.left = delete(node.left, key);
            } else if (node.key < key) {
                node.right = delete(node.right, key);
            } else {
                if (node.left == null || node.right == null) {
                    node = (node.left == null) ? node.right : node.left;
                } else {
                    Node mostLeftChild = mostLeftChild(node.right);
                    node.key = mostLeftChild.key;
                    node.right = delete(node.right, node.key);
                }
            }
            if (node != null) {
                node = rebalance(node);
            }
            return node;
        }

        private Node mostLeftChild(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        private Node rebalance(Node z) {
            updateHeight(z);
            int balance = getBalance(z);
            if (balance > 1) {
                if (height(z.right.right) > height(z.right.left)) {
                    z = rotateLeft(z);
                } else {
                    z.right = rotateRight(z.right);
                    z = rotateLeft(z);
                }
            } else if (balance < -1) {
                if (height(z.left.left) > height(z.left.right))
                    z = rotateRight(z);
                else {
                    z.left = rotateLeft(z.left);
                    z = rotateRight(z);
                }
            }
            return z;
        }

        private Node rotateRight(Node y) {
            Node x = y.left;
            Node z = x.right;
            x.right = y;
            y.left = z;
            updateHeight(y);
            updateHeight(x);
            return x;
        }


        private void r(Node y){


        }


        private Node rotateLeft(Node y) {
            Node x = y.right;
            Node z = x.left;
            x.left = y;
            y.right = z;
            updateHeight(y);
            updateHeight(x);
            return x;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(root, sb);
            return sb.toString();
        }

        public StringBuilder toString(Node node, StringBuilder sb) {
            if (node == null) {
                return sb.append("-1 ");
            }
            sb.append(node.key).append(' ');
            toString(node.left, sb);
            toString(node.right, sb);
            return sb;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        List<Integer> values = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        AVLTree tree = new AVLTree();
        tree.initTree(values);

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int command = scanner.nextInt();
            int value = scanner.nextInt();
            switch (command) {
                case 1:
                    tree.insert(value, false);
                    break;
                case 2:
                    tree.delete(value);
                    break;
            }
            System.out.println(tree);
        }
    }
}