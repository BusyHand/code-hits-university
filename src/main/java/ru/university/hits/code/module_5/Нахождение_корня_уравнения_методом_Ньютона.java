package ru.university.hits.code.module_5;

import java.util.Scanner;

import static java.lang.Math.abs;

class Нахождение_корня_уравнения_методом_Ньютона {

    private static double function;
    private static double derivative;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double x = 1;
        double x1 = x;
        refreshFunctionAndDerivativeValue(x);
        if (function * derivative != 0) {
            do {
                x = x1;
                refreshFunctionAndDerivativeValue(x);
                x1 = x - function / derivative;
            } while (abs(x1 - x) > 10e-6);

            if (x1 <= -100 || x1 >= 100) {
                throw new RuntimeException();
            }
        }
        System.out.println("! " + x1);
    }

    private static void refreshFunctionAndDerivativeValue(double x) {
        System.out.println("? " + x);
        System.out.flush();
        function = scanner.nextDouble();
        derivative = scanner.nextDouble();
    }


}
