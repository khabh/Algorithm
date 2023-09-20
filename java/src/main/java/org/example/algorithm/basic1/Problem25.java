package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int b = scanner.nextInt();
        StringBuilder result = new StringBuilder();

        while (n > 0) {
            int remain = n % b;
            n /= b;
            if (remain > 9) {
                result.append((char)(remain - 10 + 'A'));
                continue;
            }
            result.append(remain);
        }

        System.out.println(result.reverse());
    }
}
