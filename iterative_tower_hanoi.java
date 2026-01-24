import java.util.*;

public class iterative_tower_hanoi {
    static void moveDisk(Stack<Integer> src, Stack<Integer> dst, char s, char d) {
        int pole1 = src.isEmpty() ? Integer.MAX_VALUE : src.peek();
        int pole2 = dst.isEmpty() ? Integer.MAX_VALUE : dst.peek();

        if (pole1 == Integer.MAX_VALUE) {
            src.push(dst.pop());
            System.out.println("move " + pole2 + " from " + d + " to " + s);
        } else if (pole2 == Integer.MAX_VALUE) {
            dst.push(src.pop());
            System.out.println("move " + pole1 + " from " + s + " to " + d);
        } else if (pole1 < pole2) {
            dst.push(src.pop());
            System.out.println("move " + pole1 + " from " + s + " to " + d);
        } else {
            src.push(dst.pop());
            System.out.println("move " + pole2 + " from " + d + " to " + s);
        }
    }

    public static void main(String args[]) {
        int numDisks = 3;
        Stack<Integer> src = new Stack<>();
        Stack<Integer> aux = new Stack<>();
        Stack<Integer> dst = new Stack<>();

        char s = 'S', a = 'A', d = 'D';

        if (numDisks % 2 == 0) {
            char temp = d;
            d = a;
            a = temp;
        }

        for (int i=numDisks; i>=1; i--) src.push(i);

        int totalMoves = (int) Math.pow(2, numDisks) - 1;
        
        for (int i=1; i<=totalMoves; i++) {
            if (i % 3 == 1) moveDisk(src, dst, s, d);
            else if (i % 3 == 2) moveDisk(src, aux, s, a);
            else if (i % 3 == 0) moveDisk(aux, dst, a, d);
        }
    }
}
