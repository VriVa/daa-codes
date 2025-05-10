// Graph Algorithm
// Greedy Algorithm
// Time Complexit : O(V^2) for adjacency matrix, best case O(E log V) if adjacency list is used
import java.util.*;
public class PrimsAlgorithm {

    public static void primMST(int [][] graph, int V){

        int [] minEdgeWeight = new int [V]; // stores minimum weight edge
        boolean[] inMST = new boolean [V]; // stores vertices included in MST
        int parent []= new int [V]; // stores parent vertices   
       Arrays.fill(minEdgeWeight, Integer.MAX_VALUE); // initialize all keys to max value
        Arrays.fill(parent, -1); // initialize all parents to -1
        minEdgeWeight[0]=0;

        for (int count = 0; count < V-1; count++) {
            int u = minKey(minEdgeWeight, inMST,V); // find vertex with minimum key
            inMST[u]= true; // include vertex in MST

            for (int v = 0; v < V; v++) {
                // update key and parent if vertex is not in MST and weight of edge is less than key
               
                if (graph[u][v]!=0 && graph[u][v]<minEdgeWeight[v] && !inMST[v]){
                    minEdgeWeight[v]= graph[u][v]; // update key value
                    parent[v]= u; // update parent
                }
                
            }
        }
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }


    }

    public static int minKey(int [] minEdgeWeight, boolean [] inMST, int V){
        int min= Integer.MAX_VALUE, minIndex=-1;

        for (int v = 0; v < V; v++) {
            if (!inMST[v] && minEdgeWeight[v]<min){
                min= minEdgeWeight[v];
                minIndex= v;
            }
        }
        return minIndex;
    }
        
    public static void main(String[] args) {
         int V= 5; // number of vertices

        int [][] graph ={
            {0,2,0,6,0},
            {2,0,3,8,5},
            {0,3,0,0,7},
            {6,8,0,0,9},
            {0,5,7,9,0}
        };

        primMST(graph, V);

    }
    
}
