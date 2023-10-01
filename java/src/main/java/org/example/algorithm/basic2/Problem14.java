package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Problem14 {
    static StringJoiner resultMaker = new StringJoiner("\n");

    private static boolean hasOneVowelAndTwoConsonant(String str) {
        int vowelCount = 0;
        int maxVowelCount = str.length() - 2;
        for (char c : str.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelCount++;
                if (vowelCount > maxVowelCount) {
                    return false;
                }
            }
        }
        return vowelCount >= 1;
    }

    private static void dfs(String[] alphabets, int prevSelected, int currentIndex, String[] selected) {
        if (currentIndex == selected.length) {
            String result = String.join("", selected);
            if (hasOneVowelAndTwoConsonant(result))
                resultMaker.add(result);
            return;
        }

        int maxIndex = alphabets.length + 1 - selected.length + currentIndex;
        for (int i = prevSelected + 1; i < maxIndex; i++) {
            selected[currentIndex] = alphabets[i];
            dfs(alphabets, i, currentIndex + 1, selected);
        }
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
        dfs(alphabets, -1, 0, new String[l]);
        System.out.println(resultMaker);
    }
}
