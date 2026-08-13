class Solution {

    class Node {
        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int best;

        Node(char leftChar, char rightChar,
             int prefix, int suffix, int best) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String s, String queryCharacters,
                                  int[] queryIndices) {

        int n = s.length();

        this.s = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryCharacters.length();
        int[] answer = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Update the string
            this.s[index] = ch;

            // Update segment tree
            update(1, 0, n - 1, index, ch);

            // Root contains answer for entire string
            answer[i] = tree[1].best;
        }

        return answer;
    }

    private void build(int node, int left, int right) {

        // Leaf node
        if (left == right) {
            char ch = s[left];

            tree[node] = new Node(
                ch,     // leftChar
                ch,     // rightChar
                1,      // prefix
                1,      // suffix
                1       // best
            );

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1],
                           mid - left + 1,
                           right - mid);
    }

    private void update(int node, int left, int right,
                        int index, char ch) {

        // We reached the required position
        if (left == right) {
            tree[node] = new Node(
                ch, ch,
                1, 1, 1
            );

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        // Recalculate this node using its children
        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1],
            mid - left + 1,
            right - mid
        );
    }

    private Node merge(Node L, Node R,
                       int leftLength, int rightLength) {

        char leftChar = L.leftChar;
        char rightChar = R.rightChar;

        int prefix = L.prefix;
        int suffix = R.suffix;

        // Can prefix extend into right side?
        if (L.prefix == leftLength &&
            L.rightChar == R.leftChar) {

            prefix = L.prefix + R.prefix;
        }

        // Can suffix extend into left side?
        if (R.suffix == rightLength &&
            L.rightChar == R.leftChar) {

            suffix = L.suffix + R.suffix;
        }

        // Best answer is either:
        // 1. Completely inside L
        // 2. Completely inside R
        // 3. Crossing the boundary
        int best = Math.max(L.best, R.best);

        if (L.rightChar == R.leftChar) {
            best = Math.max(best, L.suffix + R.prefix);
        }

        return new Node(
            leftChar,
            rightChar,
            prefix,
            suffix,
            best
        );
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna