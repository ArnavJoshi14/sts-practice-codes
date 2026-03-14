# BSTS302P – All Topic Codes (Extracted from PPTs)

---

## Topic 1: Recover the BST
**Date: 2026-01-19**

### Approach 1: Inorder Traversal & Two-Pointer

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class Main {
    TreeNode firstIncorrectNode = null;
    TreeNode secondIncorrectNode = null;
    TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        // Perform inorder traversal
        inorder(root);
        // Swap the values of the two incorrectly placed nodes
        int temp = firstIncorrectNode.val;
        firstIncorrectNode.val = secondIncorrectNode.val;
        secondIncorrectNode.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        // Check for incorrectly placed nodes
        if (firstIncorrectNode == null && prevNode.val >= node.val) {
            firstIncorrectNode = prevNode;
        }
        if (firstIncorrectNode != null && prevNode.val >= node.val) {
            secondIncorrectNode = node;
        }
        prevNode = node;
        inorder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        Main solution = new Main();
        solution.recoverTree(root);
        // Print the corrected BST
        System.out.println("Inorder Traversal of Recovered BST:");
        printInorder(root);
    }

    private static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
```

---

### Approach 2: Morris Traversal (Constant Space)

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class Main {
    TreeNode firstIncorrectNode = null;
    TreeNode secondIncorrectNode = null;
    TreeNode prevNode = null;

    public void recoverTree(TreeNode root) {
        TreeNode current = root;
        TreeNode temp;

        while (current != null) {
            if (current.left == null) {
                // Process current node
                if (prevNode != null && prevNode.val >= current.val) {
                    if (firstIncorrectNode == null) {
                        firstIncorrectNode = prevNode;
                    }
                    secondIncorrectNode = current;
                }
                prevNode = current;
                current = current.right;
            } else {
                // Find the inorder predecessor
                temp = current.left;
                while (temp.right != null && temp.right != current) {
                    temp = temp.right;
                }
                if (temp.right == null) {
                    // Set the right pointer to enable returning to the current node
                    temp.right = current;
                    current = current.left;
                } else {
                    // Restore the right pointer and process current node
                    temp.right = null;
                    if (prevNode != null && prevNode.val >= current.val) {
                        if (firstIncorrectNode == null) {
                            firstIncorrectNode = prevNode;
                        }
                        secondIncorrectNode = current;
                    }
                    prevNode = current;
                    current = current.right;
                }
            }
        }
        // Swap the values of the two incorrectly placed nodes
        int tempVal = firstIncorrectNode.val;
        firstIncorrectNode.val = secondIncorrectNode.val;
        secondIncorrectNode.val = tempVal;
    }

    public static void main(String[] args) {
        // Create a sample binary search tree with 5 numbers (incorrect order)
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        Main solution = new Main();
        solution.recoverTree(root);
        // Print the inorder traversal of the recovered BST
        System.out.println("Inorder Traversal of Recovered BST:");
        printInorder(root);
    }

    // Helper function to print the inorder traversal of a tree
    private static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
```

---

### Approach 3 (Alternative)

```java
import java.util.*;

class Node {
    int data;
    Node left, right;
    Node(int d) {
        data = d;
        left = right = null;
    }
}

public class Main {
    Node first, middle, last, prev;

    void correctBST(Node root) {
        // Initialize variables to track the nodes to be swapped
        first = middle = last = prev = null;
        // Correct the BST
        correctBSTUtil(root);
        // If last is null, it means only two nodes were swapped
        if (last == null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        } else {
            // More than two nodes are swapped
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        }
    }

    void correctBSTUtil(Node root) {
        if (root != null) {
            correctBSTUtil(root.left);
            if (prev != null && root.data < prev.data) {
                if (first == null) {
                    first = prev;
                    middle = root;
                } else {
                    last = root;
                }
            }
            prev = root;
            correctBSTUtil(root.right);
        }
    }

    void printInorder(Node node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    public static void main(String[] args) {
        Main tree = new Main();
        Node root = new Node(6);
        root.left = new Node(10);
        root.right = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.right = new Node(12);
        root.right.left = new Node(7);
        System.out.println("Inorder traversal before correction:");
        tree.printInorder(root);
        System.out.println();
        tree.correctBST(root);
        System.out.println("Inorder traversal after correction:");
        tree.printInorder(root);
    }
}
```

---

### Accenture Problem – Recover BST (Morris Traversal, O(1) space)

```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { val = v; }
}

public class RecoverBST {
    TreeNode first = null, second = null, prev = null;

    public void recoverTree(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                detect(curr);
                curr = curr.right;
            } else {
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    pre.right = null;
                    detect(curr);
                    curr = curr.right;
                }
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void detect(TreeNode curr) {
        if (prev != null && prev.val > curr.val) {
            if (first == null) first = prev;
            second = curr;
        }
        prev = curr;
    }
}
```

---

## Topic 2: Views of Tree
**Date: 2026-01-20**

### Horizontal View

```java
import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;
    public TreeNode(char val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Main {
    // Function to obtain the Horizontal View of a binary tree
    public static List<Character> horizontalView(TreeNode root) {
        List<Character> horizontalView = new ArrayList<>();
        if (root == null) {
            return horizontalView;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                horizontalView.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return horizontalView;
    }

    public static void main(String[] args) {
        // Sample binary tree input
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.left.right = new TreeNode('E');
        root.right.left = new TreeNode('F');
        root.right.right = new TreeNode('G');

        List<Character> horizontalViewResult = horizontalView(root);
        // Printing the Horizontal View
        System.out.print("Horizontal View: ");
        for (char node : horizontalViewResult) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
```

---

### Vertical View

```java
import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;
    public TreeNode(char val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Main {
    // Function to obtain the Vertical View of a binary tree
    public static List<Character> verticalView(TreeNode root) {
        List<Character> verticalView = new ArrayList<>();
        if (root == null) {
            return verticalView;
        }
        Map<Integer, List<Character>> verticalMap = new TreeMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> hdQueue = new LinkedList<>();
        nodeQueue.offer(root);
        hdQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int hd = hdQueue.poll();
            verticalMap.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                nodeQueue.offer(node.left);
                hdQueue.offer(hd - 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                hdQueue.offer(hd + 1);
            }
        }
        for (List<Character> values : verticalMap.values()) {
            verticalView.addAll(values);
        }
        return verticalView;
    }

    public static void main(String[] args) {
        // Sample binary tree input
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.right.left = new TreeNode('E');
        root.right.right = new TreeNode('F');

        List<Character> verticalViewResult = verticalView(root);
        // Printing the Vertical View
        System.out.print("Vertical View: ");
        for (char node : verticalViewResult) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
```

---

### Left View

```java
import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;
    public TreeNode(char val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Main {
    // Function to obtain the Left View of a binary tree
    public static List<Character> leftView(TreeNode root) {
        List<Character> leftView = new ArrayList<>();
        if (root == null) {
            return leftView;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = nodeQueue.poll();
                // Add the leftmost node at each level to the leftView list
                if (i == 0) {
                    leftView.add(node.val);
                }
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                }
            }
        }
        return leftView;
    }

    public static void main(String[] args) {
        // Sample binary tree input
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.left.right = new TreeNode('E');
        root.right.left = new TreeNode('F');
        root.right.right = new TreeNode('G');

        List<Character> leftViewResult = leftView(root);
        // Printing the Left View
        System.out.print("Left View: ");
        for (char node : leftViewResult) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
```

---

### Right View

```java
import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;
    public TreeNode(char val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Main {
    // Function to obtain the Right View of a binary tree
    public static List<Character> rightView(TreeNode root) {
        List<Character> rightView = new ArrayList<>();
        if (root == null) {
            return rightView;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                // Add the rightmost node at each level to the result
                if (i == levelSize - 1) {
                    rightView.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return rightView;
    }

    public static void main(String[] args) {
        // Sample binary tree input
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.left.right = new TreeNode('E');
        root.right.left = new TreeNode('F');
        root.right.right = new TreeNode('G');

        List<Character> rightViewResult = rightView(root);
        // Printing the Right View
        System.out.print("Right View: ");
        for (char node : rightViewResult) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
```

---

### Top View

```java
import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;
    public TreeNode(char val) {
        this.val = val;
        left = null;
        right = null;
    }
}

class Pair {
    TreeNode node;
    int hd;
    public Pair(TreeNode node, int hd) {
        this.node = node;
        this.hd = hd;
    }
}

public class Main {
    // Function to obtain the Top View of a binary tree
    public static List<Character> topView(TreeNode root) {
        List<Character> topView = new ArrayList<>();
        if (root == null) {
            return topView;
        }
        Map<Integer, Character> verticalMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int hd = pair.hd;
            if (!verticalMap.containsKey(hd)) {
                verticalMap.put(hd, node.val);
            }
            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }
        for (char nodeVal : verticalMap.values()) {
            topView.add(nodeVal);
        }
        return topView;
    }

    public static void main(String[] args) {
        // Sample binary tree input
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.right.left = new TreeNode('E');
        root.right.right = new TreeNode('F');

        List<Character> topViewResult = topView(root);
        // Printing the Top View
        System.out.print("Top View: ");
        for (char node : topViewResult) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
```

---

### Bottom View

```java
import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;
    public TreeNode(char val) {
        this.val = val;
        left = null;
        right = null;
    }
}

class NodeInfo {
    TreeNode node;
    int hd;
    public NodeInfo(TreeNode node, int hd) {
        this.node = node;
        this.hd = hd;
    }
}

public class Main {
    // Function to obtain the Bottom View of a binary tree
    public static List<Character> bottomView(TreeNode root) {
        List<Character> bottomView = new ArrayList<>();
        if (root == null) {
            return bottomView;
        }
        Map<Integer, Character> bottomMap = new TreeMap<>();
        Queue<NodeInfo> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new NodeInfo(root, 0));
        while (!nodeQueue.isEmpty()) {
            NodeInfo info = nodeQueue.poll();
            // (every node at same hd overwrites, so last one remains = bottom view)
            bottomMap.put(info.hd, info.node.val);
            if (info.node.left != null)
                nodeQueue.offer(new NodeInfo(info.node.left, info.hd - 1));
            if (info.node.right != null)
                nodeQueue.offer(new NodeInfo(info.node.right, info.hd + 1));
        }
        bottomView.addAll(bottomMap.values());
        return bottomView;
    }
}
```

---

### Accenture Problem – Left View (with int nodes)

```java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { val = v; }
}

public class LeftViewTree {
    public static List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (i == 0) result.add(curr.val);
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return result;
    }
}
```

---

### Infosys Problem – Top View (with int nodes)

```java
import java.util.*;

class Pair {
    TreeNode node;
    int hd;
    Pair(TreeNode n, int h) { node = n; hd = h; }
}

public class TopViewTree {
    public static List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            if (!map.containsKey(curr.hd))
                map.put(curr.hd, curr.node.val);
            if (curr.node.left != null)
                q.add(new Pair(curr.node.left, curr.hd - 1));
            if (curr.node.right != null)
                q.add(new Pair(curr.node.right, curr.hd + 1));
        }
        for (int val : map.values()) result.add(val);
        return result;
    }
}
```

---

## Topic 3: Vertical Order Traversal
**Date: 2026-01-22**

```java
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Main {
    // Function to perform Vertical Order Traversal of a binary tree
    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Integer>> verticalMap = new TreeMap<>();
        Queue<SimpleEntry<TreeNode, Integer>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new SimpleEntry<>(root, 0));
        while (!nodeQueue.isEmpty()) {
            SimpleEntry<TreeNode, Integer> entry = nodeQueue.poll();
            TreeNode node = entry.getKey();
            int col = entry.getValue();
            verticalMap.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                nodeQueue.offer(new SimpleEntry<>(node.left, col - 1));
            }
            if (node.right != null) {
                nodeQueue.offer(new SimpleEntry<>(node.right, col + 1));
            }
        }
        for (List<Integer> values : verticalMap.values()) {
            result.add(values);
        }
        return result;
    }

    public static void main(String[] args) {
        // Sample binary tree input
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
```

---

## Topic 4: Boundary Traversal
**Date: 2026-02-02**

### Approach 1 (Full Boundary – Left, Leaves, Right)

```java
class TreeNode {
    int val;
    TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Main {
    private static void printLeftBoundary(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        System.out.print(root.val + " ");
        if (root.left != null) {
            printLeftBoundary(root.left);
        } else {
            printLeftBoundary(root.right);
        }
    }

    private static void printLeaves(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            System.out.print(root.val + " ");
            return;
        }
        printLeaves(root.left);
        printLeaves(root.right);
    }

    private static void printRightBoundary(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.right != null) {
            printRightBoundary(root.right);
        } else {
            printRightBoundary(root.left);
        }
        System.out.print(root.val + " ");
    }

    public static void boundaryTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printLeftBoundary(root.left);
        printLeaves(root.left);
        printLeaves(root.right);
        printRightBoundary(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);
        boundaryTraversal(root);
    }
}
```

---

### Approach 2 (Leaves Only Variant)

```java
class TreeNode {
    int val;
    TreeNode left, right;
    public TreeNode(int value) {
        val = value;
        left = right = null;
    }
}

public class BinaryTreeBoundaryTraversal {
    private static void printLeaves(TreeNode root) {
        if (root != null) {
            printLeaves(root.left);
            if (root.left == null && root.right == null)
                System.out.print(root.val + " ");
            printLeaves(root.right);
        }
    }

    public static void boundaryTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printLeaves(root.left);
            printLeaves(root.right);
        }
    }

    public static void main(String[] args) {
        // Constructing the example tree
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);
        // Calling the boundaryTraversal function
        boundaryTraversal(root);
    }
}
```

---

## Topic 5: BFS and DFS
**Date: 2026-02-03**

### BFS (Breadth-First Search)

```java
import java.util.*;

public class Graph {
    private int V;
    private LinkedList<Integer> adj[];

    // Create a graph
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Add edges to the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // BFS algorithm
    void BFS(int s) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList();
        visited[s] = true;
        queue.add(s);
        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println("Following is Breadth First Traversal " +
                           "(starting from vertex 2)");
        g.BFS(2);
    }
}
```

---

### DFS (Depth-First Search)

```java
import java.util.*;

class Graph {
    private LinkedList<Integer> adjLists[];
    private boolean visited[];

    // Graph creation
    Graph(int vertices) {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<Integer>();
    }

    // Add edges
    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    void DFS(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        Iterator<Integer> ite = adjLists[vertex].listIterator();
        while (ite.hasNext()) {
            int adj = ite.next();
            if (!visited[adj])
                DFS(adj);
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        System.out.println("Following is Depth First Traversal");
        g.DFS(2);
    }
}
```

---

## Topic 6: Dial's Algorithm
**Date: 2026-02-05**

*(The PPT for Dial's Algorithm contains the explanation, pseudocode, and worked examples with tables, but no Java implementation code was included in the slides.)*

---

## Topic 7: Bellman-Ford Algorithm
**Date: 2026-02-07**

### Bellman-Ford – Shortest Path

```java
import java.util.*;

public class Main {
    static class Edge {
        int src, dest, weight;
        Edge(int s, int d, int w) {
            src = s; dest = d; weight = w;
        }
    }

    static class Graph {
        int V, E;
        Edge[] edges;

        Graph(int v, int e) {
            V = v;
            E = e;
            edges = new Edge[E];
        }

        void bellmanFord(int source) {
            int[] dist = new int[V];
            for (int i = 0; i < V; i++)
                dist[i] = Integer.MAX_VALUE;
            dist[source] = 0;

            for (int i = 1; i < V; i++) {
                for (int j = 0; j < E; j++) {
                    int u = edges[j].src;
                    int v = edges[j].dest;
                    int w = edges[j].weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
                        dist[v] = dist[u] + w;
                }
            }

            for (int j = 0; j < E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int w = edges[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    System.out.println("Negative weight cycle detected");
                    return;
                }
            }
            printDistances(dist);
        }

        void printDistances(int[] dist) {
            System.out.println("Vertex\tDistance from Source");
            for (int i = 0; i < V; i++)
                System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        Graph graph = new Graph(V, E);
        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            graph.edges[i] = new Edge(src, dest, weight);
        }
        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();
        graph.bellmanFord(source);
        sc.close();
    }
}
```

---

### Amazon Problem – Negative Cycle Detection

```java
import java.util.*;

class Main {
    static class Edge {
        int u, v;
        long w;
        Edge(int u, int v, long w) {
            this.u = u; this.v = v; this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextLong());
        }

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }

        boolean negativeCycle = false;
        for (Edge e : edges) {
            if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                negativeCycle = true;
                break;
            }
        }
        System.out.println(negativeCycle ? "YES" : "NO");
        sc.close();
    }
}
```

---

## Topic 8: Topological Sort (Kahn's Algorithm)
**Date: 2026-02-09**

### Kahn's Algorithm

```java
import java.util.*;

class Graph {
    private int vertices;
    private List<List<Integer>> adj;

    public Graph(int vertices) {
        this.vertices = vertices;
        adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++)
            adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public void topologicalSort() {
        int[] indegree = new int[vertices];
        for (int i = 0; i < vertices; i++)
            for (int v : adj.get(i))
                indegree[v]++;

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < vertices; i++)
            if (indegree[i] == 0) q.offer(i);

        List<Integer> order = new ArrayList<>();
        int count = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : adj.get(u)) {
                if (--indegree[v] == 0) q.offer(v);
            }
            count++;
        }

        if (count != vertices)
            System.out.println("Cycle detected. Topological sort not possible.");
        else
            System.out.println(order);
    }
}

// Main class
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter number of vertices: ");
    int V = sc.nextInt();
    System.out.print("Enter number of edges: ");
    int E = sc.nextInt();
    Graph g = new Graph(V);
    System.out.println("Enter edges (source destination):");
    for (int i = 0; i < E; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        g.addEdge(u, v);
    }
    g.topologicalSort();
    sc.close();
}
```

---

## Topic 9: Heap Sort
**Date: 2026-02-10**

```java
class HeapSort {
    static void heapify(int a[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && a[left] > a[largest])
            largest = left;
        if (right < n && a[right] > a[largest])
            largest = right;
        if (largest != i) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            heapify(a, n, largest);
        }
    }

    static void heapSort(int a[], int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);
        for (int i = n - 1; i >= 0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapify(a, i, 0);
        }
    }

    static void printArr(int a[], int n) {
        for (int i = 0; i < n; ++i)
            System.out.print(a[i] + " ");
    }

    public static void main(String args[]) {
        int a[] = {35, 17, 10, 90, 24, -3, -8};
        int n = a.length;
        System.out.println("Original Array:");
        printArr(a, n);
        heapSort(a, n);
        System.out.println("\nSorted Array:");
        printArr(a, n);
    }
}
```

---

## Topic 10: Binomial Heap
**Date: 2026-02-12**

```java
import java.io.*;

class BinomialHeapNode {
    int key, degree;
    BinomialHeapNode parent;
    BinomialHeapNode sibling;
    BinomialHeapNode child;

    public BinomialHeapNode(int k) {
        key = k; degree = 0;
        parent = null; sibling = null; child = null;
    }

    public BinomialHeapNode reverse(BinomialHeapNode sibl) {
        BinomialHeapNode ret;
        if (sibling != null) ret = sibling.reverse(this);
        else ret = this;
        sibling = sibl;
        return ret;
    }

    public BinomialHeapNode findMinNode() {
        BinomialHeapNode x = this, y = this;
        int min = x.key;
        while (x != null) {
            if (x.key < min) { y = x; min = x.key; }
            x = x.sibling;
        }
        return y;
    }

    public BinomialHeapNode findANodeWithKey(int value) {
        BinomialHeapNode temp = this, node = null;
        while (temp != null) {
            if (temp.key == value) { node = temp; break; }
            if (temp.child == null) temp = temp.sibling;
            else {
                node = temp.child.findANodeWithKey(value);
                if (node == null) temp = temp.sibling;
                else break;
            }
        }
        return node;
    }

    public int getSize() {
        return (1 + ((child == null) ? 0 : child.getSize())
                  + ((sibling == null) ? 0 : sibling.getSize()));
    }
}

class BinomialHeap {
    private BinomialHeapNode Nodes;
    private int size;

    public BinomialHeap() { Nodes = null; size = 0; }

    public boolean isEmpty() { return Nodes == null; }
    public int getSize() { return size; }
    public void makeEmpty() { Nodes = null; size = 0; }

    public void insert(int value) {
        if (value > 0) {
            BinomialHeapNode temp = new BinomialHeapNode(value);
            if (Nodes == null) { Nodes = temp; size = 1; }
            else { unionNodes(temp); size++; }
        }
    }

    private void merge(BinomialHeapNode binHeap) {
        BinomialHeapNode temp1 = Nodes, temp2 = binHeap;
        while ((temp1 != null) && (temp2 != null)) {
            if (temp1.degree == temp2.degree) {
                BinomialHeapNode tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            } else {
                if (temp1.degree < temp2.degree) {
                    if ((temp1.sibling == null) ||
                        (temp1.sibling.degree > temp2.degree)) {
                        BinomialHeapNode tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    } else { temp1 = temp1.sibling; }
                } else {
                    BinomialHeapNode tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;
                    if (tmp == Nodes) { Nodes = temp1; }
                }
            }
        }
        if (temp1 == null) {
            temp1 = Nodes;
            while (temp1.sibling != null) { temp1 = temp1.sibling; }
            temp1.sibling = temp2;
        }
    }

    private void unionNodes(BinomialHeapNode binHeap) {
        merge(binHeap);
        BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.sibling;
        while (nextTemp != null) {
            if ((temp.degree != nextTemp.degree) ||
                ((nextTemp.sibling != null) &&
                 (nextTemp.sibling.degree == temp.degree))) {
                prevTemp = temp;
                temp = nextTemp;
            } else {
                if (temp.key <= nextTemp.key) {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                } else {
                    if (prevTemp == null) { Nodes = nextTemp; }
                    else { prevTemp.sibling = nextTemp; }
                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }

    public int findMinimum() { return Nodes.findMinNode().key; }

    public void delete(int value) {
        if ((Nodes != null) && (Nodes.findANodeWithKey(value) != null)) {
            decreaseKeyValue(value, findMinimum() - 1);
            extractMin();
        }
    }

    public void decreaseKeyValue(int old_value, int new_value) {
        BinomialHeapNode temp = Nodes.findANodeWithKey(old_value);
        if (temp == null) return;
        temp.key = new_value;
        BinomialHeapNode tempParent = temp.parent;
        while ((tempParent != null) && (temp.key < tempParent.key)) {
            int z = temp.key;
            temp.key = tempParent.key;
            tempParent.key = z;
            temp = tempParent;
            tempParent = tempParent.parent;
        }
    }

    public int extractMin() {
        if (Nodes == null) return -1;
        BinomialHeapNode temp = Nodes, prevTemp = null;
        BinomialHeapNode minNode = Nodes.findMinNode();
        while (temp.key != minNode.key) { prevTemp = temp; temp = temp.sibling; }
        if (prevTemp == null) { Nodes = temp.sibling; }
        else { prevTemp.sibling = temp.sibling; }
        temp = temp.child;
        BinomialHeapNode fakeNode = temp;
        while (temp != null) { temp.parent = null; temp = temp.sibling; }
        if ((Nodes == null) && (fakeNode == null)) { size = 0; }
        else {
            if ((Nodes == null) && (fakeNode != null)) {
                Nodes = fakeNode.reverse(null);
                size = Nodes.getSize();
            }
            // else: union handled separately
        }
        return minNode.key;
    }
}
```

---

## Topic 11: K-ary Heap
**Date: 2026-02-16**

### D-ary Heap Implementation (Menu-Driven)

```java
import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;

class DaryHeap {
    private int d;
    private int heapSize;
    private int[] heap;

    public DaryHeap(int capacity, int numChild) {
        heapSize = 0;
        d = numChild;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public boolean isEmpty() { return heapSize == 0; }
    public boolean isFull() { return heapSize == heap.length; }
    public void clear() { heapSize = 0; }

    private int parent(int i) { return (i - 1) / d; }
    private int kthChild(int i, int k) { return d * i + k; }

    public void insert(int x) {
        if (isFull()) throw new NoSuchElementException("Overflow Exception");
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }

    public int findMin() {
        if (isEmpty()) throw new NoSuchElementException("Underflow Exception");
        return heap[0];
    }

    public int delete(int ind) {
        if (isEmpty()) throw new NoSuchElementException("Underflow Exception");
        int keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(ind);
        return keyItem;
    }

    private void heapifyUp(int childInd) {
        int tmp = heap[childInd];
        while (childInd > 0 && tmp < heap[parent(childInd)]) {
            heap[childInd] = heap[parent(childInd)];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }

    private void heapifyDown(int ind) {
        int child;
        int tmp = heap[ind];
        while (kthChild(ind, 1) < heapSize) {
            child = minChild(ind);
            if (heap[child] < tmp) heap[ind] = heap[child];
            else break;
            ind = child;
        }
        heap[ind] = tmp;
    }

    private int minChild(int ind) {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize)) {
            if (heap[pos] < heap[bestChild]) bestChild = pos;
            pos = kthChild(ind, k++);
        }
        return bestChild;
    }

    public void printHeap() {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }
}

public class DaryHeapTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter size and D of D-ary Heap");
        DaryHeap dh = new DaryHeap(scan.nextInt(), scan.nextInt());
        char ch;
        do {
            System.out.println("\nD-ary Heap Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete");
            System.out.println("3. check full");
            System.out.println("4. check empty");
            System.out.println("5. clear");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter integer element to insert");
                        dh.insert(scan.nextInt());
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                    break;
                case 2:
                    try {
                        System.out.println("Enter delete position");
                        dh.delete(scan.nextInt() - 1);
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                    break;
                case 3: System.out.println("Full status = " + dh.isFull()); break;
                case 4: System.out.println("Empty status = " + dh.isEmpty()); break;
                case 5: dh.clear(); System.out.println("Heap Cleared\n"); break;
                default: System.out.println("Wrong Entry \n "); break;
            }
            /** Display heap **/
            dh.printHeap();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
```

---

### Google Problem – K-ary Min-Heap (Optimized Task Scheduler)

```java
import java.util.*;

public class Main {
    static class KAryMinHeap {
        int k;
        ArrayList<Long> heap;

        KAryMinHeap(int k) {
            this.k = k;
            this.heap = new ArrayList<>();
        }

        void insert(long x) {
            heap.add(x);
            siftUp(heap.size() - 1);
        }

        Long peek() {
            if (heap.isEmpty()) return null;
            return heap.get(0);
        }

        Long extractMin() {
            if (heap.isEmpty()) return null;
            long root = heap.get(0);
            long last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                siftDown(0);
            }
            return root;
        }

        void siftUp(int i) {
            while (i > 0) {
                int parent = (i - 1) / k;
                if (heap.get(parent) <= heap.get(i)) break;
                long tmp = heap.get(parent);
                heap.set(parent, heap.get(i));
                heap.set(i, tmp);
                i = parent;
            }
        }

        void siftDown(int i) {
            while (true) {
                int best = i;
                for (int j = 1; j <= k; j++) {
                    int child = k * i + j;
                    if (child < heap.size() && heap.get(child) < heap.get(best)) {
                        best = child;
                    }
                }
                if (best == i) break;
                long tmp = heap.get(i);
                heap.set(i, heap.get(best));
                heap.set(best, tmp);
                i = best;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int q = sc.nextInt();
        KAryMinHeap h = new KAryMinHeap(k);
        while (q-- > 0) {
            int op = sc.nextInt();
            if (op == 1) {
                long x = sc.nextLong();
                h.insert(x);
            } else if (op == 2) {
                Long res = h.extractMin();
                System.out.println(res == null ? "EMPTY" : res);
            } else if (op == 3) {
                Long res = h.peek();
                System.out.println(res == null ? "EMPTY" : res);
            }
        }
        sc.close();
    }
}
```

---

### Meta Problem – Partial K-ary Heap Sort

```java
import java.util.*;

public class Main {
    static class KAryMinHeap {
        int k;
        ArrayList<Long> heap;

        KAryMinHeap(int k) { this.k = k; this.heap = new ArrayList<>(); }

        void insert(long x) { heap.add(x); siftUp(heap.size() - 1); }

        Long extractMin() {
            if (heap.isEmpty()) return null;
            long root = heap.get(0);
            long last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) { heap.set(0, last); siftDown(0); }
            return root;
        }

        void siftUp(int i) {
            while (i > 0) {
                int parent = (i - 1) / k;
                if (heap.get(parent) <= heap.get(i)) break;
                long tmp = heap.get(parent);
                heap.set(parent, heap.get(i));
                heap.set(i, tmp);
                i = parent;
            }
        }

        void siftDown(int i) {
            while (true) {
                int best = i;
                for (int j = 1; j <= k; j++) {
                    int child = k * i + j;
                    if (child < heap.size() && heap.get(child) < heap.get(best)) {
                        best = child;
                    }
                }
                if (best == i) break;
                long tmp = heap.get(i);
                heap.set(i, heap.get(best));
                heap.set(best, tmp);
                i = best;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        KAryMinHeap h = new KAryMinHeap(k);
        for (int i = 0; i < n; i++) h.insert(sc.nextLong());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            Long x = h.extractMin();
            if (x == null) break;
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString().trim());
        sc.close();
    }
}
```

---

## Topic 12: Winner Tree
**Date: 2026-02-17**

### Winner Tree – Approach 1

```java
public class WinnerTree {
    private int[] tree;
    private int[] players;
    private int numPlayers;

    public WinnerTree(int[] players) {
        this.players = players;
        this.numPlayers = players.length;
        int treeSize = 2 * numPlayers - 1;
        this.tree = new int[treeSize];
        buildWinnerTree();
    }

    private void buildWinnerTree() {
        for (int i = numPlayers - 1; i < tree.length; i++) {
            tree[i] = i - (numPlayers - 1);
        }
        for (int i = numPlayers - 2; i >= 0; i--) {
            tree[i] = players[tree[2 * i + 1]] < players[tree[2 * i + 2]] ?
                      tree[2 * i + 2] : tree[2 * i + 1];
        }
    }

    public int getWinner() { return players[tree[0]]; }

    public static void main(String[] args) {
        int[] players = {3, 7, 1, 9, 4, 2, 8, 5};
        WinnerTree winnerTree = new WinnerTree(players);
        System.out.println("The winner is: " + winnerTree.getWinner());
    }
}
```

---

### Winner Tree – Approach 2

```java
import java.util.Arrays;

public class Main {
    public static class WinnerTree {
        private int[] tree;
        private int[] players;

        public WinnerTree(int[] players) {
            this.players = players;
            int n = players.length;
            int treeSize = 1;
            while (treeSize < n) { treeSize *= 2; }
            tree = new int[2 * treeSize - 1];
            Arrays.fill(tree, -1);
            for (int i = 0; i < n; i++) {
                tree[treeSize - 1 + i] = i;
            }
            build(0, 0, treeSize - 1);
        }

        private void build(int node, int left, int right) {
            if (left == right) return;
            int mid = (left + right) / 2;
            build(2 * node + 1, left, mid);
            build(2 * node + 2, mid + 1, right);
            tree[node] = players[tree[2 * node + 1]] <= players[tree[2 * node + 2]] ?
                         tree[2 * node + 1] : tree[2 * node + 2];
        }

        public int getWinner() { return tree[0]; }
    }

    public static void main(String[] args) {
        int[] players = {3, 7, 1, 9, 4, 2, 8, 5};
        WinnerTree winnerTree = new WinnerTree(players);
        int winnerIndex = winnerTree.getWinner();
        System.out.println("The player with the highest score is at index: " + winnerIndex);
        System.out.println("The score of the winning player is: " + players[winnerIndex]);
    }
}
```

---

### Google Problem – Merge k Sorted Lists Using Winner Tree

```java
import java.util.*;

public class Main {
    static class Node {
        int val;
        int listIdx;
        int idxInList;
        Node(int v, int l, int i) { val = v; listIdx = l; idxInList = i; }
    }

    static Node[] buildWinnerTree(Node[] leaves) {
        int n = leaves.length;
        int size = 1;
        while (size < n) size <<= 1;
        Node[] tree = new Node[2 * size];
        for (int i = 0; i < n; i++) tree[size + i] = leaves[i];
        for (int i = n; i < size; i++)
            tree[size + i] = new Node(Integer.MAX_VALUE, -1, -1);
        for (int i = size - 1; i > 0; i--) {
            Node left = tree[2 * i], right = tree[2 * i + 1];
            if (left.val <= right.val) tree[i] = left;
            else tree[i] = right;
        }
        return tree;
    }

    static void updateTree(Node[] tree, int pos, Node newLeaf) {
        int idx = pos + (tree.length / 2);
        tree[idx] = newLeaf;
        for (idx /= 2; idx > 0; idx /= 2) {
            Node left = tree[2 * idx], right = tree[2 * idx + 1];
            tree[idx] = (left.val <= right.val ? left : right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        List<List<Integer>> lists = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            int ni = sc.nextInt();
            List<Integer> cur = new ArrayList<>(ni);
            for (int j = 0; j < ni; j++) cur.add(sc.nextInt());
            lists.add(cur);
        }
        Node[] leaves = new Node[k];
        for (int i = 0; i < k; i++) {
            leaves[i] = new Node(lists.get(i).get(0), i, 0);
        }
        Node[] tree = buildWinnerTree(leaves);
        StringBuilder sb = new StringBuilder();
        while (true) {
            Node minNode = tree[1];
            if (minNode.val == Integer.MAX_VALUE) break;
            sb.append(minNode.val).append(" ");
            int li = minNode.listIdx;
            int ni = minNode.idxInList + 1;
            if (ni < lists.get(li).size()) {
                updateTree(tree, li, new Node(lists.get(li).get(ni), li, ni));
            } else {
                updateTree(tree, li, new Node(Integer.MAX_VALUE, -1, -1));
            }
        }
        System.out.println(sb.toString().trim());
        sc.close();
    }
}
```
