class Solution {
    public boolean uniformArray(int[] nums1) {
    int minOdd = Integer.MAX_VALUE;
    int minEven = Integer.MAX_VALUE;

    for (int num : nums1) {
        if (num % 2 == 0) {
            minEven = Math.min(minEven, num);
        } else {
            minOdd = Math.min(minOdd, num);
        }
    }

    // Already all even
    if (minOdd == Integer.MAX_VALUE) {
        return true;
    }

    // Smallest odd can convert every even into odd
    return minOdd < minEven;
}
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna