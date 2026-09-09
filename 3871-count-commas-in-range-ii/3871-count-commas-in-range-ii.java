class Solution {
    public long countCommas(long n) {
        long ans = 0;

        // 1,000 to 999,999 → 1 comma
        if (n >= 1_000L) {
            ans += Math.min(n, 999_999L) - 1_000L + 1;
        }

        // 1,000,000 to 999,999,999 → 2 commas
        if (n >= 1_000_000L) {
            ans += (Math.min(n, 999_999_999L) - 1_000_000L + 1) * 2;
        }

        // 1,000,000,000 to 999,999,999,999 → 3 commas
        if (n >= 1_000_000_000L) {
            ans += (Math.min(n, 999_999_999_999L) - 1_000_000_000L + 1) * 3;
        }

        // 1,000,000,000,000 to 999,999,999,999,999 → 4 commas
        if (n >= 1_000_000_000_000L) {
            ans += (Math.min(n, 999_999_999_999_999L)
                    - 1_000_000_000_000L + 1) * 4;
        }

        // 1,000,000,000,000,000 → 5 commas
        if (n >= 1_000_000_000_000_000L) {
            ans += (n - 1_000_000_000_000_000L + 1) * 5;
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna