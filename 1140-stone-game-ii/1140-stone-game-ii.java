class Solution {

    int[][] memo;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        // suffix[i] = sum of piles from i to n-1
        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        memo = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            for (int m = 0; m <= n; m++) {
                memo[i][m] = -1;
            }
        }

        return solve(0, 1, piles);
    }

    private int solve(int i, int M,  int[] piles) {

        // No piles left
        if (i >= n) {
            return 0;
        }

        // Can take all remaining piles
        if (i + 2 * M >= n) {
            return suffix[i];
        }

        // Already calculated
        if (memo[i][M] != -1) {
            return memo[i][M];
        }

        int best = 0;
        int taken = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            taken += piles[i + X - 1];

            int newM = Math.max(M, X);

            // What opponent can get after our move
            int opponent = solve(i + X, newM, piles);

            // Total remaining - opponent's stones
            int currentPlayer = suffix[i] - opponent;

            best = Math.max(best, currentPlayer);
        }

        memo[i][M] = best;

        return best;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna