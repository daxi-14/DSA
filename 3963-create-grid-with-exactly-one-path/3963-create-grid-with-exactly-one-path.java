class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<n;i++) {
            sb.append('.');
        }
        ans[0] = sb.toString();

        for (int i=1;i<m;i++) {
            sb = new StringBuilder();
            for (int j=0;j<n-1;j++) {
                sb.append('#');
            }
            sb.append('.');
            ans[i] = sb.toString();
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna