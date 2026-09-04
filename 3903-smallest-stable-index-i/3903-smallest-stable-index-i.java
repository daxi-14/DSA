class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] min = new int[n], max = new int[n];

        max[0] = nums[0];
        for (int i = 1;i<n;i++) {
            max[i] = Math.max(max[i-1], nums[i]);
        }

        min[n-1] = nums[n-1];
        for (int i=n-2;i>=0;i--) {
            min[i] = Math.min(min[i+1], nums[i]);
        }

        for (int i=0;i<n;i++) {
            if (max[i] - min[i] <= k) return i;
        }

        return -1;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna