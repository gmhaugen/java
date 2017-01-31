/**
 * 
 * @author Geir Haugen
 *
 * To test the functionality of the RandomPrime.java class, please uncomment the lines that test the
 * different methods. For better output experience, only the method being checked should be uncommented.
 */

public class Program {
	public static void main(String[] args) {
		RandomPrime randPrime = new RandomPrime();
		//long randomNumber = randPrime.generateRandomPrime();
		//System.out.print(randomNumber);
		
		//randPrime.sieveofEratosthenes(2567);
		
		
		//System.out.println(randPrime.trialDivision(236354777));
		
		
		// Recommended "iterations" is 8
		//randPrime.middleSquareMethod(4782, 8);
		
		
		//System.out.println(randPrime.fermat(2, 1));
		
		
		System.out.println(randPrime.trialDivision(19));
	}

}
