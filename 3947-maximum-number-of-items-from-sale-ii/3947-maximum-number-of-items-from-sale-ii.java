import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Calculate free-item gain for each factor
        int[] gain = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int multiple = f; multiple <= n; multiple += f) {
                gain[f] += freq[multiple];
            }

            if (gain[f] > 0) {
                gain[f]--;
            }
        }

        // Collect boosted purchases (each yields 2 items)
        List<int[]> boosted = new ArrayList<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (gain[factor] > 0 && price < 2L * minPrice) {
                boosted.add(new int[]{price, gain[factor]});
            }
        }

        // Cheapest boosted purchases first
        boosted.sort(Comparator.comparingInt(a -> a[0]));

        long total = 0;
        long remaining = budget;

        for (int[] purchase : boosted) {
            int price = purchase[0];
            int maxCount = purchase[1];

            long count = Math.min(maxCount, remaining / price);

            total += count * 2;
            remaining -= count * price;
        }

        // Spend the remaining budget on cheapest regular items
        total += remaining / minPrice;

        return (int) total;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna