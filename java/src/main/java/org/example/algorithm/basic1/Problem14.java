package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem14 {


    public static void main(String[] args) {
        new Problem14().solve();
    }

    private int calcGCD(int a, int b) {
        int n = a % b;
        while (n != 0) {
            a = b;
            b = n;
            n = a % b;
        }
        return b;
    }

    private void solve() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int gcd = calcGCD(a, b);
        System.out.println(gcd);
        System.out.println(a / gcd * b);
    }
}
