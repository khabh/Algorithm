package org.example.algorithm.basic2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int current = 100;
        Set<Integer> broken = new HashSet<>();

        for (int i = 0; i < m; i++) {
            broken.add(scanner.nextInt());
        }
        int result = Math.abs(current - n);
        int up = 0;

        for (int i = 0; i <= n; i++) {
            result = Math.min(result, getButtonCount(n, n - i, broken));
        }

        while(up <= result) {
            int buttonCount = getButtonCount(n, n + up, broken);
            if (buttonCount != Integer.MAX_VALUE) {
                result = Math.min(buttonCount, result);
                break;
            }
            up++;
        }

        System.out.println(result);
    }

    private static int getButtonCount(int target, int movedChannel, Set<Integer> broken) {
        String channel = String.valueOf(movedChannel);
        boolean canMove = channel.chars()
                .mapToObj(cipher -> cipher - '0')
                .noneMatch(broken::contains);

        if (!canMove) {
            return Integer.MAX_VALUE;
        }

        return channel.length() + Math.abs(target - movedChannel);
    }
}
