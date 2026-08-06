class Solution {
    public int smallestNumber(int n, int t) {
        int ans = n;
        while (getProduct(ans) % t != 0) {
            ans++;
            continue;
        }
        return ans;
    }

    private int getProduct(int num) {
        int ans = 1;
        while (num > 0) {
            ans *= (num % 10);
            num /= 10;
        }
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna