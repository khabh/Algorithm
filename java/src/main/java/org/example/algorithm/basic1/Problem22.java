package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String octal = scanner.next();
        StringBuilder result = new StringBuilder();

        for (char num : octal.toCharArray()) {
            int number = num - '0';
            StringBuilder temp = new StringBuilder();
            while (number > 0) {
                temp.append(number % 2);
                number /= 2;
            }
            while (temp.length() < 3) {
                temp.append('0');
            }
            result.append(temp.reverse());
        }
        int startIndex = 0;
        while (result.length() - startIndex > 1 && result.charAt(startIndex) == '0')
            startIndex++;
        System.out.println(result.substring(startIndex));
    }
}
