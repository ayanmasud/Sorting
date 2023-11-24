/*
 * This project reads in .txt files and uses different sort methods to sort them from smallest to biggest into
 * a new file. It also tells you how long it took to sort in milliseconds.
 *
 * Author: Ayan Masud
 * Date: 11/24/23
 * Teacher: Jason Galbraith
 */

import java.io.*;
import java.util.Scanner;

public class Sort {
	Scanner consoleInput = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	long startTime;
	
	public Sort() { // gets the input for what file wants to be sorted and what sorting method should be used
		System.out.println("Enter a number for the input file.");
		System.out.println("1: input1.txt  2: input2.txt  3: input3.txt  4: input4.txt");
		input = consoleInput.nextLine();
		if(input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2'
				&& input.charAt(0) != '3' && input.charAt(0) != '4') {
			System.out.println("Enter 1, 2, 3, or 4.");
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2'
					&& input.charAt(0) != '3' && input.charAt(0) != '4') {
				input = consoleInput.nextLine();
			}
		}
		try {
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		String infile = fileInput.nextLine();
		String[] inputStringArray = infile.split(",");
		inputArray = new int[inputStringArray.length];
		for (int i = 0; i < inputStringArray.length; i++) {
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
			System.out.println(inputArray[i]);
		}
		System.out.println("Enter a number for the sort you want to use.");
		System.out.println("1: Bubble  2: Selection  3: Table  4: Quick");
		input = consoleInput.nextLine();
		if(input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2'
				&& input.charAt(0) != '3' && input.charAt(0) != '4') {
			System.out.println("Enter 1, 2, 3, or 4");
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2'
					&& input.charAt(0) != '3' && input.charAt(0) != '4') {
				input = consoleInput.nextLine();
			}
		}
		startTime = System.currentTimeMillis(); // runs the sorting algorithm based off their input earlier
		if(input.equals("1")){
			inputArray = bubbleSort(inputArray);
		}
		if(input.equals("2")){
			inputArray = selectionSort(inputArray);
		}
		if(input.equals("3")){
			inputArray = tableSort(inputArray);
		}
		if (input.equals("4")){
			inputArray = quickSort(inputArray, 0, inputArray.length - 1);
		}
		long totalTime = System.currentTimeMillis() - startTime; // calculates total time it took to sort
		System.out.println("Total time: " + totalTime);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(new File("output.txt"))); // creates new file with the numbers sorted
			String output = "";
			for (int i = 0; i < inputArray.length; i++) {
				System.out.println(inputArray[i]);
				output += inputArray[i] + ",";
			}
			output += "\nTotal Time:" + totalTime;
			pw.write(output);
			pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}
	// compare each pair of numbers and move the larger to the right
	int[] bubbleSort(int[] array){
		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				// if the one on the left is larger
				if(array[i] > array[i+1]){
					// swap
					int temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
		}
		return array;
	}

	// find the smallest and move it to the front
	int[] selectionSort(int[] array){
		for (int j = 0; j < array.length; j++) {
			int smallestNumber = array[j];
			int smallestIndex = j;
			for (int i = j; i < array.length; i++) {
				if(array[i] < smallestNumber){
					smallestNumber = array[i];
					smallestIndex = i;
				}
			}
			int temp = array[smallestIndex];
			array[smallestIndex] = array[j];
			array[j] = temp;
		}
		return array;
	}

	// tally how often you see each number, print out that number of times
	int[] tableSort(int[] array){
		int[] tally = new int[1001];
		for (int i = 0; i < array.length; i++) {
			tally[array[i]]++;
		}

		int count = 0;
		// i keeps track of the actual number
		for (int i = 0; i < tally.length; i++) {
			// j keeps track of how many times we've seen that number
			for (int j = 0; j < tally[i]; j++) {
				array[count] = i;
				count++;
			}
		}

		return array;
	}

	// compare each pair of numbers and move the larger to the right and less than or equal to the left
	int[] quickSort(int[] array, int low, int high) {
		if (low < high) {
			int partition = array[high]; // decided to start the partition with the highest index
			int i = low - 1;

			for (int j = low; j < high; j++) {
				if (array[j] < partition) { // swap the current number with the index + 1 if it is less than the partition
					i++;
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}

			int temp = array[i + 1]; // place partition number in correct spot
			array[i + 1] = array[high];
			array[high] = temp;

			int partitionIndex = i + 1;

			quickSort(array, low, partitionIndex - 1); // sort numbers before partition index
			quickSort(array, partitionIndex + 1, high); // sort numbers after partition index
		}
		return array;
	}
	
	public static void main(String[] args) {
		new Sort();
	}
}
