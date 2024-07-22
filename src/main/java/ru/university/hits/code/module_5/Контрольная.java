package ru.university.hits.code.module_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

public class Контрольная {

    private static class E {
        String word;
        int levinshtein;

        public E(String word, int levinshtein) {
            this.word = word;
            this.levinshtein = levinshtein;
        }

        public int getLevinshtein() {
            return levinshtein;
        }

        public String getWord() {
            return word;
        }
    }

    public static void main(String... args) {
        Scanner s = new Scanner(System.in);
        String sourceLine = s.nextLine();
        List<E> wordList = new ArrayList<>();
        String word;
        while (!(word = s.nextLine()).isBlank()) {
            wordList.add(new E(word, getLevinshtein(sourceLine, word)));
        }

        wordList.sort(comparing(E::getLevinshtein));

        System.out.println(sourceLine);
        System.out.println(wordList.stream()
                .map(E::getWord)
                .collect(joining("\n")));
    }

    private static int getLevinshtein(String firstWord, String secondWord) {
        int m = firstWord.length() + 1;
        int n = secondWord.length() + 1;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int mm = firstWord.charAt(i - 1) != secondWord.charAt(j - 1) ? 1 : 0;
                dp[i][j] = min(dp[i - 1][j] + 1, min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + mm));
            }
        }
        return dp[m - 1][n - 1];
    }


}
