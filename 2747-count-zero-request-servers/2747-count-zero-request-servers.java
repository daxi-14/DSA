class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] freq = new int[n + 1];
        int[] ans = new int[m];

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int[] query : sortedQueries) {
            int time = query[0];
            int index = query[1];

            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];

                if (freq[server]++ == 0) {
                    activeServers++;
                }

                right++;
            }

            while (left < logs.length && logs[left][1] < time - x) {
                int server = logs[left][0];

                if (--freq[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            ans[index] = n - activeServers;
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna