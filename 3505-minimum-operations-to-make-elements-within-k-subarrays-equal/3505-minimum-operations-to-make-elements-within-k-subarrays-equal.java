class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        long[] cost = new long[n - x + 1];

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // Coordinate compression
        int[] values = Arrays.stream(sorted).distinct().toArray();
        int m = values.length;

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        long[] windowCost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            int idx = Arrays.binarySearch(values, nums[i]) + 1;
            count.add(idx, 1);
            sum.add(idx, nums[i]);
        }

        for (int i = 0; i <= n - x; i++) {
            int medianIdx = count.kth((x + 1) / 2);
            long median = values[medianIdx - 1];

            long leftCount = count.query(medianIdx);
            long leftSum = sum.query(medianIdx);

            long rightCount = x - leftCount;
            long rightSum = sum.query(m) - leftSum;

            cost[i] = median * leftCount - leftSum
                    + rightSum - median * rightCount;

            if (i < n - x) {
                int removeIdx = Arrays.binarySearch(values, nums[i]) + 1;
                int addIdx = Arrays.binarySearch(values, nums[i + x]) + 1;

                count.add(removeIdx, -1);
                sum.add(removeIdx, -nums[i]);

                count.add(addIdx, 1);
                sum.add(addIdx, nums[i + x]);
            }
        }

        // DP: dp[j][i] = minimum cost using j subarrays
        // within the first i elements.
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 0; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        Arrays.fill(dp[0], 0);

        for (int j = 1; j <= k; j++) {
            for (int i = j * x; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];

                int start = i - x;
                if (dp[j - 1][start] != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][start] + cost[start]
                    );
                }
            }
        }

        return dp[k][n];
    }

    class Fenwick {
        int n;
        long[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void add(int i, long val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {
            long result = 0;
            while (i > 0) {
                result += tree[i];
                i -= i & -i;
            }
            return result;
        }

        int kth(int k) {
            int idx = 0;
            int bit = Integer.highestOneBit(n);

            for (; bit > 0; bit >>= 1) {
                int next = idx + bit;

                if (next <= n && tree[next] < k) {
                    idx = next;
                    k -= (int) tree[next];
                }
            }

            return idx + 1;
        }
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna