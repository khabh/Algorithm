package org.example.practice;

import java.util.Arrays;
import java.util.Scanner;

public class DynamicProgramming {

    private final static Scanner scanner = new Scanner(System.in);
    private final static int MAX = 30002;

    public static void main(String[] args) {
        problem4();
    }

    // 효율적인 화폐 구성
    public static void problem4() {
        final int INFINITE = 10001;

        int[] price = new int[100];
        int[] count = new int[20002];
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            price[i] = scanner.nextInt();
        }

        Arrays.fill(count, 1, m + 1, INFINITE);


        for (int i = 0; i < m; i++) {
            int nextCount = count[i] + 1;
            for (int p : price) {
                count[i + p] = Math.min(count[i + p], nextCount);
            }
        }

        if (count[m] == INFINITE) {
            System.out.println("-1");
        } else {
            System.out.println(count[m]);
        }
    }


    // 바닥 공사
    public static void problem3() {
        int[] floorCase = new int[1001];
        int n = scanner.nextInt();

        floorCase[1] = 1;
        floorCase[2] = 3;
        for (int i = 3; i <= n; i++) {
            floorCase[i] = (floorCase[i - 1] + floorCase[i - 2] * 2) % 796796;
        }

        System.out.println(floorCase[n]);
    }

    // 개미 전사
    public static void problem2() {
        int[] food = new int[100];
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            food[i] = scanner.nextInt();
        }

        food[1] = Math.max(food[1], food[0]);
        for (int i = 2; i < n; i++) {
            food[i] = Math.max(food[i - 1], food[i - 2] + food[i]);
        }

        System.out.println(food[n - 1]);
    }

    // 1로 만들기
    public static void problem1() {
        int[] count = new int[30001];
        int x = scanner.nextInt();

        for (int i = 2; i <= x; i++)
            count[i] = MAX;
        count[1] = 0;

        for (int i = 1; i < x; i++) {
            int nextCount = count[i] + 1;
            count[i + 1] = Math.min(count[i + 1], nextCount);
            if (i * 2 > x)
                continue;
            count[i * 2] = Math.min(count[i * 2], nextCount);
            if (i * 3 > x)
                continue;
            count[i * 3] = Math.min(count[i * 3], nextCount);
            if (i * 5 > x)
                continue;
            count[i * 5] = Math.min(count[i * 5], nextCount);
        }
        System.out.println(count[x]);
    }
}
