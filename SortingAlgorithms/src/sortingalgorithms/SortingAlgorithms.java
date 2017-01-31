package sortingalgorithms;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Geir Haugen
 * This class shows examples of the different sorting algorithms in the project.
 */
public class SortingAlgorithms {
    public static void main(String[] args) {
        int[] randomArray;
        int[] sortedList = new int[20];
        randomArray = fillRandomArray(randomArray = new int[20]);
        BubbleSort bubbleSort = new BubbleSort();
        print(randomArray);
        sortedList = bubbleSort.sort(randomArray);
        print(sortedList);
    }
    
    // This method fills an array of random numbers between 0 and 500.
    public static int[] fillRandomArray(int[] rArray) {
        Random rand = new Random();
        
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = rand.nextInt(500);
        }
        
        return rArray;
    }
    
    // This method prints the array.
    public static void print(int[] arrayToPrint) {
        System.out.println(Arrays.toString(arrayToPrint));
    }
}