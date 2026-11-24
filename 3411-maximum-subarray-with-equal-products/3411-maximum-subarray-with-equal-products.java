
class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;

        int maxElement = 0;
        int overallLcm = 1;

        // Calculate the maximum possible GCD * LCM
        for (int num : nums) {
            maxElement = Math.max(maxElement, num);
            overallLcm = lcm(overallLcm, num);
        }

        int maxProduct = maxElement * overallLcm;
        int answer = 0;

        // Try every starting index
        for (int i = 0; i < n; i++) {

            int product = 1;
            int gcdValue = 0;
            int lcmValue = 1;

            // Expand the subarray
            for (int j = i; j < n; j++) {

                int num = nums[j];

                product *= num;
                gcdValue = gcd(gcdValue, num);
                lcmValue = lcm(lcmValue, num);

                // Check product equivalence
                if (product == gcdValue * lcmValue) {
                    answer = Math.max(answer, j - i + 1);
                }

                // Product can never become valid again
                if (product > maxProduct) {
                    break;
                }
            }
        }

        return answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna