import java.util.*;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;

    private static final long BASE1 = 31;
    private static final long BASE2 = 37;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int left = 1;
        int right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;

        long pow1 = 1;
        long pow2 = 1;

        for (int i = 1; i < len; i++) {
            pow1 = (pow1 * BASE1) % MOD1;
            pow2 = (pow2 * BASE2) % MOD2;
        }

        long hash1 = 0;
        long hash2 = 0;

        // Build hash of the first window
        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * BASE1 + nums[i]) % MOD1;
            hash2 = (hash2 * BASE2 + nums[i]) % MOD2;
        }

        Map<Long, Integer> frequency = new HashMap<>();

        // Store first window's hash
        long key = hash1 * MOD2 + hash2;
        frequency.put(key, 1);

        // Slide the window
        for (int i = len; i < n; i++) {
            int outgoing = nums[i - len];
            int incoming = nums[i];

            hash1 = (hash1 - outgoing * pow1 % MOD1 + MOD1) % MOD1;
            hash1 = (hash1 * BASE1 + incoming) % MOD1;

            hash2 = (hash2 - outgoing * pow2 % MOD2 + MOD2) % MOD2;
            hash2 = (hash2 * BASE2 + incoming) % MOD2;

            key = hash1 * MOD2 + hash2;

            frequency.put(key, frequency.getOrDefault(key, 0) + 1);
        }

        // Is any window appearing exactly once?
        for (int count : frequency.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna