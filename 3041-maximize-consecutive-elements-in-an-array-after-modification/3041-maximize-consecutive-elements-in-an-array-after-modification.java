class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[2];
        dp[0] = dp[1] = 1;

        int ans = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                dp[1] = dp[0] + 1;
            } else if (nums[i] == nums[i - 1] + 1) {
                dp[0]++;
                dp[1]++;
            } else if (nums[i] == nums[i - 1] + 2) {
                dp[0] = dp[1] + 1;
                dp[1] = 1;
            } else {
                dp[0] = dp[1] = 1;
            }

            ans = Math.max(ans, Math.max(dp[0], dp[1]));
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna