package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.Stack;

public class Problem5 {
    enum Command {
        LEFT("L"),
        RIGHT("D"),
        DELETE("B"),
        WRITE("P");

        private final String command;

        Command(String command) {
            this.command = command;
        }

        boolean isEqualsTo(String command) {
            return this.command.equals(command);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        String input = scanner.next();
        for (char c : input.toCharArray()) {
            left.push(c);
        }
        int n = scanner.nextInt();

        while (n-- > 0) {
            String command = scanner.next();
            if (Command.LEFT.isEqualsTo(command)) {
                if (!left.isEmpty()) {
                    right.push(left.pop());
                }
                continue;
            }
            if (Command.RIGHT.isEqualsTo(command)) {
                if (!right.isEmpty()) {
                    left.push(right.pop());
                }
                continue;
            }
            if (Command.DELETE.isEqualsTo(command)) {
                if (!left.isEmpty()) {
                    left.pop();
                }
                continue;
            }
            if (Command.WRITE.isEqualsTo(command)) {
                left.push(scanner.next().charAt(0));
            }
        }
        StringBuffer resultMaker = new StringBuffer();
        while (!left.isEmpty()) {
            resultMaker.append(left.pop());
        }
        StringBuilder result = new StringBuilder(resultMaker.reverse().toString());
        while (!right.isEmpty()) {
            result.append(right.pop());
        }
        System.out.println(result);
    }
}
