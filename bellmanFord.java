import java.util.*;

public class bellmanFord {

    static int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++)
            for (int[] e : edges)
                if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]])
                    dist[e[1]] = dist[e[0]] + e[2];

        // Check for negative weight cycles
        for (int[] e : edges)
            if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]]) {
                System.out.println("Negative weight cycle detected");
                return null;
            }

        return dist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(), E = sc.nextInt();
        int[][] edges = new int[E][3];
        for (int[] e : edges) { e[0] = sc.nextInt(); e[1] = sc.nextInt(); e[2] = sc.nextInt(); }
        int src = sc.nextInt();
        sc.close();

        int[] dist = bellmanFord(V, edges, src);
        if (dist != null) {
            System.out.println("Vertex\tDistance from Source");
            for (int i = 0; i < V; i++)
                System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }
}

/*
import java.util.*;

public class Main {
    static class Edge {
        int src, dest, weight;
        Edge(int s, int d, int w) {
            src = s; dest = d; weight = w;
        }
    }

    static class Graph {
        int V, E;
        Edge[] edges;

        Graph(int v, int e) {
            V = v;
            E = e;
            edges = new Edge[E];
        }

        void bellmanFord(int source) {
            int[] dist = new int[V];
            for (int i = 0; i < V; i++)
                dist[i] = Integer.MAX_VALUE;
            dist[source] = 0;

            for (int i = 1; i < V; i++) {
                for (int j = 0; j < E; j++) {
                    int u = edges[j].src;
                    int v = edges[j].dest;
                    int w = edges[j].weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
                        dist[v] = dist[u] + w;
                }
            }

            for (int j = 0; j < E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int w = edges[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    System.out.println("Negative weight cycle detected");
                    return;
                }
            }
            printDistances(dist);
        }

        void printDistances(int[] dist) {
            System.out.println("Vertex\tDistance from Source");
            for (int i = 0; i < V; i++)
                System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        Graph graph = new Graph(V, E);
        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            graph.edges[i] = new Edge(src, dest, weight);
        }
        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();
        graph.bellmanFord(source);
        sc.close();
    }
}
*/


/*
import java.util.*;

public class Main {

    static boolean hasNegativeCycle(int V, int[][] edges) {
        int[] dist = new int[V]; // all zeros - no need to set source

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++)
            for (int[] e : edges)
                if (dist[e[0]] + e[2] < dist[e[1]])
                    dist[e[1]] = dist[e[0]] + e[2];

        // If any edge can still be relaxed, negative cycle exists
        for (int[] e : edges)
            if (dist[e[0]] + e[2] < dist[e[1]])
                return true;

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(), E = sc.nextInt();
        int[][] edges = new int[E][3];
        for (int[] e : edges) { e[0] = sc.nextInt(); e[1] = sc.nextInt(); e[2] = sc.nextInt(); }
        sc.close();

        System.out.println(hasNegativeCycle(V, edges) ? "Negative cycle detected" : "No negative cycle");
    }
}
*/