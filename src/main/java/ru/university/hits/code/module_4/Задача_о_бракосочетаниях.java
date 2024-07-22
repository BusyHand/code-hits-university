package ru.university.hits.code.module_4;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.sort;
import static java.util.Arrays.stream;

class Задача_о_бракосочетаниях {

    static class Vertex {

        int index;
        int[] rateMap = new int[1000];

        boolean isBusy;

        public Vertex(int index) {
            this.index = index;
        }

        public void addRate(int j, int rate) {
            rateMap[j] = rate;
        }

        public int getIndex() {
            return index;
        }
    }

    static class Woman extends Vertex {

        Man currentMan;

        public Woman(int index) {
            super(index);
        }

        public boolean compareCurrent(Man man) {

            return rateMap[man.index] > rateMap[currentMan.index];
        }
    }

    static class Man extends Vertex {

        private int curentPosition = 0;

        public Man(int index) {
            super(index);
        }

        List<Woman> womanPriority = new ArrayList<>();

        public Woman getWoman() {
            return womanPriority.get(curentPosition);
        }

        public void removeWoman() {
            curentPosition++;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();

        int n = s.nextInt();
        Man[] mans = new Man[n];
        Woman[] womans = new Woman[n];
        fill(n, mans, womans);

        fillRates(s, n, mans, womans);
        fillRates(s, n, womans, mans);
        sortMansWomanPriority(mans);

        int freeMan = n;
        while (freeMan > 0) {
            for (Man man : mans) {
                if (man.isBusy) {
                    continue;
                }

                Woman woman = man.getWoman();
                if (!woman.isBusy) {
                    woman.currentMan = man;
                    woman.isBusy = man.isBusy = true;
                    freeMan--;
                } else if (woman.compareCurrent(man)) {
                    Man oldMan = woman.currentMan;
                    oldMan.isBusy = false;
                    woman.currentMan = man;
                    man.isBusy = true;
                } else {
                    man.removeWoman();
                }
            }
        }

        System.out.println(getMaxSumHappy(mans, womans));
    }

    private static void fill(int n, Man[] mans, Woman[] womans) {
        for (int i = 0; i < n; i++) {
            mans[i] = new Man(i);
            womans[i] = new Woman(i);
        }
    }


    private static void fillRates(Reader s, int n, Vertex[] someFirst, Vertex[] someSecond) throws IOException {
        for (int i = 0; i < n; i++) {
            Vertex vertex = someFirst[i];
            for (int j = 0; j < n; j++) {
                vertex.addRate(j, s.nextInt());
                if (vertex instanceof Man) {
                    ((Man) vertex).womanPriority.add((Woman) someSecond[j]);
                }

            }
        }
    }

    private static void sortMansWomanPriority(Man[] mans) {
        stream(mans).forEach(man -> {
            man.womanPriority
                    .sort(Comparator
                            .comparing((Woman woman) -> man.rateMap[woman.index])
                            .reversed()
                    );
        });
    }

    private static String getMaxSumHappy(Man[] mans, Woman[] womans) {
        sort(womans, Comparator.comparing(woman -> woman.currentMan.index));
        long sum = 0;
        StringBuilder result = new StringBuilder();
        for (Woman woman : womans) {
            sum += woman.rateMap[woman.currentMan.index] + woman.currentMan.rateMap[woman.index];
            result.append((woman.index + 1) + " ");
        }
        result.insert(0, sum + "\n");
        return result.toString();
    }

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

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
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
}