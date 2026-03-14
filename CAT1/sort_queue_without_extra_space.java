import java.util.*;

class sort_queue_without_extra_space{

    static void sortQueue(Queue<Integer> q) {
        int n = q.size();

        for (int i = 0; i < n; i++) {

            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            // Find minimum in unsorted part
            for (int j = 0; j < n; j++) {
                int x = q.poll();
                if (j < n - i && x < min) {
                    min = x;
                    minIndex = j;
                }
                q.add(x);
            }

            // Remove min element
            for (int j = 0; j < n; j++) {
                int x = q.poll();
                if (j != minIndex)
                    q.add(x);
            }

            // Place min at rear
            q.add(min);
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(30); q.add(11); q.add(15); q.add(4);

        sortQueue(q);
        System.out.println(q);
    }
}
