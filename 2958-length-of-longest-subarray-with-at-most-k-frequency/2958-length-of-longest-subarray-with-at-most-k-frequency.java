class Solution {
    public int maxSubarrayLength(int[] nums, int k) {

        Map<Integer, Integer> freq = new HashMap<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {

            // Add current element
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            // Window is invalid
            while (freq.get(nums[right]) > k) {

                // Remove nums[left]
                freq.put(nums[left], freq.get(nums[left]) - 1);

                left++;
            }

            // Current window is valid
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna