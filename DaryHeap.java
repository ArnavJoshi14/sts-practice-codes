import java.util.*;

public class DaryHeap {
    private PriorityQueue<Integer> pq;

    DaryHeap()              { pq = new PriorityQueue<>(); }          // min heap
    DaryHeap(boolean max)   { pq = new PriorityQueue<>(Collections.reverseOrder()); } // max heap

    void insert(int val)    { pq.offer(val); }
    int findMin()           { return pq.peek(); }
    int extractMin()        { return pq.poll(); }
    int size()              { return pq.size(); }

    public static void main(String[] args) {
        DaryHeap dh = new DaryHeap();
        dh.insert(10); dh.insert(3); dh.insert(7); dh.insert(1); dh.insert(5);
        System.out.println("Min: " + dh.findMin());
        System.out.println("Extract: " + dh.extractMin());
        System.out.println("Extract: " + dh.extractMin());
        System.out.println("Min: " + dh.findMin());
    }
}