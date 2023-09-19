package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        StringBuilder result = new StringBuilder();

        while (n != 0) {
            int remain = n % -2;
            n /= (-2);
            if (remain == -1) {
                n++;
                remain = 1;
            }
            result.append(remain);
        }
        System.out.println(result.reverse());
    }
}
