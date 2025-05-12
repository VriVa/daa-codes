// time complexity O(V^2E), where V is the number of vertices and E is the number of edges

import java.util.*;
public class FordFulkerson {

    static final int V =6; // Number of vertices in the graph

    //dfs to find an augmenting path from source to sink

    static boolean dfs(int [][] rGraph, int s, int d, boolean [] visited, int [] parent){
        visited[s]=true; //mark the source as visited
        if (s==d){
            return true;
        }

        for (int v = 0; v < V; v++) {
            if (!visited[v] && rGraph[s][v]>0){
                parent [v]=s; //store the parent of the vertex   
                if (dfs(rGraph, v, d, visited, parent)){
                    return true;
                }
            }
            
        }
        
        return false;
    }
     static void printPath(int[] parent, int s, int d) {
        int[] path = new int[V];
        int index = 0;
        int v = d;

        while (v != s) {
            path[index++] = v;
            v = parent[v];
        }
        path[index++] = s;

        System.out.print("Augmenting Path: ");
        for (int i = index - 1; i >= 0; i--) {
            System.out.print(path[i]);
            if (i > 0) System.out.print(" -> ");
        }
    }

    public static int fordFulkerson(int [][] graph, int s, int d){
        int u, v;

        //create a residual graph and fill it with given capacities in the original graph
        int [][] rGraph = new int [V][V];
        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                rGraph[u][v]=graph[u][v];  
           }         
        }

        int []parent = new int [V]; //store path found by dfs
        int maxFlow = 0;
        int pathCount = 1; //initialize max flow

        //while there is a path from source to sink in the residual graph
        while (dfs(rGraph, s, d, new boolean[V], parent)){
            int pathFlow = Integer.MAX_VALUE;
            for (v = d; v!=s; v=parent[v]){
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);

            }

            for (v = d; v!=s; v=parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow; //update residual capacities of the edges and reverse edges
                rGraph[v][u] += pathFlow;
                
            }
             System.out.print("Path " + pathCount + ": ");
            printPath(parent, s, d);
            System.out.println(" | Flow = " + pathFlow);
            pathCount++;
            maxFlow += pathFlow; //add path flow to overall flow
         
                   
        }


        return maxFlow;


    }


    public static void main(String[] args) {
        //each cell of the matrix represents the capacity of the edge between the vertices
        //for example, graph[0][1] = 16 means there is an edge from vertex 0 to vertex 1 with capacity 16
        int [][] graph = {
            {0, 16, 13, 0, 0, 0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
        };
        int source=0;
        int sink =5;

        System.out.println("The maximum possible flow is: " + fordFulkerson(graph, source, sink));
    }
    
}
