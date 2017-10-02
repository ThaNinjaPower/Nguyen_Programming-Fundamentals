// CS 210, Fall 2016
// NAME: Kevin Nguyen, Emilio Ortega, Ahmad Dashan
// PROGRAMMING ASSIGNMENT 3 w/ EXTRA CREDIT

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenGameBoard {
	static String fileName = "tictactoe-save-file.txt";

	// Board print method
	public static void printBoard(int size, char board[][]) {
		System.out.println();
		// This is for the dashed lines on the board
		int frameLength = 2 * size + 1;
		if (size > 9 && size < 100) {
			frameLength = 3 * size + 1;
		}

		// These are the numbers that get printed on the top of the board
		System.out.print(' ');
		if (size > 9) {
			System.out.print(' ');
		}
		for (int colNum = 1; colNum <= size; colNum++) {
			if (size > 9 && colNum < 10) {
				System.out.print(' ');
			}
			System.out.print(" " + colNum);
		}
		System.out.println();

		// This prints the top of the board
		System.out.print(' ');
		if (size > 9) {
			System.out.print(' ');
		}
		for (int topBorder = 0; topBorder < frameLength; topBorder++) {
			if (topBorder < frameLength){
				System.out.print('-');
			}

		}
		System.out.println();

		// This prints out the rows and columns for the board
		for (int row = 0; row < size; row++) {
			// This prints the numbers on the left side of the board
			if (size > 9 && row < 9) {
				System.out.print(' ');
			}
			System.out.print(row + 1);	

			// This prints the columns for the board
			for (int col = 0; col < size; col++) {
				System.out.print('|');

				if (size > 9) {
					System.out.print(' ');
				}

				if (board[row][col] != 'X' && board[row][col] != 'O') {
					board[row][col] = ' ';
				}

				// This prints the item in array, it will print a blank space when empty
				System.out.print(board[row][col]);

				// This prints a vertical line at the end of the row
				if (col == size - 1) {
					System.out.print('|');
				}
			}

			// This aligns the dashed lines
			System.out.println();
			System.out.print(' ');
			if (size > 9) {
				System.out.print(' ');
			}

			// This prints the dashed lines
			for (int hBorder = 0; hBorder < frameLength; hBorder++) {
				System.out.print('-');
			}
			System.out.println();
		}
	}

	// User and computer inputs method
	public static boolean makeMove(int turn, int inputRow, int inputCol, char board[][]) {
		if (turn % 2 != 0) {
			board[inputRow][inputCol] = 'X';
			return true;
		}
		else if (turn % 2 == 0 && turn > 0) {
			board[inputRow][inputCol] = 'O';
			return true;
		}
		else {
			return false;
		}
	}

	// Save file method
	public static boolean gameSave(int size, char board[][]) throws IOException {
		// A save file will be created, containing size of board and progress
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter outFile = new PrintWriter(bw);

		// This saves the board and size number in the save file
		outFile.println(size);
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				outFile.print(board[row][col]);

				if (col < size - 1) {
					outFile.print(',');
				}
			}
			outFile.println();
		}
		outFile.close();

		System.out.println("\nGame saved!");
		return true;
	}

	// Victory checker method
	public static boolean checkWin(int size, int turn, char board[][]) {
		int row, col;

		// Checks "\" diagonal
		int winX = 0;
		int winO = 0;
		for (row = 0; row < size; row++) {
			// Checks for player
			if (turn % 2 != 0) {
				// Counts the X in diagonal
				if (board[row][row] == 'X') {
					winX++;
				}

				// If there's no X in diagonal, break loop and move on to "/" diagonal
				else {
					break;
				}

				// If diagonal is filled with X, player wins
				if (winX == size) {
					System.out.println("\nGAME OVER\nPlayer wins!");
					return true;
				}
			}

			// Checks for computer
			else if (turn % 2 == 0 && turn > 0) {
				// Counts the O in diagonal
				if (board[row][row] == 'O') {
					winO++;
				}

				// If there's no O in diagonal, break loop and move on to "/" diagonal
				else {
					break;
				}

				// If diagonal is filled with O, computer wins
				if (winO == size) {
					System.out.println("\nGAME OVER\nComputer wins!");
					return true;
				}
			}
		}

		// Checks "/" diagonal
		winX = 0;
		winO = 0;
		for (row = 0; row < size; row++) {
			// Checks for player
			if (turn % 2 != 0) {
				// Counts the X in diagonal
				if (board[row][(size - 1) - row] == 'X') {
					winX++;
				}

				// If there's no X in diagonal, break loop and move on to rows
				else {
					break;
				}

				// If diagonal is filled with X, player wins
				if (winX == size) {
					System.out.println("\nGAME OVER\nPlayer wins!");
					return true;
				}
			}

			// Checks for computer
			else if (turn % 2 == 0 && turn > 0) {
				// Counts the O in diagonal
				if (board[row][(size - 1) - row] == 'O') {
					winO++;
				}

				// If there's no O in diagonal, break loop and move on to rows
				else {
					break;
				}

				// If diagonal is filled with O, then computer wins
				if (winO == size) {
					System.out.println("\nGAME OVER\nComputer wins!");
					return true;
				}
			}
		}

		// Checks rows
		for (row = 0; row < size; row++) {
			winX = 0;
			winO = 0;
			for (col = 0; col < size; col++) {
				//Checks for player
				if (turn % 2 != 0) {
					if (board[row][col] == 'X') {
						winX++;
					}

					// If there's no X in row, skip to the next row
					// If it's the last row, then move on to columns
					else {
						break;
					}

					// If row is filled with X, then player wins
					if (winX == size) {
						System.out.println("\nGAME OVER\nPlayer wins!");
						return true;
					}
				}

				// Checks for computer
				else if (turn % 2 == 0 && turn > 0) {
					if (board[row][col] == 'O') {
						winO++;
					}

					// If there's no O in row, skip to the next row
					// If it's the last row, then move on to columns
					else {
						break;
					}

					// If row is filled with O, then computer wins
					if (winO == size) {
						System.out.println("\nGAME OVER\nComputer wins!");
						return true;
					}
				}
			}
		}

		// Checks columns
		for (col = 0; col < size; col++) {
			winX = 0;
			winO = 0;
			for (row = 0; row < size; row++) {
				// Checks for player
				if (turn % 2 != 0) {
					if (board[row][col] == 'X') {
						winX++;
					}

					// If there's no X in column, skip to the next column
					// If it's the last column, then checkWin is false
					else {
						break;
					}

					// If column is filled with X, then player wins
					if (winX == size) {
						System.out.println("\nGAME OVER\nPlayer wins!");
						return true;
					}
				}

				// Checks for computer
				else if (turn % 2 == 0 && turn > 0) {
					if (board[row][col] == 'O') {
						winO++;
					}

					// If there's no O in column, skip to the next column
					// If it's the last column, then checkWin is false
					else {
						break;
					}

					// If column is filled with O, then computer wins
					if (winO == size) {
						System.out.println("\nGAME OVER\nComputer wins!");
						return true;
					}
				}
			}
		}
		return false;
	}

	// No more moves method
	public static boolean noMoreMoves() {
		System.out.println("\nGAME OVER\nIt was a draw!");
		return true;
	}
}