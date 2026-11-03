class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        int n = nums.length;
        for (int i=1;i<n;i++) {
            if (nums[i] > max) max = nums[i];
        }

        int ans = 0;
        for (int i=0;i<n;i++) {
            ans += (max - nums[i]);
        }
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna