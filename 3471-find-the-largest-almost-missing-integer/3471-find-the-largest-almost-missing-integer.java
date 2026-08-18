class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[51];
        int max = -1;
        for (int num : nums) {
            freq[num]++;
            max = Math.max(num, max);
        }
        if (k == 1) {
            Arrays.sort(nums);
            for (int i=n-1;i>=0;i--) {
                if (freq[nums[i]] == 1) return nums[i];
            }
        }
        if (k == n) return max;
        if (nums[0] == nums[n-1]) return -1;
        if (freq[nums[0]] > 1 && freq[nums[n-1]] > 1) return -1;
        else if (freq[nums[0]] > 1) return nums[n-1];
        else if (freq[nums[n-1]] > 1) return nums[0];
        return nums[0] > nums[n-1] ? nums[0] : nums[n-1];
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna