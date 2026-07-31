class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int[] freq = new int[26];
        for (int i=0;i<n;i++) {
            freq[word.charAt(i) - 'a']++;
        }

        Arrays.sort(freq);
        int counter = 1;
        int ans = 0;
        for (int i=25;i>=0;i--) {
            if (freq[i] > 0) {
                ans += (Math.ceil((double)counter++/8) * freq[i]);
            } else break;
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna