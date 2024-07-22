package ru.university.hits.code.module_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.min;
import static java.util.stream.Collectors.joining;

class Расстояние_Левенштейна {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(getJakkarCoefficient(s.nextLine(), s.nextLine()));
    }

    private static int getJakkarCoefficient(String firstLine, String secondLine) {
        List<String> normalizeLines = normalizeLine(firstLine, secondLine);
        firstLine = normalizeLines.get(1);
        secondLine = normalizeLines.get(0);

        int n = firstLine.length() + 1;
        int m = secondLine.length() + 1;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = j;
        }

        int mm = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                mm = firstLine.charAt(i - 1) == secondLine.charAt(j - 1) ? 0 : 1;
                dp[i][j] = min(dp[i][j - 1] + 1, min(dp[i - 1][j] + 1, dp[i - 1][j - 1] + mm));
            }
        }
        return dp[n - 1][m - 1];
    }

    private static List<String> normalizeLine(String... lines) {
        List<String> linesList = new ArrayList<>();
        for (String line : lines) {
            Pattern pattern1 = Pattern.compile("[a-zA-Z\\d\\s]+");
            Matcher matcher = pattern1.matcher(line);
            String firstStep = matcher.results()
                    .map(MatchResult::group)
                    .collect(joining())
                    .toLowerCase();

            Pattern pattern2 = Pattern.compile("[a-z\\d]{4,}\\s?");
            matcher = pattern2.matcher(firstStep);
            linesList.add(matcher.results()
                    .map(MatchResult::group)
                    .collect(joining())
                    .trim());

        }
        return linesList;
    }
}