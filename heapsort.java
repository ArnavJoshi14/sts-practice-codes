import java.util.*;

public class heapsort {

    // min heap
    static int[] heapSort(int[] a) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : a) pq.offer(x);
        int[] sorted = new int[a.length];
        for (int i = 0; i < sorted.length; i++) sorted[i] = pq.poll();
        return sorted;
    }

    public static void main(String[] args) {
        int[] a = {35, 17, 10, 90, 24, -3, -8};
        System.out.println("Original: " + Arrays.toString(a));
        System.out.println("Sorted:   " + Arrays.toString(heapSort(a)));
    }
}

/*
max heap:

import java.util.*;

public class Main {

    static int[] heapSort(int[] a) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : a) pq.offer(x);
        int[] sorted = new int[a.length];
        for (int i = 0; i < sorted.length; i++) sorted[i] = pq.poll();
        return sorted;
    }

    public static void main(String[] args) {
        int[] a = {35, 17, 10, 90, 24, -3, -8};
        System.out.println("Original: " + Arrays.toString(a));
        System.out.println("Sorted:   " + Arrays.toString(heapSort(a)));
    }
}
*/