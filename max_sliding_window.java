import java.util.*;

public class max_sliding_window {
    static void maxSlidingWindow(int arr[], int k) {
        Deque<Integer> dq = new LinkedList<>();
        for (int i=0; i<arr.length; i++) {
            // remove indices outside window
            while (!dq.isEmpty() && dq.peekFirst() == i - k) {
                dq.removeFirst();
            }

            // remove smaller elements
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
                dq.removeLast();
            }

            dq.addLast(i);

            // print max for valid windows
            if (i >= k-1) {
                System.out.print(arr[dq.peekFirst()] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        maxSlidingWindow(arr, k);
    }
}
