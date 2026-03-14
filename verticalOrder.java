import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class verticalOrder {

    static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> colQ = new LinkedList<>();
        nodeQ.offer(root); colQ.offer(0);
        while (!nodeQ.isEmpty()) {
            TreeNode node = nodeQ.poll();
            int col = colQ.poll();
            map.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);
            if (node.left != null)  { nodeQ.offer(node.left);  colQ.offer(col - 1); }
            if (node.right != null) { nodeQ.offer(node.right); colQ.offer(col + 1); }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<List<Integer>> verticalOrderResult = verticalOrderTraversal(root);
        // Printing the Vertical Order Traversal result
        for (List<Integer> column : verticalOrderResult) {
            for (int val : column) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}