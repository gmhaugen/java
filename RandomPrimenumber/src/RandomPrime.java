import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * 
 * @author Geir Haugen
 *
 * This class contain methods for:
 * -generating prime numbers based on java.security.SecureRandom.
 * -generating random numbers using the Middle Square Method and java.security.SecureRandom.
 * -determining if a number is a prime number using trial division and the Fermat test.
 * -generation of primes using the sieve of Eratosthenes.
 */

public class RandomPrime {
	SecureRandom random = new SecureRandom();
	boolean randPrimeFound = false;
	ArrayList<Integer> list = new ArrayList<Integer>();
	
	public RandomPrime() {
	}
	
	// Generates a random number of type <long> using java.Security.SecureRandom and returns it.
	public long generateRandomNumber() {
		long temp = random.nextInt();
		
		// Making sure that the number is not negative.
		while (temp <= 0) {
			temp = random.nextInt();
		}
		return temp;
	}
	
	// Produces and prints a list of pseudorandom numbers using the Middle Square Method.
	// Because this method does not take in consideration that the Middle Square Method
	// makes the output getting close to zero as it proceeds.
	// The method uses a stringbuilder and string-to-int and int-to-string conversion to extract the seed
	// from the current output value.
	public void middleSquareMethod(int seed, int iterations) {
		ArrayList<Long> randomList = new ArrayList<Long>(); // Holds the pseudorandom numbers
		int currentSeed = seed; // Initial seed (initialized here because it changes throughout the algorithm
		String tempCurrentNum;
		String tempCurrentSeed;
		System.out.println("Start seed = " + seed);
		
		for (int i = 0; i < iterations; i++) {
			long currentNum = currentSeed * currentSeed; // Calculate the new "random" number
			StringBuilder sb = new StringBuilder();
			randomList.add(currentNum);
			
			// extracting the new seed from the current "random" number
			tempCurrentNum = String.valueOf(currentNum);
			sb.append(tempCurrentNum.charAt(2));
			sb.append(tempCurrentNum.charAt(3));
			sb.append(tempCurrentNum.charAt(4));
			sb.append(tempCurrentNum.charAt(5));
			tempCurrentSeed = sb.toString();
			
			currentSeed = Integer.parseInt(tempCurrentSeed);
		}
		
		// Printing out the list of numbers generated
		for (int i = 0; i < randomList.size(); i++) {
			System.out.println(randomList.get(i));
		}
	}
	
	// Returns a random number using the generateRandomPrime method.
	public long generateRandomPrime() {
		long temp = generateRandomNumber();
		
		while (!trialDivision(temp)) {
			temp = generateRandomNumber();
		}
		return temp;
	}
	
	// Checks if input number is prime.
	// Returns true if number is prime and false if it is not.
	// This method takes an int as a parameter and test it.
	public boolean trialDivision(int num) {
		int squared = (int)Math.sqrt(num);
		
		if (num < 2) {
			return false;
		}
		
		// Testing every number up to the square root of the number being tested.
		for (int i = 2; i <= squared; i++) {
			if (num % i == 0) {
				return false; // is composite.
			}
		}
		return true; // is prime.
	}
	
	// Checks if input number is prime.
	// Returns true if number is prime and false if it is not.
	// This method takes a long as a parameter and test it.
	public boolean trialDivision(long num) {
		long squared = (int)Math.sqrt(num);
		
		if (num < 2) {
			return false;
		}
		
		// Testing every number up to the square root of the number being tested.
		for (int i = 2; i <= squared; i++) {
			if (num % i == 0) {
				return false; // is composite.
			}
		}
		return true; // is prime.
	}
	
	// This method determines if a number is prime using
	// the fermat primality test.
	public boolean fermat(int num, int iterations) {
		
		// there is no prime number lower than 2.
		if (num < 2) {
			return false;
		}
		
		// need this check because the algorithm will not count 2 as a prime number.
		// this is because 2^(2-1) = 0 mod 2.
		if (num == 2) {
			return true;
		}
		
		for (int i = 0; i < iterations; i++) {
			for (int j = 2; j < 10; j++) {
				long temp = num -1;
				long temp2 = (long)Math.pow(j, temp); // 
				
				// performs modulus and checks whether the number is prime or composite
				if (temp2 % num != 1) { // is probably composite
					return false;
				} else { // is probably prime
					return true;
				}
			}
		}
		return true;
	}
	
	// Generates every prime number from 2 up until the limit (parameter)
	// and adds it to the list.
	public void sieveofEratosthenes(int limit) {
		// true values of this array means that the number is prime, false means composite.
		boolean[] primes = new boolean[(int)limit];
		
		// Filling the array with "true" variables
		for (int i = 1; i < limit; i++) {
			primes[i] = true;
		}
		
		for (int i = 2; i <= limit; i++) {
			
			// here the algorithm assumes that the numbers that
			// have not been crossed out is primes.
			if (primes[i-1]) {
				list.add(i);
				
				// "Crossing out" the numbers that are not prime numbers (multiplicatives of
				// the current number in focus).
				for (int j = i*i; j <= limit; j += i) {
					primes[j-1] = false;
				}
			}
		}
		System.out.println();
		
		// Printing out the list of primes
		for (int i = 0; i < list.size(); i++) {
			System.out.print(" " + list.get(i));
		}
	}
}
