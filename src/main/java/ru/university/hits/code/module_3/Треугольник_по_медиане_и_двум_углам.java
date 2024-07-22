package ru.university.hits.code.module_3;

import java.util.Scanner;

import static java.lang.Math.*;

class Треугольник_по_медиане_и_двум_углам {

    static class Vertex {
        double x;
        double y;

        Vertex(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y + "\n";
        }
    }

    public static void main(String[] args) {
        Vertex firstVertex = new Vertex(0, 0);
        Vertex secondVertex = new Vertex(0, 0);
        Vertex thirdVertex = new Vertex(0, 0);

        Scanner scanner = new Scanner(System.in);
        double median = scanner.nextDouble();
        double angleA = scanner.nextDouble();
        double angleB = scanner.nextDouble();

        double radA = toRadians(angleA);
        double radB = toRadians(angleB);
        double halfOfMedian = 4 * pow(tan(radA), 2) * pow(tan(radB), 2) - 2 * tan(radA) * tan(radB) + pow(tan(radA), 2) + pow(tan(radB), 2);
        double sumOfTan = (tan(radB) + tan(radA));
        secondVertex.x = 2 * median * sqrt(halfOfMedian) * sumOfTan / halfOfMedian;
        thirdVertex.x = tan(radB) * secondVertex.x / sumOfTan;
        thirdVertex.y = tan(radA) * thirdVertex.x;


        System.out.println("" + firstVertex + secondVertex + thirdVertex);

    }
}