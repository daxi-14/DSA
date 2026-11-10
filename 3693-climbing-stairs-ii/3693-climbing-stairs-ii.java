
class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            for (int j = Math.max(0, i - 3); j < i; j++) {

                int jump = i - j;

                int cost = costs[i - 1] + (jump * jump);

                dp[i] = Math.min(dp[i], dp[j] + cost);
            }
        }

        return dp[n];
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna