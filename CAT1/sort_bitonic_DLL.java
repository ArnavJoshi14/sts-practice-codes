public class sort_bitonic_DLL {
    static class Node {
        int data;
        Node next;
        Node prev;
        Node(int d) {
            data = d;
            next = null;
            prev = null;
        }
    }

    static Node sort(Node head) {
        if (head == null || head.next == null) return head;

        Node front = head;
        Node last = head;
        while (last.next != null) last = last.next;
        Node res = new Node(0);
        Node tail = res;
        Node next;

        while (front != last) {
            if (last.data <= front.data) {
                tail.next = last;
                next = last.prev;
                last.prev.next = null;
                last.prev = tail;
                last = next;
                tail = tail.next;
            } else {
                tail.next = front;
                next = front.next;
                front.next = null;
                front.prev = tail;
                front = next;
                tail = tail.next;
            }
        }
        
        tail.next = front;
        front.prev = tail;

        return res.next;
    }

    static void printList(Node head) {
        if (head == null) return;
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(5); head.next.prev = head;
        head.next.next = new Node(7); head.next.next.prev = head.next;
        head.next.next.next = new Node(12); head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(10); head.next.next.next.next.prev = head.next.next.next;
        head.next.next.next.next.next = new Node(6); head.next.next.next.next.next.prev = head.next.next.next.next;
        
        System.out.println("Original Bitonic List:");
        printList(head);
        
        head = sort(head);
        
        System.out.println("Sorted List:");
        printList(head);
    }
}
