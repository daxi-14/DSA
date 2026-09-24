class Solution {
    public int smallestIndex(int[] nums) {
        int n = nums.length;
        for (int i=0;i<n;i++) {
            int num = nums[i];
            if (i == sumDigits(num)) return i;
        }
        return -1;
    }

    private int sumDigits(int num) {
        int ans = 0;
        while (num > 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna