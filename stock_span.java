import java.util.*;

public class stock_span {
    static void calculate(int prices[], int n, int span[]) {
        Stack<Integer> st = new Stack<>();

        st.push(0);
        span[0] = 1;

        for (int i=1; i<n; i++) {
            while (!st.isEmpty() && prices[st.peek()] <= prices[i]) {
                st.pop();
            }
            span[i] = st.isEmpty() ? (i+1) : (i-st.peek());
            st.push(i);
        }
    }

    public static void main(String args[]) {
        int prices[] = {100, 80, 60, 70, 60, 75, 85};
        int n = prices.length;
        int span[] = new int[n];

        calculate(prices, n, span);

        System.out.println("stock span: " + Arrays.toString(span));
    }
}
