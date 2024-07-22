package ru.university.hits.code.module_3;

import java.util.Scanner;
import java.util.function.Consumer;

public class Контрольная_модуль_3 {


    private static class Node {
        Node next;
        Node prev;
        int value;

        Node(int value) {
            this.value = value;
        }
    }

    private static class List {
        Node first;
        Node last;

        private boolean isEmpty() {
            return first == null;
        }

        private void addLast(Node newNode) {
            if (isEmpty()) {
                first = newNode;
            } else {
                last.next = newNode;
                newNode.prev = last;
            }
            last = newNode;

        }

        private void addFirst(Node newNode) {
            if (isEmpty()) {
                last = newNode;
            } else {
                first.prev = newNode;
                newNode.next = first;
            }
            first = newNode;
        }

        private Node deleteFirst() {
            Node returnNode = first;
            if (first == last) {
                first = last = null;
            } else {
                first = first.next;
                first.prev = null;
            }
            returnNode.next = returnNode.prev = null;
            return returnNode;
        }

        private Node deleteLast() {
            Node returnNode = last;
            if (last == first) {
                last = first = null;
            } else {
                last = last.prev;
                last.next = null;
            }
            returnNode.next = returnNode.prev = null;
            return returnNode;
        }

        @Override
        public String toString() {
            String result = "";
            Node current = first;
            while (current != null) {
                result += current.value + " ";
                current = current.next;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List list = new List();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            list.addLast(new Node(scanner.nextInt()));
        }

        String command = scanner.next();
        final int offset = scanner.nextInt();
        Consumer<List> run = null;

        switch (command) {
            case "left" -> {
                run = (List l) -> {
                    l.addLast(l.deleteFirst());
                };
            }
            case "right" -> {
                run = (List l) -> {
                    l.addFirst(l.deleteLast());
                };

            }
        }
        for (int i = 0; i < offset; i++) {
            run.accept(list);
        }

        System.out.println(list);
    }
}
