import java.util.Scanner;
class solution {
    public  boolean  isSafe(char[][] board, int row, int col, int num){
        //cond row and col
        for(int i = 0; i<board.length; i++){
            if(board[i][col] == (char)(num + '0')){
                return false;
            }
            if(board[row][i] == (char)(num + '0')){
                return false;
            }
        } 

        //cond grid
        int sr = (row/3)*3;
        int sc = (col/3)*3;

        for(int i=sr; i<sr+3;i++){
            for(int j=sc;j<sc+3;j++){
                if(board[i][j] == (char)(num + '0')){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean helper(char[][] board, int row, int col){
        if(row == board.length){
            return  true;
        }

        int nrow = 0;
        int ncol = 0;

        // to increment the col that is moving to next col of same row
        if(col != board.length-1){
            nrow = row;
            ncol = col+1;        
        }
        //moving to next row after completion of reading last column
        else{
            nrow = row+1;
            ncol = 0;
        }
        
        if(board[row][col] != '.'){
            if(helper(board, nrow, ncol)){
                return true;
            }
        }
        else {
            for(int i=1;i<=9;i++){
                if(isSafe(board, row, col, i)){
                    board[row][col] = (char)(i+'0');
                if(helper(board, nrow, ncol))
                    return true;
                else {
                    board[row][col] = '.';
                }
                }
            }
        }
        return false;
    }

   public static void printpuzzle(char[][] board){
    for(int i = 0;i<board.length-1;i++){
        for(int j = 0;j<board.length-1;j++){
            System.err.print(board[i][j]+ " ");
        }
        System.err.println("");
    }
   }

    public static void main(String[] args) {
        char[][] board = new char[9][9];
        //we use object to convert non static class to static class
        solution obj = new solution();
        Scanner in = new Scanner(System.in);
        System.out.println("enter the puzzle");
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                board[i][j] = in.next().charAt(0);
            }
        }

        System.out.println("before solving :");
        printpuzzle(board);

        if(obj.helper(board,0,0)){
            System.out.println("after solving : ");
            obj.printpuzzle(board);
        }
        else{
            System.err.println("no solution exists");
        }
           
    }
}
