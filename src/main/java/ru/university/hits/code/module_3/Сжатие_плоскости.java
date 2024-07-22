package ru.university.hits.code.module_3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Сжатие_плоскости {

    private static class Event {
        int x1;
        int x2;
        int y;
        int isStart;

        public int getY() {
            return y;
        }

        public int getIsStart() {
            return isStart;
        }

        public Event(int x1, int x2, int y, int isStart) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.isStart = isStart;
        }
    }

    private static class SegmentArray {
        int[] array;

        public SegmentArray(int size) {
            this.array = new int[size];
        }

        public void add(int value, int left, int right) {
            for (int i = left; i < right; i++) {
                array[i] += value;
            }
        }

        public int max() {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < array.length; i++) {
                max = Math.max(array[i], max);
            }
            return max;
        }
    }

    private static int read(Scanner sc) {
        return Integer.parseInt(sc.next());
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = read(sc);
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x1 = read(sc);
            int y1 = read(sc);
            int x2 = read(sc);
            int y2 = read(sc);
            events.add(new Event(x1, x2, y1, 0));
            events.add(new Event(x1, x2, y2, 1));
            x.add(x1);
            x.add(x2);
            y.add(y1);
            y.add(y2);
        }
        events.sort(Comparator.comparing(Event::getY).thenComparing(Event::getIsStart));
        x = x.stream().sorted().distinct().collect(Collectors.toList());

        Map<Integer, Integer> mapX = IntStream.range(0, x.size())
                .boxed()
                .collect(Collectors.toMap(x::get, i -> i + 1));

        SegmentArray segmentArray = new SegmentArray(events.size());
        int max = 1;
        int lastValue = 0;
        for (Event event : events) {
            int value = 1;
            if (event.isStart == 1) {
                if (lastValue != 1) {
                    max = Math.max(segmentArray.max(), max);
                }
                value *= -1;
            }
            lastValue = event.isStart;
            segmentArray.add(value, mapX.get(event.x1) - 1, mapX.get(event.x2));
        }
        System.out.println(max == 1 ? 0 : max);
    }
}

