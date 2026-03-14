import java.util.*;

public class DaryHeap {
    private int[] heap;
    private int size, d;

    DaryHeap(int capacity, int d) {
        this.d = d;
        heap = new int[capacity];
        size = 0;
    }

    private int parent(int i)        { return (i - 1) / d; }
    private int child(int i, int k)  { return d * i + k; }

    void insert(int val) {
        heap[size++] = val;
        int i = size - 1;
        while (i > 0 && heap[i] < heap[parent(i)]) {
            int tmp = heap[i]; heap[i] = heap[parent(i)]; heap[parent(i)] = tmp;
            i = parent(i);
        }
    }

    int extractMin() {
        int min = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return min;
    }

    private void heapifyDown(int i) {
        int smallest = i;
        for (int k = 1; k <= d; k++) {
            int c = child(i, k);
            if (c < size && heap[c] < heap[smallest]) smallest = c;
        }
        if (smallest != i) {
            int tmp = heap[i]; heap[i] = heap[smallest]; heap[smallest] = tmp;
            heapifyDown(smallest);
        }
    }

    int findMin() { return heap[0]; }

    public static void main(String[] args) {
        DaryHeap dh = new DaryHeap(10, 3); // 3-ary heap
        dh.insert(10); dh.insert(3); dh.insert(7); dh.insert(1); dh.insert(5);
        System.out.println("Min: " + dh.findMin());
        System.out.println("Extract: " + dh.extractMin());
        System.out.println("Extract: " + dh.extractMin());
        System.out.println("Min: " + dh.findMin());
    }
}