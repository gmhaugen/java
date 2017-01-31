package sortingalgorithms;

import java.util.Arrays;

/**
 *
 * @author Geir Haugen
 * This class shows an example of how the bubble sort algorithm can be implemented in java.
 * 
 */
public class QuickSort {
    private int[] sortingList;
    private int number;
    
    // Constructor
    public QuickSort() {
        
    }
    
    // This method is called at first. This is because quicksort uses recursive method calling. Initializing local variables and starts the sorting.
    public void sort(int[] unsortedList) {
        this.sortingList = unsortedList;
        number = unsortedList.length;
        sorting(0, number - 1);
    }
    
    // This method performs the sorting. Recursive calls are performed.
    private void sorting(int left, int right) {
        int tempLeft = left;
        int tempRight = right;
        int pivot = sortingList[left + (right - left)/2];   // Calculating the current pivot point (value of the middle index).
        
        while (tempLeft <= tempRight) {
            while (sortingList[tempLeft] < pivot) {     // Increment the temporary left value as long as the index of "temporary left value" is smaller than the pivot point.
                tempLeft++;
            }
        
            while (sortingList[tempRight] > pivot) {    // Decrement the temporary right value as long as the index of "temporary right value" is larger than the pivot point.
                tempRight--;
            }
        
            if (tempLeft <= tempRight) {    // If the left value is smaller or equal to the right value, perform swap, increment the temporary left value and decrepemt the temporary right value.
                swap(tempLeft, tempRight);
                tempLeft++;
                tempRight--;
            }
        
            if (left < tempRight) {     // If the left value is smaller than the temporary right value, do recursive call with left value and temporary right value.
                sorting(left, tempRight);
            }
        
            if (tempLeft < right) {     // If the temporary left value is smaller than the right value, do recursive call with temporary left value and right value.
                sorting(tempLeft, right);
            }
        }
    }
    
    // This method performs swapping of values in the array.
    private void swap(int indexA, int indexB) {
        int tempA = sortingList[indexA];
        
        sortingList[indexA] = sortingList[indexB];
        sortingList[indexB] = tempA;
    }

    // Get method for the sorted array.
    public int[] getSortedArray() {
        return this.sortingList;
    }
}