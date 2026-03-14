public class priority_queue_using_DLL {
    static class Node {
        int data;
        int priority;
        Node next, prev;
        Node(int data, int priority) {
            this.data = data;
            this.priority = priority;
            next = null;
            prev = null;
        }
    }

    static Node head = null;

    static void push(int data, int priority) {
        Node newNode = new Node(data, priority);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head, parent = null;

        while (temp != null && temp.priority >= priority) {
            parent = temp;
            temp = temp.next;
        }

        if (parent == null) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (temp == null) {
            parent.next = newNode;
            newNode.prev = parent;
        } else {
            parent.next = newNode;
            newNode.prev = parent;
            newNode.next = temp;
            temp.prev = newNode;
        }
    }

    static int pop() {
        if (head != null) {
            int curr = head.data;
            head = head.next;
            if (head != null) head.prev = null;
            return curr;
        }
        return -1;
    }

    public static void main(String[] args) {
        push(10, 1);
        push(20, 5); // Higher priority
        push(30, 3);
        
        System.out.println("Popped (Highest Priority): " + pop()); // Should be 20
        System.out.println("Popped: " + pop()); // Should be 30
    }
}
