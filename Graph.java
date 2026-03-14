// // BFS, DFS
// import java.util.*;

// public class Graph {
//     private int V;
//     private List<List<Integer>> adj;
//     private boolean[] visited;

//     Graph(int v) {
//         V = v;
//         adj = new ArrayList<>();
//         for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
//     }

//     void addEdge(int src, int dest) { adj.get(src).add(dest); }

//     // BFS - uses a queue, iterative
//     void BFS(int start) {
//         visited = new boolean[V];
//         Queue<Integer> queue = new LinkedList<>();
//         visited[start] = true;
//         queue.offer(start);
//         while (!queue.isEmpty()) {
//             int node = queue.poll();
//             System.out.print(node + " ");
//             for (int neighbor : adj.get(node)) {
//                 if (!visited[neighbor]) {
//                     visited[neighbor] = true;
//                     queue.offer(neighbor);
//                 }
//             }
//         }
//     }

//     // DFS - uses recursion
//     void DFS(int node) {
//         visited[node] = true;
//         System.out.print(node + " ");
//         for (int neighbor : adj.get(node))
//             if (!visited[neighbor]) DFS(neighbor);
//     }

//     public static void main(String[] args) {
//         Graph g = new Graph(4);
//         g.addEdge(0, 1); g.addEdge(0, 2);
//         g.addEdge(1, 2); g.addEdge(2, 0);
//         g.addEdge(2, 3); g.addEdge(3, 3);

//         System.out.println("BFS from vertex 2:");
//         g.BFS(2);

//         System.out.println("\nDFS from vertex 2:");
//         g.visited = new boolean[g.V]; // reset visited for DFS
//         g.DFS(2);
//     }
// }

import java.util.*;

public class Graph {
    int V;
    List<List<Integer>> adj;
    boolean[] visited;
    
    Graph(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i=0; i<v; i++) {
            adj.add(new ArrayList<>());
        }
    }
    
    void addEdge(int src, int dst) {
        adj.get(src).add(dst);
    }
    
    void bfs(int start) {
        visited = new boolean[V];
        Arrays.fill(visited, false);
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(neighbor);
                }
            }
        }
    }
    
    void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int neighbor : adj.get(start)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
    
    void startDFS(int start) {
        visited = new boolean[V];
        dfs(start);
    }
    
    public static void main(String args[]) {
        Graph g = new Graph(4);
        g.addEdge(0, 1); g.addEdge(0, 2);
        g.addEdge(1, 2); g.addEdge(2, 0);
        g.addEdge(2, 3); g.addEdge(3, 3);

        System.out.println("BFS from vertex 2:");
        //g.bfs(2)
        g.startDFS(2);
    }
}