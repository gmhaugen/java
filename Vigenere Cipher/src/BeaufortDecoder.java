import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * @Author: 140464
 * @Description: Class for deciphering a ciphered text using the Beaufort cipher.
 * Input can only be uppercase.
 * 
 */

public class BeaufortDecoder {

	// Holds the 26 letters of the english alphabet and the corresponding numbers
	private Map<Character, Integer> lettersToNumbers;
	
	// Holds the numbers 0-25 and the corresponding letters of the english alphabet
	private Map<Integer, Character> numbersToLetters;
	
	// Holds the cleartext
	private String clearText;
	
	// Holds the ciphertext
	private String cipherText;
	
	// Holds the key for enchiphering/deciphering
	private String key;
	
	// Holds the numbers corresponding to each character in the cleartext
	private ArrayList<Integer> clearTextNumbers;
	
	// Holds the numbers corresponding to each character in the chiphertext
	private ArrayList<Integer> cipherTextNumbers;
	
	// Holds the numbers corresponding to each character in the key
	private ArrayList<Integer> keyNumbers;
	
	// Is used to build the decoded characters to a string
	private StringBuilder stringBuilder;
	
	// Constructor
	public BeaufortDecoder() {
		lettersToNumbers = new HashMap<Character, Integer>();
		numbersToLetters = new HashMap<Integer, Character>();
		
		lettersToNumbers.put('A', 0);
		lettersToNumbers.put('B', 1);
		lettersToNumbers.put('C', 2);
		lettersToNumbers.put('D', 3);
		lettersToNumbers.put('E', 4);
		lettersToNumbers.put('F', 5);
		lettersToNumbers.put('G', 6);
		lettersToNumbers.put('H', 7);
		lettersToNumbers.put('I', 8);
		lettersToNumbers.put('J', 9);
		lettersToNumbers.put('K', 10);
		lettersToNumbers.put('L', 11);
		lettersToNumbers.put('M', 12);
		lettersToNumbers.put('N', 13);
		lettersToNumbers.put('O', 14);
		lettersToNumbers.put('P', 15);
		lettersToNumbers.put('Q', 16);
		lettersToNumbers.put('R', 17);
		lettersToNumbers.put('S', 18);
		lettersToNumbers.put('T', 19);
		lettersToNumbers.put('U', 20);
		lettersToNumbers.put('V', 21);
		lettersToNumbers.put('W', 22);
		lettersToNumbers.put('X', 23);
		lettersToNumbers.put('Y', 24);
		lettersToNumbers.put('Z', 25);
		
		numbersToLetters.put(0, 'A');
		numbersToLetters.put(1, 'B');
		numbersToLetters.put(2, 'C');
		numbersToLetters.put(3, 'D');
		numbersToLetters.put(4, 'E');
		numbersToLetters.put(5, 'F');
		numbersToLetters.put(6, 'G');
		numbersToLetters.put(7, 'H');
		numbersToLetters.put(8, 'I');
		numbersToLetters.put(9, 'J');
		numbersToLetters.put(10, 'K');
		numbersToLetters.put(11, 'L');
		numbersToLetters.put(12, 'M');
		numbersToLetters.put(13, 'N');
		numbersToLetters.put(14, 'O');
		numbersToLetters.put(15, 'P');
		numbersToLetters.put(16, 'Q');
		numbersToLetters.put(17, 'R');
		numbersToLetters.put(18, 'S');
		numbersToLetters.put(19, 'T');
		numbersToLetters.put(20, 'U');
		numbersToLetters.put(21, 'V');
		numbersToLetters.put(22, 'W');
		numbersToLetters.put(23, 'X');
		numbersToLetters.put(24, 'Y');
		numbersToLetters.put(25, 'Z');
	}
	
	// Decodes a ciphertext. Parameters are the ciphertext and the key.
	public void decode(String cipherText, String key) {
		this.cipherText = cipherText;
		this.key = key;

		clearTextNumbers = new ArrayList<Integer>();
		cipherTextNumbers = new ArrayList<Integer>();
		keyNumbers = new ArrayList<Integer>();
		stringBuilder = new StringBuilder();
		
		// Holds the index used to iterate the key
		int keyCounter = 0;
		
		// Holds the calculated/decoded number
		int decodedValue = 0;
		
		convertCipherTextToNumbers();
		convertKeyToNumbers();
		
		for (int i = 0; i < cipherTextNumbers.size(); i++) {
			
			// Making sure that we restart the iteration of the key if we are at the last letter
			if (keyCounter == keyNumbers.size()) {
				keyCounter = 0;	
			}
			
			// Subtracting value from the key with the value from the ciphertext
			decodedValue = keyNumbers.get(keyCounter) - cipherTextNumbers.get(i);
			
			// Here we do the mod26 operation
			while (decodedValue < 0) {
				decodedValue += 26;
			}
			
			// Adding the decoded value of a letter
			clearTextNumbers.add(decodedValue);
			
			// Adding the decoded value (and turning numeric to character) to the stringbuilder
			stringBuilder.append(numbersToLetters.get(decodedValue));
			
			// Adding the builded text to the cleartext string
			clearText = stringBuilder.toString();
			
			// Incrementing keycounter to make iteration over the key possible
			keyCounter++;
		}
	}
	
	// Converts key characters to the numeric representation and adds it to the list which holds the (numeric) key
	private void convertKeyToNumbers() {
		for (int i = 0; i < this.key.length(); i++) {
			keyNumbers.add(lettersToNumbers.get(Character.toUpperCase(key
					.charAt(i))));
		}
	}
	
	// Converts ciphertext characters to the numeric representation and adds it to the list which holds the (numeric) chipertext
	private void convertCipherTextToNumbers() {
		for (int i = 0; i < this.cipherText.length(); i++) {
			cipherTextNumbers.add(lettersToNumbers.get(Character
					.toUpperCase(this.cipherText.charAt(i))));
		}
	}
	
	// Prints the input from the user and the result of the decoding
	public void printAll() {
		System.out.println();

		System.out.print("your key: " + this.key + " (");

		for (int i = 0; i < keyNumbers.size(); i++) {
			System.out.print(keyNumbers.get(i));
			if (i != keyNumbers.size() - 1)
				System.out.print("-");
		}
		System.out.print(")");

		System.out.println();

		System.out.print("ciphertext: " + this.cipherText + "(");

		for (int i = 0; i < cipherTextNumbers.size(); i++) {
			System.out.print(cipherTextNumbers.get(i));
			if (i != cipherTextNumbers.size() - 1)
				System.out.print("-");
		}
		
		System.out.print(")");

		System.out.println();

		System.out.print("clearText: " + this.clearText + " (");

		for (int i = 0; i < clearTextNumbers.size(); i++) {
			System.out.print(clearTextNumbers.get(i));
			if (i != clearTextNumbers.size() - 1)
				System.out.print("-");
		}
		System.out.print(")");

		System.out.println();
	}
	
}
