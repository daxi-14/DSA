class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j][0] = ways to reach (i,j) moving right
        // dp[i][j][1] = ways to reach (i,j) moving down
        int[][][] dp = new int[m][n][2];

        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int right = dp[i][j][0];
                int down = dp[i][j][1];

                if (grid[i][j] == 0) {
                    // Empty cell: can move either right or down
                    if (j + 1 < n) {
                        dp[i][j + 1][0] = (int) (
                            (dp[i][j + 1][0] + (long) right + down) % MOD
                        );
                    }

                    if (i + 1 < m) {
                        dp[i + 1][j][1] = (int) (
                            (dp[i + 1][j][1] + (long) right + down) % MOD
                        );
                    }

                } else {
                    // Mirror: direction gets changed
                    if (i + 1 < m) {
                        dp[i + 1][j][1] = (int) (
                            (dp[i + 1][j][1] + (long) right) % MOD
                        );
                    }

                    if (j + 1 < n) {
                        dp[i][j + 1][0] = (int) (
                            (dp[i][j + 1][0] + (long) down) % MOD
                        );
                    }
                }
            }
        }

        return (int) (
            ((long) dp[m - 1][n - 1][0] +
             dp[m - 1][n - 1][1]) % MOD
        );
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna