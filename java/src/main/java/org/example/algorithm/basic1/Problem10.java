package org.example.algorithm.basic1;

import java.util.*;

class Number implements Comparable<Number> {
    int count;
    int index;

    public Number(int count, int index) {
        this.count = count;
        this.index = index;
    }

    @Override
    public int compareTo(Number o) {
        return count - o.count;
    }
}

public class Problem10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, Integer> counts = new HashMap<>();
        PriorityQueue<Number> queue = new PriorityQueue<>();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            counts.put(number, counts.getOrDefault(number, 0) + 1);
            numbers[i] = number;
        }

        for (int i = 0; i < n; i++) {
            int number = numbers[i];
            int count = counts.get(number);
            while (!queue.isEmpty() && queue.peek().count < count) {
                Number selected = queue.poll();
                numbers[selected.index] = number;
            }
            queue.add(new Number(count, i));
        }

        queue.forEach(number -> numbers[number.index] = -1);

        StringJoiner resultMaker = new StringJoiner(" ");
        for (int i = 0; i < n; i++) {
            resultMaker.add(Integer.toString(numbers[i]));
        }
        System.out.println(resultMaker);
    }
}
