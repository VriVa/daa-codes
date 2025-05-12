// time complexity nqueens O(N^2), where N is the number of queens


public class Nqueens {

    final int N;

    public Nqueens(int N) {
        this.N = N;
    }

    public void solve(){
        int [][] board = new int [N][N];

        if (solveNqueens(board,0)){
            printBoard(board);
        }
        else{
            System.out.println("No solution exists");
        }
    }

    private boolean solveNqueens(int [][] board, int row){
        if (row>=N){
            return true;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(board , row, col)){
                board[row][col]=1;
                if (solveNqueens(board, row+1)){
                    return true;
                }
                board[row][col]=0;

            }
            
        }
        return false;
    }

    public boolean isSafe (int [][] board, int row, int col){
        //check column
        for (int i = 0; i < row; i++) {
            if (board[i][col]==1){
                return false;
            }
            
        }

        //check upper left diagonal
        for (int i= row-1, j= col-1; i>=0 && j>=0; i--,j--) {
            if (board[i][j]==1)
            return false;
        }
        //check upper right diagonal
        for (int i = row -1, j=col+1; i>=0 && j<N ; i--, j++) {
            if (board[i][j]==1){
                return false;
            }            
        }
        return true;

    }

     private void printBoard(int[][] board) {
        System.out.println("Solution:");
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print((cell == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
    } 




    public static void main(String[] args) {
        int n=8;

        new Nqueens(n).solve();
    }
    
}
