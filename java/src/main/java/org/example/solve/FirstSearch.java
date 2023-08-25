package org.example.solve;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class Robot {
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    static int[] dx = new int[] {0, 0, 1, -1};
    static int[] dy = new int[] {1, -1, 0, 0};
    static Set<Robot> visited = new HashSet<>();
    static Robot invalidRobot = new Robot(-1, -1, -1, -1);

    static {
        visited.add(new Robot(1, 1, 1, 2));
    }

    public Robot(int x1, int y1, int x2, int y2) {
        this.firstX = x1;
        this.firstY = y1;
        this.secondX = x2;
        this.secondY = y2;
    }

    public boolean isNewPosition() {
        if (visited.contains(this))
            return false;
        visited.add(this);
        return true;
    }

    public boolean arrived(int n) {
        return secondX == n && secondY == n;
    }

    public Robot moveParallelTo(int index, int[][] board) {
        int x1 = firstX + dx[index];
        int y1 = firstY + dy[index];
        if (board[x1][y1] == 1)
            return invalidRobot;
        int x2 = secondX + dx[index];
        int y2 = secondY + dy[index];
        if (board[x2][y2] == 1)
            return invalidRobot;
        return new Robot(x1, y1, x2, y2);
    }

    public Stream<Robot> getNextMoves(int[][] board) {
        List<Robot> robots = IntStream.range(0, 4)
                .mapToObj(index -> moveParallelTo(index, board))
                .filter(robot -> robot != invalidRobot)
                .collect(Collectors.toList());

        if (firstY == secondY) {
            if (board[firstX][firstY - 1] == 0 && board[secondX][secondY - 1] == 0) {
                robots.add(new Robot(firstX, firstY - 1, firstX, firstY));
                robots.add(new Robot(secondX, secondY - 1, secondX, secondY));
            }
            if (board[firstX][firstY + 1] == 0 && board[secondX][secondY + 1] == 0) {
                robots.add(new Robot(firstX, firstY, firstX, firstY + 1));
                robots.add(new Robot(secondX, secondY, secondX, secondY + 1));
            }
        } else {
            if (board[firstX - 1][firstY] == 0 && board[secondX - 1][secondY] == 0) {
                robots.add(new Robot(firstX, firstY, firstX - 1, firstY));
                robots.add(new Robot(secondX - 1, secondY, secondX, secondY));
            }
            if (board[firstX + 1][firstY] == 0 && board[secondX + 1][secondY] == 0) {
                robots.add(new Robot(firstX, firstY, firstX + 1, firstY));
                robots.add(new Robot(secondX, secondY, secondX + 1, secondY));
            }
        }

        return robots.stream().filter(Robot::isNewPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return firstX == robot.firstX && firstY == robot.firstY && secondX == robot.secondX && secondY == robot.secondY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstX, firstY, secondX, secondY);
    }
}

public class FirstSearch {
    public static void main(String[] args) {
        new FirstSearch().problem6();
    }

    // 블록 이동하기
    class Solution {

        private void initializeBoard(int[][] board, int[][] givenBoard) {
            int n = givenBoard.length;
            for (int i = 0; i < n; i++) {
                System.arraycopy(givenBoard[i], 0, board[i + 1], 1, n);
            }
            for (int i = 0; i < board.length; i++) {
                board[i][0] = board[i][n + 1] = 1;
            }
            Arrays.fill(board[0], 1);
            Arrays.fill(board[n + 1], 1);
        }

        public int solution(int[][] givenBoard) {
            int boardSize = givenBoard.length + 2;
            int n = givenBoard.length;
            int[][] board = new int[boardSize][boardSize];
            initializeBoard(board, givenBoard);

            Queue<Robot> queue = new LinkedList<>();
            queue.add(new Robot(1, 1, 1, 2));

            int move = 0;
            while (!queue.isEmpty()) {
                Queue<Robot> nextRobots = new LinkedList<>();
                while (!queue.isEmpty()) {
                    Robot robot = queue.poll();
                    if (robot.arrived(n))
                        return move;
                    robot.getNextMoves(board)
                            .forEach(nextRobots::add);
                }
                queue = nextRobots;
                move++;
            }

            return move;
        }
    }


    public class Nation {
        private final int x;
        private final int y;
        private int count;

        public Nation(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public boolean isUniteWith(Nation nation, int high, int low) {
            int gap = Math.abs(nation.count - count);
            return gap <= high && gap >= low;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return count + " ";
        }
    }

    private Nation[][] nations;
    int[] dx = new int[] {0, 0, 1, -1};
    int[] dy = new int[] {1, -1, 0, 0};

    public boolean moveWithUnite(Nation nation, boolean[][] visited, int low, int high) {
        List<Nation> nationsOfUnite = new ArrayList<>();
        nationsOfUnite.add(nation);
        visited[nation.x][nation.y] = true;
        int index = 0;

        while (index < nationsOfUnite.size()) {
            Nation currentNation = nationsOfUnite.get(index);
            IntStream.range(0, 4)
                    .forEach(move -> {
                        int nextX = currentNation.x + dx[move];
                        int nextY = currentNation.y + dy[move];
                        if (nextX < 0 || nextY < 0 || nextX >= nations.length || nextY >= nations.length || visited[nextX][nextY])
                            return;
                        if (!currentNation.isUniteWith(nations[nextX][nextY], high, low))
                            return;
                        nationsOfUnite.add(nations[nextX][nextY]);
                        visited[nextX][nextY] = true;
                    });
            index++;
        }

        if (nationsOfUnite.size() == 1) {
            return false;
        }

        int averageCount = (int)nationsOfUnite.stream()
                .mapToInt(Nation::getCount).average()
                .orElseThrow();
        nationsOfUnite.forEach(n -> n.setCount(averageCount));

        return true;
    }

    // 인구 이동
    public void problem6() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        int count = 0;

        nations = new Nation[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nations[i][j] = new Nation(i, j, scanner.nextInt());
            }
        }

