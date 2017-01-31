package sortingalgorithms;

import java.util.Arrays;

/**
 *
 * @author Geir Haugen
 * This class shows an example of how the bubble sort algorithm can be implemented in java.
 * 
 */
public class BubbleSort {
    private int[] sortingArray;     // This array holds the received array and is completely the sorted array.
    private boolean swapped;        // Boolean value to determine if a swap have been performed.
    
    // Constructor. Initialize the swapped value with the value true.
    public BubbleSort() {
        this.swapped = true;
    }
    
    // This methods performs the sorting. 
    public void sort(int[] unsortedArray) {
        this.sortingArray = unsortedArray;
        
        while (swapped) {
            swapped = false;
            
            for (int i = 0; i < sortingArray.length; i++) {
                if (i != sortingArray.length - 1) {
                    if (sortingArray[i] > sortingArray[i + 1]) {
                        swap(i, (i + 1));
                    }
                }
            }
        }
        //return sortingArray;
    }
    
    // This method performs sorting just as sort(int[] unsortedArray does, but prints the progress of the sorting.
    public int[] sortShowFull(int[] unsortedArray) {
        sortingArray = unsortedArray;
        
        while (swapped) {
            swapped = false;
            
            for (int i = 0; i < unsortedArray.length; i++) {
                if (i != this.sortingArray.length - 1) {
                    if (this.sortingArray[i] > this.sortingArray[i + 1]) {
                        swap(i, (i + 1));
                    }
                }
                System.out.println(Arrays.toString(unsortedArray));
            }
        }
        return sortingArray;
    }
    
    // This method performs swapping of two variables if the first one is greater than the second.
    private void swap(int indexA, int indexB) {
        if (indexA != this.sortingArray.length) {
            int tempA = this.sortingArray[indexA];
            int tempB = this.sortingArray[indexB];
        
            this.sortingArray[indexA] = tempB;
            this.sortingArray[indexB] = tempA;
        
            swapped = true;
        }
    }
    
    // Get method for the sorted array.
    public int[] getSortedArray() {
        return this.sortingArray;
    }
}