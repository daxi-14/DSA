class Solution {
    public boolean checkDivisibility(int n) {
        int n1=n,  n2=n;

        int sum = 0;
        while (n1 > 0) {
            sum += n1 % 10;
            n1 /= 10;
        }

        int mul = 1;
        while (n2 > 0) {
            mul *= n2 % 10;
            n2 /= 10;
        }

        return (n % (sum + mul) == 0);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna