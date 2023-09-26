package org.example.algorithm.basic2;

import java.util.Scanner;
import java.util.StringJoiner;

public class Problem6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        StringJoiner result = new StringJoiner("\n");

        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int year = x;
            boolean validYear = false;

            while (year <= n * m) {
                if ((year - y) % m == 0) {
                    result.add(Integer.toString(year));
                    validYear = true;
                    break;
                }
                year += n;
            }

            if (!validYear) {
                result.add("-1");
            }
        }

        System.out.println(result);
    }
}
