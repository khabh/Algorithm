package org.example.solve;

import java.util.*;
import java.util.stream.IntStream;

public class Implement {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        new Implement().problem7();
    }

    // 외벽 점검
    class Problem8 {
        private int[] friends;
        private int[] weaks;
        private boolean[] visited = new boolean[8];

        public int calcFriendsCount(int[] dist) {
            int result = dist.length + 1;
            int n = weaks.length / 2;
            int maxIndex = n * 2;
            for (int i = 0; i < n; i++) {
                int start = i;
                int end = i;
                for (int j = 0; j < dist.length; j++) {
                    while ((end + 1) < maxIndex && (weaks[end + 1] - weaks[start]) <= dist[j])
                        end++;
                    if (end - i + 1 >= n) {
                        result = Math.min(result, j + 1);
                        break;
                    }
                    start = end + 1;
                    end = start;
                }
            }

            return result;
        }

        public int dfs(int[] selected, int index) {
            if (index == friends.length) {
                return calcFriendsCount(selected);
            }
            int result = selected.length + 1;
            for (int i = 0; i < friends.length; i++) {
                if (!visited[i]) {
                    selected[index] = friends[i];
                    visited[i] = true;
                    result = Math.min(result, dfs(selected, index + 1));
                    visited[i] = false;
                }
            }

            return result;
        }

        public int solution(int n, int[] weak, int[] dist) {
            friends = dist;
            weaks = new int[weak.length * 2];
            for (int i = 0; i < weak.length; i++) {
                weaks[i] = weak[i];
                weaks[i + weak.length] = n + weak[i];
            }
            int result = dfs(new int[dist.length], 0);
            if (result > dist.length)
                return -1;
            return result;
        }
    }
    class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistanceFrom(Position position) {
            return Math.abs(x - position.x) + Math.abs(y - position.y);
        }
    }

    class House extends Position {
        private static final int MAX_DISTANCE = 200;

        public House(int x, int y) {
            super(x, y);
        }

        public int getMinDistance(Position[] positions) {
            int minDistance = MAX_DISTANCE;
            for (Position position : positions) {
                minDistance = Math.min(minDistance, getDistanceFrom(position));
            }

            return minDistance;
        }
    }

    Position[] chickens;
    List<House> houses = new ArrayList<>();

    private int closeChicken(int count, int index, boolean[] visited, Position[] selected) {
        if (count == selected.length) {
            return houses.stream()
                    .map(house -> house.getMinDistance(selected))
                    .flatMapToInt(IntStream::of)
                    .sum();
        }

        int result = Integer.MAX_VALUE;

        for (int i = index; i < visited.length; i++) {
            if (!visited[index]) {
                visited[index] = true;
                selected[count] = chickens[i];
                result = Math.min(result, closeChicken(count + 1, index + 1, visited, selected));
                visited[index] = false;
            }
            index++;
        }
        selected[count] = null;

        return result;
    }

    private void problem7() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int type = scanner.nextInt();
                if (type == 0)
                    continue;
                if (type == 1)
                    houses.add(new House(i, j));
                else
                    positions.add(new Position(i, j));
            }
        }

        chickens = positions.toArray(new Position[0]);

        System.out.println(closeChicken(0, 0, new boolean[positions.size()],new Position[m]));
    }


    // 기둥과 보 설치
    class Problem6 {

        private static final int INSTALL = 1;

        class Building {
            private static final int PILLAR = 0;
            private static final int FLOOR = 1;

            private final int size;
            private int componentCount = 0;
            private boolean[][][] struct = new boolean[101][101][2];

            Building(int buildingSize) {
                size = buildingSize;
            }

            public void addComponent(int x, int y, int type) {
                struct[x][y][type] = true;
                componentCount++;
            }

            public void removeComponent(int x, int y, int type) {
                struct[x][y][type] = false;
                componentCount--;
            }

            public boolean isValid() {
                for (int i = 0; i <= size; i++) {
                    for (int j = 0; j <= size; j++) {
                        if ((struct[i][j][PILLAR] && !isValidPillar(i, j)) || (struct[i][j][FLOOR] && !isValidFloor(i, j))) {
                            return false;
                        }
                    }
                }

                return true;
            }

            public int[][] getComponents() {
                int[][] components = new int[componentCount][3];
                int index = 0;
                for (int i = 0; i <= size; i++) {
                    for (int j = 0; j <= size; j++) {
                        if (struct[i][j][PILLAR]) {
                            components[index] = new int[]{i, j, PILLAR};
                            index++;
                        }
                        if (struct[i][j][FLOOR]) {
                            components[index] = new int[]{i, j, FLOOR};
                            index++;
                        }
                    }
                }
                return components;
            }

            private boolean isValidPillar(int x, int y) {
                if (y == 0)
                    return true;
                return struct[x][y - 1][PILLAR] || struct[x][y][FLOOR]
                        || (x > 0 && struct[x - 1][y][FLOOR]);
            }

            private boolean isValidFloor(int x, int y) {
                if (struct[x][y - 1][PILLAR] || struct[x + 1][y - 1][PILLAR])
                    return true;
                return x < size && x > 0 && struct[x - 1][y][FLOOR] && struct[x + 1][y][FLOOR];
            }
        }

        public int[][] solution(int size, int[][] buildFrame) {
            Building building = new Building(size);
            for (int[] buildComponent : buildFrame) {
                int x = buildComponent[0];
                int y = buildComponent[1];
                int type = buildComponent[2];
                int operate = buildComponent[3];

                if (operate == INSTALL) {
                    building.addComponent(x, y, type);
                    if (!building.isValid())
                        building.removeComponent(x, y, type);
                    continue;
                }
                building.removeComponent(x, y, type);
                if (!building.isValid())
                    building.addComponent(x, y, type);
            }

            return building.getComponents();
        }
    }

    class SnakePiece {
        final int x;
        final int y;

        public SnakePiece(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Snake {

        // 오른쪽 아래 왼쪽 위
        int[] dy = new int[]{1, 0, -1, 0};
        int[] dx = new int[]{0, 1, 0, -1};

        int direction;
        Deque<SnakePiece> pieces = new LinkedList<>();

        Snake(int x, int y, int direction) {
            pieces.addLast(new SnakePiece(x, y));
            this.direction = direction;
        }

        void turnRight() {
            direction += 1;
            if (direction == 4)
                direction = 0;
        }

        void turnLeft() {
            direction -= 1;
            if (direction == -1)
                direction = 3;
        }

        boolean moveFront(int[][] board, int limit) {
            SnakePiece snakePiece = pieces.peekFirst();
            int nextX = snakePiece.x + dx[direction];
            int nextY = snakePiece.y + dy[direction];

            if (nextX > limit || nextX < 1 || nextY > limit || nextY < 1 || board[nextX][nextY] == 1)
                return false;
            if (board[nextX][nextY] == 0) {
                SnakePiece removedPiece = pieces.pollLast();
                board[removedPiece.x][removedPiece.y] = 0;
            }
            board[nextX][nextY] = 1;
            pieces.addFirst(new SnakePiece(nextX, nextY));

            return true;
        }
    }

    // 뱀
    private void problem5() {
        final int LEFT = 1;
        final int RIGHT = 2;
        int[][] board = new int[101][101];

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        while (k-- > 0) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            board[x][y] = 2;
        }

        int l = scanner.nextInt();
        int[] moves = new int[10001];
        while (l-- > 0) {
            int time = scanner.nextInt();
            String move = scanner.next();
            moves[time] = move.equals("L") ? LEFT : RIGHT;
        }

        Snake snake = new Snake(1, 1, 0);
        board[1][1] = 1;
        int time = 0;

        while (true) {
            time++;
            boolean isMoveSuccess = snake.moveFront(board, n);
            if (!isMoveSuccess) {
                System.out.println(time);
                break;
            }
            if (moves[time] != 0) {
                if (moves[time] == LEFT)
                    snake.turnLeft();
                else
                    snake.turnRight();
            }
        }
    }

    // 자물쇠와 열쇠
    class problem4 {

        public int[][] rotateKey(int[][] key, int keySize) {
            int[][] newKey = new int[keySize][keySize];

            for (int i = 0; i < keySize; i++) {
                for (int j = 0; j < keySize; j++) {
                    newKey[i][j] = key[j][keySize - i - 1];
                }
            }
            return newKey;
        }

        public boolean isRightKey(int x, int y, int[][] key, int[][] lock) {
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    if (key[i][j] == 1) {
                        if (lock[x + i][y + j] == 1)
                            return false;
                        lock[x + i][y + j] = 1;
                    }
                }
            }

            int lockSize = lock.length / 3;

            for (int i = lockSize; i < lockSize * 2; i++) {
                for (int j = lockSize; j < lockSize * 2; j++) {
                    if (lock[i][j] == 0)
                        return false;
                }
            }

            return true;
        }

        public int[][] cloneLock(int[][] lock) {
            int lockSize = lock.length;
            int[][] newLock = new int[lockSize][lockSize];

            for (int i = 0; i < lockSize; i++) {
                newLock[i] = lock[i].clone();
            }

            return newLock;
        }

        public boolean solution(int[][] key, int[][] lock) {
            int n = lock.length;
            int m = key.length;
            int searchLimit = n * 3 - m - 1;
            int[][] extendedLock = new int[n * 3][n * 3];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    extendedLock[n + i][n + j] = lock[i][j];
                }
            }

            for (int k = 0; k < 4; k++) {
                for (int i = 1; i < searchLimit; i++) {
                    for (int j = 1; j < searchLimit; j++) {
                        boolean result = isRightKey(i, j, key, cloneLock(extendedLock));
                        if (result)
                            return true;
                    }
                }
                key = rotateKey(key, m);
            }

            return false;
        }
    }

    // 문자열 압축
    class Problem3 {
        public int solution(String s) {
            int answer = s.length();

            for (int i = 1; i < s.length(); i++) {
                int end = i;
                int result = 0;
                int currentCount = 0;
                String prev = "";
                boolean needCalc = true;
                while (end <= s.length() && result < answer) {
                    String current = s.substring(end - i, end);
                    end += i;
                    if (current.equals(prev)) {
                        currentCount += 1;
                        continue;
                    }
                    prev = current;
                    result += i;
                    if (result >= answer) {
                        needCalc = false;
                        break;
                    }
                    if (currentCount > 1) {
                        result += (int)(Math.log10(currentCount) + 1);
                    }
                    currentCount = 1;
                }
                if (!needCalc)
                    continue;
                result += (s.length() % i);
                if (currentCount > 1)
                    result += ((int)Math.log10(currentCount) + 1);
                answer = Math.min(result, answer);
            }
            return answer;
        }
    }

    // 문자열 재정렬
    private void problem2() {
        char[] s = scanner.next().toCharArray();
        Arrays.sort(s);

        int result = 0;
        int index = -1;
        while ((index + 1) < s.length && Character.isDigit(s[index + 1])) {
            index++;
            result += (s[index] - '0');
        }
        for (int i = index + 1; i < s.length; i++)
            System.out.print(s[i]);
        if (index > -1)
            System.out.println(result);
    }

    // 럭키 스트레이트
    private void problem1() {
         int[] score = Arrays.stream(scanner.next().split(""))
                 .mapToInt(Integer::parseInt)
                 .toArray();
        int cipher = score.length / 2;
        int leftSum = 0;
        int rightSum = 0;

        for (int i = 0; i < cipher; i++) {
            leftSum += score[i];
        }
        for (int i = cipher; i < score.length; i++) {
            rightSum += score[i];
        }

        if (leftSum == rightSum) {
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }
    }
}
