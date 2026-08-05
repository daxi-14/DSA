class Solution {

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // Find all suspicious methods
        boolean[] suspicious = new boolean[n];
        dfs(k, graph, suspicious);

        // Check if any non-suspicious method invokes a suspicious one
        for (int[] edge : invocations) {

            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {

                // Cannot remove anything
                List<Integer> ans = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }

                return ans;
            }
        }

        // Remove suspicious methods
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    private void dfs(int node,
                     List<Integer>[] graph,
                     boolean[] suspicious) {

        suspicious[node] = true;

        for (int next : graph[node]) {
            if (!suspicious[next]) {
                dfs(next, graph, suspicious);
            }
        }
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna