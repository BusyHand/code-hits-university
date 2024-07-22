package ru.university.hits.code.module_3;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Максимальные_ключи {
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


    private static class Entity {
        long k;
        long v;

        int index = -1;

        public Entity(long k, long v) {
            this.k = k;
            this.v = v;
        }
    }

    private static class BoundedHeap {
        private StringBuilder result = new StringBuilder();
        private Entity[] heap = new Entity[10];

        private int size = 0;

        private int maxSize = 10;


        public void add(Entity entity) {
            if (size < maxSize) {
                heap[size] = entity;
                entity.index = size;
                siftUp(size);
                size++;
            } else if (entity.v >= heap[0].v) {
                heap[0].index = -1;
                entity.index = 0;
                heap[0] = entity;
                siftDown(0);
            }
        }

        public void siftUp(int index) {
            int parentIndex = (index - 1) / 2;
            while (index > 0 && heap[index].v <= heap[parentIndex].v) {
                swap(index, parentIndex);
                index = parentIndex;
                parentIndex = (index - 1) / 2;
            }
        }

        public void siftDown(int index) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largestIndex = index;

            if (leftChildIndex < size && heap[leftChildIndex].v <= heap[largestIndex].v) {
                largestIndex = leftChildIndex;
            }
            if (rightChildIndex < size && heap[rightChildIndex].v <= heap[largestIndex].v) {
                largestIndex = rightChildIndex;
            }

            if (largestIndex != index) {
                swap(index, largestIndex);
                siftDown(largestIndex);
            }
        }

        private void swap(int i, int j) {
            Entity temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
            heap[i].index = j;
            heap[j].index = i;
        }

        public void appendResult() {
            for (int i = 0; i < 10 && i < size; i++) {
                result.append(heap[i].k).append(' ');
            }
            result.append('\n');
        }

        public String getResult() {
            return result.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        long n = s.nextLong();
        BoundedHeap heap = new BoundedHeap();
        Map<Long, Entity> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long k = s.nextLong();
            long x = s.nextLong();
            Entity entity;
            if (!map.containsKey(k)) {
                entity = new Entity(k, x);
                heap.add(entity);
                map.put(k, entity);

            } else {
                entity = map.get(k);
                entity.v += x;
                if (entity.index == -1) {
                    heap.add(entity);
                } else {
                    heap.siftDown(entity.index);
                }
            }
            heap.appendResult();
        }
        System.out.println(heap.getResult());
    }
}