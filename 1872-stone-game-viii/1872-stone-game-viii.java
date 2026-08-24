class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Convert stones[] into prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // Base case: dp[n - 1]
        int dp = stones[n - 1];

        // Calculate dp[i] backwards
        for (int i = n - 2; i >= 1; i--) {

            dp = Math.max(
                dp,
                stones[i] - dp
            );
        }

        return dp;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna