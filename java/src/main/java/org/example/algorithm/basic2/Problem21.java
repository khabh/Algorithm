package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;

class Node {
    enum Direction {
        DOWN(1),
        RIGHT(0),
        BOTH(2);

        private final int index;

        Direction(int index) {
            this.index = index;
        }

        public Direction getOpposite() {
            if (this.equals(DOWN)) {
                return RIGHT;
            }
            return DOWN;
        }
    }

    static int maxX;
    static int maxY;
    static int[][] numbers;
    static int[] dx = new int[]{0, 1};
    static int[] dy = new int[]{1, 0};

    int x;
    int y;
    Direction direction;

    public Node(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    Node moveRight() {
        return new Node(x + 1, y, Direction.RIGHT);
    }

    Node moveDown() {
        return new Node(x, y + 1, Direction.DOWN);
    }

    public boolean canMoveDown() {
        return x + 1< maxX && numbers[x + 1][y] >= 0;
    }

    public boolean canMoveRight() {
        return y + 1 < maxY && numbers[x][y + 1] >= 0;
    }

    public boolean canMoveNext() {
        if (Direction.DOWN.equals(direction)) {
            return canMoveDown();
        }
        return canMoveRight();
    }

    public Node getNextNode() {
        return new Node(x + dx[direction.index], y + dy[direction.index], direction);
    }

    public Node toggleDirection() {
        direction = direction.getOpposite();
        return this;
    }


    public static Node getStartNode() {
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (numbers[i][j] < 0) {
                    continue;
                }
                if (j > 0 && numbers[i][j - 1] == -2) {
                    return new Node(i, j, Direction.DOWN);
                }
                if (i > 0 && numbers[i - 1][j] == -1) {
                    return new Node(i, j, Direction.RIGHT);
                }
                return new Node(i, j, Direction.BOTH);
            }
        }
        return null;
    }

    public int getNumber() {
        return numbers[x][y];
    }

    public void visitNode() {
        if (Direction.DOWN.equals(direction))
            numbers[x][y] = -1;
        else if (Direction.RIGHT.equals(direction))
            numbers[x][y] = -2;
        else
            numbers[x][y] = -3;
    }

    public void setNumber(int number) {
        numbers[x][y] = number;
    }

    public boolean hasBothDirection() {
        return Direction.BOTH.equals(direction);
    }

    public Node initDirection() {
        direction = Direction.RIGHT;
        return this;
    }

    public static boolean isVisitedNodeCountMax(int count) {
        return count >= maxX * maxY;
    }

    public static void setBoard() {
        Scanner scanner = new Scanner(System.in);
        maxX = scanner.nextInt();
        maxY = scanner.nextInt();

        numbers = new int[maxX][maxY];

        for (int i = 0; i < maxX; i++) {
            numbers[i] = Arrays.stream(scanner.next().split("")).mapToInt(Integer::parseInt).toArray();
        }
    }
}

public class Problem21 {
    private static int dfs(Node node, int current, int prevSum, int count) {
        int result = -1;
        if (node.hasBothDirection()) {
            result = Math.max(dfs(node.initDirection(), current, prevSum, count), dfs(node.toggleDirection(), current, prevSum, count));
            return result;
        }
        int nodeNumber = node.getNumber();
        current = current * 10 + nodeNumber;
        if (Node.isVisitedNodeCountMax(count)) {
            return current + prevSum;
        }
        count++;
        node.visitNode();
        if (node.canMoveNext()) {
            result = Math.max(result, dfs(node.getNextNode(), current, prevSum, count));
        }
        Node nextStartNode = Node.getStartNode();
        result = Math.max(result, dfs(nextStartNode, 0, prevSum + current, count));
        node.setNumber(nodeNumber);

        return result;
    }

    public static void main(String[] args) {
        Node.setBoard();
        System.out.println(dfs(Node.getStartNode(), 0, 0, 1));
    }
}
