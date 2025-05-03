// Dynamic Programming
// Time Complexity: O(n*W), where n is the number of items and W is the capacity of the knapsack

import java.util.*;

public class ZeroOneKnapsack {

    public static int knapsack (int capacity, int [] values, int [] weights){
         int n = weights.length;

         int [][] dp = new int [n+1][capacity+1];

         // dp[i][j] = maximum value that can be obtained with i items and j capacity

         for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity  ; j++) {
                if (i==0 || j==0){
                    dp[i][j]=0;
                }
                else if (weights[i-1] <= j){

                    int include = values[i-1] + dp[i-1][j-weights[i-1]];
                   int exclude = dp[i-1][j];
                    dp[i][j]= Math.max(include, exclude);
                }
                else{
                    // Cannot include item i-1, just take the previous best
                    dp[i][j]= dp[i-1][j];      
                }
                }
                
            }
            return dp[n][capacity];
            
         }


    public static void main(String[] args) {
        // input data
        int capacity = 50;
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};

        System.out.println("Item details:");
        System.out.println("Capacity: " + capacity);
        System.out.println("Values: " + Arrays.toString(values));
        System.out.println("Weights: " + Arrays.toString(weights));
        System.out.println("Maximum Profit: " + knapsack(capacity, values, weights));
    }
    
}
