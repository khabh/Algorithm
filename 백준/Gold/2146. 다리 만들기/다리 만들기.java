import java.util.*;

class IslandSearcher {
    private final int[][] board;
    private final int[] dx = new int[] {1, -1, 0, 0};
    private final int[] dy = new int[] {0, 0, 1, -1};

    public IslandSearcher(int[][] board) {
        this.board = board;
    }

    public Island search(int a, int b, int order) {
        Island island = new Island(order);
        Queue<Island.Block> blocks = new LinkedList<>();
        blocks.add(new Island.Block(a, b));
        board[a][b] = order;
        while (!blocks.isEmpty()) {
            Island.Block block = blocks.poll();
            int x = block.getX();
            int y = block.getY();
            boolean isSeaside = false;
            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + x;
                int nextY = dy[i] + y;
                if (nextY < 0 || nextX < 0 || nextX == board.length || nextY == board.length)
                    continue;
                if (board[nextX][nextY] == 0) {
                    isSeaside = true;
                    continue;
                }
                if (board[nextX][nextY] == 1) {
                    board[nextX][nextY] = order;
                    blocks.add(new Island.Block(nextX, nextY));
                }
            }
            if (isSeaside) {
                island.addSeasideBlock(block);
            }
        }
        return island;
    }
}

class Island {
    static class Block {
        private final int x;
        private final int y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDistanceWith(Block block) {
            return Math.abs(block.x - x) + Math.abs(block.y - y) - 1;
        }
    }

    private final List<Block> seasideBlocks = new ArrayList<>();
    private final int order;

    public Island(int order) {
        this.order = order;
    }

    public void addSeasideBlock(Block block) {
        seasideBlocks.add(block);
    }

    public int getShortestBridgeWith(Island island) {
        int bridgeLength = Integer.MAX_VALUE;

        for (Block block : seasideBlocks) {
            for (Block otherBlock : island.seasideBlocks) {
                bridgeLength = Math.min(bridgeLength, block.getDistanceWith(otherBlock));
            }
        }

        return bridgeLength;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] board = new int[n][n];

        int order = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        IslandSearcher islandSearcher = new IslandSearcher(board);
        List<Island> islands = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    islands.add(islandSearcher.search(i, j, order++));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < islands.size() - 1; i++) {
            Island firstIsland = islands.get(i);
            for (int j = i + 1; j < islands.size(); j++) {
                result = Math.min(result, firstIsland.getShortestBridgeWith(islands.get(j)));
            }
        }

        System.out.println(result);
    }
}