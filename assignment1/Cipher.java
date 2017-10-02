// CS 210, Fall 2016
// NAME: Kevin Nguyen
// PROGRAMMING ASSIGNMENT 1 w/ EXTRA CREDIT

import java.util.Scanner;
import java.util.Random;

public class Cipher {
	public static void main(String[] args) {
		// Five numbers prompt from 0 to 19
		// Program quits if any of the numbers are outside the range
		System.out.println("CS 210, Fall 2016");
		System.out.println("NAME: Kevin Nguyen");
		System.out.println("PROGRAMMING ASSIGNMENT 1 w/ EXTRA CREDIT\n");

		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the Cipher program.");
		System.out.println("Please enter five numbers from 0 to 19");

		System.out.print("1st number: ");
		int num1 = scan.nextInt();
		if (num1 < 0 || num1 > 19) {
			System.out.println("Please read directions and try again!");
			System.exit(0);
		}

		System.out.print("2nd number: ");
		int num2 = scan.nextInt();
		if (num2 < 0 || num2 > 19) {
			System.out.println("Please read directions and try again!");
			System.exit(0);
		}

		System.out.print("3rd number: ");
		int num3 = scan.nextInt();
		if (num3 < 0 || num3 > 19) {
			System.out.println("Please read directions and try again!");
			System.exit(0);
		}

		System.out.print("4th number: ");
		int num4 = scan.nextInt();
		if (num4 < 0 || num4 > 19) {
			System.out.println("Please read directions and try again!");
			System.exit(0);
		}

		System.out.print("5th number: ");
		int num5 = scan.nextInt();
		if (num5 < 0 || num5 > 19) {
			System.out.println("Please read directions and try again!");
			System.exit(0);
		}
		
		// Sum
		int sum = num1 + num2 + num3 + num4 + num5;
		System.out.println("\nTotal = " + sum);

		// Key generator
		Random gen = new Random();
		int key = gen.nextInt(10);
		System.out.println("Your random key is " + key);

		// Encoded number
		int sumTens = sum / 10;
		int sumOnes = sum - sumTens * 10;
		int encNumTens = (sumTens + key) % 10 * 10;
		int encNumOnes = (sumOnes + key) % 10;
		int encNum = encNumTens + encNumOnes;
		System.out.println("Your encoded number is: " + encNum);

		// Plain text input
		Scanner scan2 = new Scanner(System.in);
		System.out.print("\nEnter a plaintext message composed of up to 10 lowercase alphabetic characters: ");
		String plainText = scan2.nextLine();

		// Program quits if length of the input is outside the range of 1 to 10
		if (plainText.length() > 10 || plainText.length() < 1) {
			System.out.println("Please read directions and try again!");
			System.exit(0);
		}

		// Checks if the characters typed are all lowercase letters
		for (int i = 0; i < plainText.length(); i++) {
			// If not, the program then quits
			if ((int)plainText.charAt(i) < (int)'a' || (int)plainText.charAt(i) > (int)'z') {
				System.out.println("Please read directions and try again!");
				System.exit(0);
			}
		}

		// Key value prompt
		System.out.print("Enter a key value between 1 and 26: ");
		int keyVal = scan.nextInt();
		if (keyVal < 1 || keyVal > 26) {
			System.out.println("Please read directions and try again!");
			System.exit(0);
		}

		// Generate Ciphertext
		System.out.print("Ciphertext: ");
		for (int i = 0; i < plainText.length(); i++) {
			char initialLetter = plainText.charAt(i);
			int initialAsciiValue = (int)initialLetter;
			int finalAsciiValue = initialAsciiValue + keyVal;
			if (finalAsciiValue > (int)'z') {
				finalAsciiValue -= 26;
			}

			System.out.print((char)finalAsciiValue);
		}
		scan.close();
		scan2.close();
	}
}