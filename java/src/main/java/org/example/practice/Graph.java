package org.example.practice;

import java.util.*;

public class Graph {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Graph().problem3();
    }

    class Course {
        private int time = 0;
        private int preTime = 0;
        private int degree;

        private final List<Course> nextCourses = new ArrayList<>();

        public void setTime(int time) {
            this.time = time;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public boolean canTakeCourse() {
            return degree == 0;
        }

        public void decreaseDegree() {
            this.degree--;
        }

        public void addNextCourse(Course course) {
            nextCourses.add(course);
        }

        public List<Course> getNextCourses() {
            return nextCourses;
        }

        public void setPreTime(int preTime) {
            this.preTime = Math.max(this.preTime, preTime);
        }

        public int getTotalTime() {
            return preTime + time;
        }
    }

    // 커리큘럼
    private void problem3() {
        int n = scanner.nextInt();
        Course[] courses = new Course[501];

        for (int i = 1; i <=n ; i++) {
            courses[i] = new Course();
        }

        for (int i = 1; i <= n; i++) {
            int time = scanner.nextInt();
            int degree = 0;
            courses[i].setTime(time);
            while (true) {
                int preCourse = scanner.nextInt();
                if (preCourse == -1)
                    break;
                degree++;
                courses[preCourse].addNextCourse(courses[i]);
            }
            courses[i].setDegree(degree);
        }

        Queue<Course> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (courses[i].canTakeCourse())
                q.add(courses[i]);
        }

        while (!q.isEmpty()) {
            Course course = q.poll();
            int preTime = course.getTotalTime();

            for (Course nextCourse : course.getNextCourses()) {
                nextCourse.decreaseDegree();
                nextCourse.setPreTime(preTime);
                if (nextCourse.canTakeCourse())
                    q.add(nextCourse);
            }
        }

        for (int i = 1; i <=n ; i++) {
            System.out.println(courses[i].getTotalTime());
        }
    }

    class Path implements Comparable<Path> {
        final int firstNode;
        final int secondNode;
        final int distance;

        public Path(int firstNode, int secondNode, int distance) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.distance = distance;
        }

        @Override
        public int compareTo(Path o) {
            return distance - o.distance;
        }
    }

    // 도시 분할 계획
    private void problem2() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] parents = new int[100001];
        boolean[] visited = new boolean[100001];
        int count = n;
        int result = 0;
        PriorityQueue<Path> paths = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            visited[i] = false;
        }

        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            paths.add(new Path(a, b, c));
        }

        while (!paths.isEmpty()) {
            if (count == 2)
                break;
            Path path = paths.poll();
            int a = path.firstNode;
            int b = path.secondNode;

            if (areInSameSet(a, b, parents)) {
                continue;
            }
            unionParent(parents, a, b);
            result += path.distance;
            count--;
        }

        System.out.println(result);
    }

    private static int findParent(int[] parents, int node) {
        int child = node;
        while (child != parents[child]) {
            child = parents[child];
        }
        parents[node] = child;

        return parents[node];
    }

    private static void unionParent(int[] parents, int a, int b) {
        a = findParent(parents, a);
        b = findParent(parents, b);

        if (a < b) {
            parents[b] = a;
            return;
        }
        parents[a] = b;
    }

    private static boolean areInSameSet(int a, int b, int[] parents) {
        return findParent(parents, a) == findParent(parents, b);
    }

    // 팀 결성
    private static void problem1() {
        final int MERGE = 0;
        final int CONFIRM = 1;
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] parents = new int[100001];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        while (m-- > 0) {
            int operator = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            if (operator == MERGE) {
                unionParent(parents, a, b);
            } else if (operator == CONFIRM) {
                if (areInSameSet(a, b, parents))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }
    }
}
