class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // suffix[i] = earliest index in word1 from which
        // word2[i...] can be matched as a subsequence.
        int[] suffix = new int[m];

        int p = n - 1;

        for (int j = m - 1; j >= 0; j--) {

            while (p >= 0 && word1.charAt(p) != word2.charAt(j)) {
                p--;
            }

            if (p < 0) {
                suffix[j] = -1;
            } else {
                suffix[j] = p;
                p--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        int j = 0;
        boolean mismatchUsed = false;

        while (i < n && j < m) {

            // Case 1: exact match
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                i++;
                j++;

            }
            // Case 2: use our one allowed mismatch
            else if (!mismatchUsed) {

                /*
                 * We use word1[i] as the mismatch.
                 *
                 * Now everything after word2[j] must match exactly.
                 *
                 * suffix[j + 1] tells us the earliest position
                 * from which word2[j + 1 ...] can be matched.
                 *
                 * We need that suffix to start after i.
                 */
                if (j == m - 1 || 
                    (suffix[j + 1] != -1 && suffix[j + 1] > i)) {

                    ans[j] = i;
                    mismatchUsed = true;

                    i++;
                    j++;
                } else {
                    i++;
                }

            }
            // Already used mismatch, so this character can't be used
            else {
                i++;
            }
        }

        // If we managed to construct the complete sequence
        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna