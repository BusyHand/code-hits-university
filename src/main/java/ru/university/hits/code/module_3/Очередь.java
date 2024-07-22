package ru.university.hits.code.module_3;

import java.util.Scanner;

import static java.lang.Math.max;

class Очередь {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        TimePriorityQueue priorityQueue = new TimePriorityQueue(k);
        for (int i = 0; i < n; i++) {
            priorityQueue.add(scan.nextInt());
        }
        System.out.println(priorityQueue.getMaxTime());
    }

    private static class TimePriorityQueue {
        int[] priorityQueue;
        int size = 0;
        int limit;

        public TimePriorityQueue(int limit) {
            priorityQueue = new int[limit];
            this.limit = limit;

        }

        public void add(int value) {
            if (size < limit) {
                priorityQueue[size] = value;
                siftUp(size);
                size++;
            } else {
                priorityQueue[0] += value;
                siftDown(0);
            }
        }

        public void siftUp(int index) {
            int parentIndex = (index - 1) / 2;

            while (index > 0 && priorityQueue[index] < priorityQueue[parentIndex]) {
                swap(index, parentIndex);
                index = parentIndex;
                parentIndex = (index - 1) / 2;
            }
        }

        public void siftDown(int index) {
            int smallestIndex = index;
            int left = index * 2 + 1;
            int right = index * 2 + 2;

            if (left < size && priorityQueue[left] < priorityQueue[smallestIndex]) {
                smallestIndex = left;
            }

            if (right < size && priorityQueue[right] < priorityQueue[smallestIndex]) {
                smallestIndex = right;
            }

            if (smallestIndex != index) {
                swap(smallestIndex, index);
                siftDown(smallestIndex);
            }
        }

        public void swap(int a, int b) {
            int temp = priorityQueue[a];
            priorityQueue[a] = priorityQueue[b];
            priorityQueue[b] = temp;
        }

        private int getMaxTime() {
            int maxValue = priorityQueue[size - 1];
            for (int i = size / 2 - 1; i >= 0; i--) {
                int left = i * 2 + 1;
                int right = i * 2 + 2;
                if (left < size) {
                    maxValue = max(priorityQueue[left], maxValue);
                }

                if (right < size) {
                    maxValue = max(priorityQueue[right], maxValue);
                }
            }
            return maxValue;
        }

    }
}

