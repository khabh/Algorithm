package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Problem12 {
    public static void main(String[] args) {
        new Problem12().solve();
    }

    enum Operator {
        PLUS('+', 2),
        MINUS('-', 2),
        MUL('*', 1),
        DIV('/', 1),
        OPEN('(', 3),
        CLOSE(')', 3);


        private final char value;
        private final int priority;

        Operator(char value, int priority) {
            this.value = value;
            this.priority = priority;
        }

        static Operator toOperator(char o) {
            return Arrays.stream(Operator.values())
                    .filter(operator -> operator.value == o)
                    .findFirst()
                    .orElseThrow();
        }

        boolean isPriorityLowerThan(Operator operator) {
            return this.priority >= operator.priority;
        }
    }

    private void solve() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        Stack<Operator> operators = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (char component : input.toCharArray()) {
            if (Character.isAlphabetic(component)) {
                result.append(component);
                continue;
            }
            Operator operator = Operator.toOperator(component);
            if (Operator.OPEN.equals(operator)) {
                operators.push(operator);
                continue;
            }
            if (Operator.CLOSE.equals(operator)) {
                while (!operators.peek().equals(Operator.OPEN)) {
                    result.append(operators.pop().value);
                }
                operators.pop();
                continue;
            }
            while (!operators.isEmpty() && operator.isPriorityLowerThan(operators.peek())) {
                result.append(operators.pop().value);
            }
            operators.push(operator);
        }
        while (!operators.isEmpty()) {
            result.append(operators.pop().value);
        }
        System.out.println(result);
    }
}
