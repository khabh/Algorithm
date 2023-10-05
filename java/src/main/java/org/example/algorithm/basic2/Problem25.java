package org.example.algorithm.basic2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TomatoStorage {
    private int ripenCount = 0;
    private List<Tomato> visitableTomatoes = new ArrayList<>();
    private final Map<Integer, Tomato> storage = new HashMap<>();

    public void setTomatoes() {
        Scanner scanner = new Scanner(System.in);
        int maxY = scanner.nextInt();
        int maxX = scanner.nextInt();
        Tomato.MoveDirection.setMaxIndex(maxX, maxY);

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                char status = scanner.next().charAt(0);
                if (status == '-') {
                    continue;
                }
                Tomato tomato = new Tomato(i, j, status);
                storage.put(i * maxY + j, tomato);
                if (status == '1') {
                    visitableTomatoes.add(tomato);
                    ripenCount++;
                }
            }
        }
    }

    public boolean hasNotRipenedTomato() {
        return storage.size() > ripenCount;
    }

    public boolean hasVisitableTomatoes() {
        return visitableTomatoes.size() != 0;
    }

    public void spreadRipenedTomatoes() {
        visitableTomatoes = visitableTomatoes.stream()
                .flatMap(Tomato::getReachablePositions)
                .filter(storage::containsKey)
                .map(storage::get)
                .filter(Tomato::isRipeSuccess)
                .collect(Collectors.toList());
        ripenCount += visitableTomatoes.size();
    }
}
class Tomato {
    enum MoveDirection {
        RIGHT(0),
        LEFT(1),
        DOWN(2),
        UP(3);

        private static int maxX;
        private static int maxY;
        private static final int[] dx = new int[] {1, -1, 0, 0};
        private static final int[] dy = new int[] {0, 0, 1, -1};
        private final int index;

        MoveDirection(int index) {
            this.index = index;
        }

        static void setMaxIndex(int x, int y) {
            maxX = x;
            maxY = y;
        }

        public Optional<Integer> getNextPosition(int x, int y) {
            int nextX = x + dx[index];
            if (nextX < 0 || nextX >= maxX)
                return Optional.empty();
            int nextY = y + dy[index];
            if (nextY < 0 || nextY >= maxY)
                return Optional.empty();
            return Optional.of(nextX * maxY + nextY);
        }
    }

    final int x;
    final int y;
    boolean isRipened;

    public Tomato(int x, int y, char status) {
        this.x = x;
        this.y = y;
        this.isRipened = status == '1';
    }

    public Stream<Integer> getReachablePositions() {
        return Arrays.stream(MoveDirection.values())
                .map(this::moveTo)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public Optional<Integer> moveTo(MoveDirection moveDirection) {
        return moveDirection.getNextPosition(x, y);
    }

    public boolean isRipeSuccess() {
        if (isRipened)
            return false;
        isRipened = true;
        return true;
    }
}

public class Problem25 {

    public static void main(String[] args) {
        int dayCount = 0;
        TomatoStorage tomatoStorage = new TomatoStorage();
        tomatoStorage.setTomatoes();
        while (tomatoStorage.hasNotRipenedTomato() && tomatoStorage.hasVisitableTomatoes()) {
            tomatoStorage.spreadRipenedTomatoes();
            dayCount++;
        }
        if (tomatoStorage.hasNotRipenedTomato())
            System.out.println(-1);
        else
            System.out.println(dayCount);
    }
}
