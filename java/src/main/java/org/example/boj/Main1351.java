package org.example.boj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main1351 {

    static Map<Long, Long> result = new HashMap<>();

    public static void main(String[] args) {
        result.put(0L, 1L);
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long p = scanner.nextLong();
        long q = scanner.nextLong();
        System.out.println(solve(n, p, q));
    }

    private static long solve(long i, long p, long q) {
        if (result.containsKey(i)) {
            return result.get(i);
        }
        long current = solve(i / p, p, q) + solve(i / q, p, q);
        result.put(i, current);
        return current;
    }
}
