// time complexity: O(N^2)

import java.util.*;

class PuzzleNode implements Comparable<PuzzleNode> {
    int[][] mat;
    int x, y; // Blank tile position
    int cost, level;
    PuzzleNode parent;

    public PuzzleNode(int[][] mat, int x, int y, int newX, int newY, int level, PuzzleNode parent) {
        this.mat = new int[4][4];
        for (int i = 0; i < 4; i++)
            System.arraycopy(mat[i], 0, this.mat[i], 0, 4);

        // Move blank tile
        this.mat[x][y] = this.mat[newX][newY];
        this.mat[newX][newY] = 0;

        this.x = newX;
        this.y = newY;
        this.level = level;
        this.parent = parent;
        this.cost = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(PuzzleNode other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class FifteenPuzzleProblem{
    static final int N = 4;

    static final int[][] GOAL = {
        {1,  2,  3,  4},
        {5,  6,  7,  8},
        {9, 10, 11, 12},
        {13,14, 15, 0}
    };

    static final int[] row = {-1, 1, 0, 0}; // up, down
    static final int[] col = {0, 0, -1, 1}; // left, right

    public static boolean isSafe(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static int calculateCost(int[][] mat) {
        int cost = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int val = mat[i][j];
                if (val != 0) {
                    int goalX = (val - 1) / N;
                    int goalY = (val - 1) % N;
                    cost += Math.abs(i - goalX) + Math.abs(j - goalY);
                }
            }
        return cost;
    }

    public static void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row)
                System.out.print((val == 0 ? "_" : val) + "\t");
            System.out.println();
        }
        System.out.println();
    }

    public static void printPath(PuzzleNode node) {
        if (node == null) return;
        printPath(node.parent);
        printMatrix(node.mat);
    }

    public static void solve(int[][] start, int x, int y) {
        PriorityQueue<PuzzleNode> pq = new PriorityQueue<>();
        PuzzleNode root = new PuzzleNode(start, x, y, x, y, 0, null);
        root.cost = calculateCost(root.mat);
        pq.offer(root);

        while (!pq.isEmpty()) {
            PuzzleNode min = pq.poll();

            if (min.cost == 0) {
                System.out.println("Solution found in " + min.level + " moves:\n");
                printPath(min);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newX = min.x + row[i];
                int newY = min.y + col[i];

                if (isSafe(newX, newY)) {
                    PuzzleNode child = new PuzzleNode(min.mat, min.x, min.y, newX, newY, min.level + 1, min);
                    child.cost = calculateCost(child.mat);
                    pq.offer(child);
                }
            }
        }

        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        int[][] start = {
            {1,  2,  3,  4},
            {5,  6,  0,  8},
            {9, 10,  7, 12},
            {13,14, 11,15}
        };

        int blankX = 1, blankY = 2; // Position of 0
        solve(start, blankX, blankY);
    }
}
