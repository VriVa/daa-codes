// Dynamic Programming
// Time Complexity: O(n^3), all three cases
// Space Complexity: O(n^2) 
// Bottom Up Approach

public class MatrixChainMultiplication {

    public static int matrixChainOrder(int [] dims){
        int n= dims.length;
        int [][] dp = new int [n][n];

        for (int chainLength = 2; chainLength < n; chainLength++) {
            for (int i = 0; i < n-chainLength; i++) {
                int j= i+ chainLength;
                dp[i][j]= Integer.MAX_VALUE;

                for (int k = i+1; k < j; k++) {
                    int cost = dp[i][k] + dp[k][j] + dims[i]*dims[k]*dims[j];

                    dp[i][j]= Math.min(dp[i][j], cost);
                }
            }
            
        }

        return dp[0][n-1];
    }
  
    
    public static void main(String[] args) {
        
        //dimensions of the matrices
        int [] dims ={10, 30, 5, 60};

        System.out.println("Matrix Chain Multiplication Mininum Cost: " + matrixChainOrder(dims));

    }
}
