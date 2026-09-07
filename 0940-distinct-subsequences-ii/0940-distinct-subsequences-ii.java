class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[26];

        for (char c : s.toCharArray()) {
            int i = c - 'a';
            long total = 1;

            for (long count : dp) {
                total = (total + count) % MOD;
            }

            dp[i] = total;
        }

        long ans = 0;
        for (long count : dp) {
            ans = (ans + count) % MOD;
        }

        return (int) ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna