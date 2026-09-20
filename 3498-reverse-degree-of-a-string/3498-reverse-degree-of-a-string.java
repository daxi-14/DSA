class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        int counter = 1;
        for(int i=0;i<s.length();i++) {
            ans += counter * degree(s.charAt(i));
            counter++;
        }

        return ans;
    }

    private int degree(char c) {
        return 26 - (c - 'a');
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna