package recursion;

public class Sudoku_solver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, 
		          { 6, 0, 0, 1, 9, 5, 0, 0, 0 }, 
		          { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
		          { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, 
		          { 4, 0, 0, 8, 0, 3, 0, 0, 1 }, 
		          { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
		          { 0, 6, 0, 0, 0, 0, 2, 8, 0 }, 
		          { 0, 0, 0, 4, 1, 9, 0, 0, 5 }, 
		          { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
		int n=9;
		boolean [][] fixedcells= new boolean [n][n];
		setfixedcells(arr,fixedcells,n);
		boolean ans= Sudokusolver(arr,fixedcells, 0,0,n);
		if(ans) {
			printBoard(arr);
		}
		else {
			System.out.println("cannot be solved");
		}
		

	}
	
	static void setfixedcells(int [][] arr, boolean[][] fixedcells, int n) {
		for(int i=0; i<n;i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j]!=0) {
					fixedcells[i][j]=true;
				}
			}
		}
	}
	
	static boolean Sudokusolver(int [][] arr,boolean [][] fixedcells, int row, int col ,int n) {
		if(row==n) {
			return true;
		}
		if(col==n) {
			return Sudokusolver(arr, fixedcells, row+1, 0, n);
		}
		
		if(fixedcells[row][col]) {
			return Sudokusolver(arr, fixedcells, row, col+1, n);
		}
		
		for(int setnum=1; setnum<=n; setnum++) {
			boolean canplace= canplace(arr, row, col, n, setnum);
			if(canplace) {
				arr[row][col]=setnum;
				boolean solverest= Sudokusolver(arr, fixedcells, row, col+1, setnum);
				
				if(solverest) {
					return true;
				}
				else {
					arr[row][col]=0;
				}
			}
		}
		return false;
	}
	
	static boolean canplace(int [][] arr, int row, int col, int n, int setnum) {
		for(int i=0; i<9; i++) {
			
			if(arr[i][col]==setnum) {
				return false;
			}
			if(arr[row][i]==setnum) {
				return false;
			}
		}
		
		int sqrtN= (int)Math.sqrt(n);
		int startrow= (row/sqrtN)*sqrtN;
		int startcol= (col/sqrtN)*sqrtN;
		
		for(int i= startrow; i<startrow+sqrtN;i++) {
			for(int j= startcol; j<startcol+sqrtN;j++) {
				if(arr[i][j]==setnum) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void printBoard(int[][] board) {
	       for(int i=0;i<board.length;i++) {
	    	   for(int j=0;j<board.length;j++) {
	    		   System.out.print(board[i][j]+" ");
	    	   }
	    	   
	    	   System.out.println();
	       }
		}

}
