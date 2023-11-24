import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Sort {
	Scanner consoleInput = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	long startTime;
	
	public Sort() {
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
		System.out.println("1: Bubble  2: Selection  3: Table");
		input = consoleInput.nextLine();
		if(input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2'
				&& input.charAt(0) != '3') {
			System.out.println("Enter 1, 2, or 3");
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2'
					&& input.charAt(0) != '3') {
				input = consoleInput.nextLine();
			}
		}
		startTime = System.currentTimeMillis();
		if(input.equals("1")){
			inputArray = bubbleSort(inputArray);
		}
		if(input.equals("2")){
			inputArray = selectionSort(inputArray);
		}
		if(input.equals("3")){
			inputArray = tableSort(inputArray);
		}
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Total time: " + totalTime);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(new File("output.txt")));
			String output = "";
			for (int i = 0; i < inputArray.length; i++) {
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
	
	public static void main(String[] args) {
		new Sort();
	}
}
