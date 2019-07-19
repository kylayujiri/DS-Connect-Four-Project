import java.util.Arrays;

// most of the code here is taken from the TicTacToeProject and modified to suit Connect4

public class Connect4 {

	static final int BLUE = 1, RED = 2;
	static final int NUM_COLUMNS = 3; // change to 4 for 4x4 board
	static final int NUM_IN_ROW = 3; // change to 4 for 4x4 board
	static int firstplayer;
	static int p1=0,p2=0,ties=0;
	static long cnt=0; // number of recursion calls

	public static void main(String[] args) {


		for (int i=0; i<NUM_COLUMNS; i++) {
			int[ ][ ] list = new int[NUM_COLUMNS][NUM_COLUMNS];
			for (int j = 0; j < list.length; j++) {
				Arrays.fill(list[j], 0);
			}
			firstplayer = RED;
			p1=0;p2=0;cnt=0;ties=0;
			switch(i) {
			case 0: list[list.length-1][0] = RED; break; 
			case 1: list[list.length-1][1] = RED; break; 
			case 2: list[list.length-1][2] = RED; break; 
			case 3: list[list.length-1][3] = RED; break;
			}
			Play(list , BLUE);
			System.out.println ("NetWins: " + (p1-p2));
			System.out.println("RED-wins: "+p1 +"  BLUE-Wins: "+p2);
			System.out.println ("Recursion calls: " + cnt);
			System.out.println();
		}
	}

	public static int Play(int[][] inlist, int clr) {
		cnt++;

		int res = checkBoard(inlist,clr);

		if (res < 3) { // meaning that the game is done
			if (res == 0) {
				ties++;
				return 0; // game is tied
			} else {
				if (res == firstplayer) {
					p1++;
					return 1; // game won by RED
				} else {
					p2++;
					return -1; // game won by BLUE
				}
			}
		}	

		res = 0;

		// for each space that can be the next move
		// make a copy of board (next lines)	
		// update the board for this move


		for (int col = 0; col < NUM_COLUMNS; col++) {

			for (int row = NUM_COLUMNS-1; row >= 0; row--) {
				if (inlist[row][col] == 0) {
					int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
					for (int x = 0; x < NUM_COLUMNS; x++){
						for (int y = 0; y < NUM_COLUMNS; y++){
							clonelist[x][y] = inlist[x][y] ;
						}
					}
					clonelist[row][col] = clr;
					Play(clonelist, 3 - clr);
					break;
				}
			}

		}

		return res;
	}

	public static int checkBoard(int[][] inlist ,int clr){
		// return 0 if board full (tie), 1 if red wins, 2 if blue wins, 3 if game not over

		int chkclr = 3-clr; // other player

		// check if horizontal win
		for (int i = 0 ; i < NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j < NUM_COLUMNS; j++) {
				if (inlist[i][j] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt = 0;
				}
			}
		}

		// check if vertical win
		for (int i = 0 ; i < NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j = 0; j < NUM_COLUMNS; j++) {
				if (inlist[j][i] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt = 0;
				}
			}
		}

		// check if diagonal win (top left to bottom right)
		int colcnt = 0;
		for (int i = 0 ; i < NUM_COLUMNS; i++ ) {
			if (inlist[i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  {return chkclr;}	 
			}  else {
				colcnt = 0;
			}
		}

		// check if diagonal win (bottom left to top right)
		colcnt = 0;
		for (int i = 0 ; i < NUM_COLUMNS; i++ ) {
			if (inlist[NUM_COLUMNS-1-i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
			}  else {
				colcnt = 0;
			}
		}

		// check if board is full; if not, game is not finished
		if (isFull(inlist)) {
			return 0;
		} else {
			return 3;
		}
	}

	public static boolean isFull(int[][] inlist){
		boolean empty = true;
		for (int i = 0 ; i < NUM_COLUMNS ; i++) {
			for (int j = 0 ; j < NUM_COLUMNS ; j++) {
				if (inlist[i][j] == 0) {
					empty = false;
					break;	
				} 
			}
		}
		return empty;
	}
	
	public static void printlist(int[][] inlist) {
		for (int i =0; i<inlist.length; i++) {
			for (int j =0; j<inlist.length; j++) {
				System.out.print(inlist[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}


}