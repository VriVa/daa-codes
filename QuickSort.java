// Divide and Conquer Algorithm
// Time Complexity: O(n^2), best and avg case O(n log n)
// Space Complexity: O(n)
// not stable
// In-place
// Recursive

import java.util.*;
public class QuickSort {

    public static void quickSort(int [] arr, int low, int high){
        if (low<high){
            int pivot = partition (arr, low, high);
            quickSort(arr, low, pivot-1);
            quickSort(arr, pivot+1, high);
        }
    }
    public static void swap (int [] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition (int [] arr, int low, int high){
        int pivot = arr[high];
        int i = low-1;

        for (int j = low; j < high; j++) {
            if (arr[j]<=pivot){
                i++;
                swap(arr, i, j);
            }        
        }
        swap(arr, i+1, high);
        return i+1;
    }

    public static void main (String[] args){

        // input array
        int [] arr = {10, 6, 7, 3, 2, 0};
         System.out.println ("Array Before Sorting:" + Arrays.toString(arr));
        
        // call quick sort function
        quickSort(arr, 0, arr.length-1);

        System.out.println ("Array After Sorting:" + Arrays.toString(arr));
    }
    
}
