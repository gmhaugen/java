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
        randomArray = fillRandomArray(randomArray = new int[18]);
        BubbleSort bubbleSort = new BubbleSort();
        QuickSort quickSort = new QuickSort();
        MergeSort mergeSort = new MergeSort();
        print(randomArray);
        
        // Testing the different sorting algorithms.
        //bubbleSort.sort(randomArray);
        //quickSort.sort(randomArray);
        mergeSort.sort(randomArray);
        
        // Printing the different sorted arrays.
        //print(bubbleSort.getSortedArray());
        //print(quickSort.getSortedArray());
        print(mergeSort.getSortingArray());
        
    }
    
    // This method fills an array of random numbers between 0 and 500 and return it.
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