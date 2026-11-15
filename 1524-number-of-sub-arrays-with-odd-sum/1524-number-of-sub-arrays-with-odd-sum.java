
class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;

        int even = 1; // Empty prefix sum
        int odd = 0;

        int prefixSum = 0;
        int result = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                result = (result + odd) % MOD;
                even++;
            } else {
                result = (result + even) % MOD;
                odd++;
            }
        }

        return result;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna