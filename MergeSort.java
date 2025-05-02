// Divide and Conquer Algorithm
// Time Complexity: O(n log n), all three cases
// Space Complexity: O(n)
// Stable
// In-place
// Recursive

import java.util.*;

public class MergeSort{

    public static void mergeSort(int[] arr, int left, int right){
        if(left<right){
            int mid = left + (right -left)/2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1,right);
            merge(arr, left, mid,right);

        }
    }

    //function to merge sub arrays
   public static void merge (int[] arr, int left, int mid, int right){

    int n1= mid-left +1;
    int n2= right-mid;

    int [] leftArr = new int [n1];
    int [] rightArr = new int [n2];

    //copy elements to temp arrays
    for (int i = 0; i < n1; i++) {
        leftArr[i]= arr[left + i];
    }
    for (int i = 0; i < n2; i++) {
        rightArr[i]= arr[mid + 1 + i];
        
    }
     int i=0;
     int j=0;
     int k=left; 

     while (i<n1 && j<n2){
        if (leftArr[i] <= rightArr[j]){
            arr[k]= leftArr[i];
            i++;
        }
        else{
            arr[k]= rightArr[j];
            j++;
        }
        k++;
     }

    //copy remaining elements
    while(i<n1){
        arr[k]= leftArr[i];
        i++;
        k++;
    }
    while(j<n2){
        arr[k]= rightArr[j];
        j++;
        k++;
    }

    }
     

    public static void main(String[] args) {
       //input array
       int[] arr={10,9,7,3,1};
       System.out.println("Array Before Sorting:" + Arrays.toString(arr));
        
       //call merge sort function
       mergeSort(arr,0, arr.length-1);
       System.out.println("Array After Sorting:" + Arrays.toString(arr));
    }
    
    }

