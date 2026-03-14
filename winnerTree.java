import java.util.Arrays;

public class winnerTree {

    static int getWinner(int[] players) {
        int n = players.length;
        int[] tree = new int[2 * n - 1];

        // Fill leaves with player indices
        for (int i = 0; i < n; i++) tree[n - 1 + i] = i;

        // Build tree bottom-up, winner = higher value
        for (int i = n - 2; i >= 0; i--) {
            int l = tree[2 * i + 1], r = tree[2 * i + 2];
            tree[i] = players[l] > players[r] ? l : r;
        }

        return players[tree[0]];
    }

    public static void main(String[] args) {
        int[] players = {3, 7, 1, 9, 4, 2, 8, 5};
        System.out.println("Winner: " + getWinner(players));
    }
}