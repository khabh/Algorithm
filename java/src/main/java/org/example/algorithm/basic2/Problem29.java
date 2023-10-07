package org.example.algorithm.basic2;

import java.util.*;

public class Problem29 {
    static Map<Integer, Set<Integer>> connection = new HashMap<>();
    private static int[] answer;

    private static boolean removeConnection(int first, int second) {
        if (!connection.containsKey(first) || !connection.get(first).contains(second))
            return false;

        connection.get(first).remove(second);
        connection.get(second).remove(first);
        return true;
    }

    private static boolean solve() {
        int manageIndex = 0;
        int checkIndex = 1;
        int[] prev = new int[answer.length + 1];
        for (int i = 0; i < answer.length; i++) {
            prev[i] = i;
        }

        while (checkIndex < answer.length) {
            int currentNumber = answer[manageIndex];
            int nextNumber = answer[checkIndex];
            if (removeConnection(currentNumber, nextNumber)) {
                prev[checkIndex] = manageIndex;
                manageIndex = checkIndex++;
            } else {
                if (!connection.getOrDefault(currentNumber, new HashSet<>()).isEmpty())
                    return false;
                if (manageIndex == prev[manageIndex])
                    return false;
                manageIndex = prev[manageIndex];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        answer = new int[n];

        for (int i = 1; i < n; i++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            connection.computeIfAbsent(first, index -> new HashSet<>());
            connection.get(first).add(second);
            connection.computeIfAbsent(second, index -> new HashSet<>());
            connection.get(second).add(first);
        }

        for (int i = 0; i < n; i++) {
            answer[i] = scanner.nextInt();
        }

        if (answer[0] != 1) {
            System.out.println(0);
            return;
        }

        if (solve())
            System.out.println(1);
        else
            System.out.println(0);
    }
}
