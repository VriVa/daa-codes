// single source shortest path algorithm
// Dijkstra's algorithm
// adjacency matrix representation
// Time Complexity : O(V^2) for adjacency matrix, O(E log V) for adjacency list
import java.util.*;

public class DijkstrasAlgorithm {

    static class Node1 implements Comparable<Node1>{
        int vertex;
        int distance;

        Node1(int v, int d){
            this.vertex=v;
            this.distance=d;
        }

        public int compareTo(Node1 other){
            return Integer.compare(this.distance, other.distance);
        }
            
    }

    public static void dijkstra (int [][]graph, int source){
        int V = graph.length;
        int [] dist = new int [V];
        boolean [] visited =new boolean [V];

        PriorityQueue<Node1> pq = new PriorityQueue<>();

        Arrays.fill (dist, Integer.MAX_VALUE); // initialize distances to max value
        dist[source]=0; // distance to source is 0
        pq.add(new Node1(source, 0)); // add source to priority queue
        System.out.println("Step by step distance from source vertex to all other vertices:");

        while (!pq.isEmpty()){
            Node1 current = pq.poll();
             int u = current.vertex;

             if (visited[u]){
                continue;
             }
             System.out.println("Visiting vertex: " + u + ", distance: " + current.distance);

             //Relaxation step

             for (int v = 0; v < V; v++) {

                if (graph[u][v]!=0 && !visited[v]){
                    int newDist = dist[u]+ graph[u][v];
                    if (newDist<dist[v]){
                        dist[v]= newDist;
                        pq.add(new Node1(v, newDist));
                        System.out.println("Updating distance of vertex " + v + " to " + newDist);
                    }
                }
                
             }

        }

        System.out.println("Final distances from source vertex " + source + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + dist[i]);
        }

    }

    public static void main(String[] args) {
        int graph[][]={
                {0, 7, 9, 0, 0, 14},
                {7, 0, 10, 15, 0, 0},
                {9, 10, 0, 11, 0, 2},
                {0, 15, 11, 0, 6, 0},
                {0, 0, 0, 6, 0, 9},
                {14, 0, 2, 0, 9, 0}
        };

        int source = 0; // starting vertex
         dijkstra(graph, source);
    }
    
}
