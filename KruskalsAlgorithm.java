// Greedy Algorithm
// // Time Complexity : O(E log E), adjacent list representation

import java.util.*;
public class KruskalsAlgorithm{

    public static class Edge implements Comparable<Edge>{
        int src, dest, weight;

        public Edge(int src, int dest, int weight){
            this.src= src;
            this.dest=dest;
            this.weight=weight;
        }

        public int compareTo(Edge other){
            return this.weight - other.weight;
        }

    }


    public static class UnionFind{
        int [] parent, rank;

         UnionFind(int n){
            parent = new int[n];
            rank=new int[n];
            for (int i = 0; i < n; i++){
                parent[i]=i; 
            }
            }

            int find (int x){
                if (parent[x]!=x){
                    parent[x]= find(parent[x]); 
                }
                 return parent[x];
            }

            boolean union (int x, int y){
                int rootX= find(x);
                int rootY=find(y);

                if (rootX==rootY){
                    return false; // cycle detected
                }

                if (rank[rootX]<rank[rootY]){
                    parent[rootX]= rootY;
                }
                else if (rank[rootX]> rank[rootY]){
                    parent[rootY]= rootX;
                }
                else{
                    parent[rootY]= rootX;
                    rank[rootX]++;
                }
                return true;
            
         }
    }

    public static void kruskalMST(List<Edge> edges, int V){
        Collections.sort(edges);
        UnionFind uf= new UnionFind(V);

        int mstWeight=0;
        System.out.println("Edge \tWeight");
        for (Edge e: edges){
            if (uf.union(e.src,e.dest)){
                System.out.println(e.src + " - " + e.dest + "\t" + e.weight);
                mstWeight+= e.weight;
            }
        }

        System.out.println("Total weight of MST: " + mstWeight);
    }




    public static void main(String[] args) {
        int V =5; //number of vertices

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0,1,2));
        edges.add(new Edge(0,3,6));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(1,3,8));
        edges.add(new Edge(1,4,5));
        edges.add(new Edge(2,4,7));
        edges.add(new Edge(3,4,9));

        kruskalMST(edges,V);
    }
}