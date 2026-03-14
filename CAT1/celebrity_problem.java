import java.util.*;

public class celebrity_problem {
    static int celebrity(int mat[][]) {
        Stack<Integer> stk = new Stack<>();
        for (int i=0; i<mat.length; i++) stk.push(i);

        while (stk.size() > 1) {
            int col = stk.pop();
            int row = stk.pop();

            if (mat[row][col] == 1) {
                stk.push(col);
            } else {
                stk.push(row);
            }
        }

        if (stk.isEmpty()) return -1;

        int cel = stk.pop();

        //verify
        for (int j=0; j<mat.length; j++) {
            if (j == cel) continue;
            if (mat[cel][j] == 1) return -1;
        }
        for (int i=0; i<mat.length; i++) {
            if (i == cel) continue;
            if (mat[i][cel] == 0) return -1;
        }

        return cel;

    }

    public static void main(String args[]) {
        int[][] M = { {0, 0, 1, 0},
                      {0, 0, 1, 0},
                      {0, 0, 0, 0},
                      {0, 0, 1, 0} };
        int celeb = celebrity(M);
        System.out.println(celeb);
    }
}
