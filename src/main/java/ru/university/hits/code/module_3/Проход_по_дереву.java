package ru.university.hits.code.module_3;

import java.util.Scanner;

class Проход_по_дереву {

    private static class Tree {

        int[] tree;
        int currentIndex = 0;

        public Tree(int size) {
            tree = new int[size];
        }

        public String moveIndex(String routeCommand) {
            switch (routeCommand) {
                case "left":
                    currentIndex = currentIndex * 2 + 1;
                    break;
                case "right":
                    currentIndex = currentIndex * 2 + 2;
                    break;
            }
            if (currentIndex >= tree.length || tree[currentIndex] == -1) return "empty";
            else return tree[currentIndex] + "";


        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Tree tree = new Tree(n);
        for (int i = 0; i < n; i++) {
            tree.tree[i] = scan.nextInt();
        }

        int m = scan.nextInt();
        String result = "";
        result += tree.moveIndex("") + "\n";
        for (int i = 0; i < m; i++) {
            result += tree.moveIndex(scan.next()) + "\n";
        }
        System.out.println(result);
    }
}

