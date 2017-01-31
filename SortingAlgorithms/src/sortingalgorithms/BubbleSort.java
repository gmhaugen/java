package sortingalgorithms;

import java.util.Arrays;

/**
 *
 * @author Geir Haugen
 * This class shows an example of how the bubble sort algorithm can be implemented in java.
 * 
 */
public class BubbleSort {
    int[] sortedList;
    int[] tempList;
    boolean swapped;
    
    // Constructor
    public BubbleSort() {
        sortedList = null;
        tempList = null;
        swapped = true;
    }
    
    // This methods performs the sorting.
    public int[] sort(int[] unsortedList) {
        tempList = unsortedList;
        
        while (swapped) {
            swapped = false;
            
            for (int i = 0; i < unsortedList.length; i++) {
                if (i != unsortedList.length - 1) {
                    if (unsortedList[i] > unsortedList[i + 1]) {
                        swap(i, (i + 1));
                    }
                }
            }
        }
        return tempList;
    }
    
    // This method performs sorting just as sort(int[] unsortedList does, but prints the progress of the sorting.
    public int[] sortShowFull(int[] unsortedList) {
        tempList = unsortedList;
        
        while (swapped) {
            swapped = false;
            
            for (int i = 0; i < unsortedList.length; i++) {
                if (i != unsortedList.length - 1) {
                    if (unsortedList[i] > unsortedList[i + 1]) {
                        swap(i, (i + 1));
                    }
                }
                System.out.println(Arrays.toString(unsortedList));
            }
        }
        return tempList;
    }
    
    // This method performs swapping of two variables if the first one is greater than the second.
    private void swap(int indexA, int indexB) {
        if (indexA != tempList.length) {
            int tempA = tempList[indexA];
            int tempB = tempList[indexB];
        
            tempList[indexA] = tempB;
            tempList[indexB] = tempA;
        
            swapped = true;
        }
    }
}