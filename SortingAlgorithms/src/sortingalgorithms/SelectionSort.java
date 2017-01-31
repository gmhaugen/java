package sortingalgorithms;

/**
 *
 * @author Geir Haugen
 * 
 * This class shows an example of how the selection sort algorithm can be implemented.
 */
public class SelectionSort {
    int[] sortingArray;
    
    // Constructor.
    public SelectionSort() {
        
    }
    
    // This method performs the sorting.
    public void sort(int[] unsortedArray) {
        this.sortingArray = unsortedArray;
        for (int i = 0; i < sortingArray.length; i++) {
            int index = i;
            for (int j = i + 1; j < sortingArray.length; j++) {
                if (sortingArray[j] < sortingArray[index]) {
                    index = j;
                }
            }
            swap(index, i);
        }
    }
    
    // This method swaps the values at the given indexes.
    private void swap(int index, int i) {
        int low = sortingArray[index];
        sortingArray[index] = sortingArray[i];
        sortingArray[i] = low;
    }
    
    // Get method for the sorted array.
    public int[] getSortedArray() {
        return sortingArray;
    }
}