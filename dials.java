import java.util.*;

public class dials {

    static int[] dijkstra(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax edges V-1 times, but only from settled nodes
        boolean[] settled = new boolean[V];
        for (int i = 0; i < V; i++) {
            // Pick the unvisited node with smallest distance (like a manual PQ)
            int u = -1;
            for (int v = 0; v < V; v++)
                if (!settled[v] && (u == -1 || dist[v] < dist[u]))
                    u = v;

            settled[u] = true;

            // Relax all edges from u (same as Bellman-Ford)
            for (int[] e : edges)
                if (e[0] == u && dist[u] != Integer.MAX_VALUE && dist[u] + e[2] < dist[e[1]])
                    dist[e[1]] = dist[u] + e[2];
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

        int[] dist = dijkstra(V, edges, src);
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
    }
}