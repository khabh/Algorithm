package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().replace("()", ".");
        int count = 0;
        int result = 0;
        for (char p : input.toCharArray()) {
            if (p == '(') {
                count++;
                continue;
            }
            if (p == ')') {
                count--;
                result++;
            }
            else
                result += count;
        }
        System.out.println(result);
    }
}
