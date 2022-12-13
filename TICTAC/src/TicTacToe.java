import javax.lang.model.element.Element;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] symbol = new char[2];
		char mov1 = '0';
		char mov2 = '0';
		boolean draw = true;
		char[] movepos = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		boolean check = false; // Check if the position is already taken

		System.out.println("Start playing TIC TAC TOE");

		System.out.println("Player 1, choose your symbol ");
		symbol[0] = sc.next().charAt(0);
		System.out.println("Player 2, choose your symbol ");
		symbol[1] = sc.next().charAt(0);

		// Loop for continuity of the game
		for (int count = 0; count < 10; count++) {

			// Print out box
			int i = 0;
			for (; i < 3; i++) {
				System.out.print(" " + movepos[i] + " |");
			}
			System.out.print("\n");
			System.out.print("-------------");
			System.out.print("\n");
			for (; i < 6; i++) {
				System.out.print(" " + movepos[i] + " |");
			}
			System.out.print("\n");
			System.out.print("-------------");
			System.out.print("\n");
			for (; i < 9; i++) {
				System.out.print(" " + movepos[i] + " |");
			}
			// Win conditions
			if (movepos[0] == movepos[1] && movepos[0] == movepos[2]
					|| movepos[0] == movepos[3] && movepos[3] == movepos[6]
					|| movepos[1] == movepos[4] && movepos[1] == movepos[7]
					|| movepos[2] == movepos[5] && movepos[5] == movepos[8]
					|| movepos[3] == movepos[4] && movepos[4] == movepos[5]
					|| movepos[6] == movepos[7] && movepos[7] == movepos[8]
					|| movepos[2] == movepos[4] && movepos[4] == movepos[6]
					|| movepos[0] == movepos[4] && movepos[4] == movepos[8]) {

				System.out.print("\n");
				System.out.println("Well done");
				draw = false;
			}
			System.out.println("\n");
			System.out.println("Player 1 Symbol: " + symbol[0]);
			System.out.println("Player 2 Symbol: " + symbol[1]);

			check = false;

			if (count != 9) {
				// For player 1 taking input, checking and replacing symbol
				if (count % 2 == 0) {
					System.out.print("Player 1, Make a move, Enter position: ");
					mov1 = sc.next().charAt(0);
					while (check == false) {
						for (int d = 0; d < 9; d++) {
							if (mov1 == movepos[d]) {
								check = true;
								break;
							}
						}
						if (check == false) {
							System.out.print("Player 1, Invalid input, re-enter position: ");
							mov1 = sc.next().charAt(0);
						}
					}

				}

				// For player 2 taking input, checking and replacing symbol
				else {
					System.out.print("Player2, Make a move Enter position ");

					mov2 = sc.next().charAt(0);

					while (check == false) {
						for (int d = 0; d < 9; d++) {
							if (mov2 == movepos[d]) {
								check = true;
								break;
							}
						}
						if (check == false) {
							System.out.print("Player 2, Invalid input, re-enter position:");
							mov2 = sc.next().charAt(0);
						}

					}
				}

				// Change the integer of array into symbol
				for (int r = 0; r < 9; r++) {

					//
					if (count % 2 == 0) {
						if (mov1 == movepos[r]) {
							movepos[r] = symbol[0];
						}
					}

					if (count % 2 != 0) {

						if (mov2 == movepos[r]) {
							movepos[r] = symbol[1];
						}

					}
				}

			}

			if (count == 9 && draw ==true) {
				System.out.println("It is draw");
			}

		}
		System.out.print("The End");

	}

}
