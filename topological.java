import java.util.*;

public class topological {

    static void topologicalSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++)
            for (int v : adj.get(u))
                indegree[v]++;

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++)
            if (indegree[i] == 0) q.offer(i);

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : adj.get(u))
                if (--indegree[v] == 0) q.offer(v);
        }

        if (order.size() != V)
            System.out.println("Cycle detected. Topological sort not possible.");
        else
            System.out.println(order);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(), E = sc.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < E; i++) adj.get(sc.nextInt()).add(sc.nextInt());

        sc.close();
        topologicalSort(V, adj);
    }
}