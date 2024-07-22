package ru.university.hits.code.module_3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Немаксимальные_ключи {
    private static class Node {
        int value;
        int height;
        int sizeTree = 1;
        int key;
        Node left;
        Node right;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class ModifiedAVLTree {
        StringBuilder result = new StringBuilder();
        private Node root;
        private final Map<Integer, Node> map = new HashMap<>();

        private int size;

        public void insert(int key, int value) {
            if (map.containsKey(key)) {
                updateNodePlace(key, value);
            } else {
                size++;
                root = insert(root, key, value);
            }
        }


        private Node insert(Node root, int key, int value) {
            if (root == null) {
                Node newNode = map.containsKey(key) ? map.get(key) : new Node(key, value);
                map.put(key, newNode);
                return newNode;
            } else if (root.value > value) {
                root.left = insert(root.left, key, value);
            } else if (root.value < value) {
                root.right = insert(root.right, key, value);
            } else if (root.key > key) {
                root.left = insert(root.left, key, value);
            } else {
                root.right = insert(root.right, key, value);
            }
            return rebalance(root);
        }

        private void updateNodePlace(int key, int value) {
            Node oldNode = map.get(key);
            delete(oldNode.key, oldNode.value);
            oldNode.value += value;
            oldNode.left = null;
            oldNode.right = null;
            oldNode.height = 0;
            oldNode.sizeTree = 1;
            root = insert(root, oldNode.key, oldNode.value);
        }

        private void delete(int key, int value) {
            root = delete(root, key, value);
        }

        private Node delete(Node node, int key, int value) {

            if (node == null) {
                return null;
            } else if (node.value > value) {
                node.left = delete(node.left, key, value);
            } else if (node.value < value) {
                node.right = delete(node.right, key, value);
            } else if (node.key > key) {
                node.left = delete(node.left, key, value);
            } else if (node.key < key) {
                node.right = delete(node.right, key, value);
            } else {
                if (node.left == null || node.right == null) {

                    node = (node.left == null) ? node.right : node.left;

                } else {

                    Node mostLeftChild = mostLeftChild(node.right);
                    node.right = delete(node.right, mostLeftChild.key, mostLeftChild.value);
                    mostLeftChild.left = node.left;
                    mostLeftChild.right = node.right;
                    node = mostLeftChild;
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
            updateSizeTree(z);
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
            updateSizeTree(y);
            updateSizeTree(x);
            return x;
        }

        private Node rotateLeft(Node y) {
            Node x = y.right;
            Node z = x.left;
            x.left = y;
            y.right = z;
            updateHeight(y);
            updateHeight(x);
            updateSizeTree(y);
            updateSizeTree(x);
            return x;
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

        void updateSizeTree(Node n) {
            n.sizeTree = 1 + sizeTree(n.left) + sizeTree(n.right);
        }

        int sizeTree(Node n) {
            return n == null ? 0 : n.sizeTree;
        }

        int k = 0;
        int count = 0;
        int countLine = 1;

        public void showKMaxValue(int s) {
            this.k = size - s;
            if (k > 0) showKMaxValue(root);
            result.append('\n');
            count = 0;
        }

        private void showKMaxValue(Node root) {
            if (root == null || count >= 10) {
                return;
            }

            if (count != 0) {
                showKMaxValue(root.right);
                if (count < 10) {
                    result.append(root.key).append(' ');
                    count++;
                    showKMaxValue(root.left);
                }
                return;
            }

            int leftTreeSize = root.left != null ? root.left.sizeTree : 0;

            if (leftTreeSize + 1 < k) {
                k = k - leftTreeSize - 1;
                showKMaxValue(root.right);
                if (count != 0 && count < 10) {
                    result.append(root.key).append(' ');
                    count++;
                    showKMaxValue(root.left);
                }
            } else if (leftTreeSize + 1 > k) {
                showKMaxValue(root.left);
            } else {
                count++;
                result.append(root.key).append(' ');
                showKMaxValue(root.left);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ModifiedAVLTree tree = new ModifiedAVLTree();


        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int command = scanner.nextInt();
            switch (command) {
                case 1 -> {
                    int key = scanner.nextInt();
                    int value = scanner.nextInt();
                    tree.insert(key, value);
                }
                case 2 -> {
                    int k = scanner.nextInt();
                    tree.showKMaxValue(k);
                }
            }
        }
        System.out.println(tree.result);
    }
}
/*
#include <iostream>
#include <map>
#include <string>
using namespace std;

class Node {
public:
    int key;
    int value;
    int height;
    int sizeTree = 1;
    Node* left;
    Node* right;

    Node(int key, int value) : key(key), value(value), height(0), sizeTree(1), left(nullptr), right(nullptr) {}
};


class ModifiedAVLTree {
public:
    string result;
private:
    std::map<int, Node*> map;
    Node* root;
    int size = 0;
    int k = 0;
    int count = 0;

public:
    void insert(int key, int value) {
        if (!(map.find(key) == map.end())) {
            Node* oldNode = map[key];
            updateNodePlace(oldNode, value);
        }
        else {
            size++;
            root = insert(root, key, value);
        }
    }

    Node* insert(Node* node, int key, int value) {
        if (node == nullptr) {
            Node* newNode = !(map.find(key) == map.end()) ? map[key] : new Node(key, value);
            map[key] = newNode;
            return newNode;
        }
        else if (node->value > value) {
            node->left = insert(node->left, key, value);
        }
        else if (node->value < value) {
            node->right = insert(node->right, key, value);
            }
        else if (node->key > key) {
            node->left = insert(node->left, key, value);
            }
        else {
            node->right = insert(node->right, key, value);
            }
        return rebalance(node);
    }

    Node* mostLeftChild(Node* node) {
        while (node->left != nullptr) {
            node = node->left;
        }
        return node;
    }

    Node* deleteNode(Node* node, int key, int value) {
        if (node == nullptr) {
            return nullptr;
        } else if (node->value > value) {
            node->left = deleteNode(node->left, key, value);
        } else if (node->value < value) {
            node->right = deleteNode(node->right, key, value);
        } else if (node->key > key) {
            node->left = deleteNode(node->left, key, value);
        } else if (node->key < key) {
            node->right = deleteNode(node->right, key, value);
        } else {
            if (node->left == nullptr || node->right == nullptr) {
                node = (node->left == nullptr) ? node->right : node->left;
            }
            else {
                Node* mostLeftChildPointer = mostLeftChild(node->right);
                node->right = deleteNode(node->right, mostLeftChildPointer->key, mostLeftChildPointer->value);
                mostLeftChildPointer->right = node->right;
                mostLeftChildPointer->left = node->left;
                node = mostLeftChildPointer;
            }
        }
        if (node != nullptr) {
            node = rebalance(node);
        }
        return node;
    }

    void deleteNode(int key, int value) {
        root = deleteNode(root, key, value);
    }

    void updateNodePlace(Node* oldNode, int value) {
        deleteNode(oldNode->key, oldNode->value);
        oldNode->value += value;
        oldNode->right = nullptr;
        oldNode->left = nullptr;
        oldNode->sizeTree = 1;
        oldNode->height = 0;
        root = insert(root, oldNode->key, oldNode->value);
    }

    Node* rebalance(Node* z) {
        updateHeight(z);
        updateSizeTree(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z->right->right) > height(z->right->left)) {
                z = rotateLeft(z);
            }
            else {
                z->right = rotateRight(z->right);
                z = rotateLeft(z);
            }
        }
        else if (balance < -1) {
            if (height(z->left->left) > height(z->left->right)) {
                z = rotateRight(z);
            }
            else {
                z->left = rotateLeft(z->left);
                z = rotateRight(z);
            }
        }
        return z;
    }


    void updateHeight(Node* n) {
        n->height = 1 + std::max(height(n->left), height(n->right));
    }

    int height(Node* n) {
        return (n == nullptr) ? -1 : n->height;
    }

    int getBalance(Node* n) {
        return (n == nullptr) ? 0 : height(n->right) - height(n->left);
    }

    void updateSizeTree(Node* n) {
        n->sizeTree = 1 + sizeTree(n->left) + sizeTree(n->right);
    }

    int sizeTree(Node* n) {
        return (n == nullptr) ? 0 : n->sizeTree;
    }

    Node* rotateRight(Node* y) {
        Node* x = y->left;
        Node* z = x->right;
        x->right = y;
        y->left = z;
        updateHeight(y);
        updateHeight(x);
        updateSizeTree(y);
        updateSizeTree(x);
        return x;
    }

    Node* rotateLeft(Node* y) {
        Node* x = y->right;
        Node* z = x->left;
        x->left = y;
        y->right = z;
        updateHeight(y);
        updateHeight(x);
        updateSizeTree(y);
        updateSizeTree(x);
        return x;
    }

public:
    void showKMaxValue(int s) {
        this->k = size - s;
        if(k > 0) showKMaxValue(root);
        result.append("\n");
        count = 0;

    }

    void showKMaxValue(Node* root) {
        if (root == nullptr || count >= 10) {
            return;
        }

        if (count != 0) {
            showKMaxValue(root->right);
            if (count < 10) {
                result.append(to_string(root->key) + " ");
                count++;
                showKMaxValue(root->left);
            }
            return;
        }

        int leftTreeSize = (root->left != nullptr) ? root->left->sizeTree : 0;

        if (leftTreeSize + 1 < k) {
            k = k - leftTreeSize - 1;
            showKMaxValue(root->right);
            if (count != 0 && count < 10) {
                result.append(to_string(root->key) + " ");
                count++;
                showKMaxValue(root->left);
            }
        }
        else if (leftTreeSize + 1 > k) {
            showKMaxValue(root->left);
        }
        else {
            count++;
            result.append(to_string(root->key) + " ");
            showKMaxValue(root->left);
        }
    }
};

int main() {
    ModifiedAVLTree tree;
    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        int command;
        cin >> command;

        switch (command) {
        case 1: {
            int key, value;
            cin >> key >> value;
            tree.insert(key, value);
            break;
        }
        case 2: {
            int k;
            cin >> k;
            tree.showKMaxValue(k);
            break;
        }

        }
    }
    cout << tree.result;
    return 0;
}

 */
