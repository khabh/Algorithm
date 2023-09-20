package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem26 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = new StringBuilder(scanner.next()).reverse().toString();
        int b = scanner.nextInt();
        int result = 0;

        int cipher = 1;
        for (char c : n.toCharArray()) {
            if (Character.isDigit(c)) {
                result += ((c - '0') * cipher);
            } else {
                result += ((c - 'A' + 10) * cipher);
            }
            cipher *= b;
        }
        System.out.println(result);
    }
}
