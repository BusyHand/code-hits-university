package ru.university.hits.code.module_4;

import java.io.DataInputStream;
import java.io.IOException;

class Джим_на_лодке_Бена_Ганна {

    static class Vertex {

        int i;
        int j;

        boolean visited;

        int data;


        public Vertex(int i, int j, int data) {
            this.i = i;
            this.j = j;
            this.data = data;
        }
    }

    private static Vertex[][] graph;

    public static void main(String[] args) throws IOException {
        Reader scan = new Reader();
        int n = scan.nextInt();
        graph = new Vertex[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = new Vertex(i, j, scan.nextInt());
            }
        }
        DFS(graph[0][0]);
        System.out.println(graph[n - 1][n - 1].visited ? "Yes" : "No");
    }

    private static void DFS(Vertex vertex) {
        if (vertex.visited) {
            return;
        }

        vertex.visited = true;
        int data = vertex.data;
        while (data > 0) {
            int i = vertex.i;
            int j = vertex.j;

            if (data - 8 >= 0) {
                j -= 1;
                data -= 8;
            } else if (data - 4 >= 0) {
                j += 1;
                data -= 4;
            } else if (data - 2 >= 0) {
                i += 1;
                data -= 2;
            } else {
                i -= 1;
                data -= 1;
            }
            if (i >= 0 && i < graph.length && j >= 0 && j < graph.length) {
                DFS(graph[i][j]);
            }
        }
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
