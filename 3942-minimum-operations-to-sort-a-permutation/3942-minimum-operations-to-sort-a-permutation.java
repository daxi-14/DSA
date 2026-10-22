class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < n; i++) {
            int prev = nums[(zero + i - 1) % n];
            int curr = nums[(zero + i) % n];

            if (curr != (prev + 1) % n) {
                increasing = false;
            }

            if (curr != (prev - 1 + n) % n) {
                decreasing = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasing) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, (n - zero) % n + 2);
        }

        if (decreasing) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, (n - zero - 1 + n) % n + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna