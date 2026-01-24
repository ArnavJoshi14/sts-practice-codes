import java.util.*;

public class stack_permutations {
    static boolean check(int ip[], int op[], int n) {
        Stack<Integer> s = new Stack<>();
        int j = 0;
        for (int i=0; i<n; i++) {
            s.push(ip[i]);
            while (!s.isEmpty() && s.peek() == op[j]) {
                s.pop();
                j++;
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        int input[] = { 1, 2, 3 };
        int output[] = { 2, 1, 3 };
        
        if (check(input, output, 3))
            System.out.println("YES, Valid Stack Permutation");
        else
            System.out.println("NO, Invalid");
            
        int output2[] = { 3, 1, 2 }; // This one should be false
        if (check(input, output2, 3))
            System.out.println("YES");
        else
            System.out.println("NO (Expected)");
    }
}
