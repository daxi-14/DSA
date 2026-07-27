class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = 0;
        Arrays.sort(nums);
        return (nums[n-1] - 1) * (nums[n-2] - 1);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna