package org.example.algorithm.basic2;

import java.util.*;

public class Problem28 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, Set<Integer>> tree = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            tree.computeIfAbsent(first, index -> new HashSet<>());
            tree.computeIfAbsent(second, index -> new HashSet<>());
            tree.get(first).add(second);
            tree.get(second).add(first);
        }

        for (int i = 0; i < n; i++) {
            result.add(scanner.nextInt());
        }

        if (result.get(0) != 1) {
            System.out.println(0);
            return;
        }

        int addIndex = 1;
        int manageIndex = 0;

        while (true) {
            if (manageIndex >= addIndex) {
                System.out.println(0);
                break;
            }
            Integer currentParent = result.get(manageIndex);
            Set<Integer> nextNumbers = tree.getOrDefault(currentParent, new HashSet<>());

            while (!nextNumbers.isEmpty() && addIndex < n) {
                Integer neededNumber = result.get(addIndex);
                if (nextNumbers.contains(neededNumber)) {
                    nextNumbers.remove(neededNumber);
                    tree.get(neededNumber).remove(currentParent);
                    addIndex++;
                } else {
                    break;
                }
            }
            if (!nextNumbers.isEmpty()) {
                System.out.println(0);
                return;
            }
            if (addIndex == n) {
                System.out.println(1);
                return;
            }
            tree.remove(currentParent);
            manageIndex++;
        }
    }
}
