package sortingalgorithms;

import java.util.Arrays;

/**
 *
 * @author Geir Haugen
 * 
 * This class shows an example of how the insertion sort algorithm can be implemented.
 */
public class InsertionSort {
    private int currentIndex;
    private int[] sortingArray;
    
    // Constructor.
    public InsertionSort() {
        currentIndex = 0;
    }
    
    // This method performs the printing.
    public void sort(int[] unsortedArray) {
        for (int i = 1; i < sortingArray.length; i++) {
            for (int j = i; j > 0; j--) {
                if (unsortedArray[j] < unsortedArray[j - 1]) {
                    currentIndex = unsortedArray[j];
                    unsortedArray[j] = unsortedArray[j - 1];
                    unsortedArray[j - 1] = currentIndex;
                }   
            }
        }
        this.sortingArray = unsortedArray;      // Saving the sorted array to a local variable (this is to make printing the sorted array after the sorting.
    }
    
    // This method performs sorting just as the sort(int[]) method does, but also print the array for each step.
    public void sortShowFull(int[] unsortedArray) {
        this.sortingArray = unsortedArray;
        
        for (int i = 1; i < sortingArray.length; i++) {
            for (int j = i; j > 0; j--) {
                if (unsortedArray[j] < unsortedArray[j - 1]) {
                    currentIndex = unsortedArray[j];
                    unsortedArray[j] = unsortedArray[j - 1];
                    unsortedArray[j - 1] = currentIndex;
                }
                System.out.println(Arrays.toString(unsortedArray));
            }
        }
        this.sortingArray = unsortedArray;
    }
    
    // Get method for the sorted array.
    public int[] getSortedArray() {
        return sortingArray;
    }
    
}
