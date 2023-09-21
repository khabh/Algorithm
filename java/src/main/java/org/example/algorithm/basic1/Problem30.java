package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem30 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 2; i <= n; i++) {
            int temp = (prev2 + prev1) % 10007;
            prev1 = prev2;
            prev2 = temp;
        }

        System.out.println(prev2);
    }
}
