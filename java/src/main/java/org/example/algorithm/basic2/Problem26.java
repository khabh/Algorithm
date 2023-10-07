package org.example.algorithm.basic2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum Movement {
    RIGHT(0, 1),
    LEFT(0, -1),
    UP(-1, 0),
    DOWN(1, 0);

    private static final int UNREACHABLE = -1;
    private static int maxX;
    private static int maxY;

    private final int dx;
    private final int dy;

    Movement(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static void setMaxIndex(int maxX, int maxY) {
        Movement.maxX = maxX;
        Movement.maxY = maxY;
    }

    public int move(int x, int y) {
        int nextX = x + dx;
        int nextY = y + dy;
        if (nextX < 0 || nextX >=  maxX || nextY < 0 || nextY >= maxY) {
            return UNREACHABLE;
        }
        return nextX * maxY + nextY;
    }
}

class Dot {
    private static final int NOT_VISITED = -1;
    private static final int ALREADY_CHECKED = -2;
    private static final int START = 1;

    private final int x;
    private final int y;
    private final char color;
    private int visitedOrder = NOT_VISITED;

    public Dot(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public boolean isNotVisited() {
        return visitedOrder == NOT_VISITED;
    }

    public IntStream getReachablePositions() {
        return Arrays.stream(Movement.values())
                .mapToInt(movement -> movement.move(x, y))
                .filter(index -> index >= 0);
    }

    public void setAsStartDot() {
        visitedOrder = START;
    }

    public boolean hasColorOf(char color) {
        return this.color == color;
    }

    public boolean makeChainWith(Dot dot) {
        return dot.visitedOrder >= START && (visitedOrder - dot.visitedOrder) >= 3;
    }

    public boolean canLinkWith(Dot dot) {
        return dot.isNotVisited() && dot.hasColorOf(color);
    }

    public void linkTo(Dot prevDot) {
        visitedOrder = prevDot.visitedOrder + 1;
    }

    public void uncheckVisited() {
        visitedOrder = NOT_VISITED;
    }

    public void setAlreadyChecked() {
        visitedOrder = ALREADY_CHECKED;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                ", visitedOrder=" + visitedOrder +
                '}';
    }
}

class DotStorage {
    private final List<Dot> dots;

    private DotStorage(List<Dot> dots) {
        this.dots = dots;
    }

    public void print() {
        for (Dot dot : dots) {
            System.out.println(dot);
        }
    }

    public static DotStorage setNewDots() {
        Scanner scanner = new Scanner(System.in);
        int maxX = scanner.nextInt();
        int maxY = scanner.nextInt();
        Movement.setMaxIndex(maxX, maxY);
        List<Dot> dots = new ArrayList<>();
        for (int i = 0; i < maxX; i++) {
            char[] input = scanner.next().toCharArray();
            for (int j = 0; j < maxY; j++) {
                dots.add(new Dot(i, j, input[j]));
            }
        }

        return new DotStorage(dots);
    }

    public boolean dfs(Set<Dot> visitedDots, Dot currentDot) {
        System.out.println("currentDot = " + currentDot);
        List<Dot> nextDots = currentDot.getReachablePositions()
                .mapToObj(dots::get)
                .collect(Collectors.toList());

        for (Dot nextDot : nextDots) {
            if (currentDot.makeChainWith(nextDot))
                return true;
            if (currentDot.canLinkWith(nextDot)) {
                visitedDots.add(nextDot);
                nextDot.linkTo(currentDot);
                if (dfs(visitedDots, nextDot))
                    return true;
                nextDot.uncheckVisited();
            }
        }
        return false;
    }

    public String findChain() {
        for (Dot dot : dots) {
            if (dot.isNotVisited()) {
                Set<Dot> newVisitedDots = new HashSet<>();
                dot.setAsStartDot();
                System.out.println("dot = " + dot);
                if (dfs(newVisitedDots, dot))
                    return "Yes";
                newVisitedDots.forEach(Dot::setAlreadyChecked);
            }
        }
        return "No";
    }
}

public class Problem26 {

    public static void main(String[] args) {
        DotStorage dotStorage = DotStorage.setNewDots();
        System.out.println(dotStorage.findChain());
    }
}
