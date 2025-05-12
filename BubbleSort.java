import java.util.*;

public class BubbleSort {

    public static void bubbleSort(int [] array){
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    // swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
                
            }
        }
    }
    public static void main(String[] args) {
        int [] array= {5, 1, 4, 2, 8};
        System.out.println("Original array: " + Arrays.toString(array));
        bubbleSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

    }
    
}
