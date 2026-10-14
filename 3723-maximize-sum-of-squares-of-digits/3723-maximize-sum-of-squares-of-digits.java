class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder result = new StringBuilder(num);

        int nines = sum / 9;
        int remainder = sum % 9;

        result.append("9".repeat(nines));

        if (remainder > 0) {
            result.append(remainder);
        }

        result.append("0".repeat(num - result.length()));

        return result.toString();
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna