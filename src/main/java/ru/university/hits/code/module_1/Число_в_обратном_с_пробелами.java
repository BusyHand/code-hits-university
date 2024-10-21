package ru.university.hits.code.module_1;

import java.util.Scanner;

public class Число_в_обратном_с_пробелами {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        long tempNumber = number;
        long inverseNumber = 0;

        while (tempNumber > 0) {
            long digit = tempNumber % 10;
            inverseNumber = inverseNumber * 10 + digit;
            tempNumber /= 10;
        }

        while (inverseNumber > 0) {
            long digit = inverseNumber % 10;
            System.out.print(digit + " ");
            inverseNumber /= 10;
        }
        System.out.println();
        while (number > 0) {
            long digit = number % 10;
            System.out.print(digit + " ");
            number /= 10;
        }
    }
}
