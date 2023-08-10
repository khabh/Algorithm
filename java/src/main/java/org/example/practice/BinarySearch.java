package org.example.practice;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        problem2();
    }



    public static void problem2() {
        Scanner scanner = new Scanner(System.in);
        int[] tteok = new int[1000000];
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        int result = 0;

        for (int i = 0; i < n; i++) {
            tteok[i] = scanner.nextInt();
        }

        int start = 0;
        int end = Arrays.stream(tteok).max().orElse(0);

        while (start <= end) {
            int mid = (start + end) / 2;
            int tteokLength = 0;

            for (int i = 0; i < n; i++) {
                if (tteok[i] > mid)
                    tteokLength += (tteok[i] - mid);
            }

            if (tteokLength >= target) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }

    private static boolean binarySearch(int[] numbers, int target, int end) {
        int start = 0;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (numbers[mid] == target)
                return true;
            if (numbers[mid] > target) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return false;
    }

    // 부품 찾기
    public static void problem1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[1000000];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        Arrays.sort(numbers, 0, n - 1);

        int m = scanner.nextInt();
        while (m-- > 0) {
            int target = scanner.nextInt();
            boolean isPartExist = binarySearch(numbers, target, n - 1);
            if (isPartExist) {
                System.out.print("yes ");
            } else {
                System.out.print("no ");
            }
        }
    }
}
