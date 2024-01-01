import java.util.*;

class Solution {
    private static final String[] dir = new String[] {"d", "l", "r", "u"};
    private static final int[] dx = new int[] {1, 0, 0, -1};
    private static final int[] dy = new int[] {0, -1, 1, 0};
    private static final String NO_RESULT = "impossible";

    private int maxX = 50;
    private int maxY = 50;
    private int targetX;
    private int targetY;
    private int count;

    private int calcDist(int x, int y) {
        return Math.abs(x - targetX) + Math.abs(y - targetY);
    }

    class Save implements Comparable<Save> {
        final int x;
        final int y;
        final String prev;

        public Save(int x, int y, String prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }

        @Override
        public int compareTo(Save o) {
            return prev.compareTo(o.prev);
        }
    }

    private String bfs(int a, int b) {
        PriorityQueue<Save> queue = new PriorityQueue<>();
        queue.add(new Save(a, b, ""));
        while (!queue.isEmpty()) {
            Save save = queue.poll();
            int x = save.x;
            int y = save.y;
            int prevCount = save.prev.length();
            int remain = count - prevCount;
            if (prevCount == count) {
                if (x == targetX && y == targetY) {
                    return save.prev;
                }
                continue;
            }
            int dist = calcDist(x, y);
            if (x == targetX && y == targetY && (count - save.prev.length()) % 2 == 1) {
                return NO_RESULT;
            }
            if (remain < dist || (dist - remain) % 2 == 1)
                continue;
            for (int i = 0; i < 4; i ++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 1 || ny < 1 || nx > maxX || ny > maxY)
                    continue;
                queue.add(new Save(nx, ny, save.prev + dir[i]));
            }
        }
        return NO_RESULT;
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        maxX = n;
        maxY = m;
        targetX = r;
        targetY = c;
        count = k;
        return bfs(x, y);
    }
}