class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        Arrays.fill(newParent, -1);

        // Track the closest ancestor having the same character.
        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, parent, s, children, newParent, last);

        // Build the modified tree.
        List<Integer>[] newChildren = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        // Calculate subtree sizes in the modified tree.
        int[] answer = new int[n];
        calculateSize(0, newChildren, answer);

        return answer;
    }

    private void dfs(int node, int[] parent, String s,
                     List<Integer>[] children,
                     int[] newParent, int[] last) {

        int ch = s.charAt(node) - 'a';

        int previous = last[ch];

        if (node == 0) {
            newParent[node] = -1;
        } else {
            newParent[node] = (previous == -1)
                    ? parent[node]
                    : previous;
        }

        // Update the closest ancestor for this character.
        last[ch] = node;

        for (int child : children[node]) {
            dfs(child, parent, s, children, newParent, last);
        }

        // Backtrack to restore the previous ancestor.
        last[ch] = previous;
    }

    private int calculateSize(int node,
                              List<Integer>[] children,
                              int[] answer) {

        int size = 1;

        for (int child : children[node]) {
            size += calculateSize(child, children, answer);
        }

        answer[node] = size;
        return size;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna