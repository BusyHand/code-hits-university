package ru.university.hits.code.module_5;

import java.util.Scanner;

import static java.lang.Math.max;

class КМП_Поиск {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] line = sc.nextLine().toCharArray();
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            char[] subLine = sc.nextLine().toCharArray();
            System.out.println(kmpSearch(line, subLine));
        }
    }

    private static String kmpSearch(char[] line, char[] subline) {
        StringBuilder result = new StringBuilder();
        int[] prefixFunc = getPrefixFunc(subline);
        int i = 0;
        int j = 0;

        while (i < line.length) {
            if (subline[j] == line[i]) {
                j++;
                i++;
            }
            if (j == subline.length) {
                result.append(i - j + 1).append(' ');
                j = 0;
            } else if (i < line.length && subline[j] != line[i]) {
                if (j != 0) {
                    j = prefixFunc[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return result.toString();
    }

    private static int[] getPrefixFunc(char[] subLine) {
        int[] prefixFunc = new int[subLine.length];
        for (int i = 1; i < subLine.length; i++) {
            int j = 0;
            while (i + j < subLine.length && subLine[i] == subLine[i + j]) {
                prefixFunc[i + j] = max(prefixFunc[i + j], j + 1);
                j++;
            }
        }
        return prefixFunc;
    }

}