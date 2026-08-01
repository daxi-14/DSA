class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        // Base case: only one number left
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Fill DP table for increasing subarray lengths
        for (int len = 2; len <= n; len++) {
            for (int left = 0; left <= n - len; left++) {
                int right = left + len - 1;

                int takeLeft = nums[left] - dp[left + 1][right];
                int takeRight = nums[right] - dp[left][right - 1];

                dp[left][right] = Math.max(takeLeft, takeRight);
            }
        }

        return dp[0][n - 1] >= 0;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna