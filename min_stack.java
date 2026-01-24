import java.util.*;

public class min_stack {
    static class Mystack {
        Stack<Integer> s = new Stack<>();
        Stack<Integer> ms = new Stack<>();

        void push(int n) {
            s.push(n);
            if (ms.isEmpty() || n <= ms.peek()) {
                ms.push(n);
            }
            System.out.println("pushed: " + n);
        }

        void pop() {
            if (s.isEmpty()) return;
            int p = s.pop();
            if (p == ms.peek()) {
                ms.pop();
            }
            System.out.println("popped: " + p);
        }

        void getMin() {
            if (ms.isEmpty()) System.out.println("stack is empty");
            else System.out.println("min element: " + ms.peek());
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Mystack stack = new Mystack();
        int n = sc.nextInt();
        for (int i=0; i<n; i++) {
            int ele = sc.nextInt();
            stack.push(ele);
        }
        stack.getMin(); // Should be 5
        stack.pop();    // Removes 5
        stack.getMin(); // Should be 10
    }
}
