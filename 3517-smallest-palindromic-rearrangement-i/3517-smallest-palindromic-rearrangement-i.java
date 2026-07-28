class Solution {
    public String smallestPalindrome(String s) {

        int[] chars = new int[26];

        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        StringBuilder middle = new StringBuilder();

        // Find the odd-frequency character (if any)
        for (int i = 0; i < 26; i++) {
            if ((chars[i] & 1) == 1) {
                middle.append((char) ('a' + i));
                chars[i]--;
                break;
            }
        }

        // Build the left half
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < chars[i] / 2; j++) {
                left.append((char) ('a' + i));
            }
        }

        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + middle.toString() + right;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna