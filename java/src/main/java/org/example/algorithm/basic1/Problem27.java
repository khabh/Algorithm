package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;

public class Problem27 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int m = scanner.nextInt();
        Stack<Integer> numbers = new Stack<>();
        StringJoiner result = new StringJoiner(" ");
        int cipher = 1;
        int total = 0;

        while (m-- > 0) {
            numbers.push(scanner.nextInt());
        }
        while (!numbers.isEmpty()) {
            total += (numbers.pop() * cipher);
            cipher *= a;
        }
        while (total > 0) {
            numbers.push(total % b);
            total /= b;
        }

        while (!numbers.isEmpty()) {
            result.add(numbers.pop().toString());
        }
        System.out.println(result);
    }
}
