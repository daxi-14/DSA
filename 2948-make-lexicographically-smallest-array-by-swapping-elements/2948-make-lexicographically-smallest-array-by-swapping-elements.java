import java.util.*;

class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // pair[i] = {value, originalIndex}
        int[][] pair = new int[n][2];

        for (int i = 0; i < n; i++) {
            pair[i][0] = nums[i];
            pair[i][1] = i;
        }

        // Sort by value
        Arrays.sort(pair, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[n];

        int start = 0;

        while (start < n) {

            int end = start;

            // Find all elements belonging to the same swappable group
            while (end + 1 < n &&
                   pair[end + 1][0] - pair[end][0] <= limit) {
                end++;
            }

            // Original indices belonging to this group
            int[] indices = new int[end - start + 1];

            for (int i = start; i <= end; i++) {
                indices[i - start] = pair[i][1];
            }

            Arrays.sort(indices);

            // pair[start...end] is already sorted by value.
            // Put smallest values at smallest indices.
            for (int i = 0; i < indices.length; i++) {
                ans[indices[i]] = pair[start + i][0];
            }

            start = end + 1;
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna