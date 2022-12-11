import javax.lang.model.element.Element;
import java.util.Scanner;

public class TicTacToe {



	public static void main(String[] args) {
		//[] movepos = new Integer[9];
		Scanner sc= new Scanner(System.in);
		int i=0;
		int mov1=0;
		int mov2=0;
		Object[] movepos= {1,2,3,4,5,6,7,8,9};


		
		
		
		for (int r=0; r<9; r++) {
			if (mov1==(int) movepos[r]) {
				movepos[r]="X";}
				//Displaying the moves box
				System.out.println("Start playing TIC TAC TOE");	
				for (; i<3; i++) {
					System.out.print(" "+movepos[i]+" |");		
				}
				System.out.print("\n");
				System.out.print("-------------");
				System.out.print("\n");
				for (; i<6; i++) {
					System.out.print(" "+movepos[i]+" |");	
				}
				System.out.print("\n");
				System.out.print("-------------");
				System.out.print("\n");
				for (; i<9; i++) {
					System.out.print(" "+movepos[i]+" |");	
				}

				System.out.println("\n");
				System.out.println("Player 1 Symbol: X");
				System.out.println("Player 2 Symbol: O");
				System.out.print("Player 1, Make a move, Enter position: ");
			    mov1=sc.nextInt();


			}

		}


	}



