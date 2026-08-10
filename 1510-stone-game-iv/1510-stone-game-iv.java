class Solution {
    public boolean winnerSquareGame(int n) {

        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones left -> current player loses

        for (int stones = 1; stones <= n; stones++) {

            for (int square = 1; square * square <= stones; square++) {

                int remaining = stones - square * square;

                // If we can leave the opponent
                // in a losing position, we win.
                if (!dp[remaining]) {
                    dp[stones] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna