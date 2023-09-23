package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem45 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int prevNone = 1;
        int prevLeft = 1;
        int prevRight =1;

        for (int i = 2; i <= n; i++) {
            int totalCase = prevNone + prevLeft + prevRight;
            prevLeft = (totalCase - prevLeft) % 9901;
            prevRight =  (totalCase - prevRight) % 9901;
            prevNone = totalCase % 9901;
        }

        System.out.println((prevNone + prevLeft + prevRight) % 9901);
    }
}
