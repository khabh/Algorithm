package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Problem28 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringJoiner result = new StringJoiner("\n");
        int n = scanner.nextInt();

        if (n == 1)
            return;

        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i])
                continue;
            for (int j = i + i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!isPrime[i])
                continue;
            String number = Integer.toString(i);
            while (n > 0 && n % i == 0) {
                n /= i;
                result.add(number);
            }
            if (n == 0)
                break;
        }

        System.out.println(result);
    }
}
