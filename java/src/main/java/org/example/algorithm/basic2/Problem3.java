package org.example.algorithm.basic2;

import java.util.Scanner;

public class Problem3 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int e = scanner.nextInt();
//        int s = scanner.nextInt();
//        int m = scanner.nextInt();
//        int year = 0;
//
//        while (true) {
//            year++;
//            if (verify(year, 15, e) && verify(year, 28, s) && verify(year, 19, m))
//                break;
//        }
//
//        System.out.println(year);
//    }
//
//    private static boolean verify(int year, int standard, int value) {
//        return year % standard == value || (year % standard == 0 && value == standard);
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int e = scanner.nextInt();
        int s = scanner.nextInt();
        int m = scanner.nextInt();
        int i = 0;

        while (true) {
            int year = i * 28 + s;
            if ((year - e) % 15 == 0 && (year - m) % 19 == 0)
                break;
            i++;
        }

        System.out.println(i * 28 + s);
    }
}
