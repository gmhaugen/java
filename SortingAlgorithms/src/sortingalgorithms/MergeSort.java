package sortingalgorithms;

/**
 *
 * @author Geir Haugen
 * 
 * This class shows an example for how the merge sort algorithm can be implemented.
 */
public class MergeSort {
    private int[] sortingArray;     // The main array. Will become the sorted array when sorting is finished.
    private int[] tempArray;        // Temporary array to perform sorting with.
    private int arrayLength;        // Holds the length of the initial array.
    
    // Constructor.
    public MergeSort() {
        
    }
    
    // The starting point for the algorithm. This extra method is needed because in this example, recursive calls are performed (see sorting()).
    // Also initialize needed variables.
    public void sort(int[] unsortedArray) {
        this.sortingArray = unsortedArray;
        this.arrayLength = unsortedArray.length;
        this.tempArray = new int[arrayLength];
        sorting(0, arrayLength - 1);            // For the first call, minimum index and maximum index is set.
    }
    
    // The main method for performing the sort. Performs recursive calls as long as the lower index is lower than the higher index.
    private void sorting(int low, int high) {
        if (low < high) {
            int pivot = low + (high - low) / 2;     // Calculates new pivot point for each call.
            sorting(low, pivot);                    // Performing recursive call for the lower part.
            sorting(pivot + 1, high);               // Performing recursive call for the higher part.
            merge(low, pivot, high);                // Merging is performed.
        }
    }
    
    // This method merge the two values into the resulting array.
    private void merge(int low, int pivot, int high) {
        for (int i = low; i <= high; i++) {
            tempArray[i] = sortingArray[i];
        }
        
        int lowCounter = low;           // Counter variable to hold the lower index.
        int midCounter = pivot + 1;     // Counter variable for the pivot or middle point.
        int highCounter = low;          // Counter variable to hold the higher index.
        
        // As long as the lower index counter is lower or equal to the pivot/middle index,
        // and the pivot/middle index is lower or equal to the higher index counter.
        // The counters are also incremented according to the conditions.
        while (lowCounter <= pivot && midCounter <= high) {         
            if (tempArray[lowCounter] <= tempArray[midCounter]) {
                sortingArray[highCounter] = tempArray[lowCounter];
                lowCounter++;
            } else {
                sortingArray[highCounter] = tempArray[midCounter];
                midCounter++;
            }
            highCounter++;
        }
        
        // As long as the lower index counter is lower or equal to the pivot/middle index,
        // update the final array with the sorted values.
        while (lowCounter <= pivot) {
            sortingArray[highCounter] = tempArray[lowCounter];
            highCounter++;
            lowCounter++;
        }
        
    }
    // Get method for the sorted array.
    public int[] getSortingArray() {
        return this.sortingArray;
    }
}
