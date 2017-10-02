// CS 210, Fall 2016
// NAME: Kevin Nguyen, Emilio Ortega, Ahmad Dahshan
// PROGRAMMING ASSIGNMENT 3 w/ EXTRA CREDIT

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GenTicTacToe {
	static int size, inputRow, inputCol;
	static char board[][];
	static int turn = 0;
	static int turnSave = 0;
	static String inputRowString, inputColString;

	public GenTicTacToe() throws IOException, NumberFormatException {
		Scanner newOrLoad = new Scanner(System.in);

		System.out.println("CS 210, Fall 2016");
		System.out.println("NAME: Kevin Nguyen, Emilio Ortega, Ahmad Dahshan");
		System.out.println("PROGRAMMING ASSIGNMENT 3 w/ EXTRA CREDIT\n");

		String newOrLoadInput;
		do {
			System.out.print("Load game or new game? ");
			newOrLoadInput = newOrLoad.nextLine();
			switch (newOrLoadInput) {
			// When the user types "load", the game will attempt to load the game
			case "load":
				String fileName = "tictactoe-save-file.txt";
				File f = new File(fileName);

				// Finding the save file
				try {
					Scanner fileScan = new Scanner(f);
					String sizeSave = fileScan.nextLine();
					size = Integer.parseInt(sizeSave);
					board = new char[size][size];
					int row = 0;
					while (fileScan.hasNext()) {
						String boardRow = fileScan.nextLine();

						for (int col = 0; col < size; col++) {
							// Bypasses the commas in the save file
							if (boardRow.charAt(2 * col) != ',') {
								board[row][col] = boardRow.charAt(2 * col);
								if (board[row][col] == 'X' || board[row][col] == 'O') {
									turnSave++;
								}
							}				
						}
						row++;
					}
					fileScan.close();
					break;
				}

				// If the save file does not exist, a new game will proceed instead
				catch (FileNotFoundException fileNoExist) {
					System.out.println("\nFile not found! Starting new game");
				}

			// A new game will begin if the user types "new"
			case "new":
				// This will ask the user for the size of the board and store it into "size"
				// It will keep looping until the user enters the correct input
				do {
					Scanner boardSize = new Scanner(System.in);
					try {
						System.out.print("\nPlease enter the size of the board n (e.g. n=3,4,5...): ");
						size = boardSize.nextInt();
						if (size < 3) {
							System.out.println("\nThe number you entered is too small!");
							continue;
						}
						else if (size > 99) {
							System.out.println("\nThe number you entered is too large!");
							continue;
						}
					}
					catch (InputMismatchException sizeNotNumber) {
						System.out.println("\nInvalid size!");
						continue;
					}
				} while (size < 3 || size > 99);

				// Creates board array
				board = new char[size][size];
				break;

				// Invalid input if the input is not "load" or "new"
			default:
				System.out.println("\nInvalid input!\n");
			}
		} while (!newOrLoadInput.equals("load") && !newOrLoadInput.equals("new"));

		// Input loop
		Scanner input = new Scanner(System.in);
		Random comInput = new Random();
		for (turn = turnSave + 1; turn <= Math.pow(size, 2); turn++) {
			// Empty board prints initially or board prints from save file
			if (turn == turnSave + 1) {
				GenGameBoard.printBoard(size, board);
			}

			// Player's turn
			if (turn % 2 != 0) {
				do {
					try {
						System.out.println("\nPlayer's Move");

						// Row prompt
						System.out.print("Choose your row: ");
						inputRowString = input.nextLine();

						// If "save" is typed, then save the game and reprompt
						if (inputRowString.equals("save")) {
							// Fills the gap of missing inputColString
							inputColString = "0";
							GenGameBoard.gameSave(size, board);
							continue;
						}
						inputRow = Integer.parseInt(inputRowString) - 1;

						// Column prompt
						System.out.print("Choose your column: ");
						inputColString = input.nextLine();

						// If "save" is typed, then save the game and reprompt
						if (inputColString.equals("save")) {
							// Resets inputRowString to zero
							inputRowString = "0";
							GenGameBoard.gameSave(size, board);
							continue;
						}

						inputCol = Integer.parseInt(inputColString) - 1;

						// Prompts that the position is already taken
						if (board[inputRow][inputCol] == 'X' || board[inputRow][inputCol] == 'O') {
							System.out.println("\nThis position is already taken!");
							continue;
						}
					}
					// Catch non-numbers other than "save"
					catch (NumberFormatException notANumber) {
						System.out.println("\nNot a valid row or column!");
						continue;
					}
					// Catch input beyond the board size or less than zero
					catch (ArrayIndexOutOfBoundsException outOfBounds) {
						System.out.println("\nNot a valid row or column!");
						continue;
					}
					break;
				} while (true);
			}

			// Computer's turn (computer moves second)
			else if (turn % 2 == 0 && turn > 0) {
				// Computer chooses O position
				// The do loop will continue if the square is already occupied
				do {
					inputRow = comInput.nextInt(size + 1 - 1);
					inputCol = comInput.nextInt(size + 1 - 1);
				} while (board[inputRow][inputCol] == 'X' || board[inputRow][inputCol] == 'O' ||
						inputRow < 0 || inputRow > size || inputCol < 0 || inputCol > size);
				System.out.println("\nComputer chose: " + (inputRow + 1) + ", " + (inputCol + 1) + "\n");
			}

			// If the move is possible, then print the board
			// The board also prints empty at the zeroth turn
			if (GenGameBoard.makeMove(turn, inputRow, inputCol, board) == true) {
				GenGameBoard.printBoard(size, board);
			}

			// If a victory is detected, declare the winner and then end the game
			if (GenGameBoard.checkWin(size, turn, board) == true) {
				input.close();
				System.exit(0);
			}
		}

		GenGameBoard.noMoreMoves();
	}

	public static void main(String[] args) throws IOException {
		// Creates a GenTicTacToe object
		GenTicTacToe game = new GenTicTacToe();
	}
}