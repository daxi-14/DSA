class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int sum = 0;
        int q = 0;

        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                q++;
            } else {
                sum += c - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                q--;
            } else {
                sum -= c - '0';
            }
        }

        if (q % 2 != 0) {
            return true;
        }

        return 2 * sum + 9 * q != 0;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna