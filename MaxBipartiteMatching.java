// time complexity O(V^2E), where V is the number of vertices and E is the number of edges

public class MaxBipartiteMatching {

    static int V;

    static boolean dfs(int[][] rGraph, int u, int t, boolean[] visited, int[] parent) {
        visited[u] = true;
        if (u == t) return true;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && rGraph[u][v] > 0) {
                parent[v] = u;
                if (dfs(rGraph, v, t, visited, parent))
                    return true;
            }
        }
        return false;
    }

    static int fordFulkerson(int[][] graph, int s, int t, int U, int V_right) {
        int[][] rGraph = new int[V][V];
        for (int u = 0; u < V; u++)
            for (int v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int[] parent = new int[V];
        int maxFlow = 0;

        System.out.println("Matched Pairs:");
        while (dfs(rGraph, s, t, new boolean[V], parent)) {
            int pathFlow = Integer.MAX_VALUE;

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;

            // Find the U -> V match in this path
            for (int v = 0; v < V_right; v++) {
                int vNode = U + v;
                if (parent[t] == vNode && parent[vNode] < U) {
                    System.out.println("U" + parent[vNode] + " -> V" + v);
                }
            }
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        int U = 3;
        int V_right = 3;
        int totalVertices = U + V_right + 2;
        V = totalVertices;

        int s = totalVertices - 2;
        int t = totalVertices - 1;

        int[][] graph = new int[V][V];

        // Source to U set
        for (int u = 0; u < U; u++) {
            graph[s][u] = 1;
        }

        // V set to Sink
        for (int v = 0; v < V_right; v++) {
            graph[U + v][t] = 1;
        }

        // U to V edges (bipartite links)
        graph[0][3] = 1;
        graph[0][4] = 1;
        graph[1][4] = 1;
        graph[2][5] = 1;

        int maxMatching = fordFulkerson(graph, s, t, U, V_right);
        System.out.println("Maximum Bipartite Matching is " + maxMatching);
    }
}
