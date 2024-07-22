package ru.university.hits.code.module_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Стэк {

    private static class Stack {
        private List<Character> stack = new ArrayList<>();
        private int index;
        private int size = 0;

        public void add(char value) {
            stack.add(size++, value);

        }

        public char get() {
            if (size - 1 < 0) {
                return ' ';
            }
            char value = stack.get(size - 1);
            size--;
            return value;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    private static Map<Character, Character> base = Map.of(')', '(', '}', '{', ']', '[', '>', '<');

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack stack = new Stack();
        String line = sc.nextLine();

        for (char ch : line.toCharArray()) {
            if (base.containsKey(ch)) {
                char returnValue = stack.get();
                if (base.get(ch) != returnValue) {
                    System.out.println(0);
                    return;
                }
            } else {
                stack.add(ch);
            }
        }

        if (stack.isEmpty() && !line.isEmpty()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }


    }
}

