// 0/1 Knapsack Problem using Branch and Bound
// Time Complexity: O(2^n) for worst case, best case O(n log n) or O(n)
import java.util.*;
class ItemBB implements Comparable<ItemBB>{
    int weight, value;
    double ratio;

    ItemBB (int value, int weight){
        this.value = value;
        this.weight = weight;
        this.ratio = (double)value /weight;

    }

    public int compareTo (ItemBB other){
        return Double.compare(other.ratio, this.ratio); // Sort in descending order
    }
}

class NodeBB{
    int level, profit, weight;
    double bound;

    NodeBB (int level, int profit, int weight){
        this.level = level;
        this.profit = profit;
        this.weight = weight;

    }

}


public class ZeroOneKnapsackBB {

    // Function to calculate the upper bound

    static double bound (NodeBB node, int n, int W, ItemBB [] items){

        if (node.weight>= W){
            return 0;
        }

        double profitBound= node.profit;
        int totalWeight= node.weight;

        int j= node.level+1;


        //add items to the knapsack until it is full
        while(j<n && totalWeight + items[j].weight<=W){
            totalWeight+= items[j].weight;
            profitBound+= items[j].value;
            j++;
        }


        if (j<n){
            profitBound+= (W-totalWeight) * items[j].ratio;
        }
        return profitBound;
    }

    // Function to solve the knapsack problem using branch and bound
    static int knapsack (ItemBB[] items, int W){

        Arrays.sort(items);
        int n= items.length;

        //bfs
        Queue<NodeBB> queue= new LinkedList<>();

        NodeBB u =new NodeBB(-1,0,0);
        u.bound = bound(u, n, W, items);
        queue.offer(u);


        int maxProfit= 0;

        while (!queue.isEmpty()){
            u = queue.poll();

            //if the node is not valid, continue
            if (u.level == n-1){
                continue;
            }

            NodeBB v = new NodeBB(u.level+1, u.profit, u.weight);
            //include the next item
            v.weight += items[v.level].weight;
            v.profit += items[v.level].value;

            //if the node is valid, check if it is better than the current max profit

            if (v.weight <= W && v.profit> maxProfit){
                maxProfit = v.profit;
            }

            //calculate the upper bound
            v.bound = bound(v, n, W, items);
            queue.offer(v);

            //exclude the next item
            v = new NodeBB(u.level+1, u.profit, u.weight);
            v.bound = bound(v, n, W, items);
            if(v.bound > maxProfit){
                queue.offer(v);
            }
        
        }

        return maxProfit;
    }



public static void main(String[] args) { 

    int W= 50;

    ItemBB [] items ={
        new ItemBB(60,10),
        new ItemBB(100,20),
        new ItemBB(120,30)
    };

    System.out.println("Knapsack capacity: " + W);
    System.out.println("Items: ");
    for (ItemBB item : items) {
        System.out.println("Weight: " + item.weight + ", Value: " + item.value);
    }
    System.out.println("Maximum Profit: " + knapsack(items, W));
    }
}
    

