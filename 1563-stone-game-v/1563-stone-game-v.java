class Solution {

    int[][] dp;
    int[] prefix;
    int[] stones;

    public int stoneGameV(int[] stoneValue) {

        stones = stoneValue;
        int n = stones.length;

        // Prefix sum
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }

        // dp[i][j] = maximum score from i to j
        dp = new int[n][n];

        // -1 means not calculated yet
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(0, n - 1);
    }

    private int solve(int left, int right) {

        // Only one stone
        if (left >= right) {
            return 0;
        }

        // Already calculated
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int ans = 0;

        // Try every possible split
        for (int k = left; k < right; k++) {

            int leftSum =
                prefix[k + 1] - prefix[left];

            int rightSum =
                prefix[right + 1] - prefix[k + 1];

            if (leftSum < rightSum) {

                // Right side is discarded
                // Left side survives

                ans = Math.max(
                    ans,
                    leftSum + solve(left, k)
                );

            } else if (leftSum > rightSum) {

                // Left side is discarded
                // Right side survives

                ans = Math.max(
                    ans,
                    rightSum + solve(k + 1, right)
                );

            } else {

                // Equal sums
                // Alice can choose either side

                int leftOption =
                    leftSum + solve(left, k);

                int rightOption =
                    rightSum + solve(k + 1, right);

                ans = Math.max(
                    ans,
                    Math.max(leftOption, rightOption)
                );
            }
        }

        dp[left][right] = ans;

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna