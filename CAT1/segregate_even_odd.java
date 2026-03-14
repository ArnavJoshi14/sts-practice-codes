public class segregate_even_odd {
    static class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
            next = null;
        } 
    }

    static Node head;

    void segregate() {
        Node evenStart = null, evenEnd = null;
        Node oddStart = null, oddEnd = null;

        Node currentNode = head;

        while (currentNode != null) {
            if (currentNode.data %2 == 0) {
                if (evenStart == null) {
                    evenStart = currentNode;
                    evenEnd = evenStart;
                } else {
                    evenEnd.next = currentNode;
                    evenEnd = evenEnd.next;
                }
            } else {
                if (oddStart == null) {
                    oddStart = currentNode;
                    oddEnd = oddStart;
                } else {
                    oddEnd.next = currentNode;
                    oddEnd = oddEnd.next;
                }
            }
            currentNode = currentNode.next;
        }

        if (oddStart == null || evenStart == null) return;

        evenEnd.next = oddStart;
        oddEnd.next = null;

        head = evenStart;
    }

    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        segregate_even_odd list = new segregate_even_odd();
        list.head = new Node(17);
        list.head.next = new Node(15);
        list.head.next.next = new Node(8);
        list.head.next.next.next = new Node(12);
        list.head.next.next.next.next = new Node(10);
        list.head.next.next.next.next.next = new Node(5);
        
        System.out.println("Original List:");
        list.printList();
        
        list.segregate();
        
        System.out.println("Segregated List:");
        list.printList();
    }
}
