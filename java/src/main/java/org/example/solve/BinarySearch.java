package org.example.solve;

import java.util.Scanner;
import java.util.stream.IntStream;

public class BinarySearch {

    public static void main(String[] args) {
        new BinarySearch().problem1();
    }

    public void problem1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();

        int[] numbers = IntStream.range(0, n)
                        .map(index -> scanner.nextInt())
                        .toArray();

        int start = 0;
        int end = n - 1;
        int targetStart = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] >= x) {
                if (numbers[mid] == x)
                    targetStart = mid;
                end = mid - 1;
                continue;
            }
            if (numbers[mid] < x) {
                start = mid + 1;
            }
        }

        if (targetStart == -1) {
            System.out.println(-1);
            return;
        }

        start = targetStart;
        end = n - 1;
        int targetLast = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] == x) {
                targetLast = mid;
                start = mid + 1;
            }
            if (numbers[mid] > x) {
                end = mid - 1;
            }
        }

        System.out.println(targetLast - targetStart + 1);
    }
}
