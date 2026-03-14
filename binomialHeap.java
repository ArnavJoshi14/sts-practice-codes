import java.util.*;

public class binomialHeap {
    private PriorityQueue<Integer> pq = new PriorityQueue<>();

    void insert(int val)   { pq.offer(val); }
    int findMin()          { return pq.peek(); }
    int extractMin()       { return pq.poll(); }
    void delete(int val)   { pq.remove(val); }
    int size()             { return pq.size(); }

    public static void main(String[] args) {
        binomialHeap bh = new binomialHeap();
        bh.insert(10); bh.insert(3); bh.insert(7); bh.insert(1); bh.insert(5);
        System.out.println("Min: " + bh.findMin());
        System.out.println("Extract: " + bh.extractMin());
        System.out.println("Extract: " + bh.extractMin());
        System.out.println("Min: " + bh.findMin());
    }
}