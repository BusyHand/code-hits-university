package ru.university.hits.code.module_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.min;
import static java.util.stream.Collectors.joining;

class Коэффициент_Жаккара {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(getJakkarCoefficient(s.nextLine(), s.nextLine()));
    }

    private static double getJakkarCoefficient(String firstLine, String secondLine) {
        List<String> normalizeLines = normalizeLine(firstLine, secondLine);
        firstLine = normalizeLines.get(0);
        secondLine = normalizeLines.get(1);
        double count = countOfCoincidences(firstLine, secondLine);
        return count / (firstLine.length() + secondLine.length() - count);
    }

    private static double countOfCoincidences(String firstLine, String secondLine) {
        double count = 0;
        for (int i = 0; i < min(firstLine.length(), secondLine.length()); i++) {
            count += firstLine.charAt(i) == secondLine.charAt(i) ? 1 : 0;
        }
        return count;
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