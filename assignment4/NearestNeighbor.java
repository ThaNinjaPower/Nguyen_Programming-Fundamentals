// CS 210, Fall 2016
// NAME: Kevin Nguyen
// PROGRAMMING ASSIGNMENT 4

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NearestNeighbor {
	// Data value arrays
	static double[][] testingValues = new double[75][4];
	static double[][] trainingValues = new double[75][4];
	
	// Distances
	static double[] distance = new double[75];
	static double shortestDistance = 0;
	
	// Accuracy
	static double accuracy = 0;
	
	// Flower type arrays
	static String[] testingFlowerType = new String[75];
	static String[] trainingFlowerType = new String[75];
	static String[] closestFlowerType = new String[75];
	
	// Confusion matrix
	static int[][] confusionMatrix = {{0, 0, 0},
									  {0, 0, 0},
									  {0, 0, 0}};
	
	// Training data file scan
	static double[][] trainDataScan() throws FileNotFoundException {
		File training = new File("iris-training-data.csv");
		Scanner trainingData = new Scanner(training);
		
		int row = 0;
		while (trainingData.hasNext()) {
			String exNumLine = trainingData.nextLine();
			String[] exNumLineParts = exNumLine.split(",");
			
			// Gather all data of the flower petal and sepal
			for (int col = 0; col < 4; col++) {
				trainingValues[row][col] = Double.parseDouble(exNumLineParts[col]);
			}
			
			// Obtain the name of the flower
			trainingFlowerType[row] = exNumLineParts[4];
			row++;
		}
		
		trainingData.close();
		return trainingValues;
	}
	
	// Test data file scan
	static double[][] testDataScan() throws FileNotFoundException {
		File test = new File("iris-testing-data.csv");
		Scanner testData = new Scanner(test);
		
		int row = 0;
		while (testData.hasNext()) {
			String exNumLine = testData.nextLine();
			String[] exNumLineParts = exNumLine.split(",");
			
			// Gather all data of the flower petal and sepal
			for (int col = 0; col < 4; col++) {
				testingValues[row][col] = Double.parseDouble(exNumLineParts[col]);
			}
			
			// Obtain the name of the flower
			testingFlowerType[row] = exNumLineParts[4];			
			row++;
		}
		
		testData.close();
		return testingValues;
	}
	
	// Find the nearest data point
	static String[] nearestFlower() {
		// This loop will list all the distances from the test data point
		// Goes over every row of the testing data
		int rowShort = 0;
		for (int rowTrain = 0; rowTrain < 75; rowTrain++) {
			// Goes over every row of the training data to find the closest one
			for (int rowTest = 0; rowTest < 75; rowTest++) {
				// Calculations for distance
				double sepalLengthDiff = testingValues[rowTest][0] - trainingValues[rowTrain][0];
				double sepalWidthDiff = testingValues[rowTest][1] - trainingValues[rowTrain][1];
				double petalLengthDiff = testingValues[rowTest][2] - trainingValues[rowTrain][2];
				double petalWidthDiff = testingValues[rowTest][3] - trainingValues[rowTrain][3];
				
				double discriminant = Math.pow(sepalLengthDiff, 2) + Math.pow(sepalWidthDiff, 2) +
									  Math.pow(petalLengthDiff, 2) + Math.pow(petalWidthDiff, 2);
				
				distance[rowTrain] = Math.sqrt(discriminant);
				
				// If there's a distance less than the shortest distance, make it the shortest
				if (rowTest == 0 || distance[rowTrain] < shortestDistance) {
					rowShort = rowTest;
					shortestDistance = distance[rowTrain];
				}
			}
			
			// Set the closest data point equal to the closest training data
			closestFlowerType[rowTrain] = testingFlowerType[rowShort];
		}
		
		return closestFlowerType;
	}
	
	// Find the accuracy of the test data
	static double predictionAccuracy() {
		int correctGuess = 0;
		for (int row = 0; row < 75; row++) {
			switch (closestFlowerType[row]) {
				// Case when closest flower type is Iris-setosa
				case "Iris-setosa":
					// If closest flower type match with training flower type, then it's correct
					if (closestFlowerType[row].equals(trainingFlowerType[row])) {
						correctGuess++;
					}
					break;
				
				// Case when the closest flower type is Iris-versicolor
				case "Iris-versicolor":
					// If closest flower type match with training flower type, then it's correct
					if (closestFlowerType[row].equals(trainingFlowerType[row])) {
						correctGuess++;
					}
					break;
				
				// Case when the closest flower type is Iris-virginica
				case "Iris-virginica":
					// If closest flower type match with training flower type, then it's correct
					if (closestFlowerType[row].equals(trainingFlowerType[row])) {
						correctGuess++;
					}
					break;
			}
		}
		
		accuracy = (double)correctGuess / 75.0;
		return accuracy;
	}
	
	// Print results
	static void printResults() {
		// Top labels
		System.out.println("EX#: TRUE LABEL, PREDICTED LABEL");
		
		// Print the data results (true label, predicted label)
		for (int row = 0; row < 75; row++) {
			// Print trial number
			System.out.print(row + 1 + ": ");
			
			// Print flower labels
			System.out.print(trainingFlowerType[row] + " ");
			System.out.print(closestFlowerType[row] + "\n");
		}
		
		// Print out accuracy of the test
		predictionAccuracy();
		System.out.println("ACCURACY: " + accuracy + "\n");
	}
	
	// Make the confusion matrix
	static int[][] makeConfusionMatrix() {
		for (int row = 0; row < 75; row++) {
			switch (closestFlowerType[row]) {
				// Case when closest flower type is Iris-setosa
				case "Iris-setosa":
					// If closest flower type match with training flower type, then it's correct
					if (closestFlowerType[row].equals(trainingFlowerType[row])) {
						confusionMatrix[0][0]++;
					}
					
					// Otherwise, the predicted label is wrong
					else {
						if (trainingFlowerType[row].equals("Iris-versicolor")) {
							confusionMatrix[1][0]++;
						}
						
						else if (trainingFlowerType[row].equals("Iris-virginica")) {
							confusionMatrix[2][0]++;
						}
					}
					break;
				
				// Case when the closest flower type is Iris-versicolor
				case "Iris-versicolor":
					// If closest flower type match with training flower type, then it's correct
					if (closestFlowerType[row].equals(trainingFlowerType[row])) {
						confusionMatrix[1][1]++;
					}
					
					// Otherwise, the predicted label is incorrect
					else {
						if (trainingFlowerType[row].equals("Iris-setosa")) {
							confusionMatrix[0][1]++;
						}
						
						else if (trainingFlowerType[row].equals("Iris-virginica")) {
							confusionMatrix[2][1]++;
						}
					}
					break;
				
				// Case when the closest flower type is Iris-virginica
				case "Iris-virginica":
					// If closest flower type match with training flower type, then it's correct
					if (closestFlowerType[row].equals(trainingFlowerType[row])) {
						confusionMatrix[2][2]++;
					}
					
					// Otherwise, the predicted label is incorrect
					else {
						if (trainingFlowerType[row].equals("Iris-setosa")) {
							confusionMatrix[0][2]++;
						}
						else if (trainingFlowerType[row].equals("Iris-versicolor")) {
							confusionMatrix[1][2]++;
						}
					}
					break;
			}
		}
		
		return confusionMatrix;
	}
	
	// Print the confusion matrix
	static void printConfusionMatrix() {
		makeConfusionMatrix();
		System.out.println("=== Confusion Matrix ===\n");
		System.out.println(" a  b  c  <-- classified as");
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (confusionMatrix[row][col] < 10) {
					System.out.print(" ");
				}
				
				System.out.print(confusionMatrix[row][col]);
				System.out.print(" ");
			}
			switch (row) {
				case 0:
					System.out.println("|  a = Iris-setosa");
					break;
				case 1:
					System.out.println("|  b = Iris-versicolor");
					break;
				case 2:
					System.out.println("|  c = Iris-virginica");
					break;
			}
		}		
	}	
	
	// Main method
	public static void main(String[] args) throws FileNotFoundException {
		// Header
		System.out.println("CS 210, Fall 2016");
		System.out.println("NAME: Kevin Nguyen");
		System.out.println("PROGRAMMING ASSIGNMENT 4\n");
		
		// Finding and analyzing data
		trainDataScan();
		testDataScan();
		nearestFlower();
		
		// Print out results
		printResults();
		printConfusionMatrix();
	}
}