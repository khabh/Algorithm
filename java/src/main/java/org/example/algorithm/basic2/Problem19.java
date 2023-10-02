package org.example.algorithm.basic2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem19 {
    static Scanner scanner = new Scanner(System.in);
    static List<Integer> allNumbers = IntStream.range(1, 21).boxed().collect(Collectors.toList());

    enum Command {
        ADD("add", numbers -> {
            numbers.add(scanner.nextInt());
            return Optional.empty();
        }),
        CHECK("check", numbers -> {
            if (numbers.contains(scanner.nextInt()))
                return Optional.of("1");
            return Optional.of("0");
        }),
        REMOVE("remove", numbers -> {
            numbers.remove(scanner.nextInt());
            return Optional.empty();
        }),
        TOGGLE("toggle", numbers -> {
            int number = scanner.nextInt();
            if (numbers.contains(number)) {
                numbers.remove(number);
            } else {
                numbers.add(number);
            }
            return Optional.empty();
        }),
        ALL("all", numbers -> {
            numbers.addAll(allNumbers);
            return Optional.empty();
        }),
        EMPTY("empty", numbers -> {
            numbers.clear();
            return Optional.empty();
        });

        private String value;
        private Function<Set<Integer>, Optional<String>> operate;

        Command(String value, Function<Set<Integer>, Optional<String>> operate) {
            this.value = value;
            this.operate = operate;
        }

        static Command getCommand(String commandInput) {
            for (Command command : Command.values()) {
                if (command.value.equals(commandInput))
                    return command;
            }
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        StringJoiner result = new StringJoiner("\n");
        int n = scanner.nextInt();
        Set<Integer> numbers = new HashSet<>();

        while (n-- > 0) {
            String input = scanner.next();
            Command command = Command.getCommand(input);
            Optional<String> operateResult = command.operate.apply(numbers);
            operateResult.ifPresent(result::add);
        }

        System.out.println(result);
    }
}
