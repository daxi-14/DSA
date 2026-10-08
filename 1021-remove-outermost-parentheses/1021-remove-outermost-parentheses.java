class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int depth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth++;

                if (depth > 1) {
                    sb.append(c);
                }
            } else {
                depth--;

                if (depth > 0) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna