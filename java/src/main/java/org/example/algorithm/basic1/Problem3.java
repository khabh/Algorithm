package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.Stack;

public class Problem3 {
    private final static char LEFT = '(';

    private static boolean isVPS(String vps) {
        Stack<Character> prev = new Stack<>();
        for (char parenthesis: vps.toCharArray()) {
            if (parenthesis == LEFT) {
                prev.push(parenthesis);
                continue;
            }
            if (prev.isEmpty() || prev.peek() != LEFT)
                return false;
            prev.pop();
        }

        return prev.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            String input = scanner.next();
            if (isVPS(input)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
