import java.util.*;

public class VertexCoverProblem {

    private int V ; // number of vertices
    private LinkedList<Integer>[] adj ; // adjacency list

VertexCoverProblem(int v){
    V=v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; i++) {
        adj[i]= new LinkedList<>();
        
    }
}

    //function to add an edge into the graph
    void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
    }


    void printGraph(){
        System.out.println("Graph:");
        for (int i = 0; i < V; i++) {
            System.out.print(i + ": ");
            for (Integer j : adj[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }


   void printVertexCover() {
boolean[] visited = new boolean[V];
System.out.println("Finding vertex cover...\n");
for (int u = 0; u < V; u++) {
if (!visited[u]) {
for (int v : adj[u]) {
if (!visited[v]) {
// Include both vertices u and v

visited[u] = true;
visited[v] = true;
break;
}
}
}
}
System.out.println("Vertices in the Vertex Cover:");
for (int j = 0; j < V; j++) {
if (visited[j])
System.out.print(j + " ");
}
System.out.println();
}










    public static void main(String[] args) {

        VertexCoverProblem g = new VertexCoverProblem(7);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);

        g.printGraph();
        g.printVertexCover();
        
    
    }
}
