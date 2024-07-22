package ru.university.hits.code.module_3;

import java.util.Scanner;

class Все_функции_на_списке {

    private static class Node {
        int value;
        Node next;
        Node previous;

        Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + "";

        }
    }

    private static class List {
        Node first;
        Node last;

        public boolean isEmpty() {
            return first == null;
        }

        public void addLast(Node node) {
            if (isEmpty()) {
                first = node;
            } else {
                last.next = node;
                node.previous = last;
            }
            last = node;
        }

        public void addFirst(Node node) {
            if (isEmpty()) {
                last = node;
            } else {
                node.next = first;
                first.previous = node;
            }
            first = node;

        }

        public Node getLast() {
            Node node;
            if (first == last) {
                node = first;
                first = last = null;
            } else {
                node = last;
                last = last.previous;
                last.next = null;
            }
            return node;

        }

        public Node getFirst() {
            Node node;
            if (first == last) {
                node = first;
                first = last = null;
            } else {
                node = first;
                first = first.next;
                first.previous = null;
            }
            return node;

        }

        public Node getByValue(int value) {
            Node node;
            Node current = findByValue(value);
            if (current == first) {
                node = getFirst();
            } else if (current == last) {
                node = getLast();
            } else {
                node = current;
                current.previous.next = current.next;
                current.previous = current.previous.previous;
            }
            return node;
        }

        public Node findByValue(int value) {
            Node current = first;
            while (current.value != value) {
                current = current.next;
            }
            return current;
        }

        public void changeValues(int value1, int value2) {
            // Находим узлы с указанными значениями
            Node node1 = findByValue(value1);
            Node node2 = findByValue(value2);

            // Проверяем, что оба узла существуют
            if (node1 != null && node2 != null) {
                // Обрабатываем случай, если узлы смежны
                if (node1.next == node2) {
                    Node temp = node1;
                    node1 = node2;
                    node2 = temp;
                }
                if (node2.next == node1) {
                    // Обрабатываем случай, если узлы смежны
                    Node node2Prev = node2.previous;
                    Node node1Next = node1.next;

                    if (node2Prev != null) {
                        node2Prev.next = node1;
                    } else {
                        first = node1;
                    }

                    if (node1Next != null) {
                        node1Next.previous = node2;
                    } else {
                        last = node2;
                    }

                    node1.next = node2;
                    node2.previous = node1;
                    node2.next = node1Next;
                    node1.previous = node2Prev;
                } else {
                    // Обрабатываем случай, если узлы не смежны
                    Node node1Prev = node1.previous;
                    Node node1Next = node1.next;
                    Node node2Prev = node2.previous;
                    Node node2Next = node2.next;

                    if (node1Prev != null) {
                        node1Prev.next = node2;
                    } else {
                        first = node2;
                    }

                    if (node1Next != null) {
                        node1Next.previous = node2;
                    } else {
                        last = node2;
                    }

                    if (node2Prev != null) {
                        node2Prev.next = node1;
                    } else {
                        first = node1;
                    }

                    if (node2Next != null) {
                        node2Next.previous = node1;
                    } else {
                        last = node1;
                    }

                    node2.next = node1Next;
                    node2.previous = node1Prev;
                    node1.next = node2Next;
                    node1.previous = node2Prev;
                }
            }
        }


        public void addByIndex(Node node, int index) {
            Node current = first;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            if (current == first) {
                addFirst(node);
            } else if (current == null) {
                addLast(node);
            } else if (current == last) {
                node.next = last;
                node.previous = last.previous;
                last.previous.next = node;
                last.previous = node;

            } else {
                node.next = current;
                node.previous = current.previous;
                current.previous.next = node;
                current.previous = node;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node current = first;
            while (current != null) {
                sb.append(current.value).append(' ');
                current = current.next;
            }
            return sb.toString();
        }

        public void sort() {
            first = mergesort(first);
            Node current = first;
            Node prev = null;
            while (current.next != null) {
                current.previous = prev;
                prev = current;
                current = current.next;
            }
            current.previous = prev;
            last = current;
        }

        private Node mergesort(Node start) {
            if (start == null || start.next == null) {
                return start;
            }
            Node oldStart = start;
            Node median = start.next;
            while (median != null && median.next != null) {
                start = start.next;
                median = median.next.next;
            }
            median = start.next;
            start.next = null;


            return merge(mergesort(oldStart), mergesort(median));
        }

        private Node merge(Node a, Node b) {
            Node dummy = new Node(0);
            Node head = dummy;
            Node c = head;

            while (a != null && b != null) {
                if (a.value < b.value) {
                    c.next = a;
                    c = a;
                    a = a.next;
                } else {
                    c.next = b;
                    c = b;
                    b = b.next;
                }
            }
            c.next = a != null ? a : b;
            return head.next;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List list = new List();
        for (int i = 0; i < 100; i++) {
            int command = scanner.nextInt();
            switch (command) {
                case 1 -> {
                    int value = scanner.nextInt();
                    list.addFirst(new Node(value));
                }
                case 2 -> {
                    int value = scanner.nextInt();
                    list.addLast(new Node(value));
                }
                case 3 -> {
                    System.out.println(list.getFirst());
                }
                case 4 -> {
                    System.out.println(list.getLast());
                }
                case 5 -> {
                    int value = scanner.nextInt();
                    System.out.println(list.getByValue(value));
                }
                case 6 -> {
                    int value1 = scanner.nextInt();
                    int value2 = scanner.nextInt();
                    list.changeValues(value1, value2);
                }
                case 7 -> {
                    int value = scanner.nextInt();
                    int index = scanner.nextInt();
                    list.addByIndex(new Node(value), index);
                }
                case 8 -> {
                    list.sort();
                }
            }
            System.out.println(list);
        }
    }
}