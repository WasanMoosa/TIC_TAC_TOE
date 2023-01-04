
import java.util.Scanner;

public class TICTACWithResumeFile {

	static char[][] position = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
	static Scanner sc = new Scanner(System.in);
	static char[] symbol = new char[2];
	static int place[]= new int[2];
    static boolean check=true;
    static int index1=0; 
    static int index2=0;

	public static void main(String[] args) {

        boolean win=false; //Stop playing if exist a winner 
		System.out.println("Start playing TIC TAC TOE");
		shownBox();
		System.out.println("\n");
		playerSymbol();

		// Loop for continuity of the game
		for (int count = 0; count < 5; count++) {

			// player turn
			for(int q=0; q<2 && !win; q++) {
				System.out.print("\n");
				System.out.print("Player" +(q+1) + ", Make a move, Enter position: ");
				char move = sc.next().charAt(0);
				check=false;
				
				while (check==false) {
					place =ChoosePosition (move, symbol);
					index1=place[0];
					index2=place[1];
					check=checkValid(index1,index2);
					
					if (check==false) {
					
					System.out.print("Player" +(q+1) + ", Invalid input, re-enter position: ");
					 move = sc.next().charAt(0);}
				}
				
				position[index1][index2]= symbol[q];
				shownBox();
				
				// Win conditions
				if (position[0][0]== position[0][1] && position[0][1] ==position[0][2] ||
					position[1][0]== position[1][1] && position[1][1] ==position[1][2] ||
					position[2][0]== position[2][1] && position[2][1] ==position[2][2] ||
					position[0][0]== position[1][0] && position[1][0] ==position[2][0] ||
					position[0][1]== position[1][1] && position[1][1] ==position[2][1] ||
					position[0][2]== position[1][2] && position[1][2] ==position[2][2] ||
					position[0][0]== position[1][1] && position[1][1] ==position[2][2] ||
					position[0][2]== position[1][1] && position[1][1] ==position[2][0]) {
					
					System.out.print("\n");
			        System.out.println("Player "+ symbol[q] + " Win");
			        win=true;
			        }
				
				//Because 9 times playing not 10 times 
				if (count==4) {
					q++;
					System.out.print("\n");
					 System.out.println("It is draw!!");}
				
				}
				
			}
		 System.out.print("The End!");
		 }
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Function of showing Box
	static void shownBox() {
		for (int i = 0; i < 3; i++) {

			if (i != 0) {
				System.out.print("\n");
				System.out.print("-------------");
			}
			System.out.print("\n");
			for (int r = 0; r < 3; r++) {
				System.out.print(" " + position[i][r] + " |");
			}
		}
	}

	static void playerSymbol() {
		System.out.print("Player 1, choose your symbol: ");
		symbol[0] = sc.next().charAt(0);
		System.out.print("Player 2, choose your symbol: ");
		symbol[1] = sc.next().charAt(0);
		while (symbol[0]==symbol[1]) {
			System.out.print("Player 2, Invalid Symbol, Please choose different symbol ");
			symbol[1] = sc.next().charAt(0);
		}
	}
	static int[] ChoosePosition (char move, char symbol[]) {

		int moveInt = Character.getNumericValue(move);
		int i = (int) Math.ceil(moveInt/3.0)-1;
		int j= moveInt%3;
		if (j==0) {

			j=3;}
		j=j-1;

		int[] s= {i,j};
		return s;
	}
	
	static boolean checkValid (int i , int j) {
	
		if (position[i][j]==symbol[0] ||position[i][j]==symbol[1]) {
			check=false;
		}
		else {
			check=true;
			
		}
			return check;
	}
}
