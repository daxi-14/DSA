class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        long costSeparate = (long) need1 * cost1 + (long) need2 * cost2;

        long costAllBoth = (long) costBoth * Math.max(need1, need2);

        int minNeed = Math.min(need1, need2);

        long costMixed = (long) minNeed * costBoth
                       + (long) (need1 - minNeed) * cost1
                       + (long) (need2 - minNeed) * cost2;

        return Math.min(costSeparate, Math.min(costAllBoth, costMixed));
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna