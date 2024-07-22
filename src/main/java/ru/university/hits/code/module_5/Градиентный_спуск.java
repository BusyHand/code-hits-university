package ru.university.hits.code.module_5;

import java.util.Scanner;

import static java.lang.Math.abs;

class Градиентный_спуск {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static double gradientX;
    private static double gradientY;
    private static double valueY;

    public static void main(String[] args) {
        double x0 = 0, y0 = 0;
        double alpha = 0.5;
        refreshValues(x0, y0);
        while (abs(alpha * gradientX) > 1e-6 && abs(alpha * gradientY) > 1e-6) {
            x0 -= alpha * gradientX;
            y0 -= alpha * gradientY;
            refreshValues(x0, y0);
        }
        System.out.println("! " + x0 + " " + y0);
    }

    private static void refreshValues(double x, double y) {
        System.out.println("? " + x + " " + y);
        System.out.flush();
        valueY = SCANNER.nextDouble();
        gradientX = SCANNER.nextDouble();
        gradientY = SCANNER.nextDouble();
    }
}