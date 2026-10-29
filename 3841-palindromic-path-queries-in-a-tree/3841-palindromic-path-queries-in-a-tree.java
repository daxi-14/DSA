import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] up = new int[LOG][n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] size = new int[n];
        int[] parent = new int[n];
        int[] order = new int[n];

        // Iterative DFS avoids stack overflow on a chain-shaped tree.
        int[] stack = new int[n];
        int top = 0;
        int timer = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = timer;
            order[timer++] = u;
            size[u] = 1;

            for (int v : graph[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Compute subtree sizes in reverse DFS order.
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            if (u != 0) {
                size[parent[u]] += size[u];
            }

            tout[u] = tin[u] + size[u] - 1;
        }

        // Binary lifting table.
        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        char[] chars = s.toCharArray();

        // Root-to-node parity masks.
        int[] masks = new int[n];

        for (int i = 0; i < n; i++) {
            int u = order[i];
            int bit = 1 << (chars[u] - 'a');

            masks[u] = bit;
            if (u != 0) {
                masks[u] ^= masks[parent[u]];
            }
        }

        // Euler-tour range updates and point queries.
        Fenwick fenwick = new Fenwick(n);

        for (int i = 0; i < n; i++) {
            fenwick.rangeXor(tin[i], tout[i], masks[i] ^ 
                (parent[i] == i ? 0 : masks[parent[i]]));
        }

        List<Boolean> result = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] == c) {
                    continue;
                }

                int delta = (1 << (chars[u] - 'a')) ^ 
                            (1 << (c - 'a'));

                fenwick.rangeXor(tin[u], tout[u], delta);
                chars[u] = c;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v, up, depth);

                int maskU = fenwick.pointQuery(tin[u]);
                int maskV = fenwick.pointQuery(tin[v]);

                int pathMask = maskU ^ maskV ^
                               (1 << (chars[lca] - 'a'));

                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return result;
    }

    private int getLCA(int u, int v, int[][] up, int[] depth) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < up.length; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = up.length - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        int n;
        int[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        void update(int index, int value) {
            for (int i = index + 1; i <= n; i += i & -i) {
                bit[i] ^= value;
            }
        }

        void rangeXor(int left, int right, int value) {
            update(left, value);

            if (right + 1 < n) {
                update(right + 1, value);
            }
        }

        int pointQuery(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= bit[i];
            }

            return result;
        }
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna