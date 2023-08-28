package org.example.solve;

import java.util.*;
import java.util.stream.IntStream;


public class BinarySearch {

    public static void main(String[] args) {
    }

    class Solution {
        Map<Integer, List<String>> groupedWords = new HashMap<>();
        Map<Integer, List<String>> groupedReversedWords = new HashMap<>();

        public int getMatchCount(List<String> words, String query) {
            if (words == null || words.size() == 0)
                return 0;
            String startQuery = query.replace('?', '0');
            String endQuery = query.replace('?', '{');
            int start = 0;
            int end = words.size() - 1;
            int left = -1;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (words.get(mid).compareTo(startQuery) >= 0) {
                    left = mid;
                    end = mid - 1;
                    continue;
                }
                start = mid + 1;
            }

            if (left == -1)
                return 0;

            start = left;
            end = words.size() - 1;
            int right = -1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (words.get(mid).compareTo(endQuery) <= 0) {
                    right = mid;
                    start = mid + 1;
                    continue;
                }
                end = mid - 1;
            }

            if (right == -1)
                return 0;
            return right - left + 1;
        }

        public int[] solution(String[] words, String[] queries) {
            for (String word : words) {
                groupedWords.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
                groupedReversedWords.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(new StringBuffer(word).reverse().toString());
            }
            groupedWords.keySet()
                    .forEach(wordLength -> groupedWords.get(wordLength).sort(String::compareTo));
            groupedReversedWords.keySet()
                    .forEach(wordLength -> groupedReversedWords.get(wordLength).sort(String::compareTo));

            return Arrays.stream(queries)
                    .mapToInt(query -> {
                        if (query.charAt(0) != '?')
                            return getMatchCount(groupedWords.get(query.length()), query);
                        return getMatchCount(groupedReversedWords.get(query.length()), new StringBuffer(query).reverse().toString());
                    })
                    .toArray();
        }
    }

    // 공유기 설치
    public void problem3() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = scanner.nextInt();
        int[] houses = new int[n];

        IntStream.range(0, n)
                .forEach(index -> {
                    houses[index] = scanner.nextInt();
                });

        Arrays.sort(houses);

        int start = 1;
        int end = (houses[n - 1] - houses[0]) / (c - 1);
        int result = Integer.MAX_VALUE;

        while (start <= end) {
            int minDistance = (start + end) / 2;
            int installedCount = 1;
            int pre = houses[0];
            for (int index = 1; index < n; index++) {
                int distance = houses[index] - pre;
                if (distance >= minDistance) {
                    pre = houses[index];
                    installedCount++;
                    if (installedCount > c)
                        break;
                }
            }

            if (installedCount >= c) {
                result = minDistance;
                start = minDistance + 1;
                continue;
            }
            end = minDistance - 1;
        }

        System.out.println(result);
    }

    // 고정점 찾기
    public void problem2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];

        IntStream.range(0, n)
                .forEach(index -> {
                    numbers[index] = scanner.nextInt();
                });

        int start = 0;
        int end = n - 1;
        int result = -1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] < mid) {
                start = mid + 1;
                continue;
            }
            if (numbers[mid] == mid) {
                result = mid;
                break;
            }
            end = mid - 1;
        }

        System.out.println(result);
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
