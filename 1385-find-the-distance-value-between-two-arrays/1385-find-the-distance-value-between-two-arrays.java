class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int m = arr1.length, n = arr2.length;
        int ans = m;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    ans--;
                    break;
                }
            }
        }
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna