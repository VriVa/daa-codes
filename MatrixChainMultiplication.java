public class MatrixChainMultiplication {

    // Function to compute minimum multiplication cost and build the split table
    public static int matrixChainOrder(int[] dims, int[][] split) {
        int n = dims.length;
        int[][] dp = new int[n][n];

        for (int chainLength = 2; chainLength < n; chainLength++) {
            for (int i = 0; i < n - chainLength; i++) {
                int j = i + chainLength;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k] + dp[k][j] + dims[i] * dims[k] * dims[j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        split[i][j] = k; // Store k to reconstruct parentheses
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    // Recursive function to print the optimal parenthesization
    public static void printOptimalParens(int[][] split, int i, int j) {
        if (i + 1 == j) {
            System.out.print("A" + i);
            return;
        }

        System.out.print("(");
        printOptimalParens(split, i, split[i][j]);
        printOptimalParens(split, split[i][j], j);
        System.out.print(")");
    }

    public static void main(String[] args) {
        int[] dims = {10, 30, 5, 60}; // Dimensions for matrices A0: 10x30, A1: 30x5, A2: 5x60

        int n = dims.length;
        int[][] split = new int[n][n]; // To reconstruct optimal parentheses

        int minCost = matrixChainOrder(dims, split);

        System.out.println("Matrix Chain Multiplication Minimum Cost: " + minCost);
        System.out.print("Optimal Parenthesization: ");
        printOptimalParens(split, 0, n - 1);
        System.out.println();
    }
}
