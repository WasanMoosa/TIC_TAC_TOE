/**
 * This is a TEC TAC TOE game with file used to resume the game 
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TICTACWithResumeFile {
	static Scanner sc = new Scanner(System.in);
	static int place[] = new int[2];
	static boolean check = true;
	/**
	 * This is the main
	 */
	public static void main(String[] args) {
		int index1 = 0;
		int index2 = 0;
		char[] symbol = new char[2];
		char[][] position = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
		Boolean continueBoolean = false;
		boolean win = false; // Stop playing if exist a winner
		System.out.println("Start playing TIC TAC TOE");
		shownBox(position);
		System.out.println("\n");
		int playerSaveState = 0;
		int count = 0;
		int playerTurn = 0;
		// reload the file if exist
		File myFile = new File("prograss.txt");
		try {
			if (myFile.exists()) {
				Scanner scfile = new Scanner(myFile);
				System.out.print("Do you want to resume (press 0) ? or Start new game (press 1)");
				int state = sc.nextInt();
				if (state == 0) {

					continueBoolean = true;
					int i = 0; // To seperate the file (0-2) for position, (3)for symbol, (4)for count
					while (scfile.hasNextLine()) {
						if (i < 3) {
							position[i] = scfile.nextLine().toCharArray();
						}
						if (i == 3) {
							symbol = scfile.nextLine().toCharArray();
						}
						if (i == 4) {
							count = scfile.nextInt();
						}
						if (i == 5) {
							playerTurn = scfile.nextInt();
						}
						i++;
					}
					shownBox(position);

				}
				scfile.close();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// If the game is not resume, choose symbol
		if (!continueBoolean) {
			playerSymbol(symbol);
		}
		boolean exit = false;
		// Loop for continuity of the game
		for (; count < 5 && !exit && !win; count++) {

			// player turn
			for (playerSaveState = 0; playerSaveState < 2 && !win && !exit; playerSaveState++) {

				if (continueBoolean) {
					playerSaveState = playerTurn;
					continueBoolean = !continueBoolean;
				}
				System.out.print("\n");
				System.out.println("Player" + (playerSaveState + 1) + ", Make a move, Enter position: ");
				System.out.print("or press 0 if you want to exit: ");

				char move = sc.next().charAt(0);
				// Exit
				if (move == '0') {
					System.out.println("You exit the game! see you soon");
					exit = true;

					// save the state
					putInFile(position, symbol, count, playerSaveState);
				}
				if (!exit) {
					check = false;
					while (check == false) {
						place = ChoosePosition(move, symbol);
						index1 = place[0];
						index2 = place[1];
						check = checkValid(index1, index2, position, symbol);

						if (check == false) {

							System.out.print("Player" + (playerSaveState + 1) + ", Invalid input, re-enter position: ");
							move = sc.next().charAt(0);
						}
					}

					position[index1][index2] = symbol[playerSaveState];
					shownBox(position);
					if (playerSaveState == 0) {
						putInFile(position, symbol, count, 1);
					} else {
						putInFile(position, symbol, count, 0);
					}

					// Win conditions
					if (position[0][0] == position[0][1] && position[0][1] == position[0][2]
							|| position[1][0] == position[1][1] && position[1][1] == position[1][2]
							|| position[2][0] == position[2][1] && position[2][1] == position[2][2]
							|| position[0][0] == position[1][0] && position[1][0] == position[2][0]
							|| position[0][1] == position[1][1] && position[1][1] == position[2][1]
							|| position[0][2] == position[1][2] && position[1][2] == position[2][2]
							|| position[0][0] == position[1][1] && position[1][1] == position[2][2]
							|| position[0][2] == position[1][1] && position[1][1] == position[2][0]) {

						System.out.print("\n");
						System.out.println("Player " + symbol[playerSaveState] + " Win");
						win = true;
						// delete File
						try {
							FileWriter writer = new FileWriter(myFile);
							writer.close();
							// Files.deleteIfExists(Paths.get("prograss.txt"));
							myFile.delete();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					// Because 9 times playing not 10 times
					if (count == 4) {
						playerSaveState++;
						System.out.print("\n");
						System.out.println("It is draw!!");
						// delete File
						try {
							FileWriter writer = new FileWriter(myFile);
							writer.close();
							// Files.deleteIfExists(Paths.get("prograss.txt"));
							myFile.delete();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}

		}

		System.out.print("The End!");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Function to show the TICTACTOE box
	 */
	static void shownBox(char[][] position) {
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

	/**
	 * 
	 * Method to choose the symbol, Invalid symbol if the symbols are the same of
	 * both player
	 */
	static void playerSymbol(char[] symbol) {
		System.out.print("Player 1, choose your symbol: ");
		symbol[0] = sc.next().charAt(0);
		System.out.print("Player 2, choose your symbol: ");
		symbol[1] = sc.next().charAt(0);
		while (symbol[0] == symbol[1]) {
			System.out.print("Player 2, Invalid Symbol, Please choose different symbol ");
			symbol[1] = sc.next().charAt(0);
		}
	}

	/**
	 * Method to choose the position return the number place of the chosen position
	 */
	static int[] ChoosePosition(char move, char symbol[]) {

		int moveInt = Character.getNumericValue(move);
		int i = (int) Math.ceil(moveInt / 3.0) - 1;
		int j = moveInt % 3;
		if (j == 0) {

			j = 3;
		}
		j = j - 1;

		int[] s = { i, j };
		return s;
	}

	/**
	 * To check if symbol are unique return true: if symbols are different return
	 * false: if symbols are identical
	 */
	static boolean checkValid(int i, int j, char[][] position, char[] symbol) {

		if (position[i][j] == symbol[0] || position[i][j] == symbol[1]) {
			check = false;
		} else {
			check = true;

		}
		return check;
	}

	/**
	 * 
	 * Method to save the state of the game if it is not complete it
	 */

	static void putInFile(char[][] state, char[] symbol, int count, int playerSaveState) {
		File myFile = new File("prograss.txt");
		try {
			FileWriter writer = new FileWriter(myFile);
			writer.write(state[0]);
			writer.write("\n");
			writer.write(state[1]);
			writer.write("\n");
			writer.write(state[2]);
			writer.write("\n");
			writer.write(symbol);
			writer.write("\n");
			writer.write(String.valueOf(count));
			writer.write("\n");
			writer.write(String.valueOf(playerSaveState));

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
