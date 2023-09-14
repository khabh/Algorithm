package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;


public class Problem11 {

    enum Operator {
        PLUS('+'),
        MINUS('-'),
        MUL('*'),
        DIV('/');

        private char operator;

        Operator(char operator) {
            this.operator = operator;
        }

        boolean isEqualTo(char operator) {
            return this.operator == operator;
        }

        static double operate(char operator, double secondNumber, double firstNumber) {
            if (PLUS.isEqualTo(operator)) {
                return firstNumber + secondNumber;
            }
            if (MINUS.isEqualTo(operator)) {
                return firstNumber - secondNumber;
            }
            if (MUL.isEqualTo(operator)) {
                return firstNumber * secondNumber;
            }
            if (DIV.isEqualTo(operator)) {
                return firstNumber / secondNumber;
            }
            throw new IllegalArgumentException();
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String input = scanner.next();
        Stack<Double> stack = new Stack<>();
        double[] values = IntStream.range(0, n)
                .mapToDouble(index -> scanner.nextInt())
                .toArray();

        for (char current : input.toCharArray()) {
            if (Character.isAlphabetic(current)) {
                stack.push(values[current - 'A']);
                continue;
            }
            stack.push(Operator.operate(current, stack.pop(), stack.pop()));
        }
        System.out.printf("%.2f", Math.ceil(stack.pop() * 100) / 100.0);
    }
}
