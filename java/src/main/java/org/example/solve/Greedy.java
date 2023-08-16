package org.example.solve;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Greedy {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        new Greedy().problem5();
    }

    // 무지의 먹방 라이브
    class Solution {

        public int solution(int[] foodTimes, long k) {
            int count = foodTimes.length;
            int[] sortedFoodTimes = foodTimes.clone();
            Arrays.sort(sortedFoodTimes);

            int avgEaten = 0;
            for (int i = 0; i < count; i++) {
                long time = (long)(sortedFoodTimes[i] - avgEaten) * (count - i);
                if (time > k) {
                    k %= (count - i);
                    break;
                }
                k -= time;
                avgEaten = sortedFoodTimes[i];
            }

            for (int i = 0; i < count; i++) {
                if (foodTimes[i] > avgEaten) {
                    if (k == 0)
                        return i + 1;
                    k--;
                }
            }

            return -1;
        }
    }

    // 볼링공 고르기
    void problem5() {
        int[] count = new int[11];
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int result = 0;

        for (int i = 0; i < n; i++) {
            int weight = scanner.nextInt();
            count[weight]++;
        }

        for (int i = 1; i <= m; i++) {
            n -= count[i];
            result += (count[i] * n);
        }

        System.out.println(result);
    }

    // 만들 수 없는 금액
    void problem4() {
        int n = scanner.nextInt();
        int[] coins = new int[1000];

        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }
        Arrays.sort(coins, 0, n);

        int price = 0;
        for (int i = 0; i < n; i++) {
            if (coins[i] > price + 1) {
                break;
            }
            price += coins[i];
        }

        System.out.println(price + 1);
    }

    // 문자열 뒤집기
    void problem3() {
        String s = scanner.next();
        char pre = '2';
        int one = 0;
        int zero = 0;

        for (char number : s.toCharArray()) {
            if (number == pre)
                continue;
            pre = number;
            if (pre == '0')
                zero++;
            else
                one++;
        }

        System.out.println(Math.min(one, zero));
    }

    // 곱하기 혹은 더하기
    void problem2() {
        int[] numbers = Arrays.stream(scanner.nextLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        int pre = 0;

        for (int number : numbers) {
            if (number * pre < number + pre) {
                pre += number;
                continue;
            }
            pre *= number;
        }

        System.out.println(pre);
    }

    // 모험가 길드
    void problem1() {
        int n = scanner.nextInt();
        int[] fear = new int[100000];
        int result = 0;

        for (int i = 0; i < n; i++) {
            fear[i] = scanner.nextInt();
        }
        Arrays.sort(fear, 0, n);

        int count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            if (fear[i] <= count) {
                count = 0;
                result++;
            }
        }

        System.out.println(result);
    }
}
