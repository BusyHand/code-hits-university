package ru.university.hits.code.module_5;

import java.util.*;

class Ахо_Корасик {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();

        Bohr bohr = new Bohr();
        int n = s.nextInt();
        s.nextLine();
        for (int i = 0; i < n; i++) {
            bohr.addString(s.nextLine(), i);
        }
        System.out.println(bohr.search(line, n));
    }

    private static class Vertex {
        char charToParent;
        List<Integer> indexes = new ArrayList<>();
        boolean isTerminal;
        Vertex suffixLink;
        Vertex parent;
        Vertex up;
        int length;
        Map<Character, Vertex> son = new HashMap<>();
        Map<Character, Vertex> go = new HashMap<>();

        private boolean containsSon(char ch) {
            return son.containsKey(ch);
        }

        private boolean containsGo(char ch) {
            return go.containsKey(ch);
        }

        private Vertex goToSon(char ch) {
            return son.get(ch);
        }

        private Vertex goTo(char ch) {
            return go.get(ch);
        }

    }

    private static class Bohr {
        Vertex root = new Vertex();


        private void addString(String word, int index) {
            Vertex current = root;
            for (char ch : word.toCharArray()) {
                if (current.containsSon(ch)) {
                    current = current.son.get(ch);
                } else {
                    Vertex newVertex = new Vertex();
                    newVertex.parent = current;
                    newVertex.charToParent = ch;
                    current.son.put(ch, newVertex);
                    current = newVertex;
                }
            }
            current.isTerminal = true;
            current.indexes.add(index);
            current.length = word.length();
        }


        private Vertex getSuffLink(Vertex vertex) {
            if (vertex.suffixLink == null) {
                if (vertex == root || vertex.parent == root) {
                    vertex.suffixLink = root;
                } else {
                    vertex.suffixLink = getLink(getSuffLink(vertex.parent), vertex.charToParent);
                }
            }
            return vertex.suffixLink;
        }


        private Vertex getLink(Vertex vertex, char ch) {
            if (!vertex.containsGo(ch)) {
                if (vertex.containsSon(ch)) {
                    vertex.go.put(ch, vertex.goToSon(ch));
                } else if (vertex == root) {
                    vertex.go.put(ch, root);
                } else {
                    vertex.go.put(ch, getLink(getSuffLink(vertex), ch));
                }
            }
            return vertex.goTo(ch);
        }

        private Vertex getUp(Vertex vertex) {
            if (vertex.up == null) {
                if (getSuffLink(vertex).isTerminal) {
                    vertex.up = getSuffLink(vertex);
                } else if (getSuffLink(vertex) == root) {
                    vertex.up = root;
                } else {
                    vertex.up = getUp(getSuffLink(vertex));
                }
            }
            return vertex.up;
        }


        private void report(Vertex vertex, String[] result, int pos) {
            for (Vertex iter = vertex; iter != root; iter = getUp(iter)) {
                if (iter.isTerminal) {
                    for (int index : iter.indexes) {
                        result[index] += (pos - iter.length + 2) + " ";
                    }
                }
            }
        }

        private String search(String line, int n) {
            String[] result = new String[n];
            Arrays.fill(result, "");

            char[] chars = line.toCharArray();
            Vertex current = root;
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                current = getLink(current, ch);
                report(current, result, i);
            }
            return String.join("\n", result);
        }
    }
}