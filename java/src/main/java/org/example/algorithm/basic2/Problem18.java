package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem18 {
    private static int[] selected;

    private static char getSign(int number) {
        if (number > 0)
            return '+';
        if (number < 0)
            return '-';
        return '0';
    }

    private static boolean dfs(char[][] signs, int[] temp, int current) {
        if (current == -1) {
            return true;
        }
        int start = -10;
        int end = 11;
        int currentSign = signs[current][current];
        if (currentSign == '-') {
            end = 0;
        } else if (currentSign == '+') {
            start = 1;
        }
        else {
            start = 0;
            end = 1;
        }

        for (int nextNumber = start; nextNumber < end; nextNumber++) {
            boolean valid = true;
            for (int j = current + 1; j < signs.length; j++) {
                if (getSign(temp[j] + nextNumber) != signs[current][j]) {
                    valid = false;
                    break;
                }
            }
            if (!valid)
                continue;
            for (int j = current; j < temp.length; j++) {
                temp[j] += nextNumber;
            }
            selected[current] = nextNumber;
            if (dfs(signs, temp, current - 1))
                return true;
            for (int j = current; j < temp.length; j++) {
                temp[j] -= nextNumber;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        char[][] signs = new char[n][n];
        String input = scanner.next();
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                signs[i][j] = input.charAt(index++);
            }
        }
        selected = new int[n];
        dfs(signs, new int[n], n - 1);
        System.out.println(Arrays.stream(selected).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }
}
