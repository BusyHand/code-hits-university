package ru.university.hits.code.module_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class Гамильтонов_путь_для_шахматного_коня {

    static class Moves {
        int x;
        int y;

        public Moves(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class ChessBoard {
        List<String> movesPath = new ArrayList<>();
        private final int NUM_OF_MOVES = 8;
        private final int[][] MOVES = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        private List<Moves> moves = new ArrayList<>();
        private int moveNumber;
        private int[][] theBoard;
        private boolean[][] theBoardVisited;
        private int N;
        private int M;

        public ChessBoard(int N, int M) {
            this.N = N;
            this.M = M;
            theBoard = new int[this.N][this.M];
            theBoardVisited = new boolean[this.N][this.M];
            moveNumber = 0;
            for (int[] move : MOVES) {
                moves.add(new Moves(move[0], move[1]));
            }
        }

        public int[] initialPosition() {
            int[] pos = new int[2];
            pos[0] = 0;
            pos[1] = 0;
            theBoard[pos[0]][pos[1]] = ++moveNumber;
            movesPath.add((pos[0] + 1) + " " + (pos[1] + 1));
            return pos;
        }

        public int[] nextMove(int[] pos) {
            int xPos = pos[0];
            int yPos = pos[1];
            int accessibility = NUM_OF_MOVES;

            for (int i = 0; i < NUM_OF_MOVES; i++) {
                int newX = xPos + moves.get(i).x;
                int newY = yPos + moves.get(i).y;
                int newAccessibility = getAccessibility(newX, newY);

                if (inRangeAndEmpty(newX, newY) && newAccessibility < accessibility) {
                    pos[0] = newX;
                    pos[1] = newY;
                    accessibility = newAccessibility;
                }
            }
            if (!theBoardVisited[pos[0]][pos[1]]) {
                theBoardVisited[pos[0]][pos[1]] = true;
                theBoard[pos[0]][pos[1]] = ++moveNumber;
                movesPath.add((pos[0] + 1) + " " + (pos[1] + 1));
            }
            return pos;
        }

        private int getAccessibility(int x, int y) {
            int accessibility = 0;
            for (int i = 0; i < NUM_OF_MOVES; i++) {
                if (inRangeAndEmpty(x + moves.get(i).x, y + moves.get(i).y))
                    accessibility++;
            }
            return accessibility;
        }

        private boolean inRangeAndEmpty(int x, int y) {
            return (x < N && x >= 0 && y < M && y >= 0 &&
                    theBoard[x][y] == 0);
        }

        public void printBoard() {
            movesPath.forEach(System.out::println);
        }

        public int getSize() {
            return N * M;
        }

        public void reload() {
            Collections.shuffle(moves);
            movesPath = new ArrayList<>();
            theBoard = new int[N][M];
            theBoardVisited = new boolean[N][M];
            moveNumber = 0;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ChessBoard knightsChessBoard = new ChessBoard(s.nextInt(), s.nextInt());

        int count = 0;
        while (knightsChessBoard.moveNumber < knightsChessBoard.getSize() - 1 && count < 50) {
            count++;
            knightsChessBoard.reload();
            int[] position = knightsChessBoard.initialPosition();
            for (int i = 1; i < knightsChessBoard.getSize(); i++) {
                position = knightsChessBoard.nextMove(position);
            }
        }

        if (count < 50) {
            knightsChessBoard.printBoard();
        } else {
            System.out.println(-1);
        }

    }
}