
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                if (open > 0 && i > 0 && s.charAt(i - 1) == ')') {
                    // Handled by the greedy logic below instead
                }
                open++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    answer++;
                }

                if (open == 0) {
                    answer++;
                } else {
                    open--;
                }
            }
        }

        return answer + 2 * open;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna