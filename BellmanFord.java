import java.util.Arrays;
public class BellmanFord {

public static void bellmanFord(int[][] graph, int V, int E, int source) {
    int [] dist = new int [V];
    Arrays.fill(dist, Integer.MAX_VALUE); // initialize distances to max value
    dist [source] = 0; // distance to source is 0
    System.out.println("Step by step distance from source vertex to all other vertices:");

    for (int i = 0; i < V-1; i++) {
        System.out.println("Iteration " + (i+1) + ":");
        for (int j = 0; j < E; j++) {
            int u =graph [j][0];
            int v = graph [j][1];
            int weight = graph [j][2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                System.out.println("Updating distance of vertex " + v + " to " + dist[v]);
            }
            
        }
        
    }

    // Check for negative-weight cycles
    for (int j = 0; j < E; j++) {
        int u = graph[j][0];// from
        int v = graph[j][1];// to
        int weight = graph[j][2];// weight

        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
            System.out.println("Graph contains negative-weight cycle");
            return;
        }
    }

    System.out.println("No negative-weight cycles found");
    System.out.println("Final distances from source vertex " + source + ":");
    for (int i = 0; i < V; i++) {
        System.out.println("Node " + i + " : " + dist[i]);
    }
}


    public static void main(String[] args) {
        int V = 5; // number of vertices
        int E = 8; // number of edges

        // Graph as list of edges: {u, v, weight}
        int[][] graph = {
            {0, 1, -1},
            {0, 2, 4},
            {1, 2, 3},
            {1, 3, 2},
            {1, 4, 2},
            {3, 2, 5},
            {3, 1, 1},
            {4, 3, -3}
        };

        int source = 0;
        bellmanFord(graph, V, E, source);
    }
    
}
