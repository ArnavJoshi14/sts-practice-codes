public class merge_sorted_LL {
    static class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
            next = null;
        }
    }

    static Node merge(Node list1, Node list2) {
        Node lst1 = list1, lst2 = list2;
        Node dummy = new Node(0);
        Node temp = dummy;

        while (lst1 != null && lst2 != null) {
            if (lst1.data <= lst2.data) {
                temp.next = lst1;
                lst1 = lst1.next;
            } else {
                temp.next = lst2;
                lst2 = lst2.next;
            }
            temp = temp.next;
        }

        if (lst1 == null) {
            temp.next = lst2;
        } else {
            temp.next = lst1;
        }

        return dummy.next;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        merge_sorted_LL m = new merge_sorted_LL();
        Node l1 = new Node(10); l1.next = new Node(20); l1.next.next = new Node(30);
        Node l2 = new Node(15); l2.next = new Node(25);
        
        System.out.println("Merging lists...");
        Node res = m.merge(l1, l2);
        printList(res);
    }
}
