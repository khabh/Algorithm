package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;

public class Problem1 {

    enum Command {
        PUSH("push"),
        TOP("top"),
        SIZE("size"),
        EMPTY("empty"),
        POP("pop");

        private final String command;

        Command(String command) {
            this.command = command;
        }

        boolean isEqualTo(String command) {
            return this.command.equals(command);
        }
    }

    private static final String NO_ELEMENT = "-1";
    private static final String STACK_IS_EMPTY = "1";
    private static final String STACK_NOT_EMPTY = "0";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        StringJoiner resultMaker = new StringJoiner("\n");

        while (n-- > 0) {
            String command = scanner.next();
            if (Command.PUSH.isEqualTo(command)) {
                int number = scanner.nextInt();
                stack.push(number);
                continue;
            }
            if (Command.POP.isEqualTo(command)) {
                if (stack.isEmpty()) {
                    resultMaker.add(NO_ELEMENT);
                } else {
                    resultMaker.add(Integer.toString(stack.pop()));
                }
                continue;
            }
            if (Command.SIZE.isEqualTo(command)) {
                resultMaker.add(Integer.toString(stack.size()));
                continue;
            }
            if (Command.EMPTY.isEqualTo(command)) {
                if (stack.isEmpty()) {
                    resultMaker.add(STACK_IS_EMPTY);
                } else {
                    resultMaker.add(STACK_NOT_EMPTY);
                }
                continue;
            }
            if (Command.TOP.isEqualTo(command)) {
                if (stack.isEmpty()) {
                    resultMaker.add(NO_ELEMENT);
                } else {
                    resultMaker.add(Integer.toString(stack.peek()));
                }
            }
        }
        System.out.println(resultMaker);
        scanner.close();
    }
}
