import java.util.*;

public class binomialHeap {
    private List<int[]> trees = new ArrayList<>(); // each tree stored as sorted list
    private int size = 0;

    void insert(int val) {
        trees.add(new int[]{val});
        size++;
        merge();
    }

    int extractMin() {
        if (trees.isEmpty()) return -1;
        int minVal = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < trees.size(); i++)
            if (trees.get(i)[0] < minVal) { minVal = trees.get(i)[0]; minIdx = i; }
        trees.remove(minIdx);
        size--;
        return minVal;
    }

    int findMin() {
        return trees.stream().mapToInt(t -> t[0]).min().orElse(-1);
    }

    private void merge() {
        trees.sort(Comparator.comparingInt(t -> t[0]));
    }

    public static void main(String[] args) {
        binomialHeap bh = new binomialHeap();
        bh.insert(10); bh.insert(3); bh.insert(7); bh.insert(1); bh.insert(5);
        System.out.println("Min: " + bh.findMin());
        System.out.println("Extract: " + bh.extractMin());
        System.out.println("Extract: " + bh.extractMin());
        System.out.println("Min: " + bh.findMin());
    }
}