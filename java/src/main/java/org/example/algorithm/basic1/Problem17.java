package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;

        while (n >= 5) {
            n /= 5;
            count += n;
        }
        System.out.println(count);
    }
}
