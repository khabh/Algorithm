package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Problem14 {
    private static String dfs(String[] alphabets, int prevSelected, int currentIndex, String[] selected) {
        if (currentIndex == selected.length) {
            String result = String.join("", selected);
            if (result.matches("^(?=.*[aeiou].*)(?=.*[bcdfghjklmnpqrstvwxyz].*)(?=.*.[bcdfghjklmnpqrstvwxyz]).*$"))
                return result;
            return null;
        }

        StringJoiner result = new StringJoiner("\n");
        int maxIndex = alphabets.length + 1 - selected.length + currentIndex;
        for (int i = prevSelected + 1; i < maxIndex; i++) {
            selected[currentIndex] = alphabets[i];
            String temp = dfs(alphabets, i, currentIndex + 1, selected);
            if (temp != null && !temp.isBlank())
                result.add(temp);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        int c = scanner.nextInt();
        String[] alphabets = new String[c];
        for (int i = 0; i < c; i++) {
            alphabets[i] = scanner.next();
        }
        Arrays.sort(alphabets);
        String result = dfs(alphabets, -1, 0, new String[l]);
        if (result != null && !result.isBlank())
            System.out.println(result);
    }
}
