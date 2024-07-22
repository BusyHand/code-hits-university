package ru.university.hits.code.module_3;

import java.util.Scanner;

class Очередь_с_двумя_концами {
    private static class Node {
        Node next;
        Node previous;
        String color;

        public Node(String color) {
            this.color = color;
        }
    }

    private static class List {
        Node first;
        Node last;

        public boolean isEmpty() {
            return first == null;
        }

        public void addFirst(Node newNode) {
            if ((first != null && first.next != null) &&
                    (first.color.equals(newNode.color) && first.next.color.equals(newNode.color))) {
                deleteFirst();
                return;
            } else if (isEmpty()) {
                last = newNode;
            } else {
                first.previous = newNode;
            }
            newNode.next = first;
            first = newNode;
        }

        public void addLast(Node newNode) {
            if ((last != null && last.previous != null) &&
                    (last.color.equals(newNode.color) && last.previous.color.equals(newNode.color))) {
                deleteLast();
                return;
            } else if (isEmpty()) {
                first = newNode;
            } else {
                last.next = newNode;
                newNode.previous = last;
            }
            last = newNode;
        }

        public void deleteFirst() {
            for (int i = 0; i < 2; i++) {
                if (first.next == null) {
                    last = null;
                } else {
                    first.next.previous = null;
                }
                first = first.next;
            }
        }



        public void deleteLast() {
            for (int i = 0; i < 2; i++) {
                if (first.next == null) {
                    first = null;
                } else {
                    last.previous.next = null;
                }
                last = last.previous;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node current = first;
            while (current != null) {
                sb.append(current.color).append(' ');
            }
            String result = sb.toString();
            if (!result.isEmpty()) {
                return result;
            } else {
                return "-1";
            }
        }

    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List list = new List();
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int command = scan.nextInt();
            Node newNode = new Node(scan.next());
            switch (command) {
                case 1:
                    list.addFirst(newNode);
                    break;
                case 2:
                    list.addLast(newNode);

            }
        }
        System.out.println(list);
    }
}

