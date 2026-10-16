class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] sum;
    private long[] sq;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            update(1, 0, n - 1, prev + 1, i);
            ans = (ans + sq[1]) % MOD;
            last[nums[i]] = i;
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r, long val) {
        long len = r - l + 1L;

        sq[node] = (sq[node] + 2L * val % MOD * sum[node] % MOD
                    + len % MOD * val % MOD * val % MOD) % MOD;

        sum[node] = (sum[node] + len * val % MOD) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = (l + r) / 2;
        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);
        lazy[node] = 0;
    }

    private void pullUp(int node) {
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        pushDown(node, l, r);

        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        pullUp(node);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna