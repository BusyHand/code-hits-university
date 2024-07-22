package ru.university.hits.code.module_3;

import java.io.DataInputStream;
import java.io.IOException;

class Проблемы_с_гримуарами {
    private static class Node {
        long value;
        int height;
        int sizeTree = 1;

        long treeSum;

        long leftChildSum;

        long rightChildSum;
        Node left;
        Node right;

        public Node(long value) {
            this.value = value;
            this.treeSum = value;
        }
    }

    private static class ModifiedAVLTree {
        StringBuilder result = new StringBuilder();
        private Node root;

        private int size;

        private long gremuar;

        private long gremuarSize;

        public void insert(long value) {
            size++;
            root = insert(root, value);
            updateGremuar(gremuarSize);
        }


        private Node insert(Node root, long value) {
            if (root == null) {
                return new Node(value);
            } else if (root.value > value) {
                root.left = insert(root.left, value);
            } else {
                root.right = insert(root.right, value);
            }
            return rebalance(root);
        }

        private void delete(long value) {
            size--;
            root = delete(root, value);
            updateGremuar(gremuarSize);
        }

        private Node delete(Node node, long value) {

            if (node == null) {
                return null;
            } else if (node.value > value) {
                node.left = delete(node.left, value);
            } else if (node.value < value) {
                node.right = delete(node.right, value);
            } else {
                if (node.left == null || node.right == null) {

                    node = (node.left == null) ? node.right : node.left;

                } else {
                    Node mostLeftChild = mostLeftChild(node.right);
                    node.value = mostLeftChild.value;
                    node.right = delete(node.right, mostLeftChild.value);
                }
            }
            if (node != null) {
                node = rebalance(node);
            }
            return node;
        }

        long offset;

        public void updateGremuar(long s) {
            gremuarSize = s;
            gremuar = 0;
            offset = size - s + 1;
            if (offset >= 0) updateGremuar(root);
            else gremuar = root == null ? 0 : root.treeSum;
        }

        private void updateGremuar(Node root) {
            if (root == null) {
                return;
            }

            int leftTreeSize = root.left != null ? root.left.sizeTree : 0;

            if (leftTreeSize + 1 < offset) {
                offset = offset - leftTreeSize - 1;
                updateGremuar(root.right);
            } else if (leftTreeSize + 1 > offset) {
                updateGremuar(root.left);
                gremuar += root.treeSum - root.leftChildSum;
            } else {
                gremuar += root.treeSum - root.leftChildSum;
            }
        }

        private Node mostLeftChild(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        private Node rebalance(Node z) {
            updateParams(z);
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

        private void updateParams(Node node) {
            updateHeight(node);
            updateSizeTree(node);
            updateSum(node);
        }

        private Node rotateRight(Node y) {
            Node x = y.left;
            Node z = x.right;
            x.right = y;
            y.left = z;
            updateParams(y);
            updateParams(x);
            return x;
        }

        private Node rotateLeft(Node y) {
            Node x = y.right;
            Node z = x.left;
            x.left = y;
            y.right = z;
            updateParams(y);
            updateParams(x);
            return x;
        }

        int getBalance(Node n) {
            return (n == null) ? 0 : height(n.right) - height(n.left);
        }

        void updateHeight(Node n) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
        }

        int height(Node n) {
            return n == null ? -1 : n.height;
        }

        void updateSizeTree(Node n) {
            n.sizeTree = 1 + sizeTree(n.left) + sizeTree(n.right);
        }

        int sizeTree(Node n) {
            return n == null ? 0 : n.sizeTree;
        }

        private void updateSum(Node z) {
            z.leftChildSum = childSum(z.left);
            z.rightChildSum = childSum(z.right);
            z.treeSum = z.leftChildSum + z.rightChildSum + z.value;

        }

        private long childSum(Node node) {
            return node == null ? 0 : node.treeSum;
        }

        public void sum() {
            result.append(root == null ? 0 : root.treeSum + gremuar).append('\n');
        }
    }

    public static void main(String[] args) throws IOException {
        ModifiedAVLTree modifiedAVLTree = new ModifiedAVLTree();
        Reader scanner = new Reader();
        long n = scanner.nextLong();
        for (int i = 0; i < n; i++) {
            int command = (int) scanner.nextLong();
            long value = scanner.nextLong();
            switch (command) {
                case 1:
                    modifiedAVLTree.insert(value);
                    break;
                case 2:
                    modifiedAVLTree.delete(value);
                    break;
                case 3:
                    modifiedAVLTree.updateGremuar(value);
                    break;
            }
            modifiedAVLTree.sum();
        }
        System.out.println(modifiedAVLTree.result);
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}