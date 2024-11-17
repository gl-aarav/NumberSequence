/*
 * Aarav Goyal
 * NumberSequence.java
 * 11/15/2024
 * 
 * Pseudocode:
 * 
 * - Field Variables
 *     - Declare integer variables: startNum (initial sequence value), 
 *       commonDifference (difference between consecutive terms),
 *       nextTerm (the next expected term in the sequence),
 *       printingSequence (controls sequence printing),
 *       incorrectMeter (counts incorrect guesses),
 *       userInput (stores the user's guessed next term),
 *       stopLooping (boolean flag to control the game loop),
 *       pattern (the string representation of the sequence's pattern),
 *       userPatternInput (stores the user's guess for the pattern),
 *       onPattern (boolean flag to check if the user is guessing the pattern).
 * 
 * - NumberSequence Constructor
 *     - Initialize all fields to default values (startNum = 0, commonDifference = 0, etc.).
 * 
 * - main()
 *     - Create an instance of the NumberSequence class.
 *     - Call the method runSequence() to start the game.
 * 
 * - runSequence()
 *     - Display a welcome message.
 *     - Generate the sequence using createSequence().
 *     - Print the sequence using printSequence().
 *     - Get the user's input using getInput().
 *     - Loop to allow the user to guess the next term:
 *       - If the guess is incorrect and less than 3 attempts, increment incorrectMeter, and ask for another guess.
 *       - If the guess is correct or 3 incorrect guesses are reached, stop the loop.
 *     - After stopping the loop, prompt the user to guess the pattern.
 *     - Print a final message based on the pattern guess.
 * 
 * - createSequence()
 *     - Generate a random startNum between -10 and 10.
 *     - Generate a random commonDifference between 1 and 10.
 *     - Calculate the nextTerm based on startNum and commonDifference.
 *     - Set the sequence pattern as "add <commonDifference>".
 * 
 * - printSequence()
 *     - Print a part of the sequence with the first term starting from startNum.
 *     - Continue printing the sequence until the sequence has been partially displayed (printingSequence > 0).
 * 
 * - getInput()
 *     - Prompt the user to guess the next term in the sequence or the sequence's pattern.
 *     - Read and store the user's input.
 * 
 * - printMessage()
 *     - Compare the user's input with the correct answer.
 *     - Display a message based on whether the guess was correct or incorrect.
 * 
 * Testing Plan:
 * - Test with various random values for startNum and commonDifference to ensure variety in the sequence.
 * - Validate that the sequence is correctly generated and printed.
 * - Ensure the input handling works for both numeric guesses and pattern guesses.
 * - Handle edge cases like invalid input (e.g., non-integer inputs for numbers).
 * - Test both correct and incorrect responses, ensuring the correct messages are displayed.
 * - Test the edge cases of minimum and maximum values for startNum and commonDifference.
 */

import java.util.Scanner;

public class NumberSequenceV2
{
	// Field Variables
	private int startNum;          // The first number in the sequence
	private int commonDifference;  // The common difference between consecutive terms
	private int nextTerm;          // The next term in the sequence
	private int printingSequence;  // Counter for how many terms to print
	private int incorrectMeter;    // Counter for incorrect guesses
	private int userInput;         // User's guess for the next term
	private boolean stopLooping;   // Flag to stop the guessing loop
	private String pattern;        // The pattern (difference) of the sequence
	private String userPatternInput; // User's guess for the pattern
	private boolean onPattern;     // Flag to check if user is guessing the pattern
	private String multiplyOrAdd;

	// Constructor to initialize variables with default values
	public NumberSequenceV2() 
	{
		startNum = 0;
		commonDifference = 0;
		nextTerm = 0;
		printingSequence = 5;
		incorrectMeter = 0;
		userInput = 0;
		stopLooping = false;
		pattern = new String("");
		userPatternInput = new String ("");
		onPattern = false;
		multiplyOrAdd =  new String ("");
	}

	// Main method to run the sequence game
	public static void main(String[] args) 
	{
		// Create an instance of NumberSequence and start the game
		NumberSequenceV2 sequence = new NumberSequenceV2();
		sequence.runSequence();
	}

	// Method to run the sequence game
	public void runSequence() 
	{
		System.out.println("Welcome to NumberSequence!\n\n");
		askMultiplyOrAdd();
		// Generate the sequence and display it
		createSequence();
		System.out.println("\nGenerated Sequence: ");
		printSequence();

		// Prompt user to guess the next term in the sequence
		getInput();

		// Allow user up to 3 incorrect guesses
		do 
		{
			if (incorrectMeter < 3 && userInput != nextTerm) 
			{
				incorrectMeter++; // Increment incorrect guess counter
				printMessage();    // Print feedback message
				getInput();        // Get new guess from user
			} 
			else 
			{
				printMessage();    // Print the final message if correct or 3 incorrect guesses reached
				stopLooping = true; // End the loop
			}
		} while (incorrectMeter <= 3 && !stopLooping); // Loop until max incorrect guesses or correct guess

		// Switch to pattern guessing mode after the sequence guessing loop
		onPattern = true;
		getInput();          // Get user input for the pattern
		printMessage();      // Print the result of pattern guess
	}
	public void askMultiplyOrAdd()
	{
		Scanner in = new Scanner (System.in);
		System.out.print("What do you want to do? Enter in the form of \"add\", \"substract\", \"multiply\" or \"divide\": ");
		do
		{
			multiplyOrAdd = in.nextLine();
			if (!multiplyOrAdd.equalsIgnoreCase("Add") && !multiplyOrAdd.equalsIgnoreCase("multiply") && !multiplyOrAdd.equalsIgnoreCase("substract") && !multiplyOrAdd.equalsIgnoreCase("divide"))
				System.out.println("Enter in the form of \"add\" or \"multiply\" or \"substract\" or \"divide\"");
		} while (!multiplyOrAdd.equalsIgnoreCase("Add") && !multiplyOrAdd.equalsIgnoreCase("multiply") && !multiplyOrAdd.equalsIgnoreCase("substract") && !multiplyOrAdd.equalsIgnoreCase("divide"));
	}

	// Method to create the sequence
	public void createSequence() 
	{

		commonDifference = (int)(Math.random() * 10) + 1;
		if (multiplyOrAdd.equalsIgnoreCase("Add") || multiplyOrAdd.equalsIgnoreCase("Substract"))
		{

			// Generate a random startNum between -10 and 10
			startNum = (int)(Math.random() * 21) - 10;
			// Generate a random commonDifference between 1 and 10
			commonDifference = (int)(Math.random() * 10) + 1;

			if (multiplyOrAdd.equalsIgnoreCase("Add"))
			{
				nextTerm = startNum + commonDifference * 5;
				// Set the pattern for the sequence (e.g., "add 5")
				pattern = "add " + commonDifference;

			}
			else if (multiplyOrAdd.equalsIgnoreCase("Substract"))
			{
				nextTerm = startNum - commonDifference * 5;
				// Set the pattern for the sequence (e.g., "add 5")
				pattern = "substract " + commonDifference;
			}
		}
		else if (multiplyOrAdd.equalsIgnoreCase("Multiply"))
		{
			// Generate a random startNum between -10 and 10
			startNum = (int)(Math.random() * 21) - 10;
			// Generate a random commonDifference between 2 and 10
			commonDifference = (int)(Math.random() * 10) + 2;

			nextTerm = (int) (startNum * Math.pow(commonDifference,5)) ;
			// Set the pattern for the sequence (e.g., "add 5")
			pattern = "multiply by " + commonDifference;
		}
		else if (multiplyOrAdd.equalsIgnoreCase("Divide"))
		{
			do
			{
				commonDifference = (int)((Math.random() * 11) - 5);
			} while (commonDifference > -2 && commonDifference < 2);
			
			int Power = (int)(Math.random()*3)+6;
			startNum = (int)(Math.pow(commonDifference, Power+1));
			nextTerm = (int)(Math.pow(commonDifference, Power-4));
			// Set the pattern for the sequence (e.g., "add 5")
			pattern = "divide by " + commonDifference;
		}

	}

	public String printSequence() 
	{
		if (multiplyOrAdd.equalsIgnoreCase("Add"))
		{
			// Base case: print sequence and stop when we have printed enough terms
			if (printingSequence == 0 && !onPattern) 
			{
				System.out.println("__");
				return "";
			} 
			else 
			{
				// Print the current term, then move to the next term
				printingSequence--;
				System.out.print(startNum + ", ");
				startNum += commonDifference;  // Increment the start number for the next term
				return printSequence();        // Recursive call to print the next term
			}
		}
		else if (multiplyOrAdd.equalsIgnoreCase("Multiply"))
		{
			if (printingSequence == 0 && !onPattern) 
			{
				System.out.println("__");
				return "";
			} 
			else 
			{
				// Print the current term, then move to the next term
				printingSequence--;
				System.out.print(startNum + ", ");
				startNum = startNum * commonDifference;  // Increment the start number for the next term
				return printSequence();        // Recursive call to print the next term
			}
		}
		else if (multiplyOrAdd.equalsIgnoreCase("Substract"))
		{
			if (printingSequence == 0 && !onPattern) 
			{
				System.out.println("__"); // Print Line for user guess
				return "";
			} 
			else 
			{
				// Print the current term, then move to the next term
				printingSequence--;
				System.out.print(startNum + ", ");
				startNum -= commonDifference;  // Increment the start number for the next term
				return printSequence();        // Recursive call to print the next term
			}
		}
		else
		{
			if (printingSequence == 0 && !onPattern) 
			{
				System.out.println("__"); // Print the line
				return "";
			} 
			else 
			{
				// Print the current term, then move to the next term
				printingSequence--;
				System.out.print(startNum + ", ");
				startNum = startNum / commonDifference;  // Increment the start number for the next term
				return printSequence();        // Recursive call to print the next term
			}
		}
	}

	// Method to get the user's input (guess for the next term or the pattern)
	public void getInput() {
		Scanner in = new Scanner(System.in);

		// If the user hasn't made an incorrect guess yet, ask for the next term
		if (incorrectMeter == 0 && !onPattern) 
		{
			System.out.print("\nWhat is the next term in the sequence: ");
			userInput = in.nextInt();
		} 
		// If the user has made an incorrect guess, continue asking for the next term
		else if (incorrectMeter != 0 && !onPattern) 
		{
			userInput = in.nextInt();
		}
		// Once we are asking for the pattern, prompt accordingly
		else 
		{

			System.out.print("\nWhat is the pattern of the sequence (For example, \"add 12\", \"substract 8\")");
			userPatternInput = in.nextLine();
		}
	}

	// Method to print feedback based on user's guess
	public void printMessage() {
		// If the user has guessed incorrectly 3 times, print the correct next term
		if (incorrectMeter >= 3 && !onPattern) 
		{
			System.out.println("\nIncorrect! The next term was: " + nextTerm);
			System.out.println("Also, The pattern was: " + pattern);
		}
		// If the user's guess for the next term is incorrect, prompt them to try again
		else if (nextTerm != userInput && !onPattern) 
		{
			System.out.print("\nTry again. What is the next number in the sequence: ");
		}
		// If the user's guess is correct, congratulate them
		else if (userInput == nextTerm && incorrectMeter < 3 && !onPattern) 
		{
			System.out.println("\nCorrect! The next term was: " + nextTerm);
		} 
		// If the user is guessing the pattern, check their answer
		else if (pattern.equalsIgnoreCase(userPatternInput)) 
		{
			System.out.println("\nCorrect! The pattern was: " + pattern);
		}
		// If the pattern guess is incorrect, print the correct pattern
		else if (!pattern.equalsIgnoreCase(userPatternInput)) 
		{
			System.out.println("\nIncorrect! The pattern you entered, " + userPatternInput + " was wrong, the correct pattern was: " + pattern);
		}
	}
}
