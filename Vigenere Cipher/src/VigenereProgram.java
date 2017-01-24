import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * @Author: 140464
 * 
 * NOTE: For using the Vigenère cipher, the lines 22, 25, 37 and 38 should be uncommented.
 * 		 For when the Beaufort cipher is being used, these lines should remain commented.
 * 		 And the lines 28, 31, 40 and 41 should be uncommented.
 */

public class VigenereProgram {
	
	// Scanner for reading input from the user
	private Scanner reader;
	
	// String variable to store input from the user
	private String input;
	
	// The Vigenère encoder
	private VigenereEncoder encoder;
	
	// The Vigenère decoder
	private VigenereDecoder decoder;
	
	// The Beaufort encoder
	//private BeaufortEncoder encoder;
	
	// The Beaufort decoder
	//private BeaufortDecoder decoder;
	
	// Constructor
	public VigenereProgram() {
		reader = new Scanner(System.in);
		
		encoder = new VigenereEncoder();
		decoder = new VigenereDecoder();
		
		//encoder = new BeaufortEncoder();
		//decoder = new BeaufortDecoder();
	}
	
	// Is called to start the program
	public void start() {
		System.out.println("The Vigenère and Beaufort decoder/encoder program");
		printHelp();
		inputHandler();
	}
	
	// Prints the commands to the user
	public void printHelp() {
		System.out.println("COMMANDS");
		System.out.println("help        Displays the commands");
		System.out.println("encode      Runs the encoder");
		System.out.println("decode      Runs the decoder");
		System.out.println("exit        Terminates the program");
	}
	
	// Handles the input from the user
	public void inputHandler() {
		System.out.print(">");
		input = reader.next();
		
		if (input.equals("help")) {
			printHelp();
			inputHandler();
		} else if (input.equals("encode")) {
			System.out.print("key>");
			String key = reader.next();
			reader = new Scanner(System.in);
			System.out.print("cleartext>");
			String clearText = reader.nextLine();
			System.out.println("encoding..");
			encoder.encode(clearText, key);
			encoder.printAll();
			inputHandler();
		} else if (input.equals("decode")) {
			System.out.print("key>");
			String key = reader.next();
			reader = new Scanner(System.in);
			
			System.out.print("ciphertext>");
			String cipherText = reader.nextLine();
			System.out.println("decoding..");
			
			decoder.decode(cipherText, key);
			decoder.printAll();
			
			// Recursive call so that the user may continue using the program insted of launching it again
			inputHandler();
		} else if (input.equals("exit")) {
			close();
		} else {
			System.out.println("\"" +input + "\"" + " is not a valid command!");
			inputHandler();
		}
	}
	
	// Called to close the program
	private void close() {
		System.out.println("exiting..");
		reader.close();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		VigenereProgram program = new VigenereProgram();
		program.start();
	}
}