        while (true) {
            boolean[][] visited = new boolean[n][n];
            boolean moved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        moved = moveWithUnite(nations[i][j], visited, l, r) || moved;
                    }
                }
            }
            if (!moved)
                break;
            count++;
        }

        System.out.println(count);
    }

    public class Teacher {
        private final int x;
        private final int y;

        public Teacher(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    List<Teacher> teachers = new ArrayList<>();

    public boolean areHidden(char[][] board) {
        for (Teacher teacher : teachers) {
            int x = teacher.x;
            int y = teacher.y;
            int moveDown = x + 1;
            while (moveDown < board.length && board[moveDown][y] != 'O') {
                if (board[moveDown][y] == 'S')
                    return false;
                moveDown++;
            }
            int moveUp = x - 1;
            while (moveUp >= 0 && board[moveUp][y] != 'O') {
                if (board[moveUp][y] == 'S')
                    return false;
                moveUp--;
            }
            int moveRight = y + 1;
            while (moveRight < board.length && board[x][moveRight] != 'O') {
                if (board[x][moveRight] == 'S')
                    return false;
                moveRight++;
            }
            int moveLeft = y - 1;
            while (moveLeft >= 0 && board[x][moveLeft] != 'O') {
                if (board[x][moveLeft] == 'S')
                    return false;
                moveLeft--;
            }
        }

        return true;
    }

    public boolean addObstacle(int x, int y, char[][] board, int count) {
        if (count == 3) {
            return areHidden(board);
        }
        for (int i = y + 1; i < board.length; i++) {
            if (board[x][i] == 'X') {
                board[x][i] = 'O';
                if (addObstacle(x, i, board, count + 1))
                    return true;
                board[x][i] = 'X';
            }
        }

        for (int i = x + 1; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = 'O';
                    if (addObstacle(i, j, board, count + 1))
                        return true;
                    board[i][j] = 'X';
                }
            }
        }

        return false;
    }


    public void problem5() {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[6][6];
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scanner.next().charAt(0);
                if (board[i][j] == 'T')
                    teachers.add(new Teacher(i, j));
            }
        }

        if (addObstacle(0, -1, board, 0)) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }

    public class MinAndMaxNumbers {
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;

        public void update(int result) {
            min = Math.min(min, result);
            max = Math.max(max, result);
        }
    }

    private final MinAndMaxNumbers minAndMaxNumbers = new MinAndMaxNumbers();


    public int calcExpression(List<Integer> numbers, int[] operators) {
        int result = numbers.get(0);
        for (int index = 1; index < numbers.size(); index++) {
            int number = numbers.get(index);
            int operator = operators[index];
            if (operator == 0) {
                result += number;
                continue;
            }
            if (operator == 1) {
                result -= number;
                continue;
            }
            if (operator == 2) {
                result *= number;
                continue;
            }
            result /= number;
        }

        return result;
    }

    public void createMathExpression(List<Integer> numbers, int[] operators, int[] selected, int index) {
        if (index == selected.length) {
            minAndMaxNumbers.update(calcExpression(numbers, selected));
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                selected[index] = i;
                createMathExpression(numbers, operators, selected, index + 1);
                operators[i]++;
            }
        }
    }

    // 연산자 끼워넣기
    public void problem4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>();
        IntStream.range(0, n)
                .forEach((index) -> numbers.add(scanner.nextInt()));
        int[] operators = new int[4];
        IntStream.range(0, 4)
                        .forEach(index -> operators[index] = scanner.nextInt());

        createMathExpression(numbers, operators, new int[n], 1);
        System.out.println(minAndMaxNumbers.max);
        System.out.println(minAndMaxNumbers.min);
    }


    // 괄호 변환
    class Problem3 {
        private static final char LEFT = '(';
        private static final char RIGHT = ')';

        public boolean isValid(String str) {
            Stack<Character> validator = new Stack<>();
            for (char c : str.toCharArray()) {
                if (c == LEFT) {
                    validator.push(c);
                    continue;
                }
                if (validator.isEmpty())
                    return false;
                validator.pop();
            }

            return validator.isEmpty();
        }

        public boolean isBalanced(String str) {
            int left = 0;
            int right = 0;
            for (char c : str.toCharArray()) {
                if (c == LEFT) {
                    left++;
                    continue;
                }
                right++;
            }

            return left == right;
        }

        public String flip(String str) {
            String result = "";
            for (int i = 1; i < str.length() - 1; i++) {
                if (str.charAt(i) == LEFT)
                    result += RIGHT;
                else
                    result += LEFT;
            }

            return result;
        }

        public String convert(String w) {
            if (w.length() == 0 || isValid(w))
                return w;
            int end = 2;
            String u = "", v = "";
            while (end <= w.length()) {
                u = w.substring(0, end);
                v = w.substring(end);
                if (isBalanced(u) && isBalanced(v))
                    break;
                end += 2;
            }
            if (isValid(u)) {
                return u + convert(v);
            }
            return "(" + convert(v) + ")" + flip(u);
        }
        public String solution(String p) {
            return convert(p);
        }
    }

    class Virus implements Comparable<Virus> {
        private final int x;
        private final int y;
        private final int type;

        public Virus(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public int getType() {
            return type;
        }

        @Override
        public int compareTo(Virus o) {
            return type - o.getType();
        }
    }

    // 경쟁적 전염
    public void problem2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] board = new int[n + 1][n + 1];

        List<Virus> viruses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int type = scanner.nextInt();
                if (type == 0)
                    continue;
                board[i][j] = type;
                viruses.add(new Virus(i, j, type));
            }
        }

        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        int s = scanner.nextInt();
        int resultX = scanner.nextInt();
        int resultY = scanner.nextInt();
        int time = 0;

        while (time++ < s) {
            viruses.sort(Virus::compareTo);
            List<Virus> nextViruses = new ArrayList<>();

            for (Virus virus : viruses) {
                int type = virus.getType();
                for (int i = 0; i < 4; i++) {
                    int nextX = dx[i] + virus.x;
                    int nextY = dy[i] + virus.y;
                    if (nextX < 1 || nextY < 1 || nextY > n || nextX > n || board[nextX][nextY] != 0)
                        continue;
                    board[nextX][nextY] = type;
                    nextViruses.add(new Virus(nextX, nextY, type));
                }
            }
            viruses = nextViruses;
        }

        System.out.println(board[resultX][resultY]);
    }

    // 연구소
    public void problem1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int x = scanner.nextInt();

        boolean[] visited = new boolean[n + 1];
        int distance = 0;

        Map<Integer, List<Integer>> graph = new HashMap<>();

        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>());
            graph.get(a).add(b);
        }

        Queue<Integer> queue = new LinkedList<>();
        visited[x] = true;
        queue.add(x);
        while (distance < k) {
            Queue<Integer> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (!graph.containsKey(current))
                    continue;
                for (int node : graph.get(current)) {
                    if (visited[node])
                        continue;
                    visited[node] = true;
                    nextQueue.add(node);
                }
            }
            queue = nextQueue;
            if (queue.isEmpty()) {
                System.out.println("-1");
                return;
            }
            distance++;
        }

        queue.stream().mapToInt(Integer::intValue)
                .sorted()
                .forEach(System.out::println);
    }
}
