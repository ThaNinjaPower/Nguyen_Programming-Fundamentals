// CS 210, Fall 2016
// NAME: Kevin Nguyen
// PROGRAMMING ASSIGNMENT 2

import java.util.Scanner;
import java.util.Random;

public class Towers {
	public static void main(String[] args) {
		// Header
		System.out.println("CS 210, Fall 2016");
		System.out.println("NAME: Kevin Nguyen");
		System.out.println("PROGRAMMING ASSIGNMENT 2\n");
		
		Scanner scan = new Scanner(System.in);
		
		// Tower 1 width input
		System.out.print("Enter the width of tower 1 (an odd number): ");
		int towerW1 = scan.nextInt();
		
		// Loop will not end until towerW1 is odd and greater than one
		while (towerW1 % 2 == 0 || towerW1 < 1) {
			System.out.println("Invalid Input! Width needs to be odd.");
			System.out.print("Reenter the width of tower 1 (an odd number): ");
			towerW1 = scan.nextInt();
		}
		
		// Tower 2 width input
		System.out.print("Enter the width of tower 2 (an odd number): ");
		int towerW2 = scan.nextInt();
		
		// Loop will not end until towerW2 is odd and greater than one
		while (towerW2 % 2 == 0 || towerW1 < 1) {
			System.out.println("Invalid Input! Width needs to be odd.");
			System.out.print("Reenter the width of tower 2 (an odd number): ");
			towerW2 = scan.nextInt();
		}
		
		// Tower 3 width input
		System.out.print("Enter the width of tower 3 (an odd number): ");
		int towerW3 = scan.nextInt();
		
		// Loop will not end until towerW3 is odd and greater than one
		while (towerW3 % 2 == 0 || towerW3 < 1) {
			System.out.println("Invalid Input! Width needs to be odd.");
			System.out.print("Reenter the width of tower 3 (an odd number): ");
			towerW3 = scan.nextInt();
		}
		
		scan.close();
		
		// Towers' heights ranging from 3 to 11 (inclusive)
		Random height = new Random();
		int towerH1, towerH2, towerH3;
		// Loop will not end until towerH1 is odd
		do {
			towerH1 = height.nextInt(12 - 3) + 3;
		}
		while (towerH1 % 2 == 0);
		
		// Loop will not end until towerH2 is odd
		do {
			towerH2 = height.nextInt(12 - 3) + 3;
		}
		while (towerH2 % 2 == 0);
		
		// Loop will not end until towerH3 is odd
		do {
			towerH3 = height.nextInt(12 - 3) + 3;
		}
		while (towerH3 % 2 == 0);
		
		// Identifies the tallest height
		System.out.println();
		int maxHeight = Math.max(Math.max(towerH1, towerH2), towerH3);
		for (int story = maxHeight; story > 0; story--) {
			// Tower 1
			for (int width = 1; width <= towerW1; width++) {
				// If story is above tower 1, then tower 1 is bypassed
				if (story > towerH1) {
					System.out.print(' ');
				}
				
				// If story reaches tower 1, then tower 1 prints
				else {
					System.out.print('*');
				}
			}
			
			// Tower 2
			System.out.print(' ');
			for (int width = 1; width <= towerW2; width++) {
				// If floor is above tower 2, then tower 2 is bypassed
				if (story > towerH2) {
					System.out.print(' ');
				}
				// If floor reaches tower 2, then tower 2 prints
				else {
					System.out.print('*');
				}
			}
			
			// Tower 3
			System.out.print(' ');
			for (int width = 1; width <= towerW3; width++) {
				// If floor is above tower 3, then tower 3 is bypassed
				if (story > towerH3) {
					System.out.print(' ');
				}
				// If floor reaches tower 3, then tower 3 prints
				else {
					System.out.print('*');
				}
			}
			
			System.out.println();
		}
	}
}