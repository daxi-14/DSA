class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (i == j) continue;
                max = Math.max(max, (nums[i] - 1) * (nums[j] - 1));
            }
        }
        return max;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna