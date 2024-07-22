package ru.university.hits.code.module_3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Отрезки {

    private static class Stop {

        private final boolean start;
        private final int number;

        public Stop(boolean start, int number) {
            this.start = start;
            this.number = number;
        }

        public int getNumber() {
            return number;
        }


    }

    private static int[] value;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        value = new int[4];
        for (int i = 0; i < 4; i++) {
            value[i] = sc.nextInt();
        }
        if (value[0] > value[1]) {
            swap(0, 1);
        }
        if (value[2] > value[3]) {
            swap(2, 3);
        }
        Stop[] stops = new Stop[4];
        stops[0] = new Stop(true, value[0]);
        stops[1] = new Stop(false, value[1]);
        stops[2] = new Stop(true, value[2]);
        stops[3] = new Stop(false, value[3]);

        Arrays.sort(stops, Comparator.comparing(Stop::getNumber));

        int countStarts = 0;
        Stop lastStart = stops[0];
        int result = 0;
        for (Stop stop : stops) {
            if (stop.start) {
                lastStart = stop;
                countStarts++;
                continue;
            } else if (!stop.start && countStarts == 2) {
                result = stop.number - lastStart.number + 1;
            }
            break;
        }
        System.out.println(result);
    }

    public static void swap(int i, int j) {
        int temp = value[i];
        value[i] = value[j];
        value[j] = temp;
    }


}

