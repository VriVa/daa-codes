// Greedy Algorithm
// Time Complexity : O(n log n), best case O(n)
// Space Complexity : O(1)

import java.util.*;
class Item{
    int value,  weight;
    double ratio;
    // constructor to initialize value, weight and ratio
    Item(int value, int weight){
        this.value= value;
        this.weight= weight;
        this.ratio= (double) value/weight;
    }
}

public class FractionalKnapsack {
    public static double fractionalKnapsack(int capacity, int [] values, int [] weights){

      int n= values.length;
      
      Item [] items = new Item [n];

      for (int i = 0; i < n; i++) {
            items[i]= new Item(values[i], weights[i]);

      }
      Arrays.sort (items, Comparator.comparingDouble((Item i)->i.ratio));
      double profit=0;
        for (int i = n-1; i >=0; i--) {
                if (capacity==0){
                    break;
                }
                if (items[i].weight<=capacity){
                    profit= profit + items[i].value;
                    capacity= capacity- items[i].weight;
                }
                else{
                    profit= profit + items[i].ratio*capacity;
                    capacity=0;
                }
            }
        return profit;
    }
    


    public static void main (String[] args){
         // input data
         int capacity= 50;
         int [] values = {60, 100, 120};
         int [] weights = {10, 20, 30};

        System.out.println("Item details:");
        System.out.println("Capacity: " + capacity);
        System.out.println("Values: " + Arrays.toString(values));
        System.out.println("Weights: " + Arrays.toString(weights));
        System.out.println("Maximum Profit:"+ fractionalKnapsack(capacity, values, weights));


    }
}
