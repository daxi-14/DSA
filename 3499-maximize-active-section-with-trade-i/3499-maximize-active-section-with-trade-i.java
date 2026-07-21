class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        int totalOnes = 0;
        int maxGain = 0;
        int prevZeroGroup = Integer.MIN_VALUE;

        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (s.charAt(i) == '1') {
                totalOnes += len;
            } else {
                maxGain = Math.max(maxGain, prevZeroGroup + len);
                prevZeroGroup = len;
            }

            i = j;
        }

        return totalOnes + maxGain;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna