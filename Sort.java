import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Sort {
	Scanner consoleInput = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	
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
		
	}
	
	public static void main(String[] args) {
		new Sort();
	}
}
