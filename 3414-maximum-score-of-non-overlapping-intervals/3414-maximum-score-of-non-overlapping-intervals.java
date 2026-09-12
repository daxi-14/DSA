class Solution {
    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long weight;
        int[] indices;

        State(long weight, int[] indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l) {
                return Integer.compare(a.l, b.l);
            }
            return Integer.compare(a.r, b.r);
        });

        State[][] dp = new State[n + 1][5];

        // dp[i][k] = best answer using intervals from i onward,
        // selecting at most k intervals.
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {
            int next = findNext(arr, i + 1, arr[i].r);

            for (int k = 0; k <= 4; k++) {

                // Skip current interval
                State skip = dp[i + 1][k];

                State best = skip;

                // Take current interval
                if (k > 0) {
                    State nextState = dp[next][k - 1];

                    int[] indices = Arrays.copyOf(
                        nextState.indices,
                        nextState.indices.length + 1
                    );

                    indices[indices.length - 1] = arr[i].idx;
                    Arrays.sort(indices);

                    State take = new State(
                        arr[i].w + nextState.weight,
                        indices
                    );

                    best = better(take, skip);
                }

                dp[i][k] = best;
            }
        }

        return dp[0][4].indices;
    }

    private int findNext(Interval[] arr, int start, int end) {
        int lo = start;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid].l > end) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private State better(State a, State b) {
        if (a.weight != b.weight) {
            return a.weight > b.weight ? a : b;
        }

        return compare(a.indices, b.indices) < 0 ? a : b;
    }

    private int compare(int[] a, int[] b) {
        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna