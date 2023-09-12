package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Problem4 {

    private final static String PUSH = "+";
    private final static String POP = "-";


    public static void main(String[] args) {
        StringJoiner resultMaker = new StringJoiner("\n");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int currentNumber = 1;
        int[] expected = IntStream.range(0, n)
                .map(index -> scanner.nextInt())
                .toArray();
        Stack<Integer> stack = new Stack<>();

        for (int nextNumber : expected) {
            while (currentNumber <= nextNumber) {
                stack.add(currentNumber++);
                resultMaker.add(PUSH);
            }
            if (stack.isEmpty() || stack.pop() != nextNumber) {
                System.out.println("NO");
                return;
            }
            resultMaker.add(POP);
        }
        System.out.println(resultMaker);
    }
}
