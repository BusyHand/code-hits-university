package ru.university.hits.code.module_3;

import java.util.*;

import static java.lang.Math.max;

class Дерево_змейкой {
    private static class Node {
        int value;

        int level;
        int offset;
        List<Node> childs = new ArrayList<>();

        public Node(int value, int level) {
            this.value = value;
            this.level = level;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Node[] tree = new Node[n];
        tree[0] = new Node(1, 1);
        int maxlvl = 0;
        for (int i = 1; i < n; i++) {
            int indexParent = scan.nextInt();
            Node parent = tree[indexParent - 1];
            Node child = new Node(i + 1, parent.level + 1);
            parent.childs.add(child);
            maxlvl = max(maxlvl, child.level);
            tree[i] = child;
        }
        int maxSize = 0;
        Map<Integer, Integer> levelCurrent = new HashMap<>();
        for (Node parent : tree) {
            int level = parent.level;
            int i = 0;
            if (levelCurrent.containsKey(level)) {
                i = levelCurrent.get(level);
            }
            for (Node child : parent.childs) {
                child.offset = i++;
            }
            maxSize = max(maxSize, i);
            levelCurrent.put(level, i);
        }

        int[][] result = new int[maxlvl + 1][maxSize + 1];
        for (Node parent : tree) {
            for (Node child : parent.childs) {
                result[child.level][child.offset] = child.value;
            }
        }
        StringBuilder sb = new StringBuilder("1 ");
        boolean isRight = true;
        for (int[] value : result) {

            if (isRight) {
                for (int i = value.length - 1; i > -1; i--) {
                    if (value[i] != 0) {
                        sb.append(value[i]).append(' ');
                    }
                }
            } else {
                for (int value1 : value) {
                    if (value1 != 0) {
                        sb.append(value1).append(' ');
                    }
                }
            }
            isRight = !isRight;
        }
        System.out.println(sb);

    }
}