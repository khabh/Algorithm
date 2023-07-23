package org.example.practice;

import java.util.Scanner;

public class Greedy {

    public static void main(String[] args) {
    }

    // 큰 수의 법칙
    static void problem2() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        int first = 0;
        int second = 0;

        while (N-- != 0) {
            int number = scanner.nextInt();
            if (number >= first) {
                second = first;
                first = number;
            }
            else if (number > second) {
                second = number;
            }
        }

        int result = M / (K + 1) * (first * K + second);
        result = result + M % (K + 1) * first;

        System.out.println(result);
    }

    // 숫자 카드 게임
    static void problem3() {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int minNumber = 10001;
            for (int j = 0; j < M; j++) {
                minNumber = Math.min(sc.nextInt(), minNumber);
            }
            result = Math.max(result, minNumber);
        }

        System.out.println(result);
    }

    // 1이 될 때까지
    static void problem4() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int result = 0;

        while (N >= K) {
            while (N % K != 0) {
                N--;
                result++;
            }
            result++;
            N /= K;
        }

        result += (N - 1);

        System.out.println(result);
    }
}
