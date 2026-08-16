class Solution {
    public boolean stoneGameIX(int[] stones) {

        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        // Count stones according to their remainder when divided by 3
        for (int stone : stones) {
            int rem = stone % 3;

            if (rem == 0) {
                count0++;
            } else if (rem == 1) {
                count1++;
            } else {
                count2++;
            }
        }

        // Even number of 0-remainder stones
        if (count0 % 2 == 0) {
            return count1 > 0 && count2 > 0;
        }

        // Odd number of 0-remainder stones
        return Math.abs(count1 - count2) > 2;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna