class Solution {
    public boolean checkString(String s) {
        boolean isA = false, isB = false;
        for (int i=0;i<s.length();i++) {
            if (s.charAt(i) == 'a') {
                isA = true;
                if (isB) return false;
            } else {
                isB = true;
            }
        }

        return true;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna