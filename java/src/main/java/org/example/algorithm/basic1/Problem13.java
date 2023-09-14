package org.example.algorithm.basic1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        List<String> suffix = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            suffix.add(input.substring(i));
        }

        suffix.stream().sorted()
                .forEach(System.out::println);
    }
}
