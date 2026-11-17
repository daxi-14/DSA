class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // Precompute all proper divisors for each length.
        List<Integer>[] factors = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            factors[i] = new ArrayList<>();
        }

        for (int d = 1; d < n; d++) {
            for (int len = d * 2; len <= n; len += d) {
                factors[len].add(d);
            }
        }

        // cost[i][j] = minimum changes to make s[i...j]
        // a semi-palindrome.
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minCost = n;

                for (int d : factors[len]) {
                    int changes = 0;

                    for (int offset = 0; offset < d; offset++) {
                        int left = i + offset;
                        int right = j - d + 1 + offset;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][j] = minCost;
            }
        }

        // dp[i][j] = minimum changes to divide
        // s[i...n-1] into exactly j semi-palindromes.
        int INF = 1_000_000;
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int parts = 1; parts <= k; parts++) {

                // Each semi-palindrome must have length >= 2.
                for (int j = i + 1; j < n; j++) {

                    if (dp[j + 1][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        cost[i][j] + dp[j + 1][parts - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna