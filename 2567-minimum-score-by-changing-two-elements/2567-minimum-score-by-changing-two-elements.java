
class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        int option1 = nums[n - 1] - nums[2];
        int option2 = nums[n - 2] - nums[1];
        int option3 = nums[n - 3] - nums[0];

        return Math.min(option1, Math.min(option2, option3));
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna