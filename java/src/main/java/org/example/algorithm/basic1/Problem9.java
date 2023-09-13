package org.example.algorithm.basic1;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.IntStream;

class Node implements Comparable<Node> {
    int index;
    int value;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return value - o.value;
    }
}

public class Problem9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = IntStream.range(0, n)
                .map(index -> scanner.nextInt())
                .toArray();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            while (!queue.isEmpty() && queue.peek().value < number) {
                Node node = queue.poll();
                numbers[node.index] = number;
            }
            queue.add(new Node(i, number));
        }
        queue.forEach(node -> numbers[node.index] = -1);
        StringJoiner resultMaker = new StringJoiner(" ");
        for (int number : numbers) {
            resultMaker.add(Integer.toString(number));
        }
        System.out.println(resultMaker);
    }
}
