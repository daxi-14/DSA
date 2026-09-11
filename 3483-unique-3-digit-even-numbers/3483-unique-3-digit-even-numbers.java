class Solution {
    public int totalNumbers(int[] digits) {
        int n = digits.length;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {

                    // Must use three different indices
                    if (i == j || j == k || i == k) {
                        continue;
                    }

                    int num = digits[i] * 100
                            + digits[j] * 10
                            + digits[k];

                    if (isValid(num)) {
                        set.add(num);
                    }
                }
            }
        }

        return set.size();
    }

    private boolean isValid(int n) {
        return n >= 100 && n % 2 == 0;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna