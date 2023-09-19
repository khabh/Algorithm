package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem21 {
    public static void main(String[] args) {
        int[] values = new int[] {4, 2, 1};
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.next();
        StringBuilder result = new StringBuilder();
        while (numbers.length() % 3 != 0) {
            numbers = "0".concat(numbers);
        }
        for (int i = 0; i < numbers.length(); i += 3) {
            int num = 0;
            for (int j = 0; j < 3; j++) {
                if (numbers.charAt(i + j) == '1') {
                    num += values[j];
                }
            }
            result.append(num);
        }
        System.out.println(result);
    }
}
